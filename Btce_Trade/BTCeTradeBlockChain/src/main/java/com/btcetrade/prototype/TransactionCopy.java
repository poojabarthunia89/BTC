package com.btcetrade.prototype;

public class TransactionCopy implements TransactionPrototype{

	private String transactionId;
	private String addressClient;
	private float balance;
	private String addressMerchant;
	
	public TransactionCopy(String transactionId,String addressClient,String addressMerchant,float balance){
		this();
		this.addressClient=addressClient;
		this.transactionId=transactionId;
		this.balance=balance;
		this.addressMerchant=addressMerchant;
	}
	
	public TransactionCopy(){
		System.out.println("Client Copy");
		System.out.println("TransactionId"+"\t"+"Transfer"+"\t"+addressMerchant+"\t"+"To"+"Address Client"+"Successfully");
		System.out.println("-------------------------------------------");
		System.out.println("Merchant Copy");
		System.out.println("TransactionId"+"\t"+"Transfer"+"\t"+addressMerchant+"\t"+"To"+"Address Client"+"Successfully");
		
	}
	
	public void showRecord(){
        System.out.println(transactionId+"\t"+balance+"\t"+addressMerchant+"\t"+addressClient+"\t"+"Success");  
	}

	@Override
	public TransactionPrototype getClone() {
		return new TransactionCopy(transactionId, addressClient, addressMerchant, balance);
	}
	
	
	
	
}
