/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import acm.util.*;

public class NameSurfer extends GraphicsProgram implements NameSurferConstants {
	
	private JTextField textField = new JTextField(TEXT_FIELD_WIDTH); 
	private String childName = "";
	private Color[] lineColors = {
			Color.BLACK,
			Color.RED,
			Color.BLUE,
			Color.MAGENTA
		};
	private NameSurferDataBase database;
	private int currentColorIndex = 0;
	

	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
		add(new JLabel("Name: "),NORTH);
		textField.addActionListener(this);
		textField.setActionCommand("Graph");
		add(textField, NORTH);
		add(new JButton("Graph"),NORTH);
		add(new JButton("Clear"),NORTH);
		addActionListeners();
		// You fill this in, along with any helper methods //
	}
	
	public void run() {
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		database = new NameSurferDataBase(NAMES_DATA_FILE);
		drawGraphBackground();
	}

	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			childName = "";
			currentColorIndex = 0;
			clear();
			redraw();
		} else if (e.getActionCommand().equals("Graph")) {
			childName = textField.getText().toLowerCase();
			println("Graph: " + childName);
			redraw();
		}
	}
	
	/**
	 * This class is responsible for detecting when the the canvas
	 * is resized. This method is called on each resize!
	 */
	public void componentResized(ComponentEvent e) { 
		redraw();
	}
	
	/**
	 * A helper method that we *strongly* recommend. Redraw clears the
	 * entire display and repaints it. Consider calling it when you change
	 * anything about the display.
	 */
	private void redraw() {
		drawGraphBackground();
		
		if (childName != "") {
			NameSurferEntry entry = database.findEntry(childName);
			String name = entry.getName();

			for (int i =0; i<10; i++) {
				
				int rank = entry.getRank(i);
				int rank2 = entry.getRank(i+1);
				drawLines(rank,rank2, name, i);
				
				
			}
			currentColorIndex = (currentColorIndex + 1) % 4;
		}
	}


	private void drawLines(int rank, int nextrank , String name, int index) {
		
		int spacing = APPLICATION_WIDTH / NDECADES;
		double percentage = 0.56;
		
		double rankD = rank == 0 ? 1000 : rank ;
		double nextrankD = nextrank == 0 ? 1000 : nextrank;
		int point1 = (int) Math.round(percentage*rankD + GRAPH_MARGIN_SIZE);
		int point2 = (int) Math.round(percentage*nextrankD + GRAPH_MARGIN_SIZE);
		
		if (index == 9) {
			addLabelToGraph(name,nextrank, (index+1)*spacing, point2-1);
		}
		
		addLabelToGraph(name,rank,index*spacing, point1-1);
		GLine line = new GLine(index*spacing,point1, (index+1)*spacing, point2);
		drawSmallCircles(index*spacing, point1);
		drawSmallCircles((index+1)*spacing, point2);
		line.setColor(lineColors[currentColorIndex]);
		add(line);

	}
	
	private void drawSmallCircles(int x , int y) {
		GOval circle = new GOval(4,4);
		circle.setFilled(true);
		circle.setColor(lineColors[currentColorIndex]);
		add(circle,x-1,y-1);
	}
	
	private void addLabelToGraph(String name , int rank, int x , double y) {	
		if (rank == 0) {
			GLabel nameTag = new GLabel(name + " *");
			nameTag.setColor(lineColors[currentColorIndex]);
			add(nameTag, x,  y);
		} else {
			GLabel nameTag = new GLabel(name + " " + rank);
			nameTag.setColor(lineColors[currentColorIndex]);
			add(nameTag, x,  y);
		}
	}
	
	private void drawGraphBackground() {
		drawTop();
		drawBottom();
		drawVerticalLines();
		drawDecadeLabels();
	}
	
	private void drawTop() {
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth() , GRAPH_MARGIN_SIZE);
		add(topLine);
	}
	
	private void drawBottom() {
		GLine bottomLine = new GLine(0,APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE, APPLICATION_WIDTH , APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE);
		add(bottomLine);
	}
	
	private void drawDecadeLabels() {
		
		int spacing = APPLICATION_WIDTH / NDECADES;
		
		for (int i =START_DECADE; i<2001; i+=10) {
			GLabel label = new GLabel(Integer.toString(i));
			add(label,spacing*(i%1900 / 10)+1,APPLICATION_HEIGHT - DECADE_LABEL_MARGIN_SIZE);
			
		}
	}
	
	private void drawVerticalLines() {
		
		int spacing = APPLICATION_WIDTH / NDECADES;
		
		for (int i =0; i<NDECADES; i++) {
			GLine verticalLine = new GLine(spacing*i, 0, spacing*i, getHeight());
			add(verticalLine);
		}
		
	}
}
