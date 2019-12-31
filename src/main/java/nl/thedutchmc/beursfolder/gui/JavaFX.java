package nl.thedutchmc.beursfolder.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nl.thedutchmc.beursfolder.BeursFolder;
import nl.thedutchmc.beursfolder.datahandler.DataHandler;
import nl.thedutchmc.beursfolder.mailHandler.LinkGenerator;
import nl.thedutchmc.beursfolder.mailHandler.MailHandler;

public class JavaFX extends Application {
	
	ArrayList<Node> addToRoot = new ArrayList<Node>();
	
	final MailHandler mailHandler = new MailHandler();
	final DataHandler dataHandler = new DataHandler();
	
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
		
		Group root = new Group(option12,option34, emailFirstSurnameFields, phoneCompanyFields);
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
		agreementText.setY(screenY / 8 * 7 + 20); //TODO
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
		TextField enterEmail = new TextField();
		enterEmail.setId("textField");
		enterEmail.setPromptText("Email...");
		enterEmail.setPrefWidth(400);
		addToRoot.add(enterEmail);
		
		TextField enterFirstName = new TextField();
		enterFirstName.setId("textField");
		enterFirstName.setPromptText("First name...");
		addToRoot.add(enterFirstName);
		
		TextField enterSurName = new TextField();
		enterSurName.setId("textField");
		enterSurName.setPromptText("Surname...");
		addToRoot.add(enterSurName);
		
		TextField enterPhoneNumber = new TextField();
		enterPhoneNumber.setId("textField");
		enterPhoneNumber.setLayoutY(enterEmail.getLayoutY() + 30);
		enterPhoneNumber.setPromptText("Phone number...");
		addToRoot.add(enterPhoneNumber);
		
		TextField enterCompanyName = new TextField();
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
	}
	
	void calculateErrorMessageBounds(Node node, double screenX) {
		node.setLayoutX((screenX / 2) - (node.getBoundsInParent().getWidth() /2 ));
	}
}