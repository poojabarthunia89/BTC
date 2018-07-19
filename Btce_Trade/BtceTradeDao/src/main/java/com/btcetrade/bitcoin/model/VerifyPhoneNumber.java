package com.btcetrade.bitcoin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="table_user")
public class VerifyPhoneNumber implements Serializable{
	/**verify_id, verifynumber, verifyCode, send_id
	 * VerifyNumber .java
	 */
	private static final long serialVersionUID = 104653951701982414L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="verify_id")
	private Integer verifyId;
	
	@Column(name="verifynumber", unique=true)
	private String verifyNumber;

	@Column(name="verifyCode")
	private String verifyCode;
	
	@Column(name="send_id")
	private String sendId;

	@Column(name="email_id")
	private String emailId;
	
	@Column(name="adhar_number")
	private String adharNumber;
	
	@Column(name="pan_number")
	private String panNumber;
	
	@Column(name="user_address")
	private String userAddress;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="secretkey")
	private String secretKey;
	
	@Column(name="admin_sing")
	private String adminSing;
	
	@Column(name="islogin")
	private Integer isLoginFlag;

	
	
	public Integer getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}

	public String getVerifyNumber() {
		return verifyNumber;
	}

	public void setVerifyNumber(String verifyNumber) {
		this.verifyNumber = verifyNumber;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getAdminSing() {
		return adminSing;
	}

	public void setAdminSing(String adminSing) {
		this.adminSing = adminSing;
	}

	public Integer getIsLoginFlag() {
		return isLoginFlag;
	}

	public void setIsLoginFlag(Integer isLoginFlag) {
		this.isLoginFlag = isLoginFlag;
	}
}
