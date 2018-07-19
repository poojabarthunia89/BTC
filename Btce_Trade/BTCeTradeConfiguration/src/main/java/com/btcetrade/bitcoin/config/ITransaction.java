package com.btcetrade.bitcoin.config;

public interface ITransaction {
  public static final String TRANSACTION_PAYMENT_TYPE="txnId";
  public ObjectEncryption getTransactionId(String txType,String txStatus);
}
