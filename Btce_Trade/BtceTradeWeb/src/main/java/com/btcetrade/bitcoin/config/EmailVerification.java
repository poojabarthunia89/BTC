package com.btcetrade.bitcoin.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;



public class EmailVerification {

	public static String emailVerify(String email)throws Exception{
		String result="";
		String url="http://apilayer.net/api/check?access_key=e46a194006e64e33a3c03858d48f65f1&email="+email+"&smtp=1&format=1";
		URL urlObj= new URL(url);
		HttpURLConnection urlConnection=(HttpURLConnection)urlObj.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setRequestProperty("User-Agent", "Mozilla/17.0");
		BufferedReader in=new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); 
		String inputLine="";
		StringBuffer response= new StringBuffer();
		while((inputLine=in.readLine())!=null){
			
			response.append(inputLine);
		}
		JSONObject json= new JSONObject(response.toString());
		boolean smtpflag=(boolean) json.get("smtp_check");
		if(smtpflag){
			result="Valid";
			
		}else{
			result="InValid";
		}
		in.close();
		return result;
	}
	
	
}
