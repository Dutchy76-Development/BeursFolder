package nl.thedutchmc.beursfolder.mailHandler;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.*;  
import javax.mail.internet.*;

import nl.thedutchmc.beursfolder.BeursFolder;
import nl.thedutchmc.beursfolder.sockethandler.SocketHandler;

public class MailHandler {

	public void sendMail(String target, String firstName, String surName, boolean option1, boolean option2, boolean option3, boolean option4, String companyName, String phoneNumber) {
		int retryCounter = 0;
		
		String SUBJECT = BeursFolder.SUBJECT;
		
		String TOKEN = BeursFolder.TOKEN;
		
		String EMAIL = BeursFolder.MAIL_USERNAME;
		String PASSWORD = BeursFolder.MAIL_PASSWORD;
		
		String HOST = BeursFolder.HOST;
		String PORT = BeursFolder.PORT;
		String SSL_TRUST = BeursFolder.SSL_TRUST;
		
		boolean STARTTLS = BeursFolder.STARTTLS;
		boolean AUTH = BeursFolder.AUTH;
		
		String OPTION1URL = BeursFolder.OPTION1URL;
		String OPTION2URL = BeursFolder.OPTION2URL;
		String OPTION3URL = BeursFolder.OPTION3URL;
		String OPTION4URL = BeursFolder.OPTION4URL;

		Properties properties = new Properties();
		
		//Information about the SMPT server
		properties.put("mail.smtp.auth", AUTH);
		properties.put("mail.smptp.starttls.enable", STARTTLS);
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.port", PORT);
		properties.put("mail.smtp.ssl.trust", SSL_TRUST);
		
		//Start a mail session, log in.
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication () {
				return new PasswordAuthentication(EMAIL, PASSWORD);
			}
		});
		
		Message message = new MimeMessage(session);
		try {
			
			//Set basic info for the Email
			message.setFrom(new InternetAddress(EMAIL));
			message.setRecipients(
			Message.RecipientType.TO, InternetAddress.parse(target));
			message.setSubject(SUBJECT);
	
			//Check if the company name is given, if not put in an '_'
			if(companyName.equals("")) {
				companyName = "_";
			}
			
			//Check if the phone number is given, if not put in an '_'
			if(phoneNumber.equals("")) {
				phoneNumber = "_";
			}
			
			SocketHandler sh = new SocketHandler();
			final LinkGenerator lg = new LinkGenerator();
			
			//Create a multipart to add the option parts to
			Multipart mp = new MimeMultipart();
			
			MimeBodyPart mbp1 = new MimeBodyPart();
			MimeBodyPart mbp2 = new MimeBodyPart();
			MimeBodyPart mbp3 = new MimeBodyPart();
			MimeBodyPart mbp4 = new MimeBodyPart();

			//If option1 is selected
			if(option1) {
				
				//Generate the suffix
				String emailLinkSuffix = generateEmailLinkSuffix(target, 1, lg);
				
				//Combine the base url with the suffix and the .html extension to get the final url
				String fileURL = BeursFolder.REDIRECT_BASE_URL + emailLinkSuffix + ".html";
				
				//Add it to the multipart
				//BodyPart prt1 = new MimeBodyPart();
				mbp1.setContent(fileURL + "\n", "text/html");
				//mp.addBodyPart(prt1);
				
				//Send all relevant information to the server
				sh.send(generateSocketText(emailLinkSuffix, firstName, surName, companyName, phoneNumber, TOKEN, 1, OPTION1URL));
			}
			
			//If option2 is selected
			if(option2) {

				//Generate the suffix
				String emailLinkSuffix = generateEmailLinkSuffix(target, 2, lg);
				
				//Combine the base url with the suffix and the .html extension to get the final url
				String fileURL = BeursFolder.REDIRECT_BASE_URL + emailLinkSuffix + ".html";
				
				//Add it to the multipart
				//BodyPart prt2 = new MimeBodyPart();
				mbp2.setContent(fileURL + "\n", "text/html");
				//mp.addBodyPart(prt2);
				
				//Send all relevant information to the server
				sh.send(generateSocketText(emailLinkSuffix, firstName, surName, companyName, phoneNumber, TOKEN, 2, OPTION2URL));
			}
			
			//If option3 is selected
			if(option3) {
				
				//Generate the suffix
				String emailLinkSuffix = generateEmailLinkSuffix(target, 3, lg);
				
				//Combine the base url with the suffix and the .html extension to get the final url
				String fileURL = BeursFolder.REDIRECT_BASE_URL + emailLinkSuffix + ".html";
				
				//Add it to the multipart
				//BodyPart prt3 = new MimeBodyPart();
				mbp3.setContent(fileURL + "\n", "text/html");
				//mp.addBodyPart(prt3);
				
				//Send all relevant information to the server
				sh.send(generateSocketText(emailLinkSuffix, firstName, surName, companyName, phoneNumber, TOKEN, 3, OPTION3URL));
			}
			
			//If option4 is selected
			if(option4) {
			
				//Generate the suffix
				String emailLinkSuffix = generateEmailLinkSuffix(target, 4, lg);
				
				//Combine the base url with the suffix and the .html extension to get the final url
				String fileURL = BeursFolder.REDIRECT_BASE_URL + emailLinkSuffix + ".html";

				//Add it to the multipart
				//BodyPart prt4 = new MimeBodyPart();
				mbp4.setContent(fileURL + "\n", "text/html");
				//mp.addBodyPart(prt4);
				
				//Send all relevant information to the server
				sh.send(generateSocketText(emailLinkSuffix, firstName, surName, companyName, phoneNumber, TOKEN, 4, OPTION4URL));
			}
			
			//Add the multipart to a MimeBodyPart, to add to the email later
		//	MimeBodyPart linkPart = new MimeBodyPart();
			//linkPart.setContent(mp);
			
			//The email's header
			String header = "Beste " + firstName + ", \n";
			String par1 = "Dank voor je bezoek aan onze Mr.Friendly stand op de Horecava.\n\n"
					+ "Tijdens je bezoek gaf je aan wat meer informatie te ontvangen over: \n";
					
			//Add the header to a MimeBodyPart, to add to the email later
			MimeBodyPart headerMBP = new MimeBodyPart();
			headerMBP.setContent(header + "\n", "text/html");

			MimeBodyPart par1MBP = new MimeBodyPart();
			par1MBP.setText(par1 + "\n");
			
			//Add the parts together
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(headerMBP);
			multipart.addBodyPart(par1MBP);
			if(option1) multipart.addBodyPart(mbp1);
			if(option2) multipart.addBodyPart(mbp2); 
			if(option3) multipart.addBodyPart(mbp3); 
			if(option4) multipart.addBodyPart(mbp4); 
			//multipart.addBodyPart(linkPart);
		//	multipart.addBodyPart(attachmentBodyPart);
			 
			message.setContent(multipart);
			 
			//Finally, send the email
			Transport.send(message);
			System.out.println("Email send to: " + target);
						
		} catch (Exception e) {
			e.printStackTrace();
			
			System.err.println("Failed to send mail, retrying in 30 seconds!");
			
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			
			if(retryCounter <= 10) {
				sendMail(target, firstName, surName, option1, option2, option3, option4, companyName, phoneNumber);
			}
		}
	}
	
	String generateEmailLinkSuffix (String target, int option, LinkGenerator lg) {
		String generated = lg.generateLink(option);
		return generated;
	}
	
	String generateSocketText (String emailLinkSuffix, String firstName, String surName, String companyName, String phoneNumber, String token, int option, String optionURL) {
		String toSend = emailLinkSuffix + "," + firstName + "," + surName + "," + companyName + "," + phoneNumber + "," + token + "," + option + ","+ optionURL;
		return toSend;
	}
}