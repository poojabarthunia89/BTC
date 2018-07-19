package com.btcetrade.bitcoin.config;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class BTCeTradeSMSGateWay {

	
	
	private static int counter=0;
	public static String getSendIdOfMessageCode(String mobileNumber,long verifyCode){
		String responceGenerate="";
		try {
			String apikey = "T6wvTQiWr02PX5nYshqPEw";
			String senderid = "TESTIN";
			String channel = "2";
			String DCS = "8";
			String flashsms = "1";
			String mobile = mobileNumber;
			String message = String.valueOf(verifyCode);
			String route = "13";
			 
			//https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=YOURAPIKEY&senderid=YOURSENDERID&channel=2&DCS=0&flashsms=0&number=91XXXXX&text=hello%20this%20is%20testing%20message&route=
			 
			 
			String requestUrl = "https://www.smsgatewayhub.com/api/mt/SendSMS?" +
			"APIKey=" + URLEncoder.encode(apikey, "UTF-8") +
			"&senderid=" + URLEncoder.encode(senderid, "UTF-8") +
			"&channel=" + URLEncoder.encode(channel, "UTF-8") +
			"&DCS=" + URLEncoder.encode(DCS, "UTF-8") +
			"&flashsms=" + URLEncoder.encode(flashsms, "UTF-8") +
			"&number=" + URLEncoder.encode(mobile, "UTF-8") +
			"&text=" + URLEncoder.encode(message, "UTF-8") +
			"&route=" + URLEncoder.encode(route, "UTF-8");
			 
			 
			 
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
			 
			System.out.println(uc.getResponseMessage());
			if(uc.getResponseMessage().equalsIgnoreCase("OK")){
				 responceGenerate="Send"+counter;
			}else{
				 responceGenerate="SMS Send dose not Successfuly For DIITTech channel";
			}
			 
			uc.disconnect();
			counter++;
			return responceGenerate;
			
		} catch(Exception ex) {
				System.out.println(ex.getMessage());
			 return "";
			}
	}
	public static void main(String[] args) {
		BTCeTradeSMSGateWay.getSendIdOfMessageCode("9166001205", 1205);
		
	}
}
