package com.btcetrade.bitcoin.config;

import java.util.Random;

public class RandomeCodeGenerator{
	
	private static long verifyCode;
	
	private static  Random random;
	
	public static long generateRandomNumber(){
		random=new Random();
		verifyCode =  random.nextInt(10000);
		return verifyCode;
	}

}
