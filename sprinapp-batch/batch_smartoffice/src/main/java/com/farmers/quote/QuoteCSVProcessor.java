package com.farmers.quote;

import org.springframework.batch.item.ItemProcessor;

public class QuoteCSVProcessor implements ItemProcessor<Quote,Quote>{

	@Override
	public Quote process(Quote input) throws Exception {		
		System.out.println("Processing..." + input.toString());		
		return input;
	}

}
