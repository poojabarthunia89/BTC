package com.btcetrade.bitcoin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="btcuser_transaction")
public class BTCTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6927179407574279266L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tr_id")
	private Integer transactionId;
	
	@Column(name="bitcoin_amount")
	private Double bitcoinAmount;
	
	@Column(name="fromAddress")
	private String fromAddress;
	
	@Column(name="toAddress")
	private String toAddress;
	
	@Column(name="bitcoin_trns_id")
	private String bitcoinTransaction;
	
	@Column(name="payment_trns_id")
	private String paymetTransaction;
	@Column(name="payment_amount")
	private Double paymentAmount;
	
	@ManyToOne
	@JoinColumn(name="verify_id")
	private VerifyPhoneNumber user;
	
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getBitcoinTransaction() {
		return bitcoinTransaction;
	}
	public void setBitcoinTransaction(String bitcoinTransaction) {
		this.bitcoinTransaction = bitcoinTransaction;
	}
	public String getPaymetTransaction() {
		return paymetTransaction;
	}
	public void setPaymetTransaction(String paymetTransaction) {
		this.paymetTransaction = paymetTransaction;
	}
	public VerifyPhoneNumber getUser() {
		return user;
	}
	public void setUser(VerifyPhoneNumber user) {
		this.user = user;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Double getBitcoinAmount() {
		return bitcoinAmount;
	}
	public void setBitcoinAmount(Double bitcoinAmount) {
		this.bitcoinAmount = bitcoinAmount;
	}
	
	
	
	
	
	
	
	
}
