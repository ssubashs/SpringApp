package com.farmers.web.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.farmers.batch.kyn.AgentDetailsRepo;
import com.farmers.batch.kyn.Agentaction;
import com.farmers.batch.kyn.AgentactionRepo;
import com.farmers.batch.kyn.Agentdetail;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;
import com.farmers.data.QuarterlyView;
import com.farmers.web.report.DownloadService;


@Controller
@Scope("request")
public class ReportsController {
	
	@Autowired
	private ReportViewRepo reportRepo;
	
	@Autowired
	private AgentactionRepo actionsRepo;
	
	@Autowired
	private AgentDetailsRepo detailsRepo;
	
	@Autowired
	private DownloadService downloadService;
	
	private final String reportview = "layouts/report/quarterlyReport";
	
	@RequestMapping(value = {"/quarterlyreport/{aor}/{year}/{quarter}"}, method = RequestMethod.GET)
	public ModelAndView renderReportView(ModelAndView view, @PathVariable("aor")String aor,@PathVariable("year")Integer year,@PathVariable("quarter")Integer quarter)
	{
		System.out.println("Reached controller to access report ");
		view.setViewName(reportview);
		List<Reportview> reportViews =  reportRepo.findAgentreportquarter(aor,year,quarter);
		List<Agentaction> agentActions = actionsRepo.findAgentreportquarter(aor,year,quarter);
		HashMap<String,QuarterlyView> viewmap = new HashMap<String,QuarterlyView>();
		
		for(Reportview rview:reportViews)
		{
//			System.out.println(rview.toString());
			this.addReportView(viewmap, rview);
			
		}
		List<QuarterlyView> views = new ArrayList<QuarterlyView>(viewmap.values());
		List<Agentdetail> agentDetails = detailsRepo.findAgentbyAor(aor);
		Agentdetail agent = new Agentdetail(); // just dummy to avoid null exception in jasper renderer. 
		if(agentDetails != null)
			agent = agentDetails.get(0);
		view.addObject("agent", agent);
		view.addObject("year",year);
		view.addObject("quarter",quarter);
		view.addObject("performanceData", views);
		view.addObject("recommendations", getlatestByType(agentActions));
		
		return view;
	}
	
	
	@RequestMapping(value = {"/downloadreport/pdf/{aor}/{year}/{quarter}"}, method = RequestMethod.GET)
	public ModelAndView downloadpdfreport(ModelAndView mv, @PathVariable("aor")String aor,@PathVariable("year")Integer year,@PathVariable("quarter")Integer quarter)
	{
		System.out.println("Reached controller to download report :: xls");
	
		List<Reportview> reportViews =  reportRepo.findAgentreportquarter(aor,year,quarter);
		List<Agentaction> agentActions = actionsRepo.findAgentreportquarter(aor,year,quarter);
		List<Agentdetail> agentDetails = detailsRepo.findAgentbyAor(aor);
		Agentdetail agent = new Agentdetail(); // just dummy to avoid null exception in jasper renderer. 
		if(agentDetails != null)
			agent = agentDetails.get(0);
		HashMap<String,QuarterlyView> viewmap = new HashMap<String,QuarterlyView>();
		
		for(Reportview rview:reportViews)
		{
//			System.out.println(rview.toString());
			this.addReportView(viewmap, rview);
			
		}
		List<QuarterlyView> views = new ArrayList<QuarterlyView>(viewmap.values());
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		params.put("Title", "Smart Office Report");
		params.put("AGENT", agent);
		params.put("YEAR", year);
		params.put("QUARTER", quarter);
		params.put("datasource",  new JRBeanCollectionDataSource(views));
		params.put("actionsdatasource",  new JRBeanCollectionDataSource(getlatestByType(agentActions)));	
		mv.setViewName("pdfReport");
		mv.addAllObjects(params);
		
//		downloadService.download("pdf",response,new JRBeanCollectionDataSource(views),params);
		return mv;
	}
	
	@RequestMapping(value = {"/downloadreport/xls/{aor}/{year}/{quarter}"}, method = RequestMethod.GET)
	public ModelAndView downloadexcelreport(ModelAndView mv, @PathVariable("aor")String aor,@PathVariable("year")Integer year,@PathVariable("quarter")Integer quarter)
	{
		System.out.println("Reached controller to download report ::xls");
	
		List<Reportview> reportViews =  reportRepo.findAgentreportquarter(aor,year,quarter);
		List<Agentaction> agentActions = actionsRepo.findAgentreportquarter(aor,year,quarter);
		List<Agentdetail> agentDetails = detailsRepo.findAgentbyAor(aor);
		Agentdetail agent = new Agentdetail(); // just dummy to avoid null exception in jasper renderer. 
		if(agentDetails != null)
			agent = agentDetails.get(0);
		HashMap<String,QuarterlyView> viewmap = new HashMap<String,QuarterlyView>();
		
		for(Reportview rview:reportViews)
		{
//			System.out.println(rview.toString());
			this.addReportView(viewmap, rview);
			
		}
		List<QuarterlyView> views = new ArrayList<QuarterlyView>(viewmap.values());
		HashMap<String, Object> params = new HashMap<String, Object>(); 
		params.put("Title", "Smart Office Report");
		params.put("AGENT", agent);
		params.put("YEAR", year);
		params.put("QUARTER", quarter);
		params.put("datasource",  new JRBeanCollectionDataSource(views));
		params.put("actionsdatasource",  new JRBeanCollectionDataSource(getlatestByType(agentActions)));	
		mv.setViewName("xlsReport");
		mv.addAllObjects(params);
		
//		downloadService.download("pdf",response,new JRBeanCollectionDataSource(views),params);
		return mv;
	}
	
	private List<Agentaction> getlatestByType(List<Agentaction> agentActions) {
		HashMap<String,Agentaction> map = new HashMap<String,Agentaction>();
		for(Agentaction action:agentActions)
		{
			String key = action.getId().getAgentNum()+action.getId().getYear()+action.getId().getCategory();
			if(map.containsKey(key))
				continue;
			else
				map.put(key, action);
				
		}
		return new ArrayList<Agentaction>(map.values());
	}

	
	
	public void addReportView(HashMap<String,QuarterlyView> viewmap, Reportview view)
	{
		String key = view.getId().getAgentnum()+view.getId().getYear()+view.getId().getDatacategory()+view.getId().getQuarter();
		if(viewmap.get(key)==null)
		{
			QuarterlyView viewbean = new QuarterlyView(view.getId().getAgentnum(), view.getId().getYear(),view.getId().getQuarter(), view.getId().getDatacategory());			
			viewmap.put(key,viewbean);			
		}
		QuarterlyView viewbean = viewmap.get(key);
		mapper(viewbean, view);
	}
	
	public static void mapper(QuarterlyView qview, Reportview rView)
	{
		boolean doconvert = false;
		if("Quotes".equalsIgnoreCase(rView.getId().getDatacategory()) || "New Business".equalsIgnoreCase(rView.getId().getDatacategory()) || "Policies".equalsIgnoreCase(rView.getId().getDatacategory()))
			doconvert = true;
		if("Auto".equalsIgnoreCase(rView.getId().getLob()))
			{
			
				qview.setAutoagentdata(roundBigDecimal(rView.getAgentdata(),doconvert).toPlainString());
				qview.setAutodistrictdata(roundBigDecimal(rView.getDistrictdata(),doconvert).toPlainString());
				qview.setAutostatedata(roundBigDecimal(rView.getStatedata(),doconvert).toPlainString());
				if(rView.getFocusarea()!=null)
				qview.setFocusarea(rView.getFocusarea());
				qview.setPastautoagentdata(roundBigDecimal(rView.getPastagentdata(),doconvert).toPlainString());
				qview.setPastautodistrictdata(roundBigDecimal(rView.getPastdistrictdata(),doconvert).toPlainString());
				qview.setPastautostatedata(roundBigDecimal(rView.getPaststatedata(),doconvert).toPlainString());
			}
		else if("Fire".equalsIgnoreCase(rView.getId().getLob()))
			{
				qview.setFireagentdata(roundBigDecimal(rView.getAgentdata(),doconvert).toPlainString());
				qview.setFiredistrictdata(roundBigDecimal(rView.getDistrictdata(),doconvert).toPlainString());
				qview.setFirestatedata(roundBigDecimal(rView.getStatedata(),doconvert).toPlainString());
				if(rView.getFocusarea()!=null)
				qview.setFocusarea(rView.getFocusarea());
				qview.setPastfireagentdata(roundBigDecimal(rView.getPastagentdata(),doconvert).toPlainString());
				qview.setPastfiredistrictdata(roundBigDecimal(rView.getPastdistrictdata(),doconvert).toPlainString());
				qview.setPastfirestatedata(roundBigDecimal(rView.getPaststatedata(),doconvert).toPlainString());
			}
	}
	
	public static BigDecimal roundBigDecimal(final BigDecimal input, boolean doconvert){
		if(doconvert)
	    return input.round(
	        new MathContext(
	            input.toBigInteger().toString().length(),
	            RoundingMode.HALF_UP
	        )  );
	        else
	        return input;	
	  
	}
		

}
