package com.btcetrade.bitcoin.config;

import java.io.Serializable;

public class ObjectEncryption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3720666732070487894L;
	
	private String encrypt;
	
	private String dcrypt;
	
	public String getEncrypt() {
		return encrypt;
	}
	
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	
	public String getDcrypt() {
		return dcrypt;
	}
	
	public void setDcrypt(String dcrypt) {
		this.dcrypt = dcrypt;
	}
	
	
	
}
