package com.btcetrade.factory;

import java.math.BigDecimal;

public class TestMain {
 public static void main(String[] args) {
	CurrencyChangeFactory factory=new CurrencyChangeFactory();
	
		ICurrency currencyINR=factory.getCurrecyININRORUSD("INR");
		BigDecimal resultINR=currencyINR.changeCurrency((float)1250.98);
		System.out.println(resultINR+"INR");
		ICurrency currencyUSD=factory.getCurrecyININRORUSD("USD");
		BigDecimal resultUSD=currencyUSD.changeCurrency((float)79837.5390625);
		System.out.println(resultUSD+"USD");
 	}
}
