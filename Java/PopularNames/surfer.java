package namesurferr;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.io.*;
import acm.util.*;
import javax.swing.*;
import acm.program.*;

public class surfer extends GraphicsProgram {
	
// Initializes the program /
		public void init(){
			createcontroller(); // initializes the buttons
			addActionListeners();
		}
			
		
		// loads the data from file 
		private void loaddata(){
			try{
				BufferedReader rd = new BufferedReader(new FileReader("NamesData.txt"));
				
				while (true){
					String line = rd.readLine();
					if(line == null) break;
					NameSurferEntry entry = new NameSurferEntry(line);
					inventory.put(entry.getName(), entry);
				}
				rd.close();
			}
				catch(IOException ex){
					throw new ErrorException(ex);
				}
			}
		
		
		

		
		
		
	
		
		
		
		/* Creates the controller at the bottom of the window */		
		private void createcontroller(){
			// created the text field for entering name
			namefield = new JTextField(MAX_NAME);
			namefield.addActionListener(this);
			// created the buttons 
			graph = new JButton("Graph");
			clear = new JButton("Clear");
			add(new JLabel("Name:"),SOUTH);
			add(namefield,SOUTH);
			add(graph,SOUTH);
			add(clear,SOUTH);
			}
			
			
		
			
		
		public void actionPerformed(ActionEvent e){
				String cmd = e.getActionCommand();
				if (e.getSource() == namefield){
					entry = nameDB.findEntry(namefield.getText());
					System.out.println("Graph: " + entry.toString());
				}
					
					
				else if (cmd.equals("Graph"))
					System.out.println("Graph: " + namefield.getText());
				else if (cmd.equals("Clear"))
					removeAll();
			}

			
			
			// instance variables
			private JTextField namefield;
			private JButton graph;
			private JButton clear;
			NameSurferEntry entry;
			private NameSurferDatabase nameDB = new NameSurferDatabase("NamesData.txt");
			// constants
			private static final int MAX_NAME = 25;
			
			
	}

