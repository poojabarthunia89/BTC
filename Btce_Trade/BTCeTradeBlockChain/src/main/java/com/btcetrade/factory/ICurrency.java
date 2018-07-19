package com.btcetrade.factory;

import java.math.BigDecimal;

public interface ICurrency {
                  public static final String CURRENCY_USD="USD";
                  public static final String CURRENCY_INR="INR";
                  public static final String URL_BASE="http://www.apilayer.net/api/live?access_key=eaf21dd87ce2dfa0a8f3313fa352049f";
                  public BigDecimal changeCurrency(float currency);
                 
                  
                  
                  
}
