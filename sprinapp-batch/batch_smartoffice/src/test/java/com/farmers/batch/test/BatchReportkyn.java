package com.farmers.batch.test;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/batch/override/appcontext.xml",
    "classpath:META-INF/spring/batch/jobs/ImportAgentDataJob.xml",   
    "classpath:META-INF/spring/config/batchrepocontext.xml",
    "classpath:META-INF/spring/config/testproperties.xml"
		})
public class BatchReportkyn 
{
	@Autowired 
	private ApplicationContext context;
	public static HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
	
	static {	
	paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
	paramsMap.put("aor", new JobParameter("040305",false));
	paramsMap.put("statecd", new JobParameter("04",false));
	paramsMap.put("district", new JobParameter("03",false));
	}
	
	@Test
	public void loadAgentData()
	{
		System.out.println("Batch initialized");
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("loadAgentPerformanceData");
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void dummyAgentDetailsJob()
	{
		System.out.println("Batch initialized");
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("dummyupDetails4Test");
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void ytdRollupAgentData()
	{
		System.out.println("Batch initialized for ytdAgentdata");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("ytdcalculation");
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void districtRollupAgentData()
	{
		System.out.println("Batch initialized for ytdAgentdata");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("districtRollup");
					
			
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void stateRollupAgentData()
	{
		System.out.println("Batch initialized for ytdAgentdata");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("stateRollup");
					
			
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void runRecommendation()
	{
		System.out.println("Batch initialized for ytdAgentdata");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("runrecommendation");
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void runHiererchyDetailsJob()
	{
		System.out.println("Batch initialized for AgentDetails");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("loadAgentDetailsjob");
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			paramsMap.put("dmid", new JobParameter("040301",false));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test	
	public void runReportViewGeneratorJob()
	{
		System.out.println("Batch initialized for reportview generation");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("reportviewupdate");
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test	
	public void runUpdatepastyearview()
	{
		System.out.println("Batch initialized for reportview generation");
		
		try {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("updatepastyearview");
			HashMap<String, JobParameter> paramsMap = new HashMap<String, JobParameter>(10);
			paramsMap.put("batchtime", new JobParameter(new Date(System.currentTimeMillis()),true));
			JobParameters params = new JobParameters(paramsMap);
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
