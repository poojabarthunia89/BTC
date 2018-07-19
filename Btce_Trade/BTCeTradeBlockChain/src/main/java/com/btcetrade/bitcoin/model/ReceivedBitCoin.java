package com.btcetrade.bitcoin.model;

import java.io.Serializable;

public class ReceivedBitCoin implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -1192578325339755864L;

	private String status;
   
   private ReceivedData received;

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public ReceivedData getReceived() {
	return received;
}

public void setReceived(ReceivedData received) {
	this.received = received;
}
   
}
