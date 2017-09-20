package namesurferr;
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
implements NameSurferConstants, ComponentListener {

/**
* Creates a new NameSurferGraph object that displays the data.
*/
public NameSurferGraph() {
addComponentListener(this);

}

/**
* Clears the list of name surfer entries stored inside this class.
*/
public void clear() {
}

/* Method: addEntry(entry) */
/**
* Adds a new NameSurferEntry to the list of entries on the display.
* Note that this method does not actually draw the graph, but
* simply stores the entry; the graph is drawn by calling update.
*/
public void addEntry(NameSurferEntry entry) {
}

/**
* Updates the display image by deleting all the graphical objects
* from the canvas and then reassembling the display according to
* the list of entries. Your application must call update after
* calling either clear or addEntry; update is also called whenever
* the size of the canvas changes.
*/
public void update() {
removeAll();
drawGraph();
}

private void drawGraph(){

drawHorizLines();
drawVertLines();
drawDateLabel();
}
private void drawDateLabel(){

spacing = getWidth() / NDECADES;
for(int date = 0; date < NDECADES; date++){

String dateString = Integer.toString(date * 10 + START_DECADE);
GLabel dateLabel = new GLabel(dateString,date * spacing,getHeight() );
add(dateLabel);
}

}
private void drawVertLines(){

spacing = getWidth() / NDECADES;
for( int lines = 0 ; lines < NDECADES; lines++){

GLine vertLine = new GLine( (lines + 1) * spacing,0, (lines + 1) * spacing,getHeight() );
add(vertLine);
}

}
private void drawHorizLines(){

GLine topLine = new GLine(0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
add(topLine);

GLine botLine = new GLine(0, getHeight() – GRAPH_MARGIN_SIZE, getWidth(),getHeight() – GRAPH_MARGIN_SIZE);
add(botLine);
}

/* Implementation of the ComponentListener interface */
public void componentHidden(ComponentEvent e) { }
public void componentMoved(ComponentEvent e) { }
public void componentResized(ComponentEvent e) { update(); }
public void componentShown(ComponentEvent e) { }

//instance vars
private double spacing;

}