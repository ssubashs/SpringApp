package com.farmers.web.report;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Service;

import com.farmers.data.QuarterlyView;

@Service
public class JasperDatasourceService {

	
	/**
	 * Returns a data source that's wrapped within {@link JRDataSource}
	 * @return
	 */
	public JRDataSource getDataSource(List<QuarterlyView> viewlist) {
		
		// Return wrapped collection
		return new JRBeanCollectionDataSource(viewlist);
	}
}
