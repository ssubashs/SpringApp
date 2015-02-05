package com.farmers.batch.kyn.quote;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.AgentmonthlyRepo;
import com.farmers.batch.kyn.DistrictmonthlyRepo;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.StatemonthlyRepo;

@Service("reportGenerator")
public class YearlyReportViewTasklet implements Tasklet{

	@Autowired
	private StatemonthlyRepo staterepo;
	
	@Autowired
	private AgentmonthlyRepo monthlyrepo;
	
	@Autowired
	private DistrictmonthlyRepo districtrepo;
	
	@Autowired
	private ReportViewRepo reportRepo;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("Report Generation Step :: Starts ");
		
			
			
			System.out.println("Report Generation Step :: Ends ");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

}
