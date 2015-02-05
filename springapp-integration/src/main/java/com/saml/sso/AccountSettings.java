package com.saml.sso;


public class AccountSettings {
	private String certificatePath;
	private String idp_sso_target_url;
	
	public String getCertificatePath() {
		return certificatePath;
	}
	public void setCertificatePath(String certificate) {
		this.certificatePath = certificate;
	}
	public String getIdp_sso_target_url() {
		return idp_sso_target_url;
	}
	public void setIdpSsoTargetUrl(String idp_sso_target_url) {
		this.idp_sso_target_url = idp_sso_target_url;
	}
	
//	public void setCertificateFile(File file)
//	{
//		URL url = this.getClass().getResource(filename);
//		File file = new File(url.getFile());
//		StringBuilder result = new StringBuilder();
//		BufferedReader reader = null;
//	    try {
//	       reader = new BufferedReader(new FileReader(file));
//
//	        char[] buf = new char[1024];
//
//	        int r = 0;
//
//	        while ((r = reader.read(buf)) != -1) {
//	            result.append(buf, 0, r);
//	        }
//	    } catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    finally {
//	        try {
//				if(reader != null)
//	        	reader.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }
//	    this.certificate = reader.toString();

//	}
}
