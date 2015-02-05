package com.farmers.batch.kyn.quote;

import java.sql.Timestamp;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Districtmonthly;

@Service("districtRollupProcessor")
public class DistrictRollupProcessor implements ItemProcessor<Districtmonthly,Districtmonthly>{

	
	@Override
	public Districtmonthly process(final Districtmonthly districtData) throws Exception {
		System.out.println(" Districtmonthly rollup Processing..." + districtData.toString());
			districtData.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		return null;
	}


}
