package com.btcetrade.bitcoin.dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.btcetrade.bitcoin.BlockChainApi;
import com.btcetrade.bitcoin.BlockChainApiImpl;
import com.btcetrade.bitcoin.model.AdminUser;
import com.btcetrade.bitcoin.model.BTCResponse;
import com.btcetrade.bitcoin.model.BTCTransaction;
import com.btcetrade.bitcoin.model.Country;
import com.btcetrade.bitcoin.model.DashBoard;
import com.btcetrade.bitcoin.model.DashBoardUser;
import com.btcetrade.bitcoin.model.FeedBack;
import com.btcetrade.bitcoin.model.KYCVerify;
import com.btcetrade.bitcoin.model.SellBitCoinBTCeTrade;
import com.btcetrade.bitcoin.model.UserWallet;
import com.btcetrade.bitcoin.model.ValidAddress;
import com.btcetrade.bitcoin.model.VerifyPhoneNumber;

@Component
public class BTCeTradeDaoImpl implements BTCeTradeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Criteria query;
	
	@Override
	public VerifyPhoneNumber getVerifyNumberDetail(Integer verifyId) {
		
		  query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		  VerifyPhoneNumber verify=(VerifyPhoneNumber)query.add(Restrictions.eq("verifyId", verifyId)).uniqueResult();
	   	return verify;
	}
	
	@Override
	public BTCResponse saveVerifyNumber(VerifyPhoneNumber saveVerifyNumber) {
			
		System.out.println(saveVerifyNumber.getSendId()+","+saveVerifyNumber.getOtp()+","+saveVerifyNumber.getVerifyNumber());
			String result="";
			
			
//			from User u
//			where u.name IN (
//			select user.name
//			from User user
//			group by user.name
//			having count(user.name) > 1)
			Session session;
			Transaction tx=null;
			BTCResponse respond= new BTCResponse();
			
			try{
				
			session=sessionFactory.openSession();
			
			tx=session.beginTransaction();
			
			/*VerifyPhoneNumber verify=(VerifyPhoneNumber) session.createCriteria(VerifyPhoneNumber.class).add(Restrictions.eq("verifyNumber", saveVerifyNumber.getVerifyNumber())).uniqueResult();
			
			*select name, count(name) as times from contacts group by name, phone having times>1;
             from VerifyPhoneNumber v where v.verifyNumber IN(select vry.verifyNumber from VerifyPhoneNumber vry group by vry.verifyNumber having count(vry.verifyNumber)>1)

			*
			*/
			//Query query=session.createQuery("from VerifyPhoneNumber v where v.verifyNumber IN(select vry.verifyNumber from VerifyPhoneNumber vry group by vry.verifyNumber having count(vry.verifyNumber)>1)");	
			
				Query<String> verifyNumber=session.createQuery("select ver.verifyNumber from VerifyPhoneNumber ver where ver.verifyNumber=:verifyNumber");
				verifyNumber.setString("verifyNumber", saveVerifyNumber.getVerifyNumber());
				
				
				String phoneNumber=verifyNumber.uniqueResult();
				
				
				
				if(phoneNumber!=null){
					Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set otp=:otp where verifyNumber=:verifyNumber");
					query.setString("otp", saveVerifyNumber.getOtp());
					query.setString("verifyNumber", saveVerifyNumber.getVerifyNumber());
					int j=query.executeUpdate();
					result="success";	
				}else{
					session.saveOrUpdate(saveVerifyNumber);
					result="Success";
				}
			
				
			
			
			respond.setResultForResponse(result);
			
			tx.commit();
		}catch(Exception e){
			result="Sorry";
			e.printStackTrace();
			respond.setResultForResponse(result);
			tx.rollback();
		}
		return respond;
	}

	@Override
	public VerifyPhoneNumber getVerifyCode(String verifiCode) {
		
		try{
			VerifyPhoneNumber verifyCode=(VerifyPhoneNumber) sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class).add(Restrictions.eq("verifyCode",verifiCode)).uniqueResult();
		    return verifyCode;	
		}catch(Exception e){
			e.printStackTrace();
			return new VerifyPhoneNumber();
		}
	}

	@Override
	public String saveAdminUser(AdminUser adminUser) {
		String result="";
		try{
			int id=(int) sessionFactory.getCurrentSession().save(adminUser);
			if(id>0){
				result="Data Successfully Save In DataBase";
			}else{
				result="Sorry !Data Successfully Not Save In  ";
			}
		}catch(Exception e){
			result="So rry !Data Successfully Not Save In DataBase";
		}
		return result;
	}

	@Override
	public AdminUser fetchAdminUser(Integer adminId) {
		AdminUser adminUser;
		try{
			adminUser=(AdminUser) sessionFactory.getCurrentSession().createCriteria(AdminUser.class).add(Restrictions.idEq(adminId)).uniqueResult();
			return adminUser;
			}catch(Exception e){
			e.printStackTrace();
			return new AdminUser();
		}
		
	}

	@Override
	public List<Country> fetchCountries() {
		 
		return sessionFactory.getCurrentSession().createCriteria(Country.class).list();
	}

	@Override
	public BTCResponse getVerifyNumber(String phoneNumber, String code) {
		BTCResponse response= new BTCResponse();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber", phoneNumber));
		query.add(Restrictions.eq("otp", code));
		VerifyPhoneNumber user=(VerifyPhoneNumber)query.uniqueResult();
		if(user!=null){
		if(user.getOtp().equalsIgnoreCase(code)){
			
			Query<String> query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set verifyCode=:verCode where verifyNumber=:verifyNumber");
			query.setString("verifyNumber", phoneNumber);
			query.setString("verCode", user.getOtp());
			int j=query.executeUpdate();
			if(j>0){
			response.setResultForResponse("verify");
			}else{
				response.setResultForResponse("notverify");
			}
		}else{
			response.setResultForResponse("notverify");
		}
		}else{
			response.setResultForResponse("notverify");
		}
		
		
/*		Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set verifyCode=:verCode where verifyNumber=:verifyNumber");
		query.setString("verCode", code);
		query.setString("verifyNumber", phoneNumber);
		int j=query.executeUpdate();
		System.out.println(j);
		
		if(j>0){
			
		}else{
		
		}
*/	
		
		
		
		return response;
	}

	@Override
	public VerifyPhoneNumber authenticatePinNumber(String phoneNumber,String pinNumber) {
		
		try{
		VerifyPhoneNumber verifyNumber=(VerifyPhoneNumber) sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class)
				.add(Restrictions.eq("verifyNumber", phoneNumber))
				.add(Restrictions.eq("verifyCode", pinNumber)).uniqueResult();
		return verifyNumber;
		}catch(Exception e){
			return new VerifyPhoneNumber();
		}
		
	}

	@Override
	public VerifyPhoneNumber forgetPinNumber(String phoneNumber) {
		VerifyPhoneNumber verifyNumber=(VerifyPhoneNumber) sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class)
				.add(Restrictions.eq("verifyNumber", phoneNumber)).uniqueResult();
		return verifyNumber;
		
	}

	@Override
	public BTCResponse changePinNumber(String phoneNumber, String oldPin,String newPin) {
		BTCResponse respone= new BTCResponse();
		System.out.println(phoneNumber+","+oldPin+","+newPin);
		/*VerifyPhoneNumber phone= (VerifyPhoneNumber)sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class)
				.add(Restrictions.eq("verifyNumber", phoneNumber))
				.add(Restrictions.eq("verifyCode",oldPin))
				.uniqueResult();*/
		Query q= sessionFactory.getCurrentSession().createQuery("from VerifyPhoneNumber very where verifyNumber=:verifyNumber and verifyCode=:verifyCode");
		q.setString("verifyNumber", phoneNumber);
		q.setString("verifyCode", oldPin);
		
		VerifyPhoneNumber phone=(VerifyPhoneNumber) q.uniqueResult();
		
		try{
		
		if(phone!=null){
		System.out.println(phone.getVerifyNumber()+","+phone.getVerifyCode());
		if(phone.getVerifyNumber().equalsIgnoreCase(phoneNumber) && phone.getVerifyCode().equalsIgnoreCase(oldPin)){
			Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set verifyCode=:verCode where verifyNumber=:verifyNumber");
			query.setString("verCode", newPin);
			query.setString("verifyNumber",phoneNumber);
			int updateFlag=query.executeUpdate();
			if(updateFlag>0){
			   respone.setResultForResponse("Pinupdate");
			}
		}
		}else{
		
				respone.setResultForResponse("Pinnotupdate");
		
		   }
		}catch(Exception e){
			respone.setResultForResponse("Pinnotupdate");
		}
		return respone;
	}

	@Override
	public BTCResponse saveFeedBackUser(String phoneNumbr,Double feedBackPoint, String comment) {
		BTCResponse response=new BTCResponse();	
		try{
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class)
				.add(Restrictions.eq("verifyNumber", phoneNumbr));
		VerifyPhoneNumber number=(VerifyPhoneNumber) query.uniqueResult();
		System.out.println(number.getVerifyId());
		FeedBack feedBack= new FeedBack();
		feedBack.setFeedBackPoin(feedBackPoint);
		feedBack.setComment(comment);
		VerifyPhoneNumber phoneNumbers=new VerifyPhoneNumber();
		phoneNumbers.setVerifyId(number.getVerifyId());
      	feedBack.setUser(phoneNumbers);
		
      	Integer id=(Integer) sessionFactory.getCurrentSession().save(feedBack);
		if(id>0){
			response.setResultForResponse("Savefeedback");
		}else{
			response.setResultForResponse("Notsavefeedback");
		}
		}catch(Exception e){
			response.setResultForResponse("Notsavefeedback");
		}
		return response;  
	}

	@Override
	public BTCResponse saveAddressinWallet(String contextPath,String mobileNumber) {
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public BTCResponse saveWalletAddress(BTCResponse response, String mobileNumber) {
		BTCResponse responsed= new BTCResponse();
		try{
			query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
			query.add(Restrictions.eq("verifyNumber",mobileNumber));
			VerifyPhoneNumber verifyUpload=(VerifyPhoneNumber) query.uniqueResult();
			
			System.out.println("VerifyId"+verifyUpload.getVerifyId()+"ver"+response.getUserAddress()+"Mobile Number"+mobileNumber);
			
			UserWallet wallet= new UserWallet();
			VerifyPhoneNumber phoneNumber= new VerifyPhoneNumber();
			phoneNumber.setVerifyId(verifyUpload.getVerifyId());
			wallet.setBalance("50");
			wallet.setWalletAddress(response.getUserAddress());
			wallet.setUser(phoneNumber);
			int  query = (int)sessionFactory.openSession().save(wallet);
			System.out.println(query);

			
			return responsed;
		}catch(Exception e){
			e.printStackTrace();
		return responsed;
		}
		
		
	}
	@Override
	public BTCResponse saveUserAddress(String mobileNumber, String address) {
		BTCResponse responsed= new BTCResponse();
		
		System.out.println(mobileNumber+","+address);
		try{
			String chanelNetwork="BTC";
			
			ValidAddress validAddress=(ValidAddress) new BlockChainApiImpl().isValidAddress(address, chanelNetwork);
			System.out.println(validAddress.getIsValid());
			
			if(validAddress.getIsValid().equals(true)){
				Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set userAddress=:address where verifyNumber=:verifyNumber");
				query.setString("address", address);
				query.setString("verifyNumber",mobileNumber);
				int updateFlag=query.executeUpdate();
				if(updateFlag>0){
					responsed.setResultForResponse("AddressSave");
				}else{
			
					responsed.setResultForResponse("AddressNotSave");
			
			   }
			}else{
				responsed.setResultForResponse("!InvalidAddress");
			}
			/*query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
			query.add(Restrictions.eq("verifyNumber",mobileNumber));
			VerifyPhoneNumber verifyUpload=(VerifyPhoneNumber) query.uniqueResult();
			
			System.out.println("VerifyId"+verifyUpload.getVerifyId());
			
			VerifyPhoneNumber phoneNumber= new VerifyPhoneNumber();
			phoneNumber.setVerifyId(verifyUpload.getVerifyId());*/
			
			
			/*wallet.setBalance("50");
			wallet.setWalletAddress(response.getUserAddress());
			wallet.setUser(phoneNumber);*/
			//int  query = (int)sessionFactory.openSession().save(wallet);
			System.out.println(query);

			
			return responsed;
		}catch(Exception e){
			e.printStackTrace();
		return responsed;
		}
		
		
	}

	@Override
	public BTCResponse uploadFileinServer(String contextPath,
			String mobileNumber) {
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber",mobileNumber));
		VerifyPhoneNumber verifyUpload=(VerifyPhoneNumber) query.uniqueResult();
		return null;
	}

	@Override
	public BTCResponse uploadFileinServer(UserWallet wallet) {
		
		Session session= sessionFactory.getCurrentSession();
		  session.save(wallet);
		  BTCResponse respnse= new BTCResponse();
		  respnse.setResultForResponse("SaveWallet");
		  return respnse;
	}

	@Override
	public VerifyPhoneNumber getUserWithPhoneNumber(String mobileNumber) {
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber",mobileNumber));
		VerifyPhoneNumber verifyUpload=(VerifyPhoneNumber) query.uniqueResult();
		
		return verifyUpload;
	}

	@Override
	public BTCResponse validUser(String userAddress) {
		BTCResponse response= new  BTCResponse();
		UserWallet walletAddress= (UserWallet) sessionFactory.getCurrentSession().createCriteria(UserWallet.class)
				.add(Restrictions.eq("walletAddress", userAddress)).uniqueResult();
		System.out.println(walletAddress.getWalletAddress());
		if(walletAddress!=null){
		if(walletAddress.getWalletAddress().equalsIgnoreCase(userAddress))
		{
			response.setResultForResponse("Valid");
		}
		}else{
			
				response.setResultForResponse("Invalid");
			
		}
		return response;
	}

	@Override
	public BTCResponse saveAdharNumber(String mobileNumber, String adharNumber) {
		BTCResponse response= new  BTCResponse();
		Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set adharNumber=:adharNumber where verifyNumber=:verifyNumber");
		query.setString("adharNumber",adharNumber);
		query.setString("verifyNumber", mobileNumber);
		int j=query.executeUpdate();
		
		response.setResultForResponse("saveadhar");
		return response;
	}

	@Override
	public BTCResponse savePANNumber(String mobileNumber, String panNumber,String username, String fatherName, String dob) {
		BTCResponse response= new  BTCResponse();
		Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set panNumber=:panNumber ,user where verifyNumber=:verifyNumber");
		query.setString("panNumber",panNumber);
		query.setString("verifyNumber", mobileNumber); 
		int j=query.executeUpdate();
		
		response.setResultForResponse("savePan");
		return response;
	}

	@Override
	public UserWallet getWalletInfoforAdmin(String adminWalletAddress) {
		
		return null;
	}

	@Override
	public DashBoardUser getInfoOFDashBoard(String phoneNumber) {
		String currencyNetwork="BTC";
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber",phoneNumber));
		VerifyPhoneNumber verifyUpload=(VerifyPhoneNumber) query.uniqueResult();
		
		String address=verifyUpload.getUserAddress();	
			System.out.println(address);	
		DashBoardUser dashBoard=new BlockChainApiImpl().getDashBoardUser(address, currencyNetwork);
		return dashBoard;
	}

	@Override
	public BTCResponse saveEmailId(String mobileNumber, String emailId) {
		
		BTCResponse response= new  BTCResponse();
		Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set emailId=:emailId where verifyNumber=:verifyNumber");
		query.setString("emailId",emailId);
		query.setString("verifyNumber", mobileNumber);
		int j=query.executeUpdate();
		
		response.setResultForResponse("saveMail");
		return response;
	}

	@Override
	public String saveSaleBitCoinBTCeTrade(SellBitCoinBTCeTrade saleBitCoinBtceTrade) {
		String result="";
		Integer id=(Integer) sessionFactory.getCurrentSession().save(saleBitCoinBtceTrade);
		try{
		if(id>0){
			result= "SaveSale";
		}else{
			result= "NOTSave";
		}
		}catch(Exception e){
			result= "NOTSave";
		}
		return result;
	}

	@Override
	public BTCResponse exsistingUserORNot(String mobileNumber) {
		BTCResponse response= new BTCResponse();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber", mobileNumber));
		VerifyPhoneNumber user=(VerifyPhoneNumber) query.uniqueResult();
		if(user!=null){
		if(user.getVerifyNumber().equalsIgnoreCase(mobileNumber)){
			response.setResultForResponse("userexsist");
		}else{
			response.setResultForResponse("notexsist");
		}
		}else{
			response.setResultForResponse("notexsist");
		}
		return response;
	}

	@Override
	public BTCResponse esistAddress(String mobileNumber) {
		BTCResponse response= new BTCResponse();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber", mobileNumber));
		VerifyPhoneNumber user=(VerifyPhoneNumber) query.uniqueResult();
		if(user!=null){
			if(user.getUserAddress()!=null){
				response.setResultForResponse("addressExsist");
			}else{
				response.setResultForResponse("addressnotExsist");
			}
		}
		return response;
	}

	@Override
	public BTCResponse verifyEmailId(String phoneNumber,String emailId) {
		BTCResponse response= new BTCResponse();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		
		query.add(Restrictions.eq("verifyNumber", phoneNumber));
		query.add(Restrictions.eq("emailId", emailId));
		
		VerifyPhoneNumber user=(VerifyPhoneNumber) query.uniqueResult();
		if(user!=null){
			if(user.getUserAddress()!=null){
				response.setResultForResponse("1");
				response.setUserAddress("");
			}else{
				response.setResultForResponse("0");
				response.setUserAddress("");
			}
		}
		return response;
	}

	@Override
	public BTCResponse verifyPanCard(String phoneNumber, String panNumber) {
		
		BTCResponse response= new BTCResponse();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		
		query.add(Restrictions.eq("verifyNumber", phoneNumber));
		query.add(Restrictions.eq("panNumber", panNumber));
		
		VerifyPhoneNumber user=(VerifyPhoneNumber) query.uniqueResult();
		if(user!=null){
			if(user.getPanNumber()!=null){
				response.setResultForResponse("1");
				response.setUserAddress("");
			}else{
				response.setResultForResponse("0");
				response.setUserAddress("");
			}
		}
		return response;
	}

	@Override
	public BTCResponse verifyAdharCard(String phoneNumber, String adharCard) {
		
		BTCResponse response= new BTCResponse();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		
		query.add(Restrictions.eq("verifyNumber", phoneNumber));
		query.add(Restrictions.eq("adharNumber", adharCard));
		
		VerifyPhoneNumber user=(VerifyPhoneNumber) query.uniqueResult();
		if(user!=null){
			if(user.getAdharNumber()!=null){
				response.setResultForResponse("1");
				response.setUserAddress("");
			}else{
				response.setResultForResponse("0");
				response.setUserAddress("");
			}
		}
		return response;
	}

	@Override
	public KYCVerify fetchKYCOfUser(String mobileNumber) {
		KYCVerify kycVerify=new KYCVerify();
		query=sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class);
		query.add(Restrictions.eq("verifyNumber", mobileNumber));
		VerifyPhoneNumber user=(VerifyPhoneNumber) query.uniqueResult();
		System.out.println("Email"+user.getEmailId()+"Pan Number"+user.getPanNumber()+"Adhar Number"+user.getAdharNumber());
		
		if(user!=null){
			if(user.getEmailId()!=null){
				kycVerify.setEmailId(user.getEmailId());	
				kycVerify.setEmailFlag(1);
			}
			if(user.getPanNumber()!=null){
				
				kycVerify.setPancardNumber(user.getPanNumber());
				kycVerify.setPancardFlag(1);
			}
			if(user.getAdharNumber()!=null){
				kycVerify.setAdharNumber(user.getAdharNumber());
				kycVerify.setAdharFlag(1);
			}
			
			return kycVerify;
		}else{
			return new KYCVerify();
		}
		
	}

	@Override
	public BTCResponse saveTransaction(BTCTransaction transaction) {
		BTCResponse response= new BTCResponse();
		System.out.println(transaction.getBitcoinTransaction()+",");
		int id=(int) sessionFactory.getCurrentSession().save(transaction);
		if(id>0){
			response.setResultForResponse("Save Trasaction");
		}else{
			response.setResultForResponse("NotSave Trasaction");
		}
		return response;
	}

	@Override
	public VerifyPhoneNumber loginFlagUPUser(String phoneNumber,String pinNumber) {
		/*Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set isLoginFlag=:isLoginFlag where verifyNumber=:verifyNumber");
		query.setInteger("isLoginFlag",1);
		query.setString("verifyNumber", phoneNumber);
		int j=query.executeUpdate();
		 
		 VerifyPhoneNumber user=null;
		 if(j>0){
			user =(VerifyPhoneNumber) query.uniqueResult();	 
		 }else{
			 user= new VerifyPhoneNumber();
			 
		 }*/
		VerifyPhoneNumber user=(VerifyPhoneNumber) sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class)
				.add(Restrictions.eq("verifyNumber", phoneNumber))
				.add(Restrictions.eq("verifyCode", pinNumber)).uniqueResult();
				System.out.println(user.getVerifyId());
				
				
				Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set isLoginFlag=:isLoginFlag where verifyId=:verifyId");
				query.setInteger("isLoginFlag",1);
				query.setInteger("verifyId", user.getVerifyId());
				int id=query.executeUpdate();
				if(id>0){
					return user;
				}else {
					return new VerifyPhoneNumber();
				}
	}

	@Override
	public BTCResponse logoutUser(String phoneNumber) {
		
		VerifyPhoneNumber user=(VerifyPhoneNumber) sessionFactory.getCurrentSession().createCriteria(VerifyPhoneNumber.class)
				.add(Restrictions.eq("verifyNumber", phoneNumber))
				.uniqueResult();
				System.out.println(user.getVerifyId());
				BTCResponse response= new BTCResponse();
				
				Query query=sessionFactory.getCurrentSession().createQuery("update VerifyPhoneNumber set isLoginFlag=:isLoginFlag where verifyId=:verifyId");
				query.setInteger("isLoginFlag",0);
				query.setInteger("verifyId", user.getVerifyId());
				int id=query.executeUpdate();
				if(id>0){
					 response.setResultForResponse("Logout User");;
				}else {
					  response.setResultForResponse("Logout not perform");
				}
				return response;
	}

	@Override
	public BTCResponse savePANCardDetail(String phoneNumber, String username,String fathername, String dob) {
		BTCResponse response= new BTCResponse();
		Query query=sessionFactory.getCurrentSession()
				.createQuery("update VerifyPhoneNumber set username=:username ,fatherName=:fatherName, dob=:dob where verifyNumber=:verifyNumber");
		     query.setString("username", username);
		     query.setString("fatherName", fathername);
		     query.setString("dob", dob);
		     query.setString("verifyNumber", phoneNumber);
		     int id=query.executeUpdate();
		     if(id>0){
		    	 response.setResultForResponse("Save Pan Card Detail");
		     }else{
		    	 response.setResultForResponse("Pan Card Detail NOT Save");
		     }
				
		return response;
	}

	
	

}
