package com.farmers.batch.kyn.quote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;

@Component("jpaReportViewWriter")
public class ReportViewDataWriter implements ItemWriter<Reportview[]> {

	@Autowired
	private ReportViewRepo repo;

	@Override
	public void write(List<? extends Reportview[]> viewarray) throws Exception {
		List<Reportview> viewList = new ArrayList<Reportview>();
		for(Reportview[] viewlist:viewarray)
		{
			viewList.addAll(Arrays.asList(viewlist));
			
		}
		repo.save(viewList);
		
	}
	

}
