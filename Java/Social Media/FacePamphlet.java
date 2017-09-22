

	/* 
	 * File: FacePamphlet.java
	 * -----------------------
	 * When it is finished, this program will implement a basic social network
	 * management system.
	 */

	import acm.program.*;
	import acm.graphics.*;
	import acm.util.*;
	import java.awt.event.*;
	import javax.swing.*;

	public class FacePamphlet extends Program 
						implements FacePamphletConstants {

		/**
		 * This method has the responsibility for initializing the 
		 * interactors in the application, and taking care of any other 
		 * initialization that needs to be performed.
		 */
		public void init() {
			faceDB = new FacePamphletDatabase();

			nameLabel = new JLabel("Name: ");
			add(nameLabel, NORTH);

			nameField = new JTextField(TEXT_FIELD_SIZE);
			add(nameField, NORTH);
			nameField.addActionListener(this);

			addButton = new JButton("Add");
			add(addButton,NORTH);

			deleteButton = new JButton("Delete");
			add(deleteButton, NORTH);

			lookupButton = new JButton("Lookup");
			add(lookupButton, NORTH);

			statusField = new JTextField(TEXT_FIELD_SIZE);
			add(statusField, WEST);
			statusField.addActionListener(this);

			statusButton = new JButton("Change Status");
			add(statusButton,WEST);

			add(new JLabel(EMPTY_LABEL_TEXT), WEST);

			pictureField = new JTextField(TEXT_FIELD_SIZE);
			add(pictureField,WEST);
			pictureField.addActionListener(this);

			pictureButton = new JButton("Change Picture");
			add(pictureButton, WEST);

			add(new JLabel(EMPTY_LABEL_TEXT), WEST);

			friendField = new JTextField(TEXT_FIELD_SIZE);
			add(friendField,WEST);
			friendField.addActionListener(this);

			friendButton = new JButton("Add Friend");
			add(friendButton, WEST);

			addActionListeners();

			}

			/**
			* This class is responsible for detecting when the buttons are
			* clicked or interactors are used, so you will have to add code
			* to respond to these actions.
			*/
			public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Add")){
			//println(“Add: ” + nameField.getText());

			if (faceDB.containsProfile(nameField.getText())){
			FacePamphletProfile profile = new FacePamphletProfile(nameField.getText());
			faceDB.addProfile(profile);
			println("Add: profile for " + nameField.getText() + " already exists: "
			+ faceDB.getProfile(nameField.getText()).toString());
			currentProfile = faceDB.getProfile(nameField.getText());
			displayProfile();
			} 
			else{
			FacePamphletProfile profile = new FacePamphletProfile(nameField.getText());
			faceDB.addProfile(profile);
			println("Add: new profile: " + faceDB.getProfile(nameField.getText()).toString());
			currentProfile = faceDB.getProfile(nameField.getText());
			displayProfile();
			}

			}else if(e.getActionCommand().equals("Delete")){
			//println(“Delete: ” + nameField.getText());

			if(faceDB.containsProfile(nameField.getText())){
			faceDB.deleteProfile(nameField.getText());
			println("Delete: profile of " + nameField.getText() + " deleted");
			currentProfile = null;
			displayProfile();
			}else{
			println("Delete: profile with name " + nameField.getText() + " does not exist");
			currentProfile = null;
			displayProfile();
			}

			}else if(e.getActionCommand().equals("Lookup")){
			//println(“Lookup: ” + nameField.getText());

			if(faceDB.containsProfile(nameField.getText())){
			println("Lookup: " + faceDB.getProfile(nameField.getText()).toString());
			currentProfile = faceDB.getProfile(nameField.getText());
			displayProfile();
			}else{

			println("Lookup: profile with name " + nameField.getText() + " does not exist");
			currentProfile = null;
			displayProfile();
			}

			}else if(e.getActionCommand().equals("Change Status") ||
			e.getSource() == statusField){

			if(currentProfile != null){
			currentProfile.setStatus(statusField.getText());
			println("Status updated to " + statusField.getText());
			displayProfile();
			}else{
			println("Please select a profile to change status");
			displayProfile();
			}

			//println(“Change Status: ” + statusField.getText());
			}else if(e.getActionCommand().equals("Change Picture") ||
			e.getSource() == pictureField){

			GImage image = null;
			if(currentProfile != null){
			try{
			image = new GImage(pictureField.getText());
			currentProfile.setImage(image);

			}catch(ErrorException ex){
			image = null;
			}
			if(image == null){
			println("Picture could not be updated");
			}else{
			println("Picture updated");
			}
			displayProfile();
			}else{
			displayProfile();
			println("Please select a profile to change image");
			}

			//println(“Change Picture: ” + pictureField.getText());
			}else if(e.getActionCommand().equals("Add Friend") ||
			e.getSource() == friendField){

			if(currentProfile != null){
			if( faceDB.containsProfile(friendField.getText())){

			if( currentProfile.addFriend(friendField.getText())){
			println( friendField.getText() + " added as a friend");

			faceDB.getProfile(friendField.getText()).addFriend(currentProfile.getName());
			displayProfile();
			}else{
			println(friendField.getText() + " is already a friend in your profile");
			displayProfile();
			}
			}else{
			println("The friend " + friendField.getText() + " is not in the database" );
			displayProfile();
			}
			}else{
			println("Please select a profile to add a friend");
			displayProfile();
			}

			}

			}
			private void displayProfile(){
			if( currentProfile != null){
			println("–> Current profile: " + currentProfile.toString());
			}else{
			println("–> No current profile");
			}
			}

			//top window
			private JLabel nameLabel;
			private JTextField nameField;
			private JButton addButton;
			private JButton deleteButton;
			private JButton lookupButton;

			//left window
			private JTextField statusField;
			private JButton statusButton;
			private JTextField pictureField;
			private JButton pictureButton;
			private JTextField friendField;
			private JButton friendButton;

			private FacePamphletDatabase faceDB;
			private FacePamphletProfile currentProfile;

	}

