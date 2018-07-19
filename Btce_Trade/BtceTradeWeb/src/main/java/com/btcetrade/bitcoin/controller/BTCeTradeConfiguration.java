package com.btcetrade.bitcoin.controller;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/btcetrade")
public class BTCeTradeConfiguration extends ResourceConfig {
	
	public BTCeTradeConfiguration(){
		System.out.println("BitCoin Start");
		packages("com.btcetrade.bitcoin.controller");
	}

}
