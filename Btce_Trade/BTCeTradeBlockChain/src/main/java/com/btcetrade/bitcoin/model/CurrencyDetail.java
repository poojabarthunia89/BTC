package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class CurrencyDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1842463843965687462L;
	private String currencyType;
	private String currncyRate;
	private String companyMark;
	
	private float last;
	private float buy;
	private float sell; 
	private String symbol;
	
	private long market;
	private float volume;
	
	String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getCurrncyRate() {
		return currncyRate;
	}
	public void setCurrncyRate(String currncyRate) {
		this.currncyRate = currncyRate;
	}
	public String getCompanyMark() {
		return companyMark;
	}
	public void setCompanyMark(String companyMark) {
		this.companyMark = companyMark;
	}
	public float getLast() {
		return last;
	}
	public void setLast(float last) {
		this.last = last;
	}
	public float getBuy() {
		return buy;
	}
	public void setBuy(float buy) {
		this.buy = buy;
	}
	public float getSell() {
		return sell;
	}
	public void setSell(float sell) {
		this.sell = sell;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public long getMarket() {
		return market;
	}
	public void setMarket(long market) {
		this.market = market;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	

}
