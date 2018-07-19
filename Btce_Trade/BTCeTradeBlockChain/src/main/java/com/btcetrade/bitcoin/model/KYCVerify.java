package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class KYCVerify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String emailId;
	
	private String adharNumber;
	
	private String pancardNumber;
	
	private Integer emailFlag=0;
	
	private Integer adharFlag=0;
	
	private Integer pancardFlag=0;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public Integer getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(Integer emailFlag) {
		this.emailFlag = emailFlag;
	}

	public Integer getAdharFlag() {
		return adharFlag;
	}

	public void setAdharFlag(Integer adharFlag) {
		this.adharFlag = adharFlag;
	}

	public Integer getPancardFlag() {
		return pancardFlag;
	}

	public void setPancardFlag(Integer pancardFlag) {
		this.pancardFlag = pancardFlag;
	}
}
