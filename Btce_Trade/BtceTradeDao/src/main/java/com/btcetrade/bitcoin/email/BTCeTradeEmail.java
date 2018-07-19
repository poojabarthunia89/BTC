package com.btcetrade.bitcoin.email;

import java.util.List;

import com.btcetrade.bitcoin.model.AdminUser;
import com.btcetrade.bitcoin.model.BTCResponse;
import com.btcetrade.bitcoin.model.Country;
import com.btcetrade.bitcoin.model.VerifyPhoneNumber;

public interface BTCeTradeEmail {

	public VerifyPhoneNumber getVerifyNumberDetail(Integer verifyId);

	public BTCResponse saveVerifyNumber(VerifyPhoneNumber saveVerifyNumber);

	public VerifyPhoneNumber getVerifyCode(String verifiCode);

	public String saveAdminUser(AdminUser adminUser);

	public AdminUser fetchAdminUser(Integer adminId);

	public List<Country> fetchCountries();

	public BTCResponse getVerifyNumber(String phoneNumber, String code);

	public VerifyPhoneNumber authenticatePinNumber(String phoneNumber,	String pinNumber);

	public BTCResponse forgetPinNumber(String phoneNumber);

}
