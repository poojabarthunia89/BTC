package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class BitCoinRate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Float buyBitCoinRate;
	
	private Float saleBitCoinRate;
	
	private Float buyBitCoinRateUSD;
	
	private Float saleBitCoinRateUSD;
	
	public Float getBuyBitCoinRate() {
		return buyBitCoinRate;
	}
	
	public void setBuyBitCoinRate(Float buyBitCoinRate) {
		this.buyBitCoinRate = buyBitCoinRate;
	}
	
	public Float getSaleBitCoinRate() {
		return saleBitCoinRate;
	}
	
	public void setSaleBitCoinRate(Float saleBitCoinRate) {
		this.saleBitCoinRate = saleBitCoinRate;
	}

	public Float getBuyBitCoinRateUSD() {
		return buyBitCoinRateUSD;
	}

	public void setBuyBitCoinRateUSD(Float buyBitCoinRateUSD) {
		this.buyBitCoinRateUSD = buyBitCoinRateUSD;
	}

	public Float getSaleBitCoinRateUSD() {
		return saleBitCoinRateUSD;
	}

	public void setSaleBitCoinRateUSD(Float saleBitCoinRateUSD) {
		this.saleBitCoinRateUSD = saleBitCoinRateUSD;
	}
 }
