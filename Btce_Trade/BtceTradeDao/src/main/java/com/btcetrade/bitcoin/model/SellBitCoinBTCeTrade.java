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
@Table(name="sell_bitcoin")
public class SellBitCoinBTCeTrade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sale_id")
	private Integer saleId;
	
	@Column(name="salenetwork")
	private String network;
	
	@Column(name="sale_from_address")
	private String fromAddress;
	
	@Column(name="sale_to_address")
	private String toAddressReq;
	
	@Column(name="sale_amount_req")
	private Double amount_req;
	
	@Column(name="sale_secret_key")
	private String secreateKey;
	
	@Column(name="error_message")
	private String errorMessage;
	
	@Column(name="max_withdrawal_available")
	private String maxWithdrawalAvailable;
	
	@Column(name="minimum_balance_needed")
	private String minimumBalanceNeeded;
	
	@Column(name="estimated_network_fee")
	private String estimatedNetworkFee;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="verify_id")
	private VerifyPhoneNumber user;

	public Integer getSaleId() {
		return saleId;
	}




	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}




	public String getNetwork() {
		return network;
	}




	public void setNetwork(String network) {
		this.network = network;
	}




	public String getFromAddress() {
		return fromAddress;
	}




	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}




	public String getToAddressReq() {
		return toAddressReq;
	}




	public void setToAddressReq(String toAddressReq) {
		this.toAddressReq = toAddressReq;
	}




	public Double getAmount_req() {
		return amount_req;
	}




	public void setAmount_req(Double amount_req) {
		this.amount_req = amount_req;
	}




	public String getSecreateKey() {
		return secreateKey;
	}




	public void setSecreateKey(String secreateKey) {
		this.secreateKey = secreateKey;
	}




	public String getErrorMessage() {
		return errorMessage;
	}




	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public VerifyPhoneNumber getUser() {
		return user;
	}




	public void setUser(VerifyPhoneNumber user) {
		this.user = user;
	}




			
}
