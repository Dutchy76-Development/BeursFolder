package nl.thedutchmc.beursfolder.mailHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LinkGenerator {

	public String generateLink(int option) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");  
	    Date date = new Date(); 
	    String curTime = sdf.format(date);
	    
	    return curTime + "_" + option + "_" + suffix();
	}
	
	String suffix() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 30;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    return generatedString;
	}
}
