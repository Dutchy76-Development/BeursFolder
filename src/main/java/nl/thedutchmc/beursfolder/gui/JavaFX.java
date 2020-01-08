package nl.thedutchmc.beursfolder.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nl.thedutchmc.beursfolder.BeursFolder;
import nl.thedutchmc.beursfolder.datahandler.DataHandler;
import nl.thedutchmc.beursfolder.mailHandler.MailHandler;

public class JavaFX extends Application {
	
	ArrayList<Node> addToRoot = new ArrayList<Node>();
	
	final MailHandler mailHandler = new MailHandler();
	final DataHandler dataHandler = new DataHandler();
	
	public static TextField enterEmail = new TextField();
	public static TextField enterFirstName = new TextField();
	public static TextField enterSurName = new TextField();
	public static TextField enterPhoneNumber = new TextField();
	public static TextField enterCompanyName = new TextField();
	
	public static TextField selectedField;
	
	boolean capslock = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//set up the stage
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setMaximized(false);
		primaryStage.setTitle("BeursFolder Interface");
		
		HBox emailFirstSurnameFields = new HBox();
		HBox phoneCompanyFields = new HBox();
		HBox option12 = new HBox();
		HBox option34 = new HBox();
		
		HBox numberRow = new HBox();
		HBox QPRow = new HBox();
		HBox ALRow = new HBox();
		HBox ShiftZMRow = new HBox();
		HBox spaceSpecialChars = new HBox();
		
		VBox keyboard = new VBox();
		keyboard.getChildren().addAll(numberRow, QPRow, ALRow, ShiftZMRow, spaceSpecialChars);
		
		Group root = new Group(option12,option34, emailFirstSurnameFields, phoneCompanyFields, keyboard);
		root.setId("root");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		//Set the CSS Stylesheet
		scene.getStylesheets().add(JavaFX.class.getResource("styling.css").toExternalForm());

		//Get the screensize
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenX = screenSize.getWidth();
		double screenY = screenSize.getHeight();
		
		//Change size and position of the Stage (thus the application)
		primaryStage.setX(0);
		primaryStage.setY(0);
		
		primaryStage.setMinWidth(screenX);
		primaryStage.setMinHeight(screenY);
		
		//Image
		Image image = new Image(JavaFX.class.getResource("mrf_logo.png").toExternalForm());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(275);
		imageView.setFitWidth(454);
		imageView.setPreserveRatio(true);
		imageView.setY(20);
		addToRoot.add(imageView);
		
		//Text objects
		Text title = new Text();
		title.setId("titleText");
		title.setText(BeursFolder.HEADER);
		title.setY(imageView.getY() + imageView.getBoundsInParent().getHeight() + 40);
		addToRoot.add(title);
		
		Text agreementText = new Text();
		agreementText.setId("agreementText");
		agreementText.setText(BeursFolder.AGREEMENT);
		agreementText.setY(screenY / 8 * 7 + 20);
		addToRoot.add(agreementText);
		
		Text option1Text = new Text();
		option1Text.setId("optionsText");
		option1Text.setText(BeursFolder.OPTION1);
		addToRoot.add(option1Text);
		
		Text option2Text = new Text();
		option2Text.setId("optionsText");
		option2Text.setText(BeursFolder.OPTION2);
		addToRoot.add(option2Text);
		
		Text option3Text = new Text();
		option3Text.setId("optionsText");
		option3Text.setText(BeursFolder.OPTION3);
		addToRoot.add(option3Text);
		
		Text option4Text = new Text();
		option4Text.setId("optionsText");
		option4Text.setText(BeursFolder.OPTION4);
		addToRoot.add(option4Text);
			
		//Variable text objects
		Text updateMessage = new Text();
		updateMessage.setId("updateMessageText");
		updateMessage.setY(screenY / 4 * 3);
		addToRoot.add(updateMessage);
		
		//Text fields

		enterEmail.setId("textField");
		enterEmail.setPromptText("Email...");
		enterEmail.setPrefWidth(400);
		addToRoot.add(enterEmail);
		
	
		enterFirstName.setId("textField");
		enterFirstName.setPromptText("First name...");
		addToRoot.add(enterFirstName);
		
		
		enterSurName.setId("textField");
		enterSurName.setPromptText("Surname...");
		addToRoot.add(enterSurName);
		
		enterPhoneNumber.setId("textField");
		enterPhoneNumber.setLayoutY(enterEmail.getLayoutY() + 30);
		enterPhoneNumber.setPromptText("Phone number...");
		addToRoot.add(enterPhoneNumber);
		
		enterCompanyName.setId("TextField");
		enterCompanyName.setLayoutY(enterPhoneNumber.getLayoutY());
		enterCompanyName.setPromptText("Company...");
		addToRoot.add(enterCompanyName);
		
		//Buttons
		Button submitButton = new Button("Submit");
		submitButton.setId("submitButton");
		submitButton.setLayoutY(screenY / 8 * 7 + 40);
		addToRoot.add(submitButton);
		
		//Checkboxes
		CheckBox option1Box = new CheckBox();
		
		CheckBox option2Box = new CheckBox();
		
		CheckBox option3Box = new CheckBox();
		
		CheckBox option4Box = new CheckBox();
		
		CheckBox agreementBox = new CheckBox();
		agreementBox.setLayoutY(agreementText.getY() - agreementText.getBoundsInParent().getHeight());
		addToRoot.add(agreementBox);
		
		//Add all objects to the root group
		for(Node node : addToRoot) {
			root.getChildren().add(node);
		}
		
		//Show the stage, and set it to be maximized
		primaryStage.show();
		
		primaryStage.setMaximized(true);
		
		//Calculate Positions of all objects
		title.setX((screenX /2) - (title.getBoundsInParent().getWidth() / 2)); // (screen width / 2) - (object size / 2)
		imageView.setX((screenX/2) - (imageView.getBoundsInParent().getWidth() / 2));
		submitButton.setLayoutX((screenX / 2) - (submitButton.getBoundsInParent().getWidth() / 2));
		
		//Email and Name fields
		double emailNameFields = enterEmail.getBoundsInParent().getWidth() + enterFirstName.getBoundsInParent().getWidth() + enterSurName.getBoundsInParent().getWidth();
		
		emailFirstSurnameFields.getChildren().addAll(enterFirstName, enterSurName, enterEmail);
		HBox.setMargin(enterSurName, new Insets(0,20,0,20));
		emailFirstSurnameFields.setLayoutY(screenY / 4 * 3 + 30);
		emailFirstSurnameFields.setLayoutX((screenX / 2) - (emailNameFields / 2));

		//Phone number and company name fields		
		phoneCompanyFields.getChildren().addAll(enterPhoneNumber, enterCompanyName);
		HBox.setMargin(enterCompanyName, new Insets(0,0,0,20));
		phoneCompanyFields.setLayoutY(emailFirstSurnameFields.getLayoutY() + 30);
		phoneCompanyFields.setLayoutX(emailFirstSurnameFields.getLayoutX() + enterFirstName.getBoundsInParent().getWidth() + 20);
		
		//Agreement text and checkbox
		double agreementSize = agreementBox.getBoundsInParent().getWidth() + agreementText.getBoundsInParent().getWidth();
		agreementText.setLayoutX((screenX / 2) + (agreementSize / 2 - agreementText.getBoundsInParent().getWidth()) + 5);
		agreementBox.setLayoutX((screenX / 2) - (agreementSize / 2) - 5);
		
		//HBox for options 1 and 2
		double option1Size = option1Box.getBoundsInParent().getWidth() + option1Text.getBoundsInParent().getWidth();
		double option2Size = option2Box.getBoundsInParent().getWidth() + option2Text.getBoundsInParent().getWidth();
		
		double options12Size = option1Size + option2Size + 40;
		
		option12.getChildren().addAll(option1Box, option1Text, option2Box, option2Text);
		
		HBox.setMargin(option2Box, new Insets(0,10,0,40));
		HBox.setMargin(option1Box, new Insets(0,10,0,0));
		
		option12.setLayoutY(title.getY() + title.getBoundsInParent().getHeight() + 20);
		option12.setLayoutX((screenX / 2) - (options12Size / 2) - 20);
		
		//HBox for options 3 and 4
		double option3Size = option3Box.getBoundsInParent().getWidth() + option3Text.getBoundsInParent().getWidth();
		double option4Size = option4Box.getBoundsInParent().getWidth() + option4Text.getBoundsInParent().getWidth();
		
		double options34Size = option3Size + option4Size + 40;
		
		option34.getChildren().addAll(option3Box, option3Text, option4Box, option4Text);
		
		HBox.setMargin(option4Box, new Insets(0,10,0,40));
		HBox.setMargin(option3Box, new Insets(0,10,0,0));
		
		option34.setLayoutX((screenX / 2) - (options34Size / 2) - 20);
		option34.setLayoutY(option12.getLayoutY() + option12.getBoundsInParent().getHeight() + 40);
		
		//EventHandlers
		submitButton.setOnAction(actionEvent ->  {
		    //Button is pressed
			String email = enterEmail.getText();
			String firstName = enterFirstName.getText();
			String surName = enterSurName.getText();
			
			//Check if one of the options is selected
			boolean optionSelected = false;
			char[] options = {'0','0','0','0'};
			
			if(option1Box.isSelected()) options[0] = '1';
			if(option2Box.isSelected()) options[1] = '1';
			if(option3Box.isSelected()) options[2] = '1';
			if(option4Box.isSelected()) options[3] = '1';
			
			for(char c : options) {
				if(c == '1') {
					optionSelected = true;
				}
			}
			
			updateMessage.setText("");
			updateMessage.setVisible(true);
			
			if(agreementBox.isSelected()) {
				if(optionSelected) {
					if(!(email.equalsIgnoreCase(""))) {
						if(!(firstName.equalsIgnoreCase(""))) {
							
							//Check if the email containts an '@'
							char[] emailChars = email.toCharArray();
							boolean containsAtSymbol = false;
							
							for(char c : emailChars) {
								if(c == '@') {
									containsAtSymbol = true;
									break;
								}
							}
							
							if(containsAtSymbol) {
								//Remove errorMessage
								updateMessage.setText("Success! You should receive an email from us soon!");
								calculateErrorMessageBounds(updateMessage, screenX);								
								//Everything is correct, send email and log the email+name to file, all on their own Thread!
								new Thread(new Runnable() {
									@Override
									public void run() {
										mailHandler.sendMail(email, firstName, surName, option1Box.isSelected(), option2Box.isSelected(), option3Box.isSelected(), option4Box.isSelected(), enterCompanyName.getText(), enterPhoneNumber.getText());
									}
								}).start();
								
								new Thread(new Runnable() {
									@Override
									public void run() {
										dataHandler.storeInFile(email, firstName, surName, enterCompanyName.getText(), enterPhoneNumber.getText(), agreementBox.isSelected(), option1Box.isSelected(), option2Box.isSelected(), option3Box.isSelected(), option4Box.isSelected());
									}
								}).start();
								
								//Empty all text fields and clear all checkboxes
								enterEmail.setText("");
								enterFirstName.setText("");
								enterSurName.setText("");
								option1Box.setSelected(false);
								option2Box.setSelected(false);
								option3Box.setSelected(false);
								option4Box.setSelected(false);
								agreementBox.setSelected(false);
								enterPhoneNumber.setText("");
								enterCompanyName.setText("");
								
							} else { //Email doesn't contain '@', thus invalid
								updateMessage.setText("Invalid Email!");
								calculateErrorMessageBounds(updateMessage, screenX);
							}
						} else { //Name field is empty
							updateMessage.setText("Please enter your first name!");
							calculateErrorMessageBounds(updateMessage, screenX);
						}
					} else { //Email field is empty
						updateMessage.setText("Please enter your email address!");
						calculateErrorMessageBounds(updateMessage, screenX);
					}
				} else { //None of the options are selected
					updateMessage.setText("You need to select at least one option!");
					calculateErrorMessageBounds(updateMessage, screenX);
				}
			} else { //agreementBox isn't selected
				updateMessage.setText("You need to agree to the agreement!");
				calculateErrorMessageBounds(updateMessage, screenX);
			}
		});
		
		//Virtual Keyboard
		
		numberRow.setMinHeight(10);
		numberRow.setMinWidth(screenX);
		
		QPRow.setMinHeight(10);
		
		ALRow.setMinHeight(10);
		
		ShiftZMRow.setMinHeight(10);
		
		spaceSpecialChars.setMinHeight(10);
		
		Map<Button, Character> keyboardButton = new HashMap<Button, Character>();
		
		//Number buttons
		Button num1 = new Button("1");
		numberRow.getChildren().add(num1);
		keyboardButton.put(num1, '1');
		
		Button num2 = new Button("2");
		numberRow.getChildren().add(num2);
		keyboardButton.put(num2, '2');
		
		Button num3 = new Button("3");
		numberRow.getChildren().add(num3);
		keyboardButton.put(num3, '3');

		Button num4 = new Button("4");
		numberRow.getChildren().add(num4);
		keyboardButton.put(num4, '4');

		Button num5 = new Button("5");
		numberRow.getChildren().add(num5);
		keyboardButton.put(num5, '5');
	
		Button num6 = new Button("6");
		numberRow.getChildren().add(num6);
		keyboardButton.put(num6, '6');
		
		Button num7 = new Button("7");
		numberRow.getChildren().add(num7);
		keyboardButton.put(num7, '7');
		
		Button num8 = new Button("8");
		numberRow.getChildren().add(num8);
		keyboardButton.put(num8, '8');
		
		Button num9 = new Button("9");
		numberRow.getChildren().add(num9);
		keyboardButton.put(num9, '9');
		
		Button num0 = new Button("0");
		numberRow.getChildren().add(num0);
		keyboardButton.put(num0, '0');
				
		//Row of keys Q to P
		Button keyQ = new Button("Q");
		QPRow.getChildren().add(keyQ);
		keyboardButton.put(keyQ, 'q');
		
		Button keyW = new Button("W");
		QPRow.getChildren().add(keyW);
		keyboardButton.put(keyW, 'w');
		
		Button keyE = new Button("E");
		QPRow.getChildren().add(keyE);
		keyboardButton.put(keyE, 'e');
		
		Button keyR = new Button("R");
		QPRow.getChildren().add(keyR);
		keyboardButton.put(keyR, 'r');
		
		Button keyT = new Button("T");
		QPRow.getChildren().add(keyT);
		keyboardButton.put(keyT, 't');
		
		Button keyY = new Button("Y");
		QPRow.getChildren().add(keyY);
		keyboardButton.put(keyY, 'y');

		Button keyU = new Button("U");
		QPRow.getChildren().add(keyU);
		keyboardButton.put(keyU, 'u');
		
		Button keyI = new Button("I");
		QPRow.getChildren().add(keyI);
		keyboardButton.put(keyI, 'i');
		
		Button keyO = new Button("O");
		QPRow.getChildren().add(keyO);
		keyboardButton.put(keyO, 'o');
		
		Button keyP = new Button("P");
		QPRow.getChildren().add(keyP);
		keyboardButton.put(keyP, 'p');
				
		//Row of keys A to L
		Button keyA = new Button("A");
		ALRow.getChildren().add(keyA);
		keyboardButton.put(keyA, 'a');
		
		Button keyS = new Button("S");
		ALRow.getChildren().add(keyS);
		keyboardButton.put(keyS, 's');
		
		Button keyD = new Button("D");
		ALRow.getChildren().add(keyD);
		keyboardButton.put(keyD, 'd');

		Button keyF = new Button("F");
		ALRow.getChildren().add(keyF);
		keyboardButton.put(keyF, 'f');
		
		Button keyG = new Button("G");
		ALRow.getChildren().add(keyG);
		keyboardButton.put(keyG, 'g');
		
		Button keyH = new Button("H");
		ALRow.getChildren().add(keyH);
		keyboardButton.put(keyH, 'h');
		
		Button keyJ = new Button("J");
		ALRow.getChildren().add(keyJ);
		keyboardButton.put(keyJ, 'j');
		
		Button keyK = new Button("K");
		ALRow.getChildren().add(keyK);
		keyboardButton.put(keyK, 'k');
		
		Button keyL = new Button("L");
		ALRow.getChildren().add(keyL);
		keyboardButton.put(keyL, 'l');
				
		//Row of keys from Shift to M
		Button keyCaps = new Button("Caps\nLock");
		ShiftZMRow.getChildren().add(keyCaps);
		
		keyCaps.setOnAction(actionEvent -> {
			//TODO Make caps button darker when selected
			if(capslock) {
				capslock = false;
			} else {
				capslock = true;
			}
		});
		
		Button keyZ = new Button("Z");
		ShiftZMRow.getChildren().add(keyZ);
		keyboardButton.put(keyZ, 'z');
		
		Button keyX = new Button("X");
		ShiftZMRow.getChildren().add(keyX);
		keyboardButton.put(keyX, 'x');
		
		Button keyC = new Button("C");
		ShiftZMRow.getChildren().add(keyC);
		keyboardButton.put(keyC, 'c');
		
		Button keyV = new Button("V");
		ShiftZMRow.getChildren().add(keyV);
		keyboardButton.put(keyV, 'v');
		
		Button keyB = new Button("B");
		ShiftZMRow.getChildren().add(keyB);
		keyboardButton.put(keyB, 'b');
		
		Button keyN = new Button("N");
		ShiftZMRow.getChildren().add(keyN);
		keyboardButton.put(keyN, 'n');
		
		Button keyM = new Button("M");
		ShiftZMRow.getChildren().add(keyM);
		keyboardButton.put(keyM, 'm');
				
		//Spacebar and special characters
		Button keySpace = new Button("Space");
		spaceSpecialChars.getChildren().add(keySpace);
		keyboardButton.put(keySpace, ' ');
		
		Button keyAt = new Button("@");
		spaceSpecialChars.getChildren().add(keyAt);
		keyboardButton.put(keyAt, '@');
		
		Button keyDot = new Button(".");
		spaceSpecialChars.getChildren().add(keyDot);
		keyboardButton.put(keyDot, '.');
		
		Button keyPlus = new Button("+");
		spaceSpecialChars.getChildren().add(keyPlus);
		keyboardButton.put(keyPlus, '+');
		
		//Keyboard positioning
		keyboard.setLayoutY(10);
		keyboard.setLayoutX((screenX / 2) - (keyboard.getBoundsInParent().getWidth()) / 2);
		
		//Set action, minimumwidth
		for(Map.Entry<Button, Character> map : keyboardButton.entrySet()) {
			Button b = map.getKey();
			String value = map.getValue().toString();
			b.setMinSize(60, 60);
			
			b.setOnAction(actionEvent -> {
				String oldText = selectedField.getText();
				
				String text;
				
				if(capslock) {
					text = value.toUpperCase();
				} else {
					text = value;
				}
				
				selectedField.setText(oldText + text);
			});
		}
		
	}
	
	void calculateErrorMessageBounds(Node node, double screenX) {
		node.setLayoutX((screenX / 2) - (node.getBoundsInParent().getWidth() /2 ));
	}
}