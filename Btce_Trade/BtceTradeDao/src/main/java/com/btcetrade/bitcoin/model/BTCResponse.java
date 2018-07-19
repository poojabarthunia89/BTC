package com.btcetrade.bitcoin.model;

public class BTCResponse {
	
	private String resultForResponse;
	
	private String userAddress;
	
	public BTCResponse(){
		
	}

	public String getResultForResponse() {
		return resultForResponse;  
	}

	public void setResultForResponse(String resultForResponse) {
		this.resultForResponse = resultForResponse;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	

}
