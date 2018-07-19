package com.btcetrade.bitcoin.model;

public class TransactionData {

	private String tnxId;
	private String outputNumber;
	private String scriptASM;
	private String scriptHEX;
	private String value;
	private String confirmations;
	private String time;
	public String getTnxId() {
		return tnxId;
	}
	public void setTnxId(String tnxId) {
		this.tnxId = tnxId;
	}
	public String getOutputNumber() {
		return outputNumber;
	}
	public void setOutputNumber(String outputNumber) {
		this.outputNumber = outputNumber;
	}
	public String getScriptASM() {
		return scriptASM;
	}
	public void setScriptASM(String scriptASM) {
		this.scriptASM = scriptASM;
	}
	public String getScriptHEX() {
		return scriptHEX;
	}
	public void setScriptHEX(String scriptHEX) {
		this.scriptHEX = scriptHEX;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getConfirmations() {
		return confirmations;
	}
	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
}
