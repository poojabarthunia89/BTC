package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class SaleBitCoin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2818719771891017467L;
	private String status;
	
	private String errorMessage;
	
	private String availableBalance;
	
	private String maxWithdrawalAvailable;
	
	private String minimumBalanceNeeded;
	
	private String estimatedNetworkFee;
	
	private String txid;
	
	private Double amountwidrawal;
	
	private String amountSent;
	
	private String networkFee;
	
	private String blockioFee;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getMaxWithdrawalAvailable() {
		return maxWithdrawalAvailable;
	}
	public void setMaxWithdrawalAvailable(String maxWithdrawalAvailable) {
		this.maxWithdrawalAvailable = maxWithdrawalAvailable;
	}
	public String getMinimumBalanceNeeded() {
		return minimumBalanceNeeded;
	}
	public void setMinimumBalanceNeeded(String minimumBalanceNeeded) {
		this.minimumBalanceNeeded = minimumBalanceNeeded;
	}
	public String getEstimatedNetworkFee() {
		return estimatedNetworkFee;
	}
	public void setEstimatedNetworkFee(String estimatedNetworkFee) {
		this.estimatedNetworkFee = estimatedNetworkFee;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public Double getAmountwidrawal() {
		return amountwidrawal;
	}
	public void setAmountwidrawal(Double amountwidrawal) {
		this.amountwidrawal = amountwidrawal;
	}
	public String getAmountSent() {
		return amountSent;
	}
	public void setAmountSent(String amountSent) {
		this.amountSent = amountSent;
	}
	public String getNetworkFee() {
		return networkFee;
	}
	public void setNetworkFee(String networkFee) {
		this.networkFee = networkFee;
	}
	public String getBlockioFee() {
		return blockioFee;
	}
	public void setBlockioFee(String blockioFee) {
		this.blockioFee = blockioFee;
	}
	
	
	
}
