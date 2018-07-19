package com.btcetrade.factory;

import java.math.BigDecimal;

public class CurrencyChangeInUSD implements ICurrency{

	@Override
	public BigDecimal changeCurrency(float currency) {
		
		float dollar=(float) 63.82;
		float result=(float)currency/dollar;
		BigDecimal res= new BigDecimal(Double.valueOf(result));
		
		
		
		
		
		return res;
	}

	
}
