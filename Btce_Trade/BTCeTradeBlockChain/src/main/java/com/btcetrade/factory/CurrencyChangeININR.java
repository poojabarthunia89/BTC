package com.btcetrade.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class CurrencyChangeININR implements ICurrency{

	
	public float getCurrencyINDollar(){
		try{
		HttpURLConnection urlConnection;
		URL url=new URL(URL_BASE); 
		urlConnection=(HttpURLConnection)url.openConnection();
		urlConnection.setRequestProperty("User-Agent", "Mozilla/4.76");
		StringBuffer response= new StringBuffer();
		BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String readLine="";
		while((readLine=reader.readLine())!=null){
			response.append(readLine);
		}
		
		
		JSONObject json= new JSONObject(response.toString());
		
		float dollarValue=json.getJSONObject("quotes").getFloat("USDINR");
		System.out.println(dollarValue);
		 float btcRate = (float) Math.round(dollarValue * 100) / 100;
		 System.out.println(btcRate);
		 return btcRate;
		}catch(Exception e){
		
			e.printStackTrace();
			return (float)0.0;
		}
		
		}
		
	
	
	@Override
	public BigDecimal changeCurrency(float currency) {
		try{
		
		
		float dollar=(float)63.82;
		float rupees=currency*dollar;
		BigDecimal rupeesResult= new BigDecimal(Double.valueOf(rupees));
		
		
		return rupeesResult;
		}catch(Exception e){
			e.printStackTrace();
			return new BigDecimal(Integer.valueOf(0));
		}
	}

}
