package com.btcetrade.bitcoin;

import static com.jayway.restassured.RestAssured.get;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.btcetrade.bitcoin.model.AddressSpent;
import com.btcetrade.bitcoin.model.BTCRateOfCompanies;
import com.btcetrade.bitcoin.model.BalanceData;
import com.btcetrade.bitcoin.model.BalanceForAddress;
import com.btcetrade.bitcoin.model.BitCoinRate;
import com.btcetrade.bitcoin.model.DashBoardUser;
import com.btcetrade.bitcoin.model.Data;
import com.btcetrade.bitcoin.model.ReceivedBitCoin;
import com.btcetrade.bitcoin.model.ReceivedData;
import com.btcetrade.bitcoin.model.ReceivedTransaction;
import com.btcetrade.bitcoin.model.ReceviedTransactionData;
import com.btcetrade.bitcoin.model.SaleBitCoin;
import com.btcetrade.bitcoin.model.SentTransaction;
import com.btcetrade.bitcoin.model.SentTransactionData;
import com.btcetrade.bitcoin.model.TransactionData;
import com.btcetrade.bitcoin.model.UnSentTransaction;
import com.btcetrade.bitcoin.model.UserAddress;
import com.btcetrade.bitcoin.model.ValidAddress;
import com.btcetrade.factory.CurrencyChangeFactory;
import com.btcetrade.factory.CurrencyChangeININR;
import com.google.gson.JsonObject;
import com.jayway.restassured.response.Response;

public class BlockChainApiImpl implements BlockChainApi{
	
	
	private HttpURLConnection urlConnection;
	private URL url;
	
	public AddressSpent getAddressSpent(String address,String currencyNetwork){
		try{
		String requestURL=BASE_URL.concat(GET_ADDRESS_SPENT).concat("/").concat(currencyNetwork).concat("/").concat(address);
		url=new URL(requestURL);
		// HttpURLConnection httpcon = (HttpURLConnection) url.openConnection(); 

		urlConnection=(HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.addRequestProperty("User-Agent", "Mozilla/4.76"); 
		BufferedReader in=new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); 
		String inputLine="";
		StringBuffer response= new StringBuffer();
		while((inputLine=in.readLine())!=null){
			
			response.append(inputLine);
		}
		JSONObject json= new JSONObject(response.toString());
		AddressSpent adreesSpent= new AddressSpent();
		Data dataObject= new Data();
		JSONObject data= new JSONObject(json.getJSONObject("data"));
		System.out.println(json.getJSONObject("data"));
		JSONObject dataObj = json.getJSONObject("data");
		
		String network= dataObj.getString("network");
		System.out.println(network);
		
		
		String addres= dataObj.getString("address");
		String confirmSentValue= dataObj.getString("confirmed_sent_value");
		String unConfirmSentValue= dataObj.getString("unconfirmed_sent_value");
		dataObject.setAddress(addres);
		dataObject.setNetwork(network);
		dataObject.setConfirmSentValue(confirmSentValue);
		dataObject.setUnConfirmSentValue(unConfirmSentValue);
		String status=(String) json.get("status");
		adreesSpent.setStatus(status);
		adreesSpent.setData(dataObject);
		return adreesSpent;
		
		}catch(Exception e){
			e.printStackTrace();
			return new AddressSpent();
		}
	}
	
	public BitCoinRate getBTCRate(String netWorkCurrency,String apiCurrency,String currencyType){
		try{
		CurrencyChangeININR curencyIndia= (CurrencyChangeININR) CurrencyChangeFactory.getCurrecyININRORUSD("INR");
		float rate=curencyIndia.getCurrencyINDollar();
		List<BTCRateOfCompanies> listOfRate= new ArrayList<BTCRateOfCompanies>();
		String requestURL=BASE_URL.concat("get_price").concat("/").concat(netWorkCurrency).
				   concat("/").concat(apiCurrency);
		System.out.println(requestURL);
		BitCoinRate bitCoinRate= new BitCoinRate();
		url= new URL(requestURL);
		urlConnection=(HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setRequestProperty("User-Agent", "Mozilla/4.76");
		BufferedReader in=new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); 
		String inputLine="";
		StringBuffer response= new StringBuffer();
		while((inputLine=in.readLine())!=null){
			
			response.append(inputLine);
		}
		
	
		JSONObject json=new JSONObject(response.toString());
		System.out.println(json.getJSONObject("data").getJSONArray("prices").length());
		for(int i=0;i<json.getJSONObject("data").getJSONArray("prices").length();i++){
			BTCRateOfCompanies btcRateCompany= new BTCRateOfCompanies();
			btcRateCompany.setExchangeName(json.getJSONObject("data").getJSONArray("prices").getJSONObject(i).getString("exchange"));
			System.out.println(btcRateCompany.getExchangeName());
			btcRateCompany.setCurrency(json.getJSONObject("data").getJSONArray("prices").getJSONObject(i).getString("price_base"));
			btcRateCompany.setPrice((float)json.getJSONObject("data").getJSONArray("prices").getJSONObject(i).getFloat("price"));
			btcRateCompany.setTime(json.getJSONObject("data").getJSONArray("prices").getJSONObject(i).getInt("time"));
			listOfRate.add(btcRateCompany);
		}
		Integer btclength=listOfRate.size();
		float sum=(float)0.0;
		float averagePrice=(float)0.0;
		
		for(BTCRateOfCompanies btcCompany:listOfRate){
			sum=(float)sum+btcCompany.getPrice();
			
		}
		System.out.println(sum);
		averagePrice=sum/btclength;
		System.out.println(averagePrice);
		float finalPRiceUSD=averagePrice;
		float finalPrice=averagePrice*rate;
		System.out.println("INR Rate"+finalPrice+"RS");
		float buyRate=finalPrice+(float)100000.0;
		float buyRateUSD=finalPRiceUSD+(float)10000.0;
		float saleRateUSD=finalPRiceUSD;
		
		float saleRate=(float)finalPrice;
		System.out.println("BuyRate"+buyRate+"Sale Rate"+saleRate);
		bitCoinRate.setBuyBitCoinRate(buyRate);
		bitCoinRate.setSaleBitCoinRate(saleRate);
		bitCoinRate.setBuyBitCoinRateUSD(buyRateUSD);
		bitCoinRate.setSaleBitCoinRateUSD(saleRateUSD);
		return bitCoinRate;
		}catch(Exception e){
			e.printStackTrace();
			return new BitCoinRate();
		}
		
		
		
		
	}
	public BalanceForAddress getAddressBalance(String address,String networkCurrency,int minimumConfirmation)throws Exception{
		String requestURL=BASE_URL.concat(GET_ADDRESS_BALANCE).concat("/").concat(networkCurrency).
						   concat("/").concat(address).concat("/")
						   .concat(String.valueOf(minimumConfirmation));
		try{
		url= new URL(requestURL);
		urlConnection=(HttpURLConnection)url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setRequestProperty("User-Agent", "Mozilla/4.76");
		BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String readLine="";
		StringBuffer response= new StringBuffer();
		while((readLine=reader.readLine())!=null){
			response.append(readLine);
		}
		JSONObject json=new JSONObject(response.toString());
	    BalanceForAddress balance= new BalanceForAddress();
	    String status=json.getString("status");
	    JSONObject dataObject=json.getJSONObject("data");
	    String network=dataObject.getString("network");
	    String adress=dataObject.getString("address");
	    String confirmBal=dataObject.getString("confirmed_balance");
	    String unconfirmBal=dataObject.getString("unconfirmed_balance");
	    BalanceData data= new BalanceData();
	    data.setNetwork(network);
	    data.setAddress(adress);
	    data.setConfirmedBalance(confirmBal);
	    data.setUnconfirmed_balance(unconfirmBal);
	    balance.setStatus(status);
	    balance.setBlance(data);
	    return balance;
		}catch(Exception e){
			e.printStackTrace();
			return new BalanceForAddress();
		}
		
	}
	
	public ReceivedBitCoin getRecivedBitcoin(String address,String currencyNetwork){
		String requestURL=BASE_URL.concat(GET_ADDRESS_RECEIVED_BALANCE).concat("/").concat(currencyNetwork).concat("/").concat(address);
		
		try{
			url=new URL(requestURL);
			urlConnection=(HttpURLConnection)url.openConnection();
			BufferedReader readStream=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			StringBuffer response= new StringBuffer();
			String inputLine="";
			while((inputLine=readStream.readLine())!=null){
				response.append(inputLine);
			}
			JSONObject receivedObject= new JSONObject(response.toString());
			ReceivedBitCoin bitconReceivedAddress= new ReceivedBitCoin();
			bitconReceivedAddress.setStatus(receivedObject.getString("status"));
			JSONObject dataObject=receivedObject.getJSONObject("data");
			ReceivedData received= new ReceivedData();
			received.setNetwork(dataObject.getString("network"));
			received.setAddress(dataObject.getString("address"));
			received.setConfirmedReceivedValue(dataObject.getString("confirmed_received_value"));
			received.setUnconfirmedReceivedValue(dataObject.getString("unconfirmed_received_value"));
			
			bitconReceivedAddress.setReceived(received);
			return bitconReceivedAddress;
		}catch(Exception e){
			e.printStackTrace();
			return new ReceivedBitCoin();
		}
		
	}
	
	public UnSentTransaction unsendTransaction(String address,String network,String transactionId){
		String requestURL=BASE_URL.concat(GET_ADDRESS_UN_SPENT_TRANSACTION).concat("/").concat(network).concat("/")
				.concat(address).concat("/").concat(transactionId);
		try{
			url= new URL(requestURL);
			urlConnection=(HttpURLConnection)url.openConnection();
			BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine="";
			StringBuffer response=new StringBuffer();
			while((inputLine=reader.readLine())!=null){
					response.append(inputLine);
			}
			JSONObject json= new JSONObject(response.toString());
			UnSentTransaction unsentTransaction= new UnSentTransaction();
			unsentTransaction.setStatus(json.getString("status"));
			JSONObject data=json.getJSONObject("data");
			unsentTransaction.setNetwork(data.getString("network"));
			unsentTransaction.setAdress(data.getString("address"));
			JSONArray strarray=json.getJSONArray("txs");
			List<TransactionData> txnList= new ArrayList<TransactionData>();
			for(int i=0;i<strarray.length();i++){
				TransactionData txn= new TransactionData();
				txn.setTnxId(strarray.getJSONObject(i).getString("txid"));
				txn.setOutputNumber(strarray.getJSONObject(i).getString("output_no"));
				txn.setScriptASM(strarray.getJSONObject(i).getString("script_asm"));
				txn.setScriptHEX(strarray.getJSONObject(i).getString("script_hex"));
				txn.setValue(strarray.getJSONObject(i).getString("value"));
				txn.setConfirmations(strarray.getJSONObject(i).getString("confirmations"));
				txn.setTime(strarray.getJSONObject(i).getString("time"));
				txnList.add(txn);
			}
			/*Object[] txnId=txnList.toArray();
			TransactionData[] transactionIds=(TransactionData[])txnId;*/
			unsentTransaction.setTxnData(txnList);
			return unsentTransaction;
		}catch(Exception e){
			e.printStackTrace();
			return new UnSentTransaction();
		}
	}
		public ReceivedTransaction receivedTransaction(String address,String network,String transactionId){
			String requestURL=BASE_URL.concat(GET_ADDRESS_UN_SPENT_TRANSACTION).concat("/").concat(network).concat("/")
					.concat(address).concat("/").concat(transactionId);
			try{
				url= new URL(requestURL);
				urlConnection=(HttpURLConnection)url.openConnection();
				BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				String inputLine="";
				StringBuffer response=new StringBuffer();
				while((inputLine=reader.readLine())!=null){
						response.append(inputLine);
				}
				JSONObject json= new JSONObject(response.toString());
				ReceivedTransaction receviedTransaction= new ReceivedTransaction();
				receviedTransaction.setStatus(json.getString("status"));
				JSONObject data=json.getJSONObject("data");
				receviedTransaction.setNetwork(data.getString("network"));
				receviedTransaction.setAdress(data.getString("address"));
				JSONArray strarray=json.getJSONArray("txs");
				List<ReceviedTransactionData> txnList= new ArrayList<ReceviedTransactionData>();
				for(int i=0;i<strarray.length();i++){
					ReceviedTransactionData txn= new ReceviedTransactionData();
					txn.setTnxId(strarray.getJSONObject(i).getString("txid"));
					txn.setOutputNumber(strarray.getJSONObject(i).getString("output_no"));
					txn.setScriptASM(strarray.getJSONObject(i).getString("script_asm"));
					txn.setScriptHEX(strarray.getJSONObject(i).getString("script_hex"));
					txn.setValue(strarray.getJSONObject(i).getString("value"));
					txn.setConfirmations(strarray.getJSONObject(i).getString("confirmations"));
					txn.setTime(strarray.getJSONObject(i).getString("time"));
					txnList.add(txn);
				}
				receviedTransaction.setTxnData(txnList);
				return receviedTransaction;
			}catch(Exception e){
				e.printStackTrace();
				return new ReceivedTransaction();
			}
		}
			
			public SentTransaction sentTransaction(String address,String network,String transactionId){
				String requestURL=BASE_URL.concat(GET_ADDRESS_UN_SPENT_TRANSACTION).concat("/").concat(network).concat("/")
						.concat(address).concat("/").concat(transactionId);
				try{
					url= new URL(requestURL);
					urlConnection=(HttpURLConnection)url.openConnection();
					BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					String inputLine="";
					StringBuffer response=new StringBuffer();
					while((inputLine=reader.readLine())!=null){
							response.append(inputLine);
					}
					JSONObject json= new JSONObject(response.toString());
					SentTransaction sentTransaction= new SentTransaction();
					sentTransaction.setStatus(json.getString("status"));
					JSONObject data=json.getJSONObject("data");
					sentTransaction.setNetwork(data.getString("network"));
					sentTransaction.setAdress(data.getString("address"));
					JSONArray strarray=json.getJSONArray("txs");
					List<SentTransactionData> txnList= new ArrayList<SentTransactionData>();
					for(int i=0;i<strarray.length();i++){
						SentTransactionData txn= new SentTransactionData();
						txn.setTnxId(strarray.getJSONObject(i).getString("txid"));
						txn.setOutputNumber(strarray.getJSONObject(i).getString("output_no"));
						txn.setScriptASM(strarray.getJSONObject(i).getString("script_asm"));
						txn.setScriptHEX(strarray.getJSONObject(i).getString("script_hex"));
						txn.setValue(strarray.getJSONObject(i).getString("value"));
						txn.setConfirmations(strarray.getJSONObject(i).getString("confirmations"));
						txn.setTime(strarray.getJSONObject(i).getString("time"));
						txnList.add(txn);
					}
					sentTransaction.setTxnData(txnList);
					return sentTransaction;
				}catch(Exception e){
					e.printStackTrace();
					return new SentTransaction();
				}
	}
			
			   
						
			public ValidAddress isValidAddress(String address,String currencyNetwork){
				Boolean result=false;
				String requestURL=BASE_URL.concat(IS_VALID_ADDRESS).concat("/").concat(currencyNetwork).concat("/").concat(address);
				
				try{
				url= new URL(requestURL);
				urlConnection=(HttpURLConnection)url.openConnection();
				urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
				BufferedReader reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				 StringBuffer  response= new StringBuffer();
				 String readLine="";
				 while((readLine=reader.readLine())!=null){
					 response.append(readLine);
				 }
				 
				 JSONObject json= new JSONObject(response.toString());
				 ValidAddress validAddress= new ValidAddress();
				 validAddress.setStatus(json.getString("status"));
				 validAddress.setAddress(json.getJSONObject("data").getString("address"));
				 validAddress.setNetwork(json.getJSONObject("data").getString("network"));
				 validAddress.setIsValid(json.getJSONObject("data").getBoolean("is_valid"));
				 return validAddress;
				}catch(Exception e){
					e.printStackTrace();
					 result=false;
					 return new ValidAddress();
				}
				
				
				
				
				
				
			}
			
			public UserAddress getNewAddress(String APIKey){
				UserAddress userAddress= new UserAddress();
				String requestURL=BASE_URL_COIN_BASE.concat(CREATE_NEW_ADDRESS).concat(APIKey);
				try{
					url=new URL(requestURL);
					urlConnection=(HttpURLConnection)url.openConnection();
					urlConnection.setRequestProperty("User-Agent", "Mozilla/4.76");
					BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					String readLine="";
					StringBuffer response= new StringBuffer();
					 while((readLine=reader.readLine())!=null){
						 response.append(readLine);
					 }
					 JSONObject json= new JSONObject(response.toString());
					 userAddress.setStatus(json.getString("status"));
					 userAddress.setUserId(json.getJSONObject("data").getInt("user_id"));
					 userAddress.setAddress(json.getJSONObject("data").getString("address"));
					 userAddress.setLabel(json.getJSONObject("data").getString("label"));
					 userAddress.setNetwork(json.getJSONObject("data").getString("network")); 
					 return userAddress;
				}catch(Exception e){
					e.printStackTrace();
					return new UserAddress();
				}
				
			}
			
			public SaleBitCoin saleBitCoinAddress(String applicationKEY,String fromAddress,String toAddress,String network,double amount,String secretPIN){
				SaleBitCoin saleBitCoin= new SaleBitCoin();
				System.out.println("Intermediate COst double..:"+new DecimalFormat(".######").format(amount));
				System.out.println(amount);
				try{
				
					String requestURL=BASE_URL_COIN_BASE.concat(SALE_BIT_COIN).concat(applicationKEY)
							.concat("&amounts=").concat(String.valueOf(new DecimalFormat("#.######").format(amount)))
							.concat("&from_addresses=")
							.concat(fromAddress)
							.concat("&to_addresses=")
							.concat(toAddress)
							.concat("&pin=")
							.concat(secretPIN);
					System.out.println("###############");
						    System.out.println(requestURL);
						    Response resp = get(requestURL);
						    JSONObject json=new JSONObject(resp.asString());
						    System.out.println(json);
						    /*	System.out.println(json.getJSONObject("data").getString("amount_sent"));
						    	System.out.println(json.getJSONObject("data").getString("network_fee"));
						    	System.out.println(json.getJSONObject("data").getString("blockio_fee"));
						    	System.out.println(json.getJSONObject("data").getString("error_message"));
						    */	
						    	if(json.getString("status").equalsIgnoreCase("success")){
						    		saleBitCoin.setAmountSent(json.getJSONObject("data").getString("amount_sent"));
						    		saleBitCoin.setTxid(json.getJSONObject("data").getString("txid"));
						    		saleBitCoin.setAmountwidrawal(json.getJSONObject("data").getDouble("amount_withdrawn"));
						    		saleBitCoin.setNetworkFee(json.getJSONObject("data").getString("network_fee"));
						    		saleBitCoin.setBlockioFee(json.getJSONObject("data").getString("blockio_fee"));
						    		
						    	}else{
						    		saleBitCoin.setStatus(json.getString("status"));
						    		saleBitCoin.setAvailableBalance(json.getJSONObject("data").getString("available_balance"));
						    		saleBitCoin.setEstimatedNetworkFee(json.getJSONObject("data").getString("estimated_network_fee"));
						    		saleBitCoin.setErrorMessage(json.getJSONObject("data").getString("error_message"));
						    		saleBitCoin.setMaxWithdrawalAvailable(json.getJSONObject("data").getString("max_withdrawal_available"));
						    		saleBitCoin.setMinimumBalanceNeeded(json.getJSONObject("data").getString("minimum_balance_needed"));
						    	}
						    		
							    /*if(json.getString("status").equalsIgnoreCase("success")){
						    	System.out.println(json.getString("status"));
							    saleBitCoin.setTxid(json.getJSONObject("data").getString("txid"));
						    	saleBitCoin.setAmountwidrawal(json.getJSONObject("data").getDouble("amount_withdrawn"));
						    	saleBitCoin.setAmountSent(new DecimalFormat("#.######").format(json.getJSONObject("data").getDouble("amount_sent")));
						    	saleBitCoin.setAmountSent(json.getJSONObject("data").getDouble("network_fee"));
						    	saleBitCoin.setAmountSent(json.getJSONObject("data").getDouble("blockio_fee"));
							    }else{
							    	saleBitCoin.setStatus(json.getString("status"));
								    saleBitCoin.setAvailableBalance(json.getJSONObject("data").getDouble("available_balance"));
								    saleBitCoin.setEstimatedNetworkFee(json.getJSONObject("data").getDouble("estimated_network_fee"));
								    saleBitCoin.setErrorMessage(json.getJSONObject("data").getString("error_message"));
								    saleBitCoin.setMaxWithdrawalAvailable(json.getJSONObject("data").getDouble("max_withdrawal_available"));
								    saleBitCoin.setMinimumBalanceNeeded(json.getJSONObject("data").getDouble("minimum_balance_needed"));	
							    }*/
         	              return saleBitCoin;
				}catch(Exception e){
					e.printStackTrace();
					return new SaleBitCoin();
				}
								
			}
			
			
			public DashBoardUser getDashBoardUser(String address,String currencyNetwork){
				DashBoardUser dashBoard= new DashBoardUser();
				String requestURL=BASE_URL.concat(DASHBOARD).concat("/").concat(currencyNetwork).concat("/").concat(address);
				
				try{
					url=new URL(requestURL);
					urlConnection=(HttpURLConnection)url.openConnection();
					urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
				    BufferedReader reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				    InputStream error = urlConnection.getErrorStream();
				    System.out.println(error);
				    StringBuffer  response= new StringBuffer();
				    String readLine="";
				    while((readLine=reader.readLine())!=null){
				    	System.out.println(readLine);
				    	response.append(readLine);
				    }
				 JSONObject json= new JSONObject(response.toString());
				 dashBoard.setStatus(json.getString("status"));
				 dashBoard.setAddress(json.getJSONObject("data").getString("address"));
				 dashBoard.setNetword(json.getJSONObject("data").getString("network"));
				 dashBoard.setBalance(json.getJSONObject("data").getString("balance"));
				 dashBoard.setReceivedValue(json.getJSONObject("data").getString("received_value"));
				 dashBoard.setPendingValue(json.getJSONObject("data").getString("pending_value"));
				 dashBoard.setTotalTxs(json.getJSONObject("data").getInt("total_txs"));
				 /*JSONArray strarray=json.getJSONArray("txs");*/
				/* List<TransactionData> txnList= new ArrayList<TransactionData>();
					for(int i=0;i<strarray.length();i++){
						TransactionData txn= new TransactionData();
						txn.setTnxId(strarray.getJSONObject(i).getString("txid"));
						txn.setOutputNumber(strarray.getJSONObject(i).getString("output_no"));
						txn.setScriptASM(strarray.getJSONObject(i).getString("script_asm"));
						txn.setScriptHEX(strarray.getJSONObject(i).getString("script_hex"));
						txn.setValue(strarray.getJSONObject(i).getString("value"));
						txn.setConfirmations(strarray.getJSONObject(i).getString("confirmations"));
						txn.setTime(strarray.getJSONObject(i).getString("time"));
						txnList.add(txn);
					}
					dashBoard.setTransactions(txnList);*/
				 
				 
				 return dashBoard;
				}catch(Exception e){
					e.printStackTrace();
					 
					 return new DashBoardUser();
				}
				
			}
	
	
	public static void main(String[] args) {
		/*DashBoardUser dashBoard=new BlockChainApiImpl().getDashBoardUser("1AryFXVUqYrEUgmij4px4PMMue9jf71pTm", "BTC");
		System.out.println(dashBoard.getAddress());*/
		/*UserAddress userAddress=new BlockChainApiImpl().getNewAddress("6957-ab56-4a87-9599");
		System.out.println(userAddress.getAddress());*/
		/*SaleBitCoin sale= new BlockChainApiImpl().saleBitCoinAddress("6957-ab56-4a87-9599", "37FYSdq4TsWKMnm5uzZVmQ2YSodLhyDPqs", "392bwDsB6gAMDNpd8yZt87hqQ3W2YZHFin", "BTC", (float)1.0000, "pawansaini89");
		System.out.println(sale.getErrorMessage());	*/
		BitCoinRate rate=new BlockChainApiImpl().getBTCRate("BTC","USD", "INR");
		 new BlockChainApiImpl().saleBitCoinAddress("6957-ab56-4a87-9599", "3HbNSBB6nr8T1Ke52hmqt7zSuP1Qv7v8kW", "3HoA4Rchopy4mpSoj9kFfBY7CNY1ucBEbY", "BTC", 0.00001, "pawansaini89");
			
	
	}
	
	
	
}
