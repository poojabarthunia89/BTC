package com.btcetrade.bitcoin.model;

import java.io.Serializable;
import java.util.List;

public class DashBoardUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010050114920714536L;

	private String status;
	
	private String address;
	
	private String netword;
	
	private String balance;
	
	private String receivedValue;
	
	private String pendingValue;
	
	private Integer totalTxs;
	
	private List<TransactionData> transactions;
	
	private String code;
	
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getReceivedValue() {
		return receivedValue;
	}

	public void setReceivedValue(String receivedValue) {
		this.receivedValue = receivedValue;
	}

	public String getPendingValue() {
		return pendingValue;
	}

	public void setPendingValue(String pendingValue) {
		this.pendingValue = pendingValue;
	}

	public Integer getTotalTxs() {
		return totalTxs;
	}

	public void setTotalTxs(Integer totalTxs) {
		this.totalTxs = totalTxs;
	}

	public List<TransactionData> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionData> transactions) {
		this.transactions = transactions;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNetword() {
		return netword;
	}

	public void setNetword(String netword) {
		this.netword = netword;
	}
	
	
}
