package com.farmers.batch.kyn.quote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.FocusAreaComparator;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;

@Service("quarterlyFocusArea")
public class QuarterlyFocusArea implements Tasklet{

	
	
	@Autowired
	private ReportViewRepo reportRepo;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("Focus area Generation Step :: Starts ");
		
			this.updateFocusArea4Quarter();
			
			System.out.println("Focus area Generation Step :: Ends ");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}
	
     private void updateFocusArea4Quarter() {
		
		String queryStr = "select view from Reportview view group by agentnum,year,quarter,lob,datacategory";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(queryStr);		
		List<Reportview>  resultlist = query.getResultList();
		List<Reportview>  updatelist = new ArrayList<Reportview>();
		List<Reportview> eachQuarter4Agent = null;
		String currentDataset = "";
		for(Reportview view:resultlist )
		{	
			String currentQuarter4Agent = view.getId().getAgentnum()+view.getId().getYear()+view.getId().getQuarter();
			if(currentDataset != null && !"".equalsIgnoreCase(currentDataset.trim()) && !currentDataset.equalsIgnoreCase(currentQuarter4Agent))
			{
//				calculate the focus area for the previous collection
				Collections.sort(eachQuarter4Agent,new FocusAreaComparator());
				Reportview focusArea4Quarter = eachQuarter4Agent.get(eachQuarter4Agent.size()-1);
				focusArea4Quarter.setFocusarea("Y");
				updatelist.add(focusArea4Quarter);
				
				// set loop for collecting next list of items.
				currentDataset = currentQuarter4Agent;
				eachQuarter4Agent = new ArrayList<Reportview>();
			}
			if(currentDataset != null &&  "".equalsIgnoreCase(currentDataset.trim()) )
			{
				currentDataset = currentQuarter4Agent;
				eachQuarter4Agent = new ArrayList<Reportview>(); // new collection for each quarter in the year for each agent. 
			}
			
			eachQuarter4Agent.add(view);
			
			
		}
		manager.close();
		reportRepo.save(updatelist);
	}

	
	
}
