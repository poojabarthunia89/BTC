package com.btcetrade.bitcoin.controller;

import static com.jayway.restassured.RestAssured.get;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpRequest;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.btcetrade.bitcoin.BlockChainApiImpl;
import com.btcetrade.bitcoin.bank.JavaIntegrationKit;
import com.btcetrade.bitcoin.config.BTCeTradeSMSGateWay;
import com.btcetrade.bitcoin.config.GetBTCVerifyOTPPassword;
import com.btcetrade.bitcoin.config.ObjectEncryption;
import com.btcetrade.bitcoin.config.RandomeCodeGenerator;
import com.btcetrade.bitcoin.config.TransactionFactory;
import com.btcetrade.bitcoin.config.TransactionForPaymentActivity;
import com.btcetrade.bitcoin.config.ZebpayComparePrice;
import com.btcetrade.bitcoin.email.APIResponse;
import com.btcetrade.bitcoin.email.BTCeTradeEmail;
import com.btcetrade.bitcoin.email.BTCeTradeEmailImpl;
import com.btcetrade.bitcoin.model.AdminUser;
import com.btcetrade.bitcoin.model.BTCResponse;
import com.btcetrade.bitcoin.model.BTCTransaction;
import com.btcetrade.bitcoin.model.BitCoinRate;
import com.btcetrade.bitcoin.model.Country;
import com.btcetrade.bitcoin.model.DashBoard;
import com.btcetrade.bitcoin.model.DashBoardUser;
import com.btcetrade.bitcoin.model.KYCVerify;
import com.btcetrade.bitcoin.model.SaleBitCoin;
import com.btcetrade.bitcoin.model.SellBitCoinBTCeTrade;
import com.btcetrade.bitcoin.model.UserAddress;
import com.btcetrade.bitcoin.model.UserWallet;
import com.btcetrade.bitcoin.model.VerifyPhoneNumber;
import com.btcetrade.bitcoin.service.BTCAdminAddressGenerate;
import com.btcetrade.bitcoin.service.BTCeTradeService;
import com.btcetrade.bitcoin.service.config.BTCeTradeEmailService;
import com.btcetrade.bitcoin.service.config.BTCeTradeEmailServiceImpl;

/*import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.Refund;
*/
@Path("/btcCoin")
public class BTCeTradeController {
	
	
	
	@Autowired
	private BTCeTradeService btcTradeService;
	BTCeTradeEmailService emailService=  new BTCeTradeEmailServiceImpl();
	private static final long serialVersionUID = 1L;

    public static final String UPLOAD_FILE_SERVER = "E:/BTCeTradeUserAddress/";
    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    
	@GET
	@Path("/test")
	@Produces(value=MediaType.TEXT_PLAIN)
	public String testBitCoin(){
		return "Sucessfuly establish enviorment of BTCe-Trade";
	}
	
	
	@GET
	@Path("/getInfoOfBitCoin/{verifyId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public VerifyPhoneNumber getDetailVerfiyNumber(@PathParam("verifyId")Integer verifyId){
		VerifyPhoneNumber  verifyNumber= btcTradeService.getVerifyNumberDetail(verifyId);
		return verifyNumber;
		
	}
	@GET
	@Path("/testBit")
	@Produces(value=MediaType.TEXT_PLAIN)
	public String testBitCoins(){
		return "Success";
		
	}
	
	@POST
	@Path("/saveVerifyNumber/{phoneNumber}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
	public BTCResponse saveVerifyNumber(@PathParam("phoneNumber")String phoneNumber){
		
		long random=Long.valueOf(GetBTCVerifyOTPPassword.getGenerateOTP(4));
		VerifyPhoneNumber saveVerifyNumber= new VerifyPhoneNumber();
		String sendId=BTCeTradeSMSGateWay.getSendIdOfMessageCode(phoneNumber, random);
		//String sendId="SMS Send4";
		saveVerifyNumber.setSendId(sendId);
		saveVerifyNumber.setOtp(String.valueOf(random));
		saveVerifyNumber.setVerifyNumber(phoneNumber);
		saveVerifyNumber.setSendId(sendId);
		 BTCResponse result=btcTradeService.saveVerifyNumber(saveVerifyNumber);
		return result;
	}
	

	@GET
	@Path("/authenticatePinNumber/{phoneNumber}/{pinNumber}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
	public BTCResponse getVerifyUser(@PathParam("phoneNumber")String phoneNumber,@PathParam("pinNumber")String pinNumber){
		System.out.println(phoneNumber+","+pinNumber);
		BTCResponse result=new BTCResponse();
		try{
		
		VerifyPhoneNumber  verifyUser= btcTradeService.authenticatePinNumber(phoneNumber,pinNumber);
			
			if(verifyUser.getVerifyNumber().equalsIgnoreCase("9166001205")){
				result.setResultForResponse("admin");
				
			}else{
			   result.setResultForResponse("user");
			
			}
		
		
		
		
			
		}catch(Exception e){
			result.setResultForResponse("NotValid");
			
		}
		return result;
	}
	
	
	@GET
	@Path("/forgetPin/{phoneNumber}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
	public BTCResponse forgetPinNumber(@PathParam("phoneNumber")String phoneNumber){
		 BTCResponse responce= new BTCResponse();
		VerifyPhoneNumber forgetPIN=btcTradeService.forgetPinNumber(phoneNumber);
		System.out.println(forgetPIN.getEmailId()+","+forgetPIN.getVerifyCode());
		if(forgetPIN.getEmailId()!=null){
			BTCeTradeEmailImpl mailApi = new BTCeTradeEmailImpl();
			 String result=	mailApi.sendEmail(forgetPIN.getEmailId(), forgetPIN.getVerifyCode());
			 	if(result.equalsIgnoreCase("Done")){
			 		responce.setResultForResponse("email successfully");
			 	}
			}else{
				BTCeTradeSMSGateWay.getSendIdOfMessageCode(forgetPIN.getVerifyNumber(), Long.valueOf(forgetPIN.getVerifyCode()));
				responce.setResultForResponse("sms successfully");
		    }
		return responce;
	}
	
	
	@POST
	@Path("/saveAdmin")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
	@Consumes(value={MediaType.APPLICATION_JSON})
	public String saveAdminDetail(AdminUser adminUser){
		String result="";
		result=btcTradeService.saveAdminUser(adminUser);
		return result;
	}
	
	@GET
	@Path("/getAdminInfo/{adminId}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public AdminUser fetchAdminUser(@PathParam("adminId")Integer adminId){
		AdminUser admin=btcTradeService.fetchAdminUser(adminId);
		return admin;
	}
	
	@GET
	@Path("/countryList")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Country> fetchCountries(){
		List<Country> countries=btcTradeService.fetchCountries();
		return countries;
	}
	
	@PUT
	@Path("/changePinNumber/{phoneNumber}/{oldPin}/{newPin}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public BTCResponse changePINNumber(@PathParam("phoneNumber")String phoneNumber,@PathParam("oldPin")String oldPin,@PathParam("newPin")String newPin){
		
		BTCResponse respnse=btcTradeService.changePinNumber(phoneNumber,oldPin,newPin);
		return respnse;
	}

	@PUT
	@Path("/verifyNumber/{phoneNumber}/{verifyCode}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public BTCResponse getVerifyNumber(@PathParam("phoneNumber")String phoneNumber,@PathParam("verifyCode")String code){
		BTCResponse result=null;
		VerifyPhoneNumber userloginFlag=btcTradeService.getUserWithPhoneNumber(phoneNumber);
		
		if(userloginFlag.getIsLoginFlag()==0){
		result=btcTradeService.getVerifyNumber(phoneNumber,code);
		VerifyPhoneNumber loginUp= btcTradeService.loginFlagUPUser(phoneNumber, code);
	    }else if(userloginFlag.getIsLoginFlag()==1){
	    	result= new BTCResponse();
	    	result.setResultForResponse("User Alerady Login");
	    }
		
		
		return result;
	}
	
	@POST
	@Path("/feedBack/{phoneNumber}/{point}/{comment}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public BTCResponse feedBackPersist(@PathParam("phoneNumber")String phoneNumbr,@PathParam("point")Double feedBackPoint,@PathParam("comment")String comment){
	   BTCResponse response=btcTradeService.saveFeedBackUser(phoneNumbr,feedBackPoint,comment);
	   return response;
	}
	
	@GET
	@Path("/emailVerify/{email}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public BTCResponse getVerifyEmailAddress(@PathParam("email")String emailId){
		BTCResponse response=new BTCeTradeEmailServiceImpl().emailVerify(emailId);
		
		return response;
	}
	@PUT
	@Path("/createAdminAddress/{adminName}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public BTCResponse createAdminUserAddress(@PathParam("adminName")String adminName){
		BTCResponse response=btcTradeService.createAdminAddress(adminName);
		return response;
	}
	 	@POST
	    @Path("/upload/{mobileNumber}")
	 	@Produces(value={MediaType.TEXT_PLAIN})
	public String uploadFileOnServer(@Context HttpServletRequest request,@PathParam("mobileNumber")String mobileNumber){
	 	    BTCResponse response= new BTCResponse();
	 		
	 	    try{
		
	 	     		
	 	  	 String contextPath = request.getSession().getServletContext().getRealPath("btcDocument");
	 		
			 System.out.println(contextPath);
			 File directory= new File(contextPath);
			 if (!directory.exists()) {
		            boolean result = directory.mkdir();  
		            if(result){
		                System.out.println("DIR created on server");
		            }
		        }
 
			 BTCResponse generteAddress= BTCAdminAddressGenerate.generateAdminAddress(mobileNumber, contextPath);
			 VerifyPhoneNumber phoneNumber=btcTradeService.getUserWithPhoneNumber(mobileNumber);
			 
			 UserWallet wallet= new UserWallet();
			 wallet.setBalance("50");
			 wallet.setWalletAddress(generteAddress.getUserAddress());
			 wallet.setUser(phoneNumber);
			 
			 response=btcTradeService.uploadFileinServer(wallet);
			 
		 
		 
		 
		 
		 System.out.println("SSSSSSSSSSSSSSSSsssss"+response.getResultForResponse());
		 return response.getResultForResponse();
		}catch(Exception e){
			e.printStackTrace();
			return "SUCCESS";
		}
		
	}
	 
	 	@GET
	    @Path("/download/image")
	    @Produces({"image/png", "image/jpg", "image/gif"})
	 	public Response downloadImageFile() {
	 		 
	        File file = new File("E:/BTCeTradeUserAddress/download/MyImageFile.png");
	 
	        ResponseBuilder responseBuilder = Response.ok((Object) file);
	        responseBuilder.header("Content-Disposition", "attachment; filename=\"MyPngImageFile.png\"");
	        return responseBuilder.build();
	    }
	 	
	 	
	 	@PUT
	    @Path("/logout/{phoneNumber}")
	    @Produces(value={MediaType.APPLICATION_JSON})
	 	public BTCResponse logoutUser(@PathParam("phoneNumber")String phoneNumber) {
	 		 BTCResponse response=btcTradeService.logoutUser(phoneNumber);
	        return response;
	    }
	
	 	
	 	
	 	@GET
	 	@Path("/displayImage/{mobileNumber}")
	 	@Produces({"image/png","image/jpg"})
	 	public Response getFile(@PathParam("mobileNumber") String mobileNumber,@Context HttpServletRequest request) 
	 	{
	 		String filePath="";
	 		System.out.println(request);
	 		String serverPath = request.getRealPath("serverDocument");
	 		System.out.println("Server Path"+serverPath);
			File fileDirectory= new File(serverPath);
			System.out.println(fileDirectory.listFiles().length);
			
			for(File file:fileDirectory.listFiles()){
				System.out.println(file.getName()+file.getAbsolutePath());
				String number=file.getName().replace(".png", "");
				System.out.println(number);
				if(file.getName().replace(".png", "").equalsIgnoreCase(mobileNumber)){
					
					filePath=file.getAbsolutePath();
				}
				
			}
			System.out.println("ServerPath:"+filePath);
	 	    File imageFile = new File(filePath);
	 	    return Response.ok(imageFile, "image/png").header("Inline", "filename=\"" + imageFile.getName() + "\"")
	 	            .build();
	 	}
	 	
	 	@GET
	 	@Path("validUserAddress/{useraddress}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCResponse validUser(@PathParam("useraddress") String userAddress){
		 	 
	 		BTCResponse response= btcTradeService.validUser(userAddress);
	 		return  response;
	 	}
	 	
	 	@PUT
	 	@Path("saveAdharCard/{mobileNumber}/{adharNumber}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCResponse saveAdharNumber(@PathParam("mobileNumber") String mobileNumber,@PathParam("adharNumber")String adharNumber){
		 	 
	 		BTCResponse response= btcTradeService.saveAdharNumber(mobileNumber, adharNumber);
	 		return  response;
	 	}
	 	
	 	@PUT
	 	@Path("saveEmail/{mobileNumber}/{emailId}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCResponse saveEmailAddress(@PathParam("mobileNumber") String mobileNumber,@PathParam("emailId")String emailId){
		 	 
	 		BTCResponse response= btcTradeService.saveEmailId(mobileNumber, emailId);
	 		return  response;
	 	}
	 	
	 	@PUT
	 	@Path("savePanCard/{mobileNumber}/{panNumber}/{username}/{fathername}/{dob}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCResponse savePanNumber(@PathParam("mobileNumber") String mobileNumber,@PathParam("panNumber")String panNumber
	 									,@PathParam("username")String username,@PathParam("fathername")String fatherName,@PathParam("dob")String dob){
		 	 
	 		BTCResponse response= btcTradeService.savePANNumber(mobileNumber, panNumber,username,fatherName,dob);
	 		return  response;
	 	}
	 	
	 	@GET
	 	@Path("/dashBoard/{phoneNumber}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public DashBoardUser dashBoardUser(@PathParam("phoneNumber")String phoneNumber){
		 	String network="BTC";
	 		try{ 
	 	
	 		  DashBoardUser response= btcTradeService.getInfoOFDashBoard(phoneNumber);
	 		return  response;
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		return new DashBoardUser();
		 	}
	 		
	 	}
	 	
		@GET
	 	@Path("/payementGateWayForm")
	 	@Produces(value={MediaType.TEXT_HTML,MediaType.TEXT_PLAIN})
	 	public String paymentMode(@Context HttpServletRequest request,@Context HttpServletResponse response){
			try{
			JavaIntegrationKit integrationKit = new JavaIntegrationKit();
			String base_url="https://secure.payu.in";
			String action1 = base_url.concat("/_payment");
			System.out.println(request);
			request.setAttribute("key", "Rjv64D4M");
			request.setAttribute("hash", "");
			request.setAttribute("amount", 200);
			request.setAttribute("firstname","pawanbarthunia" );
			request.setAttribute("email", "pawanbarthunia@gmail.com");
			request.setAttribute("productinfo", "BTC");
			request.setAttribute("surl", "http://diittech.in");
			request.setAttribute("furl", "http://diittech.in");
			request.setAttribute("action", action1);
			
			Map<String, String> values = integrationKit.hashCalMethod(request, response);
			
			
			System.out.println(request.getAttribute("amount"));
			ObjectEncryption encryptObject= new ObjectEncryption();
			/*TransactionForPaymentActivity tranForPaymentActivity=(TransactionForPaymentActivity) TransactionFactory.getTransactionInstance("payment");
        	encryptObject=tranForPaymentActivity.getTransactionId();*/
        	
			request.setAttribute("txnid", encryptObject.getDcrypt());
	        if(values.get("txnid")==null){
	        	values.put("txnid", encryptObject.getDcrypt());
	        
	        }
	        
			request.setAttribute("service_provider", "payu_paisa");
			 values.put("key", String.valueOf(request.getAttribute("key"))); 
			 values.put("amount", String.valueOf(request.getAttribute("amount"))); 
			 values.put("firstname", String.valueOf(request.getAttribute("firstname"))); 
			 values.put("email",String.valueOf(request.getAttribute("email"))); 
			 values.put("phone", "9166001205");
			 values.put("productinfo",String.valueOf(request.getAttribute("productinfo")) ); 
			 values.put("surl", String.valueOf(request.getAttribute("surl"))); 
			 values.put("furl", String.valueOf(request.getAttribute("furl"))); 
			 /*values.put("txnid",String.valueOf(request.getAttribute("txnid"))); */
			 values.put("service_provider",String.valueOf(request.getAttribute("service_provider")) ); 
			 values.put("action",String.valueOf(request.getAttribute("action")) ); 
				//Map<String, String> values = integrationKit.hashCalMethod(request, response);
/*				com.btcetrade.bitcoin.controller.myServlet server= new com.btcetrade.bitcoin.controller.myServlet();
				server.doGet(request, response);
				  System.out.println(request.getAttribute("key"));
				  values.put("key", String.valueOf(request.getAttribute("key")));
				  
				  */
				  System.out.println("Action"+values.get("action"));
			        String htmlResponse = "<html> <body> \n"
			                + "      \n"
			                + "  \n"
			                + "  <h1>BTCe-Trade Payment  </h1>\n"
			                + "  \n" + "<div>"
			                + "        <form id=\"payuform\" action=\"" + values.get("action") + "\"  name=\"payuform\" method=POST >\n"
			                + "      <input type=\"hidden\" name=\"key\" value=" + values.get("key").trim() + ">"
			                + "      <input type=\"hidden\" name=\"hash\" value=" + values.get("hash").trim() + ">"
			                + "      <input type=\"hidden\" name=\"txnid\" value=" + values.get("txnid").trim() + ">"
			                + "      <table>\n"
			                + "        <tr>\n"
			                + "          <td><b>Mandatory Parameters</b></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "         <td>Amount: </td>\n"
			                + "          <td><input name=\"amount\" value=" + values.get("amount").trim() + " /></td>\n"
			                + "          <td>First Name: </td>\n"
			                + "          <td><input name=\"firstname\" id=\"firstname\" value=" + values.get("firstname").trim() + " /></td>\n"
			                + "        <tr>\n"
			                + "          <td>Email: </td>\n"
			                + "          <td><input name=\"email\" id=\"email\" value=" + values.get("email").trim() + " /></td>\n"
			                + "          <td>Phone: </td>\n"
			                + "          <td><input name=\"phone\" value=" + values.get("phone") + " ></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>Product Info: </td>\n"
			                + "<td><input name=\"productinfo\" value=" + values.get("productinfo").trim() + " ></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>Success URI: </td>\n"
			                + "          <td colspan=\"3\"><input name=\"surl\"  size=\"64\" value=" + String.valueOf(request.getAttribute("surl")) + "></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>Failure URI: </td>\n"
			                + "          <td colspan=\"3\"><input name=\"furl\" value=" + String.valueOf(request.getAttribute("furl")) + " size=\"64\" ></td>\n"
			                + "        </tr>\n"
			                + "\n"
			                + "        <tr>\n"
			                + "          <td colspan=\"3\"><input type=\"hidden\" name=\"service_provider\" value=\"payu_paisa\" /></td>\n"
			                + "        </tr>\n"
			                + "             <tr>\n"
			                + "          <td><b>Optional Parameters</b></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>Last Name: </td>\n"
			                + "          <td><input name=\"lastname\" id=\"lastname\" value=" + values.get("lastname") + " ></td>\n"
			                + "          <td>Cancel URI: </td>\n"
			                + "          <td><input name=\"curl\" value=" + values.get("curl") + " ></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>Address1: </td>\n"
			                + "          <td><input name=\"address1\" value=" + values.get("address1") + " ></td>\n"
			                + "          <td>Address2: </td>\n"
			                + "          <td><input name=\"address2\" value=" + values.get("address2") + " ></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>City: </td>\n"
			                + "          <td><input name=\"city\" value=" + values.get("city") + "></td>\n"
			                + "          <td>State: </td>\n"
			                + "          <td><input name=\"state\" value=" + values.get("state") + "></td>\n"
			                + "        </tr>\n"
			                + "        <tr>\n"
			                + "          <td>Country: </td>\n"
			                + "          <td><input name=\"country\" value=" + values.get("country") + " ></td>\n"
			                + "          <td>Zipcode: </td>\n"
			                + "          <td><input name=\"zipcode\" value=" + values.get("zipcode") + " ></td>\n"
			                + "        </tr>\n"
			                + "          <td>UDF1: </td>\n"
			                + "          <td><input name=\"udf1\" value=" + values.get("udf1") + "></td>\n"
			                + "          <td>UDF2: </td>\n"
			                + "          <td><input name=\"udf2\" value=" + values.get("udf2") + "></td>\n"
			                + " <td><input name=\"hashString\" value=" + values.get("hashString") + "></td>\n"
			                + "          <td>UDF3: </td>\n"
			                + "          <td><input name=\"udf3\" value=" + values.get("udf3") + " ></td>\n"
			                + "          <td>UDF4: </td>\n"
			                + "          <td><input name=\"udf4\" value=" + values.get("udf4") + " ></td>\n"
			                + "          <td>UDF5: </td>\n"
			               + "          <td><input name=\"udf5\" value=" + values.get("udf5") + " ></td>\n"
			                 + "          <td>PG: </td>\n"
			               + "          <td><input name=\"pg\" value=" + values.get("pg") + " ></td>\n"
			                + "        <td colspan=\"4\"><input type=\"submit\" value=\"Submit\"  /></td>\n"
			                + "      \n"
			                + "    \n"
			                + "      </table>\n"
			                + "    </form>\n"
			                + " <script> "
			                + " document.getElementById(\"payuform\").submit(); "
			                + " </script> "
			                + "       </div>   "
			                + "  </body>\n"
			                + "  \n"
			                + "</html>";
			// return response
			        //writer.println(htmlResponse);
				  
				  
			
			String paymentURL="http://localhost:8880/BTCPaymentGateWay/";
/*			String paymetHref="<a href='http://localhost:8880/BTCPaymentGateWay/'>payment</a>";
			request.setAttribute("amount", 200);
		
String htmlForm="<html>"+
    "<head>"
    +"</head>"

    +"<body>"


        +"<h1>BTCe-Trade PaymentGateWay </h1>"

       + "<form action=\"myServlet\"  name=\"payuform\" method=POST >"
        +"<input type=\"hidden\" name=\"key\"value=\"Rjv64D4M\" />"
         +"<input type=\"hidden\" name=\"hash_string\" value=\"\" />"
         +"<input type=\"hidden\" name=\"hash\" />"

           +"<input type=\"hidden\" name=\"txnid\" value=\"12345\"/>"

           +"<table>"
                +"<tr>"
                    +"<td><b>Mandatory Parameters</b></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>Amount: </td>"
                    +"<td><input name=\"amount\"  /></td>"
                    +"<td>First Name: </td>"
                    +"<td><input name=\"firstname\" id=\"firstname\"/></td>"
                +"</tr>"
               +"<tr>"
                    +"<td>Email: </td>"
                    +"<td><input name=\"email\" id=\"email\"   /></td>"
                    +"<td>Phone: </td>"
                    +"<td><input name=\"phone\"  /></td>"
               +"</tr>"
                +"<tr>"
                    +"<td>Product Info: </td>"
                    +"<td colspan=\"3\"><textarea name=\"productinfo\" >  </textarea></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>Success URI: </td>"
                    +"<td colspan=\"3\"><input name=\"surl\"  size=\"64\"  /></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>Failure URI: </td>"
                    +"<td colspan=\"3\"><input name=\"furl\"  size=\"64\" /></td>"
                +"</tr>"

                +"<tr>"
                    +"<td colspan=\"3\"><input type=\"hidden\" name=\"service_provider\" value=\"payu_paisa\" /></td>"
                +"</tr>"
                 +"<tr>"
                   +"<td><b>Optional Parameters</b></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>Last Name: </td>"
                    +"<td><input name=\"lastname\" id=\"lastname\"  /></td>"
                    +"<td>Cancel URI: </td>"
                    +"<td><input name=\"curl\" value=\"\" /></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>Address1: </td>"
                    +"<td><input name=\"address1\" /></td>"
                    +"<td>Address2: </td>"
                    +"<td><input name=\"address2\"  /></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>City: </td>"
                    +"<td><input name=\"city\"  /></td>"
                    +"<td>State: </td>"
                    +"<td><input name=\"state\"  /></td>"
               +"</tr>"
                +"<tr>"
                    +"<td>Country: </td>"
                   +"<td><input name=\"country\"  /></td>"
                    +"<td>Zipcode: </td>"
                   +"<td><input name=\"zipcode\"  /></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>UDF1: </td>"
                    +"<td><input name=\"udf1\"  /></td>"
                    +"<td>UDF2: </td>"
                    +"<td><input name=\"udf2\"  /></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>UDF3: </td>"
                    +"<td><input name=\"udf3\"   /></td>"
                    +"<td>UDF4: </td>"
                    +"<td><input name=\"udf4\"  /></td>"
                +"</tr>"
                +"<tr>"
                    +"<td>UDF5: </td>"
                    +"<td><input name=\"udf5\"  /></td>"
                   + "<td>PG: </td>"
                   +"<td><input name=\"pg\"  /></td>"
                +"</tr>"

               + "<td colspan=\"4\"><input type=\"submit\" value=\"Submit\"  /></td>"
               +"</tr>"
            +"</table>"
        +"</form>"
    +"</body>"
+"</html>";
			
				
			 
	 	
		 		
*/		 		
		 		
		 		return  htmlResponse;
			}catch(Exception e){
		 		e.printStackTrace();
		 		return new String();
		 	}
	 		
	 	}
	
	 	
	 	@GET
	 	@Path("/zebpayLiveCompare")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public APIResponse dashBoardUser(){
		 	try{ 
	 		APIResponse apiResponse=ZebpayComparePrice.zebpayBitCoinCopmareRate();
	 		
	 		return  apiResponse;
		 	}catch(Exception e){
		 		e.printStackTrace();
		 		return new APIResponse ();
		 	}
	 	}
	 	
	 
	 	@GET
	 	@Path("/exsistUser/{mobileNumber}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCResponse exsistingUserORNot(@PathParam("mobileNumber")String mobileNumber){
	 		
	 		BTCResponse response=btcTradeService.exsistingUserORNot(mobileNumber);
	 		return response;
	 	}
	 	@GET
	 	@Path("/exsistUseraddress/{mobileNumber}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCResponse exsistingUserORNotAddress(@PathParam("mobileNumber")String mobileNumber){
	 		
	 		BTCResponse response=btcTradeService.esistAddress(mobileNumber);
	 		return response;
	 	}

	 	@PUT
	 	@Path("/saveUserAddress/{mobileNumber}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public UserAddress saveUserAddress(@PathParam("mobileNumber")String mobileNumber){
	 		String APIKey="6957-ab56-4a87-9599";
	 		UserAddress userAddress= new BlockChainApiImpl().getNewAddress(APIKey);
	 		BTCResponse response=btcTradeService.saveUserAddress(mobileNumber, userAddress.getAddress());
	 		
	 		if(response.getResultForResponse().equalsIgnoreCase("AddressSave")){
	 			return userAddress;
	 		}else{
	 			return new UserAddress();
	 		}
	 		
	 	}
	 	
	 	@GET
	 	@Path("/razourPay/{amount}/{phonenumber}")
	 	@Produces(value=MediaType.TEXT_HTML)
	 	public String razourPAyPayment(@Context HttpServletRequest request, @PathParam("amount")float amount,@PathParam("phonenumber")String phoneNumber){
	 		System.out.println("SSSS");
	 		String adminNumber="9166001205";
//	 		String merchantKey="9WDCdz4B6BSnwz";
	 		System.out.println("..........."+request.getContextPath());
	 		VerifyPhoneNumber adminUser= btcTradeService.getUserWithPhoneNumber(adminNumber);
	 		VerifyPhoneNumber normalUser= btcTradeService.getUserWithPhoneNumber(phoneNumber);
	 		String URL=request.getContextPath().concat("/btcetrade/btcCoin/paymentInformation/").concat(phoneNumber).concat("/").concat(String.valueOf(amount));
//	 		String merchantId2="rzp_test_IXzNGVhHCNH2lv";
	 		String htmpResponse="<form action="+URL+" method=\"GET\">\n"
	 			//rzp_test_IXzNGVhHCNH2lv		
	 				+ "<script"
	 				+ " src='https://checkout.razorpay.com/v1/checkout.js'"+"\n"
	 				+" data-key='"+"rzp_test_IXzNGVhHCNH2lv"+"'"+"\n"
	 				+" data-amount='"+amount+"'"+"\n"
	 				+" data-buttontext='Buy BitCoin'"+"\n"
	 				+" data-name='BTCe-Trade PaymentGateWay'"+"\n"
	 				+" data-description='BTC'"+"\n"
	 				+" data-image='/logo.png'"+"\n"
	 				+" data-prefill.name='"+normalUser.getUsername()+"'"+"\n"
	 				+" data-prefill.email='"+normalUser.getEmailId()+"'"+"\n"
	 				+" data-theme.color='#F37254'"+"\n"
	 				+" > </script>"+"\n"
/*	 				+"<input type=\"hidden\" value=\"Hidden Element\" name=\"hidden\">"+"\n"
*/	 				+"</form>";
    
       System.out.println(htmpResponse);
     	return htmpResponse;
	 	}
	 	
	 	@GET
	 	@Path("/paymentInformation/{phonenumber}/{amount}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public BTCTransaction getPAymentInformation(@PathParam("phonenumber")String phoneNumber,@PathParam("amount")double amount,@QueryParam("razorpay_payment_id")String paymentId){
	 		System.out.println("Coming"+paymentId+phoneNumber+amount);
	 		BTCTransaction transactionBTC= new BTCTransaction();
	 		int idx=0;
	 		try{
	 			
	 			double actualRate= 800000.00;
	 			
	 			
	 			/*RazorpayClient paymentGateWay= new RazorpayClient("rzp_test_IXzNGVhHCNH2lv", "Dry6UnGJe8HV0fMW4dAvIYhm");
	 		    
	 			System.out.println(paymentGateWay);
	 			List<Payment> payments = paymentGateWay.Payments.fetchAll();
	 			String[] paymentID= new String[payments.size()];
	 			for(Payment pay:payments){
	 				System.out.println(pay.get("amount"));
	 				paymentID[idx]=pay.get("id");
	 				System.out.println(pay.get("id"));
	 				System.out.println(pay.get("created_at"));
	 				idx++;
	 			}
	 			Payment lastPayment= paymentGateWay.Payments.fetch(paymentID[0]);
	 			System.out.println("Last Payment Amount"+lastPayment.get("amount"));
	 			System.out.println("Last Payment Id"+lastPayment.get("id"));
	 			System.out.println("Last Payment Created"+lastPayment.get("created_at"));	
	 			JSONObject json= new  JSONObject();
	 			json.put("amount",lastPayment.get("amount"));
	 			System.out.println(paymentGateWay.Payments.capture("payment_id", json));
                
	 			JSONObject captureRequest = new JSONObject();
	 			captureRequest.put("amount", 200000); // Amount should be in paise 
	 			Payment payment = paymentGateWay.Payments.capture("payment_id", captureRequest);
	 			
	 			
	 			
	 			System.out.println(payment);*/
	 			
	 			if(paymentId!=null){
	 				HttpURLConnection urlConnection=null;
	 				String mobileNumberOfAdmin="9166001205";
	 				String apiKey="6957-ab56-4a87-9599";
	 				String network="BTC";
	 			    VerifyPhoneNumber adminUser= btcTradeService.getUserWithPhoneNumber(mobileNumberOfAdmin);
	 			    VerifyPhoneNumber normalUserNumber= btcTradeService.getUserWithPhoneNumber(phoneNumber);
	 			    String fromAddress=adminUser.getUserAddress();
	 			    String toAddress=normalUserNumber.getUserAddress();
	 			    String secret=adminUser.getSecretKey();
	 			    System.out.println(fromAddress+","+toAddress+","+secret+adminUser.getAdminSing());
	 			   String bitCoinTransaction=null;
	 			   ObjectEncryption btcFailure=null;
	 			  SaleBitCoin saleBitCoin=null;
	 			   String failureTransaction="";
	 			  double btcAmount=0.0; 
	 			   if(adminUser.getAdminSing().equalsIgnoreCase("pawan")){
	 			    	System.out.println("Aya");
	 			    	
	 			    	
	 			    	btcAmount=amount/actualRate;
	 			    	System.out.println(btcAmount);
	 			    	 saleBitCoin=new BlockChainApiImpl().saleBitCoinAddress(apiKey, fromAddress, toAddress, network,btcAmount, secret);
	 			    	bitCoinTransaction=saleBitCoin.getTxid();
	 			    }else{
	 			    	 btcFailure=TransactionFactory.getTransactionInstance("BTC").getTransactionId("BTC", "FC");
	 			    	 bitCoinTransaction=btcFailure.getDcrypt();
	 			    	 btcAmount=0.0;
	 			    }
	 			    
	 			    System.out.println("PaymentId"+paymentId+"UserId"+normalUserNumber.getVerifyId()+"BitCoin Transaction"+bitCoinTransaction+"from Address"+fromAddress+"toAddress"+toAddress+"PAyment"+amount+"BITCoin"+btcAmount);
	 			
	 			    
	 			    
	 			    BTCTransaction transaction=new BTCTransaction();
	 			    VerifyPhoneNumber verifyPhoneNumber= new VerifyPhoneNumber();
	 			    verifyPhoneNumber.setVerifyId(normalUserNumber.getVerifyId());
	 			    
	 			    transaction.setBitcoinAmount(saleBitCoin.getAmountwidrawal());
	 			    transaction.setBitcoinTransaction(bitCoinTransaction);
	 			    transaction.setFromAddress(fromAddress);
	 			    transaction.setToAddress(toAddress);
	 			    transaction.setPaymetTransaction(paymentId);
	 			    transaction.setPaymentAmount(amount);
	 			    transaction.setUser(verifyPhoneNumber);
	 			    
	 			    BTCResponse btcResponse=btcTradeService.saveTransaction(transaction);
	 			    if(btcResponse.getResultForResponse().equalsIgnoreCase("Save Trasaction")){
	 			    	return transaction;
	 			    }else{
	 			    	return transactionBTC;
	 			    }
	 				
	 			   
	 				
	 			}
	 		}catch(Exception e){
	 			return transactionBTC;
	 		}
			return transactionBTC;
	 		
	 	
	 	}
	 	@PUT
	 	@Path("/savePanCard/{phoneNumber}/{username}/{fathername}/{dob}")
	 	@Produces(value={MediaType.APPLICATION_JSON})
	 	public BTCResponse savePANCardDetail(@PathParam("phoneNumber")String phoneNumber,@PathParam("username")String username,@PathParam("fathername")String fathername,@PathParam("dob")String dob){
	 		BTCResponse response=btcTradeService.savePANCardDetail(phoneNumber,username,fathername,dob);
	 		return response;
	 	}
	 	@POST
	 	@Path("/saleBitCoin/{phoneNumber}/{applicationKEY}/{fromAddress}/{toAddress}/{network}/{amount}/{secretPIN}")
	 	@Produces(value=MediaType.APPLICATION_JSON)
	 	public SaleBitCoin sellBitCoinBTCeTrade(@PathParam("phoneNumber")String phoneNumber,@PathParam("applicationKEY")String apiKey,
	 			@PathParam("fromAddress")String formAddress,
	 			@PathParam("toAddress")String toAddress,
	 			@PathParam("network")String network,@PathParam("amount")double amount,@PathParam("secretPIN")String secretPIN){
	 			apiKey="6957-ab56-4a87-9599";
	 		    network="BTC";
	 		    VerifyPhoneNumber user= btcTradeService.getUserWithPhoneNumber(phoneNumber);
	 		    System.out.println("#####"+user.getVerifyId());
	 		 SaleBitCoin saleBitCoin=new BlockChainApiImpl().saleBitCoinAddress(apiKey, formAddress, toAddress, network, amount, secretPIN);
	 		 System.out.println(saleBitCoin.getErrorMessage());  
	 		 
	 		 SellBitCoinBTCeTrade saleBitCoinBtceTrade= new SellBitCoinBTCeTrade();
	 		 saleBitCoinBtceTrade.setAmount_req(amount);
	 		 saleBitCoinBtceTrade.setErrorMessage(saleBitCoin.getErrorMessage());
	 		 saleBitCoinBtceTrade.setEstimatedNetworkFee(saleBitCoin.getEstimatedNetworkFee());
	 		saleBitCoinBtceTrade.setFromAddress(formAddress);
	 		saleBitCoinBtceTrade.setMaxWithdrawalAvailable(saleBitCoin.getMaxWithdrawalAvailable());
	 		saleBitCoinBtceTrade.setToAddressReq(toAddress);
	 		saleBitCoinBtceTrade.setMinimumBalanceNeeded(saleBitCoin.getMinimumBalanceNeeded());
	 		saleBitCoinBtceTrade.setSecreateKey(secretPIN);
	 		saleBitCoinBtceTrade.setNetwork(network);
	 		saleBitCoinBtceTrade.setStatus(saleBitCoin.getStatus());
	 		VerifyPhoneNumber userSale= new VerifyPhoneNumber();
	 		userSale.setVerifyId(user.getVerifyId());
	 		saleBitCoinBtceTrade.setUser(userSale);
	 		
	 		String saveSaleBitCoin=btcTradeService.saveSaleBitCoinBTCeTrade(saleBitCoinBtceTrade);
	 		if(saveSaleBitCoin.equalsIgnoreCase("SaveSale")){
	 			return saleBitCoin;
	 		}else{
	 			return new SaleBitCoin();
	 		}
	 	
	 	
	 	
	 	}
	 	
	 	@GET
	 	@Path("/bitCoinRate/{network}/{defaultCurrency}/{currencyRateINR}")
	 	@Produces(value={MediaType.APPLICATION_JSON})
	 	public BitCoinRate getRateBitCoinRate(@PathParam("network")String network,@PathParam("defaultCurrency")String defaultCurrency,@PathParam("currencyRateINR")String curencyRateINR){
	 		BitCoinRate bitCoinRate=btcTradeService.getBitCoinRate(network,defaultCurrency,curencyRateINR);
	 		return bitCoinRate;
	 	}
	 	@GET
	 	@Path("/verifyEmail/{phoneNumber}/{emailId}")
	 	@Produces(value={MediaType.APPLICATION_JSON})
	 	public BTCResponse verifyEmailId(@PathParam("phoneNumber")String phoneNumber,@PathParam("emailId")String emailId){
	 		 
	 		BTCResponse verifyEmail=btcTradeService.verifyEmailId(phoneNumber,emailId);
	 		return verifyEmail;
	 		
	 	}
	 	
	 	@GET
	 	@Path("/verifyPanCard/{phoneNumber}/{panCard}")
	 	@Produces(value={MediaType.APPLICATION_JSON})
	 	public BTCResponse verifyPanCard(@PathParam("phoneNumber")String phoneNumber,@PathParam("panCard")String panCard){
	 		 
	 		BTCResponse verifyPanCard=btcTradeService.verifyPanCard(phoneNumber,panCard);
	 		return verifyPanCard;
	 		
	 	}
	 	@GET
	 	@Path("/verifyAdharCard/{phoneNumber}/{adharCard}")
	 	@Produces(value={MediaType.APPLICATION_JSON})
	 	public BTCResponse verifyAdharCard(@PathParam("phoneNumber")String phoneNumber,@PathParam("adharCard")String adharCard){
	 		 
	 		BTCResponse verifyAdharCard=btcTradeService.verifyAdharCard(phoneNumber,adharCard);
	 		return verifyAdharCard;
	 		
	 	}
	 	
	 	@GET
	 	@Path("/fetchDocument/{mobileNumber}")
	 	@Produces(value={MediaType.APPLICATION_JSON})
	 	public KYCVerify fetchDocument(@PathParam("mobileNumber")String mobileNumber){
	 		KYCVerify kycProof=btcTradeService.fetchKYCOfUser(mobileNumber);
	 		return kycProof;
	 	}
}
