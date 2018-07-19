package com.btcetrade.factory;

public class CurrencyChangeFactory {
	
	public static ICurrency getCurrecyININRORUSD(String currencyType){
		ICurrency currency=null;
		if(currencyType==null){
			return  currency;
		
		}else if(currencyType.equalsIgnoreCase("INR")){
			return currency= new CurrencyChangeININR();
		}else if(currencyType.equalsIgnoreCase("USD")){
			return currency=new CurrencyChangeInUSD();
		}
		return null;
	}
	
	
	
}
