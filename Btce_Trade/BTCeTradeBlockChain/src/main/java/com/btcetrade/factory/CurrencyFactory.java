package com.btcetrade.factory;

import org.hibernate.action.spi.BeforeTransactionCompletionProcess;

public class CurrencyFactory {
public CurrencyNetwork getCurrencyInstance(String currencyType){

	if(currencyType.equalsIgnoreCase("BIT")){
		
	 return new BitConNetwork("ZEBPAY");   
	}else{
		return null;
	}
	
}
}
