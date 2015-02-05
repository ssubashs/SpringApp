package com.farmers.quote;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class QuoteMapper implements FieldSetMapper<Quote>{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public Quote mapFieldSet(FieldSet row) throws BindException {
		Quote quote = null;
		String agentNumber = null;
		String lob = null;
		String quotedate = null;
		
		if(row != null)
		{
			agentNumber = row.readString(0);
			quotedate = row.readString(1);
			lob = row.readString(2);
			if(agentNumber != null && quotedate != null && lob != null)
			{
				try {
					quote = new Quote(agentNumber, dateFormat.parse(quotedate),lob);
					quote.setQuoteCount(row.readInt(3));
					quote.setQuoteConversion(row.readInt(4));
					quote.setSource(row.readString(5));
				} catch (ParseException e) {					
					e.printStackTrace();
				}
			}
				
			return quote;
		}
		else
			return null;
	}

}
