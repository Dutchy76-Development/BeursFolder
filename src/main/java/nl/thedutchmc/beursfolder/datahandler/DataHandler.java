package nl.thedutchmc.beursfolder.datahandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DataHandler {

	public void storeInFile(String email, String firstName, String surName, String companyName, String phoneNumber, boolean checkboxChecked, boolean option1, boolean option2, boolean option3, boolean option4) {
		
		BufferedWriter bw = null;
		
		String toWrite = email + "-" + firstName + "-" + surName + "-" + companyName + "-" + phoneNumber + "-" + checkboxChecked + "-" + option1 + "-" + option2 + "-" + option3 + "-" + option4;
		
		try {
			String path = new File("./mailinglist.txt").getAbsolutePath();
			
			File file = new File(path);
			
			FileWriter fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			if(!file.exists()) {
					file.createNewFile();
			}
			
			out.println(toWrite);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
