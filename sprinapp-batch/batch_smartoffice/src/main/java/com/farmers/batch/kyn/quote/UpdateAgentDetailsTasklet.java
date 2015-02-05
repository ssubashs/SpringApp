package com.farmers.batch.kyn.quote;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Agentdetail;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;

@Service("updateAgentDetail")
public class UpdateAgentDetailsTasklet implements Tasklet{
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private ReportViewRepo reportRepo;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("update view with agent details :: Starts ");
			String queryStr = "select view from Reportview view where view.agentname is null or view.dmname is null";
			EntityManager manager = emf.createEntityManager();
			Query query = manager.createQuery(queryStr);
			List<Reportview>  resultlist = query.getResultList();
			System.out.println("no of rows to be updated "+resultlist.size());	
			for(Reportview view:resultlist )
			{	
//				System.out.println("Agent number "+view.getId().getAgentnum());
				Agentdetail detail = getDetail(view.getId().getAgentnum());
//				System.out.println(view.getId().getAgentnum()+"  -- "+detail.toString() );
				if(detail !=null)
				{
					view.setAgentname(detail.getFirstname()+","+detail.getLastname());
					view.setDmname(detail.getDmfirstname()+","+detail.getDmlastname());
				}
			}
			
			manager.close();			
			System.out.println("no of rows to be updated "+resultlist.size());			
			reportRepo.save(resultlist);
			System.out.println("update view with agent details :: Ends ");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

	private Agentdetail getDetail(String agentnum) {
		String queryStr = "select detail from Agentdetail detail where detail.agentNum = :aor";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(queryStr);
		query.setParameter("aor", agentnum);
		List<Agentdetail>  resultlist = query.getResultList();
		Agentdetail returndetail = null;
		for(Agentdetail detail:resultlist )
		{			
			returndetail = detail;
			//break;
		}
		manager.close();
		return returndetail;
	}

}
