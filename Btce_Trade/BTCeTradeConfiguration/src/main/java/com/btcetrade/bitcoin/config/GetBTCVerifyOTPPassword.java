package com.btcetrade.bitcoin.config;

import java.util.Random;

public class GetBTCVerifyOTPPassword {
 public static void main(String[] args) {
	PasswordGenerator passwordGenerate= new PasswordGenerator.PasswordGeneratorBuilder(true, true)
	.setUseDigit(true)
	.setUsePunchauation(true).build();
	String password=getStrongPAssword(4);
	System.out.println(password);
	String otp=getGenerateOTP(4);
	System.out.println(otp);
}
 
 
 
 public static String getGenerateOTP(int len){
	 System.out.println("Your OTP password");
	 String numbers = "0123456789";
	 Random random= new Random(System.nanoTime());
	 char[] password= new char[len];
	 for(int i=0;i<len;i++){
		 password[i]=numbers.charAt(random.nextInt(numbers.length()));
	 }
	 
	 System.out.println(password.length);
	 if(password.length==4){
		 return String.valueOf(password); 
	 }else{
		 return "0000";
	 }
	 
	 
 }
 
 static String getStrongPAssword(int len)
 {
     System.out.println("Generating password using random() : ");
     System.out.print("Your new password is : ");

     
     String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     String Small_chars = "abcdefghijklmnopqrstuvwxyz";
     String numbers = "0123456789";
             String symbols = "!@#$%^&*_=+-/.?<>)";


     String values = Capital_chars + Small_chars +
                     numbers + symbols;

     // Using random method
     Random rndm_method = new Random();

     char[] password = new char[len];

     for (int i = 0; i < len; i++)
     {
         // Use of charAt() method : to get character value
         // Use of nextInt() as it is scanning the value as int
         password[i] =
           values.charAt(rndm_method.nextInt(values.length()));

     }
     char[] finalPassword=password;
     return String.valueOf(finalPassword);
 }
 
}
