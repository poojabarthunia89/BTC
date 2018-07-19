package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class BTCRateOfCompanies implements Serializable{
	
	/**
	 * Serilization ID
	 */
	private static final long serialVersionUID = 1L;
	
	private String exchangeName;
	
	private float price;
	
	private String currency;
	
	private Integer time;
	
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	
	
	

}
