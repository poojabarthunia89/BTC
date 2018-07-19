package com.btcetrade.bitcoin.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdharCardValidate {
public static String validateAdharNumber(String adharNumber){
	String result="";
	Pattern aadhaarPattern = Pattern.compile("^[2-9]{1}[0-9]{11}$"); 
	Matcher match=aadhaarPattern.matcher(adharNumber);
	if(match.matches()){
		result="Valid";
	}else{
		result="INValid";
	}
	return result;
}




}
