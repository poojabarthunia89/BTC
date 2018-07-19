package com.btcetrade.bitcoin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.btcetrade.bitcoin.BlockChainApiImpl;
import com.btcetrade.bitcoin.dao.BTCeTradeDao;
import com.btcetrade.bitcoin.model.AdminUser;
import com.btcetrade.bitcoin.model.BTCResponse;
import com.btcetrade.bitcoin.model.BTCTransaction;
import com.btcetrade.bitcoin.model.BitCoinRate;
import com.btcetrade.bitcoin.model.Country;
import com.btcetrade.bitcoin.model.DashBoard;
import com.btcetrade.bitcoin.model.DashBoardUser;
import com.btcetrade.bitcoin.model.KYCVerify;
import com.btcetrade.bitcoin.model.SellBitCoinBTCeTrade;
import com.btcetrade.bitcoin.model.UserWallet;
import com.btcetrade.bitcoin.model.VerifyPhoneNumber;

@Component
@Transactional
public class BTCeTradeServiceImpl implements BTCeTradeService{
	
	@Autowired
	private BTCeTradeDao btcTradeDao;

	@Override
	public VerifyPhoneNumber getVerifyNumberDetail(Integer verifyId) {
		
		return btcTradeDao.getVerifyNumberDetail(verifyId);
	}

	@Override
	public BTCResponse saveVerifyNumber(VerifyPhoneNumber saveVerifyNumber) {
		
		return btcTradeDao.saveVerifyNumber(saveVerifyNumber);
	}

	@Override
	public VerifyPhoneNumber getVerifyCode(String verifiCode) {
		
		return btcTradeDao.getVerifyCode(verifiCode);
	}

	@Override
	public String saveAdminUser(AdminUser adminUser) {
		
		return btcTradeDao.saveAdminUser(adminUser);
	}

	@Override
	public AdminUser fetchAdminUser(Integer adminId) {
		
		return btcTradeDao.fetchAdminUser(adminId);
	}

	@Override
	public List<Country> fetchCountries() {
		
		return btcTradeDao.fetchCountries();
	}

	@Override
	public BTCResponse getVerifyNumber(String phoneNumber, String code) {
		
		return btcTradeDao.getVerifyNumber(phoneNumber,code);
	}

	@Override
	public VerifyPhoneNumber authenticatePinNumber(String phoneNumber,String pinNumber) {
		return btcTradeDao.authenticatePinNumber(phoneNumber,pinNumber);
	}

	@Override
	public VerifyPhoneNumber forgetPinNumber(String phoneNumber) {
		return btcTradeDao.forgetPinNumber(phoneNumber);
	}

	@Override
	public BTCResponse changePinNumber(String phoneNumber, String oldPin,String newPin) {
		return btcTradeDao.changePinNumber(phoneNumber,oldPin,newPin);
	}

	@Override
	public BTCResponse saveFeedBackUser(String phoneNumbr,Double feedBackPoint, String comment) {
				return btcTradeDao.saveFeedBackUser(phoneNumbr,feedBackPoint,comment);
	}

	@Override
	public BTCResponse createAdminAddress(String adminName) {
		
		BTCAdminAddressGenerate btcetradeAdminAddress= new BTCAdminAddressGenerate();
		return new BTCResponse();  
		
	}

	@Override
	public BTCResponse uploadFileinServer(String contextPath,String mobileNumber) {
		try{
			
			return btcTradeDao.uploadFileinServer(contextPath,mobileNumber);
			
		
		}catch(Exception e){
			e.printStackTrace();
			return new BTCResponse();
		}
		
	}

	@Override
	public BTCResponse saveAddressinWallet(String contextPath,String mobileNumber) {
		
		return btcTradeDao.saveAddressinWallet(contextPath,mobileNumber);
	}

	@Override
	public BTCResponse uploadFileinServer(UserWallet wallet) {
		return btcTradeDao.uploadFileinServer(wallet);
	}

	@Override
	public VerifyPhoneNumber getUserWithPhoneNumber(String mobileNumber) {
		
		return btcTradeDao.getUserWithPhoneNumber(mobileNumber);
	}

	@Override
	public BTCResponse validUser(String userAddress) {
		
		return btcTradeDao.validUser(userAddress);
	}

	

	@Override
	public BTCResponse saveAdharNumber(String mobileNumber, String adharNumber) {
		
		return btcTradeDao.saveAdharNumber(mobileNumber,adharNumber);
	}

	@Override
	public BTCResponse savePANNumber(String mobileNumber, String panNumber,String username, String fatherName, String dob) {
		
		return btcTradeDao.savePANNumber(mobileNumber,panNumber,username,fatherName,dob);
	}

	

	@Override
	public DashBoardUser getInfoOFDashBoard(String phoneNumber) {
		
		return btcTradeDao.getInfoOFDashBoard(phoneNumber);
	}

	@Override
	public BTCResponse saveEmailId(String mobileNumber, String emailId) {
		
		return btcTradeDao.saveEmailId(mobileNumber,emailId);
	}

	@Override
	public BTCResponse saveUserAddress(String mobileNumber, String address) {
		
		return btcTradeDao.saveUserAddress(mobileNumber,address);
	}

	@Override
	public UserWallet getWalletInfoforAdmin(String adminWalletAddress) {
		
		return null;
	}

	@Override
	public String saveSaleBitCoinBTCeTrade(SellBitCoinBTCeTrade saleBitCoinBtceTrade) {
		
		return btcTradeDao.saveSaleBitCoinBTCeTrade(saleBitCoinBtceTrade);
	}

	@Override
	public BTCResponse exsistingUserORNot(String mobileNumber) {
		
		return btcTradeDao.exsistingUserORNot(mobileNumber);
	}

	@Override
	public BTCResponse exsistingUserORNotAddress(String mobileNumber) {
		
		return null;
	}

	@Override
	public BTCResponse esistAddress(String mobileNumber) {
		
		return btcTradeDao.esistAddress(mobileNumber);
	}

	@Override
	public BitCoinRate getBitCoinRate(String network, String defaultCurrency,
			String curencyRateINR) {
		BitCoinRate bitCoinRate=new BlockChainApiImpl().getBTCRate(network, defaultCurrency, curencyRateINR);
		return bitCoinRate;
	}

	@Override
	public BTCResponse verifyEmailId(String phoneNumber,String emailId) {
		
		return btcTradeDao.verifyEmailId(phoneNumber,emailId);
	}

	@Override
	public BTCResponse verifyPanCard(String phoneNumber, String panCard) {
		return btcTradeDao.verifyPanCard(phoneNumber,panCard);
	}

	@Override
	public BTCResponse verifyAdharCard(String phoneNumber, String adharCard) {
		
		return btcTradeDao.verifyAdharCard(phoneNumber,adharCard);
	}

	@Override
	public KYCVerify fetchKYCOfUser(String mobileNumber) {
		
		return btcTradeDao.fetchKYCOfUser(mobileNumber);
	}

	@Override
	public BTCResponse saveTransaction(BTCTransaction transaction) {
		
		return btcTradeDao.saveTransaction(transaction);
	}

	@Override
	public VerifyPhoneNumber loginFlagUPUser(String phoneNumber,String pinNumber) {
		
		return btcTradeDao.loginFlagUPUser(phoneNumber,pinNumber);
	}

	@Override
	public BTCResponse logoutUser(String phoneNumber) {
		
		return btcTradeDao.logoutUser(phoneNumber);
	}

	@Override
	public BTCResponse savePANCardDetail(String phoneNumber,String username, String fathername,String dob) {
		
		return btcTradeDao.savePANCardDetail(phoneNumber,username,fathername,dob);
	}

	

	

	
	
	
	
	
}
