package com.btcetrade.bitcoin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="admin_user")
public class AdminUser implements Serializable{
	//admin_id, name, mobile_number, email_address, text_sing, text_password, image_path
	//adminid, name, mobile_number, email_address, address, passwords, text_sing, image_path
	private static final long serialVersionUID = 104653951701982414L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="adminid")
	private Integer adminId;
	
	@Column(name="name")
	private String name;

	@Column(name="mobile_number")
	private String address;
	
	@Column(name="email_address")
	private String emailId;
	
	@Column(name="address")
	private String mobileNumber;
	
	@Column(name="passwords")
	private String password;
	
	@Column(name="image_path")
	private String path;
	
	@Column(name="text_sing")
	private String textSignature;
	
	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTextSignature() {
		return textSignature;
	}

	public void setTextSignature(String textSignature) {
		this.textSignature = textSignature;
	}

	

}
