package com.btcetrade.bitcoin.dao;

import java.util.List;

import com.btcetrade.bitcoin.model.AdminUser;
import com.btcetrade.bitcoin.model.BTCResponse;
import com.btcetrade.bitcoin.model.BTCTransaction;
import com.btcetrade.bitcoin.model.Country;
import com.btcetrade.bitcoin.model.DashBoard;
import com.btcetrade.bitcoin.model.DashBoardUser;
import com.btcetrade.bitcoin.model.KYCVerify;
import com.btcetrade.bitcoin.model.SellBitCoinBTCeTrade;
import com.btcetrade.bitcoin.model.UserWallet;
import com.btcetrade.bitcoin.model.VerifyPhoneNumber;

public interface BTCeTradeDao {

	public VerifyPhoneNumber getVerifyNumberDetail(Integer verifyId);

	public BTCResponse saveVerifyNumber(VerifyPhoneNumber saveVerifyNumber);

	public VerifyPhoneNumber getVerifyCode(String verifiCode);

	public String saveAdminUser(AdminUser adminUser);

	public AdminUser fetchAdminUser(Integer adminId);

	public List<Country> fetchCountries();

	public BTCResponse getVerifyNumber(String phoneNumber, String code);

	public VerifyPhoneNumber authenticatePinNumber(String phoneNumber,	String pinNumber);

	public VerifyPhoneNumber forgetPinNumber(String phoneNumber);

	public BTCResponse changePinNumber(String phoneNumber, String oldPin,String newPin);

	public BTCResponse saveFeedBackUser(String phoneNumbr,Double feedBackPoint, String comment);

	public BTCResponse saveAddressinWallet(String contextPath,String mobileNumber);

	public BTCResponse saveWalletAddress(BTCResponse response, String mobileNumber);

	public BTCResponse uploadFileinServer(String contextPath,String mobileNumber);

	public BTCResponse uploadFileinServer(UserWallet wallet);

	public VerifyPhoneNumber getUserWithPhoneNumber(String mobileNumber);

	public BTCResponse validUser(String userAddress);

	public BTCResponse saveAdharNumber(String mobileNumber, String adharNumber);

	public BTCResponse savePANNumber(String mobileNumber, String panNumber, String username, String fatherName, String dob);

	public UserWallet getWalletInfoforAdmin(String adminWalletAddress);

	public DashBoardUser getInfoOFDashBoard(String phoneNumber);

	public BTCResponse saveEmailId(String mobileNumber, String emailId);
	
	public BTCResponse saveUserAddress(String mobileNumber, String address);

	public String saveSaleBitCoinBTCeTrade(SellBitCoinBTCeTrade saleBitCoinBtceTrade);

	public BTCResponse exsistingUserORNot(String mobileNumber);

	public BTCResponse esistAddress(String mobileNumber);

	public BTCResponse verifyEmailId(String emailId, String emailId2);

	public BTCResponse verifyPanCard(String phoneNumber, String panNumber);

	public BTCResponse verifyAdharCard(String phoneNumber, String adharCard);

	public KYCVerify fetchKYCOfUser(String mobileNumber);

	public BTCResponse saveTransaction(BTCTransaction transaction);

	public VerifyPhoneNumber loginFlagUPUser(String phoneNumber,String pinNumber);

	public BTCResponse logoutUser(String phoneNumber);

	public BTCResponse savePANCardDetail(String phoneNumber, String username,String fathername, String dob);
}
