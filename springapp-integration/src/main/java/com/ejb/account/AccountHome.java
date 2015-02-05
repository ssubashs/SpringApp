package com.ejb.account;
/**
 * Home interface for Enterprise Bean: Account
 */
public interface AccountHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Account
	 */
	public com.ejb.account.Account create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
