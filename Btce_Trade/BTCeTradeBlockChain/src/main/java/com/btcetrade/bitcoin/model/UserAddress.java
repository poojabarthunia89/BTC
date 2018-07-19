package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class UserAddress implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6166288597223595660L;
	private String userAddress;
	private String status;
	private  Integer userId;
	private String address;
	private String label;
	private String network;
	
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	
	
	

}
