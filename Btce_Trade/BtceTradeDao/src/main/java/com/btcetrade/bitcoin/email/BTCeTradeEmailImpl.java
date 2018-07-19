package com.btcetrade.bitcoin.email;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class BTCeTradeEmailImpl  {

	public String sendEmail(String emailId,String pinNumber){
		final String username = "diittech234@gmail.com";
		final String password = "diit@123#";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
			message.setSubject("Forget PIN Number");
			message.setText(
				 "\t\tyour PIN Number is: \t"+pinNumber+"\n\nRegard's\n\n BTCe-Trade Support Team\n\n\n\t\t\tIf you have any query please contact BTCeTrade support team.");

			Transport.send(message);

			System.out.println("Done");
			return "Done";
		} catch (MessagingException e) {
			return "NOTDone";
		}
		
	}
	public static void main(String[] args) {
		new BTCeTradeEmailImpl().sendEmail("pawanbarthunia@gmail.com","2345");
	}
}
