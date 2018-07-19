package com.btcetrade.bitcoin.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.btcetrade.bitcoin.email.APIResponse;

public class ZebpayComparePrice {


	public static APIResponse zebpayBitCoinCopmareRate()throws Exception{
		String result="";
		APIResponse apiresponse= new APIResponse();
		String url="https://www.zebapi.com/api/v1/market/ticker/btc/inr";
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
		Double buy=(Double) json.get("buy");
		apiresponse.setZebpayBuy(String.valueOf(buy));;
		double sell=(double) json.get("sell");
		apiresponse.setZebPaySell(String.valueOf(sell));
		double volume=(double) json.get("volume");
		apiresponse.setZebPayVolume(String.valueOf(volume));
		System.out.println();
		in.close();
		return apiresponse;
	}
	
	public static void main(String[] args) throws Exception{
		zebpayBitCoinCopmareRate();
	}
	
 
	
}
