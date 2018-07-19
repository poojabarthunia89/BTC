package com.btcetrade.bitcoin.model;

import javax.swing.text.StyledEditorKit.BoldAction;

public class ValidAddress {

	private String status;
	private String network;
	private String address;
	private Boolean isValid;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
}
