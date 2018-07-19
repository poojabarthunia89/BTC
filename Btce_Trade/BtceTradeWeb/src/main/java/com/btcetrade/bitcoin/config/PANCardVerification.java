package com.btcetrade.bitcoin.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PANCardVerification {

	
	
	public static String validatePANCardNumber(String pancardNumber){
		String result="";
		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = pattern.matcher(pancardNumber);

		if (matcher.matches()) {
			result="Valid";
			}else{
				result="InValid";
			}
		return result;
	}
	
	

}
