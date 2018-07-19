package com.btcetrade.bitcoin;

public interface BlockChainApi {
	public static final String BASE_URL="https://chain.so/api/v2/";
	public static final String GET_ADDRESS_SPENT="get_address_spent";
	public static final String GET_ADDRESS_BALANCE="get_address_balance";
	public static final String GET_ADDRESS_RECEIVED_BALANCE="get_address_received";
	public static final String GET_ADDRESS_UN_SPENT_TRANSACTION="get_tx_unspent";
	public static final String IS_VALID_ADDRESS="is_address_valid";
	public static final String DASHBOARD ="address";
	public static final String BASE_URL_COIN_BASE="https://block.io/api/v2/";
	public static final String CREATE_NEW_ADDRESS="get_new_address/?api_key=";
	public static final String SALE_BIT_COIN="withdraw_from_addresses/?api_key=";
	public static final String SEND_TRANSACTION_BIT_COIN="get_tx_spent";
	
	
}
