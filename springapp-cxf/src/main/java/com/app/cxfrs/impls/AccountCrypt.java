package com.app.cxfrs.impls;

import com.app.CrytoStratergy;


@CrytoStratergy(type=AccountCrypt.class)
public class AccountCrypt implements Crypto {
	
	public AccountCrypt(){	
	}
	
	@Override
	public String encrypt(String plain_texts) {		
		String decrypted = "ACC-"+plain_texts;
				return decrypted;
	}
	
	@Override
	public String decrypt(String encrypted_text) {
		return "DE-"+encrypted_text;
	}
}