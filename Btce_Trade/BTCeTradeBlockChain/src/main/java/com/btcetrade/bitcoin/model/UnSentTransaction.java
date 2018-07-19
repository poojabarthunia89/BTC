package com.btcetrade.bitcoin.model;

import java.io.Serializable;
import java.util.List;

public class UnSentTransaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	
	private String network;
	
	private String adress;
	
	private List<TransactionData> txnData;

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TransactionData> getTxnData() {
		return txnData;
	}

	public void setTxnData(List<TransactionData> txnData) {
		this.txnData = txnData;
	}
	
	
	
	
}
