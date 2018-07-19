package com.btcetrade.bitcoin.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class PasswordGenerator {
	
	private static final String LOWERCASE="abcdefghijklmnopqrstuvwxyz";
	private static final String UPPERCASE="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String DIGIT="0123456789";
	private static final String PUNCHUATION="!@#$%&*()_+-=[]|,./?><";
	private boolean useLower;
	private boolean useUpperCase;
	private boolean useDigit;
	private boolean usePunchauation;
	
	private PasswordGenerator(){
		
		try {
			throw new Exception("Unsupported Password Generator Support Default Constructor");
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	
	public PasswordGenerator(PasswordGeneratorBuilder passwordBuilder){
		this.useUpperCase=passwordBuilder.useUpperCase;
		this.useLower=passwordBuilder.useLower;
		this.useDigit=passwordBuilder.useDigit;
		this.usePunchauation=passwordBuilder.usePunchauation;
	}
	
	public static class PasswordGeneratorBuilder{
		private boolean useLower;
		private boolean useUpperCase;
		private boolean useDigit;
		private boolean usePunchauation;
		
		public PasswordGeneratorBuilder(boolean useUpperCase,boolean useLowCase){
			this.useUpperCase=useUpperCase;
			this.useLower=useLowCase;
		}
		
		
		public PasswordGeneratorBuilder setUseDigit(boolean useDigit){
			this.useDigit=useDigit;
			return this;
		}
		
		public PasswordGeneratorBuilder setUsePunchauation(boolean usePunchauation){
			this.usePunchauation=usePunchauation;
			return this;
		}
		
		public PasswordGenerator build(){
			return new PasswordGenerator(this);
		}
	}
		public String generate(int length){
			if(length<=0){
				return "";
			}
			StringBuilder otpPassword=new  StringBuilder(length);
			Random random= new Random(System.nanoTime());
			List<String> charCategories= new ArrayList<String>(4);
			if(useLower){
				charCategories.add(LOWERCASE);
				
			}if (useUpperCase) {
				charCategories.add(UPPERCASE);
	        }
	        if (useDigit) {
	        	charCategories.add(DIGIT);
	        }
	        if (usePunchauation) {
	        	charCategories.add(PUNCHUATION);
	        }
	        for(int i=0;i<length;i++){
	        	String charCetageory=charCategories.get(random.nextInt(charCategories.size()));
	        	otpPassword.append(charCetageory);
	        }
			
			return new String(otpPassword.toString());
			
		}
		
	
	
}
