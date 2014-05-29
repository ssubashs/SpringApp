package com.app.cxfrs.impls;


public interface Crypto {
	
	String encrypt(String plain_texts);
	String decrypt(String encrypted_text);
}
