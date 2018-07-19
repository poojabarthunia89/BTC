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
@Table(name="table_feedback")
public class FeedBack implements Serializable{

	/**
	 * verify_id, send_id, verifyCode, verifynumber, email_id
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feed_id")
	private Integer feedBackId;
	@Column(name="feedback_point")
	private Double feedBackPoin;
	
	@ManyToOne
	@JoinColumn(name="verify_id")
	private VerifyPhoneNumber user;
	@Column(name="feedback_comment")
	private String comment;
	public Integer getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}
	public Double getFeedBackPoin() {
		return feedBackPoin;
	}
	public void setFeedBackPoin(Double feedBackPoin) {
		this.feedBackPoin = feedBackPoin;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public VerifyPhoneNumber getUser() {
		return user;
	}
	public void setUser(VerifyPhoneNumber user) {
		this.user = user;
	}

}
