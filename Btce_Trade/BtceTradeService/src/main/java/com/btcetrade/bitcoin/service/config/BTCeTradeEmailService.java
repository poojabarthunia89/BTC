package com.btcetrade.bitcoin.service.config;


import com.btcetrade.bitcoin.model.BTCResponse;


public interface BTCeTradeEmailService {
	public  BTCResponse emailVerify(String email);

	public BTCResponse emailVerifyForOTP(String emailId);
	
	
}
