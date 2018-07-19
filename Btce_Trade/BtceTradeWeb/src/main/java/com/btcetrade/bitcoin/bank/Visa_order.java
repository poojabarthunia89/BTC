package com.btcetrade.bitcoin.bank;
import java.math.BigDecimal;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.security.Security;
 
public class Visa_order {

	// Enter the MerchantCode you have retrieved from Bibit
    //private final static String merchantCode = "<MERCHANTCODE>";
    private final static String merchantCode = "JAMS";
 
    // Enter the username you have retrieved from Bibit
    // The username will generally be the merchantCode
    //private final static String userName = "<USERNAME>";
    private final static String userName = "JAMS";
 
 
     
    // Enter the password you have retrieved from Bibit
    //private final static String passWord = "<PASSWORD>";
        private final static String passWord = "2Re7rupr";
 
    // Enter here the URL
    // for the production environment use: https://secure.bibit.com:443/jsp/merchant/xml/paymentService.jsp
    // for the test environment use: https://secure-test.bibit.com:443/jsp/merchant/xml/paymentService.jsp
    private final static String location = "https://secure-test.bibit.com:443/jsp/merchant/xml/paymentService.jsp";
     
    private static HttpURLConnection huc;
     
    /** Creates new submitXMLExample */
    public Visa_order() {
    }  
     
    /**
     * @param args the command line arguments
     */
    public static void main (String args[]) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        System.out.println(sendXml(createXML()));
    }
     
    /*
     * creates an XML message
     * In this case an XML message is composed that sends a single order with a paymentmethod mask
     */
    private static String createXML() {
        StringBuffer xmlMessage = new StringBuffer();
        // order code should be unique, the date is unique..
        String orderCode= ""+System.currentTimeMillis();
         
         xmlMessage.append("<?xml version=\"1.0\"?>");
             xmlMessage.append("<!DOCTYPE paymentService  PUBLIC '-//Bibit//DTD Bibit PaymentService v1//EN' 'http://dtd.bibit.com/paymentService_v1.dtd'>");
                 xmlMessage.append("<paymentService  version='1.4' merchantCode='JAMS'>");
         xmlMessage.append("<submit>");
         
       xmlMessage.append("<order orderCode=\""+orderCode+"\">");
           
            xmlMessage.append("<description>Your Order</description>");
            xmlMessage.append("<amount currencyCode=\"NLG\" value=\"1060000\" exponent=\"2\"/>");
            xmlMessage.append("<orderContent>This is payment 2010 </orderContent>");
                xmlMessage.append("<paymentDetails>");
                 
                 
       xmlMessage.append("<VISA-SSL>");
                xmlMessage.append("<cardNumber>4444333322221111</cardNumber>");
                xmlMessage.append("<expiryDate><date month='12' year='2011'/></expiryDate>");
                xmlMessage.append("<cardHolderName>J. Shopper</cardHolderName>");
                xmlMessage.append("<cvc>1234</cvc>");
                 
                        xmlMessage.append("<cardAddress>");
            xmlMessage.append("<address>");
                xmlMessage.append("<firstName>John</firstName>");
                    xmlMessage.append("<lastName>Shopper</lastName>");
                xmlMessage.append("<street>11 Shopperstreet</street>");
                xmlMessage.append("<houseName>shreeganesh</houseName>");
                xmlMessage.append("<houseNumber>12345</houseNumber>");
                xmlMessage.append("<houseNumberExtension>10101010</houseNumberExtension>");
                xmlMessage.append("<postalCode>ALL</postalCode>");
                xmlMessage.append("<city>Shoppercity</city>");
                xmlMessage.append("<state>Shoppercity</state>");
                xmlMessage.append("<countryCode>TP</countryCode>");
                xmlMessage.append("<telephoneNumber>0123456789</telephoneNumber>");
            xmlMessage.append("</address>");
            xmlMessage.append("</cardAddress>");
                         
                         
    xmlMessage.append("</VISA-SSL>");
                         
                         
                         
                         
                xmlMessage.append("</paymentDetails>");
     xmlMessage.append("</order>");
                xmlMessage.append("</submit>");
            xmlMessage.append("</paymentService>");
 
     
    return xmlMessage.toString();
    }
     
    /*
     * Sends a xml message using a secure connection and username password authentication
     */
    public static String sendXml(String theXMLMessage) {
        StringBuffer response = new StringBuffer();
        try{
        	String val="0.0000000001";
        	BigDecimal decimal= new BigDecimal(val);
        	System.out.println(decimal);
            URL url = new URL(location);
            huc = (HttpURLConnection)url.openConnection();
            huc.setRequestMethod("POST");
            huc.setRequestProperty("Authorization", "Basic "+encodeBase64((userName+":"+passWord).getBytes()));
            huc.setRequestProperty("Host", url.getHost());
            huc.setDoOutput(true);
            PrintWriter writer = new PrintWriter(huc.getOutputStream());
            writer.println(theXMLMessage);
            writer.flush();
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                response.append(line);
                response.append("\n");
            }
        }catch(MalformedURLException mfue){ 
            response.append("The URL is incorrect : ");
            response.append(location);
        }catch(IOException ioe){ 
            response.append("A connection problem has occured, this could be caused by:\n");
            response.append(" - The userName is not correct : "+ userName+ "; Check with bibit support at support@bibit.com\n");
            response.append(" - The password is not correct ; Check with bibit support at support@bibit.com\n");
            response.append(" - The URL is not correct : "+ location +" ; Review the documentation\n");
            response.append(" - You are behind a firewall that does not allow secure connections; Contact your network administrator\n");
        }
        return response.toString().trim();
    }
     
     /*
     * encodes a string to Base 64
     */
    public static String encodeBase64(byte[] d)
        {
        if (d == null) return null;
        byte data[] = new byte[d.length+2];
        System.arraycopy(d, 0, data, 0, d.length);
        byte dest[] = new byte[(data.length/3)*4];
 
        // 3-byte to 4-byte conversion
        for (int sidx = 0, didx=0; sidx < d.length; sidx += 3, didx += 4){
            dest[didx]   = (byte) ((data[sidx] >>> 2) & 077);
            dest[didx+1] = (byte) ((data[sidx+1] >>> 4) & 017 | (data[sidx] << 4) & 077);
            dest[didx+2] = (byte) ((data[sidx+2] >>> 6) & 003 | (data[sidx+1] << 2) & 077);
            dest[didx+3] = (byte) (data[sidx+2] & 077);
        }
 
        // 0-63 to ascii printable conversion
        for (int idx = 0; idx <dest.length; idx++)
        {
            if (dest[idx] < 26)     dest[idx] = (byte)(dest[idx] + 'A');
            else if (dest[idx] < 52)  dest[idx] = (byte)(dest[idx] + 'a' - 26);
            else if (dest[idx] < 62)  dest[idx] = (byte)(dest[idx] + '0' - 52);
            else if (dest[idx] < 63)  dest[idx] = (byte)'+';
            else dest[idx] = (byte)'/';
        }
 
        // add padding
        for (int idx = dest.length-1; idx > (d.length*4)/3; idx--){
            dest[idx] = (byte)'=';
        }
        return new String(dest);
    }
	
}
