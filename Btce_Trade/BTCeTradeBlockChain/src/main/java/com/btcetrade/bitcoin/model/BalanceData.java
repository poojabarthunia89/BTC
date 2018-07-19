package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class BalanceData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -144653777224176269L;

	private String network;
	
	private String address;
	
	private String confirmedBalance;
	
	private String unconfirmed_balance;

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConfirmedBalance() {
		return confirmedBalance;
	}

	public void setConfirmedBalance(String confirmedBalance) {
		this.confirmedBalance = confirmedBalance;
	}

	public String getUnconfirmed_balance() {
		return unconfirmed_balance;
	}

	public void setUnconfirmed_balance(String unconfirmed_balance) {
		this.unconfirmed_balance = unconfirmed_balance;
	}
	
	
}
