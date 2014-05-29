package com.app.cxfrs.impls;


public class CryptoImpl {
	private Crypto cryptType;
	
	public CryptoImpl(Crypto cryptType) {
		this.cryptType = cryptType; 
	}
	
	public String encrypt(String plain_text) {
		return(cryptType.encrypt(plain_text));
	}
	
	public String decrypt(String encrypted_text){
		return(cryptType.decrypt(encrypted_text));
	}
	
	public void setCryptObj(Crypto cryptType) {
		this.cryptType = cryptType; 
	}
}
