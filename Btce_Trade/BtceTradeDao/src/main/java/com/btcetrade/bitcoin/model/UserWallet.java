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
@Table(name="user_wallet")
public class UserWallet implements Serializable {

	/**
	 * UserWallet
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="wallet_id")
	private Integer walletId;
	
	@Column(name="wallet_address")
	private String walletAddress;
	
	@Column(name="balance")
	private String balance;
	
	@ManyToOne
	@JoinColumn(name="verify_id")
	private VerifyPhoneNumber user;
	
	public Integer getWalletId() {
		return walletId;
	}
	
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	
	public String getWalletAddress() {
		return walletAddress;
	}
	
	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}
	
	public String getBalance() {
		return balance;
	}
	
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public VerifyPhoneNumber getUser() {
		return user;
	}
	
	public void setUser(VerifyPhoneNumber user) {
		this.user = user;
	}
}

