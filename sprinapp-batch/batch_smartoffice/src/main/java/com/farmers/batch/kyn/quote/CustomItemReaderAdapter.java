package com.farmers.batch.kyn.quote;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.adapter.AbstractMethodInvokingDelegator;

public class CustomItemReaderAdapter<T> extends AbstractMethodInvokingDelegator<T>  implements ItemReader<T>{

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
