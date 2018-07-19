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
@Table(name="btc_dashboard")
public class DashBoard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dash_id")
	private Integer dashBoardId;
	
	@Column(name="market")
	private Double market;
	
	@Column(name="buy")
	private Double buy;
	
	@Column(name="sell")
	private Double sell;

	@Column(name="dollarsell")
	private Double dollarsell;
	@Column(name="dollarbuy")
	private Double dollarbuy;
	
	@Column(name="inr")
	private String inr;

	@Column(name="virtualCurrency")
	private String virtualCurrency;
	
	@Column(name="volume")
	private Double volume;
	
	@ManyToOne
	@JoinColumn(name="wallet_id")
	private UserWallet userWallet;
	
	@ManyToOne
	@JoinColumn(name="verify_id")
	private VerifyPhoneNumber user;

	public Integer getDashBoardId() {
		return dashBoardId;
	}

	public void setDashBoardId(Integer dashBoardId) {
		this.dashBoardId = dashBoardId;
	}

	public Double getMarket() {
		return market;
	}

	public void setMarket(Double market) {
		this.market = market;
	}

	public Double getBuy() {
		return buy;
	}

	public void setBuy(Double buy) {
		this.buy = buy;
	}

	public Double getSell() {
		return sell;
	}

	public void setSell(Double sell) {
		this.sell = sell;
	}

	

	
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public UserWallet getUserWallet() {
		return userWallet;
	}

	public void setUserWallet(UserWallet userWallet) {
		this.userWallet = userWallet;
	}

	public Double getDollarsell() {
		return dollarsell;
	}

	public void setDollarsell(Double dollarsell) {
		this.dollarsell = dollarsell;
	}

	public Double getDollarbuy() {
		return dollarbuy;
	}

	public void setDollarbuy(Double dollarbuy) {
		this.dollarbuy = dollarbuy;
	}

	public VerifyPhoneNumber getUser() {
		return user;
	}

	public void setUser(VerifyPhoneNumber user) {
		this.user = user;
	}

	public String getInr() {
		return inr;
	}

	public void setInr(String inr) {
		this.inr = inr;
	}

	public String getVirtualCurrency() {
		return virtualCurrency;
	}

	public void setVirtualCurrency(String virtualCurrency) {
		this.virtualCurrency = virtualCurrency;
	}
	
	
}
