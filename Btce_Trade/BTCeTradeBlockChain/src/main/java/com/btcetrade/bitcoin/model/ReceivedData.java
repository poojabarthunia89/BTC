package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class ReceivedData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3015707152364511193L;

	private String network;
	
	private String address;
	
	private String confirmedReceivedValue;
	
	private String unconfirmedReceivedValue;

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

	public String getConfirmedReceivedValue() {
		return confirmedReceivedValue;
	}

	public void setConfirmedReceivedValue(String confirmedReceivedValue) {
		this.confirmedReceivedValue = confirmedReceivedValue;
	}

	public String getUnconfirmedReceivedValue() {
		return unconfirmedReceivedValue;
	}

	public void setUnconfirmedReceivedValue(String unconfirmedReceivedValue) {
		this.unconfirmedReceivedValue = unconfirmedReceivedValue;
	}
	
	
}
