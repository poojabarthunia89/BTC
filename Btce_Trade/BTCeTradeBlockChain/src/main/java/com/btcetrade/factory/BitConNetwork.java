package com.btcetrade.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.btcetrade.bitcoin.model.CurrencyDetail;
import com.google.gson.JsonObject;

public class BitConNetwork implements CurrencyNetwork,BitCoinFunction{

		private String companyName;
	    private URL  url;
	    private HttpURLConnection httpUrlConnection;
	    private String requestURL="";
		
		 public BitConNetwork(String companyName) {
			 this.companyName=companyName(companyName);
			 
		}
		
	@Override
	public CurrencyDetail currencyDigitalType(String currencyType) throws Exception {
		CurrencyDetail currencyDetail= new CurrencyDetail();
		if(companyName.equalsIgnoreCase("ZEBPAY")){
			requestURL=BASE_URL_ZEBPAY;
			url=new URL(requestURL);
			httpUrlConnection=(HttpURLConnection)url.openConnection();
			BufferedReader reader=new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			StringBuffer response= new StringBuffer();
			String readLine="";
			while((readLine=reader.readLine())!=null){
				response.append(readLine);
			}
			
			JSONObject json= new JSONObject();
			currencyDetail.setMarket(json.getLong("market"));
			currencyDetail.setSell(json.getFloat("sell"));
			currencyDetail.setBuy(json.getFloat("buy"));
			currencyDetail.setVolume(json.getFloat("volume"));
			currencyDetail.setCurrencyType(json.getString("virtualCurrency"));
			
		}else if(companyName.equalsIgnoreCase("BLOCKCHAIN")){
			requestURL=BASE_URL_BLOCKCHAIN;
			url=new URL(requestURL);
			httpUrlConnection=(HttpURLConnection)url.openConnection();
			BufferedReader reader=new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			StringBuffer response= new StringBuffer();
			String readLine="";
			while((readLine=reader.readLine())!=null){
				response.append(readLine);
			}
			
			JSONObject json= new JSONObject();
			currencyDetail.setSell(json.getJSONObject("INR").getFloat("sell"));
			currencyDetail.setBuy(json.getJSONObject("INR").getFloat("buy"));
			currencyDetail.setSymbol(json.getJSONObject("INR").getString("symbol"));
		}else if(companyName.equalsIgnoreCase("BITRIX")){
			
		}
		return null;
	}

	@Override
	public String  companyName(String companyName) {
		String result="";
		if(companyName.equalsIgnoreCase("ZEB")){
			result="ZEBPAY";
		}else if(companyName.equalsIgnoreCase("BLOCK")){
			result="BLOCKCHAIN";
		}else if(companyName.equalsIgnoreCase("Spect")){
			result="SPECTROCOIN";
		}
		return result;
	}
	

}
