package com.btcetrade.bitcoin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="btc_country")
public class Country implements Serializable{
	/**
	 * country_id, country_name, country_code
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="country_name")
	private String countryName;
	@Column(name="country_code")
	private Integer countryCode;
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
