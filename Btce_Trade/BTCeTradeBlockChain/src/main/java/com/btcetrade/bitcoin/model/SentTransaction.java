package com.btcetrade.bitcoin.model;

import java.io.Serializable;
import java.util.List;

public class SentTransaction implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5118572052360245013L;

	private String status;
	
	private String network;
	
	private String adress;
	
	private List<SentTransactionData> txnData;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public List<SentTransactionData> getTxnData() {
		return txnData;
	}

	public void setTxnData(List<SentTransactionData> txnData) {
		this.txnData = txnData;
	}

		
	
	
}
