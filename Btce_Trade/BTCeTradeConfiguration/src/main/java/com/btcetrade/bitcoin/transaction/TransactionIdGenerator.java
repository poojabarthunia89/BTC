package com.btcetrade.bitcoin.transaction;

import java.util.Random;

public final class TransactionIdGenerator {

	
	private final static String UPPER_CASE="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String LOWER_CASE="abcdefghijklmnopqrstuvwxyz";
	private final static String DIGGIT="0123456789";
	
	public static final  String generateTransactionId(int length,String txType,String txStatus){
		Random random= new Random();
		
		String transactionString="";
		char[] transacId=new char[length];
		for(int i=0;i<length;i++){
			if(txType.equalsIgnoreCase("BTC")){
				if(txStatus.equalsIgnoreCase("SC")){
				if(i>=0 && i<=1){
					transactionString=UPPER_CASE+LOWER_CASE;
				}else if(i>1 && i<=2){
					transactionString="SB";
				}else if(i>3 && i<length){
					transactionString=DIGGIT;
				}	
				}else if(txStatus.equalsIgnoreCase("FC")){
					if(i>=0 && i<=1){
						transactionString=UPPER_CASE+LOWER_CASE;
					}else if(i>1 && i<=2){
						transactionString="FB";
					}else if(i>3 && i<length){
						transactionString=DIGGIT;
					}	
				}
			}else{
			if(i>=0 && i<=3){
				transactionString=UPPER_CASE+LOWER_CASE;
			}else if(i>3 && i<length){
				transactionString=DIGGIT;
			}
			}
			transacId[i]=transactionString.charAt(random.nextInt(transactionString.length()));
			
		}
		return String.valueOf(transacId);
	}
	
	
	
}
