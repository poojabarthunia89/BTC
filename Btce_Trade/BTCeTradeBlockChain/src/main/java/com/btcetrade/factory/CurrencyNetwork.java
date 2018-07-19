package com.btcetrade.factory;

import com.btcetrade.bitcoin.model.CurrencyDetail;

public interface CurrencyNetwork {
   
	public CurrencyDetail currencyDigitalType(String currencyType)throws Exception;
	
}
