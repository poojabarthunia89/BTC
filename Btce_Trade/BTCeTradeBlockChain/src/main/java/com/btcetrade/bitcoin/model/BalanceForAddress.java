package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class BalanceForAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2011461927870429776L;

	private String status;
	
	private BalanceData blance;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BalanceData getBlance() {
		return blance;
	}
	public void setBlance(BalanceData blance) {
		this.blance = blance;
	}
	
	
}
