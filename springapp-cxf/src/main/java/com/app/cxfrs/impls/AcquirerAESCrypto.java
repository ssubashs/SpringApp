package com.app.cxfrs.impls;

import com.app.CrytoStratergy;


@CrytoStratergy(type=AcquirerAESCrypto.class)
public class AcquirerAESCrypto implements Crypto{
	
	// Inject the dependency
	public AcquirerAESCrypto() {		
	}
	
	@Override
	public String encrypt(String plain_text) {		
		return "aesEn-"+plain_text;
	}
	
	@Override
	public String decrypt(String encrypted_text){
		return "De-"+encrypted_text;
	}
}
