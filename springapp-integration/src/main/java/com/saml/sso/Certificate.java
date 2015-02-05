package com.saml.sso;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;

public class Certificate {

	private X509Certificate x509Cert;
	
	/**
	 * Loads certificate from a base64 encoded string 
	 */
 	public void loadCertificate(String certificate) throws CertificateException {
		CertificateFactory fty = CertificateFactory.getInstance("X.509");
		ByteArrayInputStream bais = new ByteArrayInputStream(certificate.getBytes());
		x509Cert = (X509Certificate)fty.generateCertificate(bais);
	}
	
	/**
	 * Loads a certificate from a encoded base64 byte array.
	 * @param certificate an encoded base64 byte array.
	 * @throws CertificateException In case it can't load the certificate.
	 */
	public void loadCertificate(byte[] certificate) throws CertificateException {
		CertificateFactory fty = CertificateFactory.getInstance("X.509");
		ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decodeBase64(certificate));
		x509Cert = (X509Certificate)fty.generateCertificate(bais);
	}
	
	public void loadCertificate(File certfile) throws CertificateException {
		FileInputStream fis;
		try {
			fis = new FileInputStream(certfile);
			CertificateFactory fty = CertificateFactory.getInstance("X.509");			
			x509Cert = (X509Certificate)fty.generateCertificate(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public X509Certificate getX509Cert() {
		return x509Cert;
	}						
}
