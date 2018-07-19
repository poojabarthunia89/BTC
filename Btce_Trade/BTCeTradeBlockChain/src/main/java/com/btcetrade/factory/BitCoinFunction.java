package com.btcetrade.factory;

public interface BitCoinFunction {
	public static final String BASE_URL_BLOCKCHAIN="https://blockchain.info/ticker";
	public static final String BASE_URL_SPECTRO_COIN="https://spectrocoin.com/scapi/ticker/USD/BTC";
	public static final String BASE_URL_ZEBPAY="https://live.zebapi.com/api/v1/ticker?currencyCode=inr";
	public String companyName(String companyName);
	
}
