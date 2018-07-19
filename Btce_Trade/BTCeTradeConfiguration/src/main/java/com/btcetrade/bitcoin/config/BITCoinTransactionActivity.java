package com.btcetrade.bitcoin.config;

import com.btcetrade.bitcoin.transaction.TransactionIdGenerator;

public class BITCoinTransactionActivity implements ITransaction {

	@Override
	public ObjectEncryption getTransactionId(String txType,String txStatus) {
		RSAEncryptionTransactionString encrypt= new RSAEncryptionTransactionString();
		String transactionId=TransactionIdGenerator.generateTransactionId(8,txType,txStatus);
		try{
		ObjectEncryption encryptObject=encrypt.getEncryptTransaction(transactionId);
		System.out.println(encryptObject.getEncrypt());
		System.out.println(encryptObject.getDcrypt());
		return encryptObject;
		}catch(Exception e){
			e.printStackTrace();
			return new ObjectEncryption();
		}
	}
	
}
