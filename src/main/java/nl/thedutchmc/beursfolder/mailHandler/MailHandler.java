package nl.thedutchmc.beursfolder.mailHandler;

import java.io.File;
import java.util.Properties;
import java.util.StringJoiner;

import javax.mail.*;  
import javax.mail.internet.*;  

public class MailHandler {

	public void sendMail(String target, String firstName, String surName, boolean option1, boolean option2, boolean option3, boolean option4) {
		
		String username = "";
		String password = "";
		
		Properties properties = new Properties();
		
		//Information about the SMPT server
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smptp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.hostnet.nl");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.hostnet.nl");
		
		//Start a mail session, log in.
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication () {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message message = new MimeMessage(session);
		try {
			
			//Set basic info for the Email
			message.setFrom(new InternetAddress("noreply@thedutchmc.nl"));
			message.setRecipients(
			Message.RecipientType.TO, InternetAddress.parse(target));
			message.setSubject("Nog eentje dan, weer met een chart");
			
			//TEMP
			String[] optMsg = {"no opt","","","",""};
			optMsg[0] = "no opt";
			
			if(option1) {
				optMsg[1] = "opt1";
			} else {
				optMsg[1] = "n";
			}
			
			if(option2) {
				optMsg[2] = "opt2";
			} else {
				optMsg[2] = "n";
			}
			
			if(option3) {
				optMsg[3] = "opt3";
			} else {
				optMsg[3] = "n";
			}
			
			if(option4) {
				optMsg[4] = "opt4";
			} else {
				optMsg[4] = "n";

			}
			
			
			String finalOpt;
			finalOpt = optMsg[1] + optMsg[2] + optMsg[3] + optMsg[4];
			
			//The message that is in the Email
			String msg = "Dear " + firstName + ",";
			
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg + "\n" + finalOpt, "text/html");
			
			//The attachment
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			attachmentBodyPart.attachFile(new File("C:\\Users\\tobia\\Pictures\\mekanism_fusion_chart.png")); 
			
			//Add the parts together
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			multipart.addBodyPart(attachmentBodyPart);
			 
			message.setContent(multipart);
			 
			//Finally, send the email
			Transport.send(message);
			
			System.out.println("Sent an Email to: " + target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}