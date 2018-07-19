package com.btcetrade.builder;

public class Transaction {
       /* Required Parameter*/
	   private String adharCardNumber;
     
	   private String panCardNumber;
       
	   private String emailId;
       
	   private String bankAccountNumber;
	   
	   private String address;
	   
	   private String transactionId;
	   /*optional Parameter*/
	   private String passportNumber;
	   
	   private String voterId;
	   
	   
	   
	   public String getAdharCardNumber() {
		return adharCardNumber;
	}



	public String getPanCardNumber() {
		return panCardNumber;
	}



	public String getEmailId() {
		return emailId;
	}



	public String getBankAccountNumber() {
		return bankAccountNumber;
	}



	public String getAddress() {
		return address;
	}



	public String getTransactionId() {
		return transactionId;
	}



	public String getPassportNumber() {
		return passportNumber;
	}



	public String getVoterId() {
		return voterId;
	}

	private Transaction(TransactionBuilder builder){
		this.address=builder.address;
		this.adharCardNumber=builder.adharCardNumber;
		this.emailId=builder.emailId;
		this.panCardNumber=builder.panCardNumber;
		this.bankAccountNumber=builder.bankAccountNumber;
		this.passportNumber=builder.passportNumber;
		this.transactionId=builder.transactionId;
	}

	public static class TransactionBuilder {
		   private String adharCardNumber;
		     
		   private String panCardNumber;
	       
		   private String emailId;
	       
		   private String bankAccountNumber;
		   
		   private String address;
		   
		   private String transactionId;
		   /*optional Parameter*/
		   private String passportNumber;
		   
		   private String voterId;
		   public TransactionBuilder(String adharCardNumber,String emailId,String bankAccountNumber,String address,String transactionId){
			   this.address=address;
			   this.adharCardNumber=adharCardNumber;
			   this.emailId=emailId;
			   this.bankAccountNumber=bankAccountNumber;
			   this.transactionId=transactionId;
		   }
		   
		   public TransactionBuilder setPassportNumber(String passportNumber){
			   this.passportNumber=passportNumber;
			   return this;
		   }
		   public TransactionBuilder setVoterId(String voterId){
			   this.voterId=voterId;
			   return this;
		   }
		   public Transaction build(){
			   return new Transaction(this);
		   }
		   
	   }
}
