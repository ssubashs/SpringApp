package com.farmers.batch.kyn;

import java.math.BigDecimal;
import java.util.Comparator;

public class FocusAreaComparator implements Comparator<Reportview>{

	@Override
	public int compare(Reportview view1, Reportview view2) {
		BigDecimal percent1 = null;
		BigDecimal percent2 = null;
		BigDecimal One100 = new BigDecimal(100);
		if("Quotes".equalsIgnoreCase(view1.getId().getDatacategory()))
				{
					double value = view1.getAgentdata().subtract(view1.getDistrictdata()).doubleValue();
					value = value / view1.getDistrictdata().doubleValue();
					
					percent1 = new BigDecimal(value).multiply(One100);
				}
		if("Quotes".equalsIgnoreCase(view2.getId().getDatacategory()))
		{
			double value = view2.getAgentdata().subtract(view2.getDistrictdata()).doubleValue();
			value = value / view2.getDistrictdata().doubleValue();
			
			percent2 = new BigDecimal(value).multiply(One100);
		}
		if("Retention".equalsIgnoreCase(view1.getId().getDatacategory()) || "Close Rate".equalsIgnoreCase(view1.getId().getDatacategory()))
		{
			percent1 = (view1.getAgentdata().subtract(view1.getDistrictdata()));			
		}
		if("Retention".equalsIgnoreCase(view2.getId().getDatacategory()) || "Close Rate".equalsIgnoreCase(view2.getId().getDatacategory()))
		{
			percent2 = (view2.getAgentdata().subtract(view2.getDistrictdata()));			
		}
		
		int difference = 0;
		if(percent1 !=null && percent2 !=null)
		{
			difference = (percent1.subtract(percent2)).intValue();
		}
		return difference;
	}
	
	
	

	
}
