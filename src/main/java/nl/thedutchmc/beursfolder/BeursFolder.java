package nl.thedutchmc.beursfolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import nl.thedutchmc.beursfolder.gui.JavaFX;

public class BeursFolder {

	public static String MAIL_USERNAME, MAIL_PASSWORD, HOST, PORT, SSL_TRUST, HEADER, OPTION1, OPTION2, OPTION3, OPTION4, AGREEMENT, TITLE, OPTION1URL, OPTION2URL, OPTION3URL, OPTION4URL;
	public static boolean STARTTLS, AUTH;
	
	public static void main(String[] args) {
		System.out.println("BeursAD started!");
		
		//Check if the config file location is given
		if(args.length == 1) {
			String configPath = args[0];
			System.out.println("Reading config file...");
			final BeursFolder bf = new BeursFolder();
			bf.readConfig(configPath);
			
			javafx.application.Application.launch(JavaFX.class);
		} else {
			System.err.println("Config file path is not given!");
		}
	}
	
	void readConfig(String configPath) {
		//String anotherPath = BeursFolder.class.getResource("").getPath();
		//String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = configPath + "app.properties";
		File appPropertiesFile = new File(appConfigPath);
	
		//Check if the file exists
		if(!(appPropertiesFile.exists())) {
			System.out.println("Properties file does not exist! Creating...");
			
			try {
				//Create a new file
				appPropertiesFile.createNewFile();
				
				FileWriter fw = new FileWriter(appConfigPath, true);
				
				//Write default values to file
				fw.write("# App Configuration file for BeursFolder\n");
				fw.write("# Do not change the properties below this line!\n");
				fw.write("appVersion = 1.0\n\n");
				
				fw.write("# Edit below this line!\n\n");
				
				fw.write("#Email settings\n\n");
				fw.write("#Example: \n");
				fw.write("#email_address = noreply@example.com\n" +
				"#email_password = noreplyPassword \n" + 
				"#smtp_host = smtp.example.com \n" +
				"#smtp_port = 587\n" + 
				"#smtp_ssl_trust = smtp.example.com \n" +
				"#enable_starttls = true\n" +
				"#enable_auth = true\n\n");
				
				fw.write("email_address = \n");
				fw.write("email_password = \n");
				fw.write("smtp_host = \n");
				fw.write("smtp_port = \n");
				fw.write("smtp_ssl_trust = \n");
				fw.write("enable_starttls = true\n");
				fw.write("enable_auth = true\n\n");
				
				fw.write("#Email Content Settings\n");
				fw.write("subject = \n\n");
				
				fw.write("#UI Settings\n");
				fw.write("header = \n");
				fw.write("option1 = \n");
				fw.write("option2 = \n");
				fw.write("option3 = \n");
				fw.write("option4 = \n");
				fw.write("agreement = \n\n");
				
				fw.write("#URLs for the option specific attachments\n");
				fw.write("option1_url = \n");
				fw.write("option2_url = \n");
				fw.write("option3_url = \n");
				fw.write("option4_url = \n");
				
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(appPropertiesFile.exists()) {
			Properties appProps = new Properties();
			try {
				appProps.load(new FileInputStream(appConfigPath));
				
				String appVersion = appProps.getProperty("appVersion");
				
				MAIL_USERNAME = appProps.getProperty("email_address");
				MAIL_PASSWORD = appProps.getProperty("email_password");
				HOST = appProps.getProperty("smtp_host");
				PORT = appProps.getProperty("smtp_port");
				SSL_TRUST = appProps.getProperty("smtp_ssl_trust");
				
				if(appProps.getProperty("enable_starttls").equalsIgnoreCase("true")) {
					STARTTLS = true;
				} else {
					STARTTLS = false;
				}
				
				if(appProps.getProperty("enable_auth").equalsIgnoreCase("true")) {
					AUTH = true;
				} else {
					AUTH = false;;;
				}
				
				HEADER = appProps.getProperty("header");
				
				OPTION1 = appProps.getProperty("option1");
				OPTION2 = appProps.getProperty("option2");
				OPTION3 = appProps.getProperty("option3");
				OPTION4 = appProps.getProperty("option4");
				
				AGREEMENT = appProps.getProperty("agreement");
				
				OPTION1URL = appProps.getProperty("option1_url");
				OPTION2URL = appProps.getProperty("option2_url");
				OPTION3URL = appProps.getProperty("option3_url");
				OPTION4URL = appProps.getProperty("option4_url");

				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}
