package com.farmers.batch.kyn.quote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Agentdetail;
import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.Reportview;
import com.farmers.batch.kyn.ReportviewPK;

@Service("reportViewProcessor")
public class AgentReportViewProcessor implements ItemProcessor<String,Reportview[]>{

	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public Reportview[] process(final String agentnum) throws Exception {
		System.out.println("Report view  Processing..." + agentnum.toString());
		String queryStr = "SELECT monthly FROM Agentmonthly as monthly where monthly.id.agentNum=:aor";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(queryStr);
		query.setParameter("aor", agentnum);
		List<Agentmonthly>  resultlist = query.getResultList();
		System.out.println("resultlist size "+resultlist.size());
		Reportview[] agentsViews = process(resultlist);
		manager.close();
		return agentsViews;
	}

	private Reportview[] process(List<Agentmonthly> agentmonthly) {
		
		HashMap<String,Reportview> viewmap = new HashMap<String,Reportview>();
		for(Agentmonthly monthly : agentmonthly)
		{
			buildView(monthly,viewmap);
		}
	
		List<Reportview> reportList = new ArrayList<Reportview>(viewmap.values());
		for(Reportview view:reportList)
		{
			if("Retention".equalsIgnoreCase(view.getId().getDatacategory()) || "Close Rate".equalsIgnoreCase(view.getId().getDatacategory()))
			{
				// quarter average of percentage
				view.setAgentdata(view.getAgentdata().divide(new BigDecimal(4)));
			}
			
//			String queryStr = "SELECT detail FROM Agentdetail as detail where detail.agentNum=:aor";
//			Query query = emf.createEntityManager().createQuery(queryStr);
//			query.setParameter("aor", view.getId().getAgentnum());
//			List<Agentdetail>  resultlist = query.getResultList();
//			if(resultlist!=null && resultlist.size()>0)
//			{
//				view.setAgentname(resultlist.get(0).getFirstname()+", "+resultlist.get(0).getLastname());
//				view.setDmname(resultlist.get(0).getDmfirstname()+", "+resultlist.get(0).getDmlastname());
//				
//			}
//			System.out.println("resultlist size "+resultlist.size());
			
		}
		return reportList.toArray(new Reportview[reportList.size()]);
	}

	private void buildView(Agentmonthly monthly,
			HashMap<String, Reportview> viewmap) {
		ReportviewPK quotesReportkey = new ReportviewPK(monthly.getId().getAgentNum(),monthly.getId().getYear(),monthly.getId().getMonth(),"Quotes",monthly.getId().getLob());
		ReportviewPK pifReportkey = new ReportviewPK(monthly.getId().getAgentNum(),monthly.getId().getYear(),monthly.getId().getMonth(),"Policies",monthly.getId().getLob());
		ReportviewPK nbReportkey = new ReportviewPK(monthly.getId().getAgentNum(),monthly.getId().getYear(),monthly.getId().getMonth(),"New Business",monthly.getId().getLob());
		ReportviewPK retReportkey = new ReportviewPK(monthly.getId().getAgentNum(),monthly.getId().getYear(),monthly.getId().getMonth(),"Retention",monthly.getId().getLob());
		ReportviewPK crReportkey = new ReportviewPK(monthly.getId().getAgentNum(),monthly.getId().getYear(),monthly.getId().getMonth(),"Close Rate",monthly.getId().getLob());
		

// Quotes		
		if(viewmap.get(quotesReportkey.getkeystring())==null)
		{
			Reportview quotesQuarter = new Reportview();
			quotesQuarter.setId(quotesReportkey);	
			quotesQuarter.setDistrict(monthly.getDistrict());
			quotesQuarter.setState(monthly.getState());
			viewmap.put(quotesReportkey.getkeystring(),quotesQuarter);
		}
		// roll quarter data
		Reportview quotesQuarter = viewmap.get(quotesReportkey.getkeystring());
		quotesQuarter.setAgentdata(quotesQuarter.getAgentdata().add(new BigDecimal(monthly.getQuotes())));
// Pif		
		if(viewmap.get(pifReportkey.getkeystring())==null)
		{
			Reportview pifQuarter = new Reportview();
			pifQuarter.setId(pifReportkey);
			pifQuarter.setDistrict(monthly.getDistrict());
			pifQuarter.setState(monthly.getState());
			viewmap.put(pifReportkey.getkeystring(),pifQuarter);
		}
		// roll pif data
		Reportview pifQuarter = viewmap.get(pifReportkey.getkeystring());
		pifQuarter.setAgentdata(pifQuarter.getAgentdata().add(new BigDecimal(monthly.getPif())));
		
// new business		
		if(viewmap.get(nbReportkey.getkeystring())==null)
		{
			Reportview nbQuarter = new Reportview();
			nbQuarter.setId(nbReportkey);
			nbQuarter.setDistrict(monthly.getDistrict());
			nbQuarter.setState(monthly.getState());
			viewmap.put(nbReportkey.getkeystring(),nbQuarter);
		}
		// roll nb data
		Reportview nbQuarter = viewmap.get(nbReportkey.getkeystring());
		nbQuarter.setAgentdata(nbQuarter.getAgentdata().add(new BigDecimal(monthly.getNewbusiness())));
		
// close rate 		
		if(viewmap.get(crReportkey.getkeystring())==null)
		{
			Reportview crQuarter = new Reportview();
			crQuarter.setId(crReportkey);
			crQuarter.setDistrict(monthly.getDistrict());
			crQuarter.setState(monthly.getState());
			viewmap.put(crReportkey.getkeystring(),crQuarter);
		}
		// roll quarter data
		Reportview crQuarter = viewmap.get(crReportkey.getkeystring());
		crQuarter.setAgentdata(crQuarter.getAgentdata().add(monthly.getCloserate()));
		
// retention		
		if(viewmap.get(retReportkey.getkeystring())==null)
		{
			Reportview retQuarter = new Reportview();
			retQuarter.setId(retReportkey);
			retQuarter.setDistrict(monthly.getDistrict());
			retQuarter.setState(monthly.getState());
			viewmap.put(retReportkey.getkeystring(),retQuarter);
		}
		// roll retention data
		Reportview retQuarter = viewmap.get(retReportkey.getkeystring());
		retQuarter.setAgentdata(retQuarter.getAgentdata().add(monthly.getRetention()));
		
		
		
	}
	
	
	


}
