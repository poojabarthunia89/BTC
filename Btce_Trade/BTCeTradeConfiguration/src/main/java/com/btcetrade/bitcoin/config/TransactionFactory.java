package com.btcetrade.bitcoin.config;

public class TransactionFactory {

	
	public static ITransaction getTransactionInstance(String transactionType){
		ITransaction transaction;
		if(transactionType.equalsIgnoreCase("payment")){
		return new TransactionForPaymentActivity();
		}if(transactionType.equalsIgnoreCase("bitcoin")){
			return new BITCoinTransactionActivity();
		}
		return null;
	}
	
	
}
