package hello;

import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import acm.graphics.*;
import java.awt.*;
import java.util.*;
import acm.graphics.*;


 
public class Brickgame extends GraphicsProgram {
	
	/** Width and height of application window in pixels. On some platforms
	* these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board. On some platforms these may NOT actually
	* be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private static final int TIME_DELAY = 20;
	private GRect paddle;
	private GOval ball;
	private GRect brick;

	private double vx, vy;

	/* Method: run() */
	/** Runs the Breakout program. */
	
	public void run() {
		setupGame();
		addMouseListeners();

		playGame();

		}

		private void setupGame(){

		buildBricks();
		buildPaddle();
		buildBall();
		}

		private void playGame(){

		moveBall();
		}

		private void buildBricks(){

		for(int row = 0; row <NBRICK_ROWS; row++ ){

		for( int col = 0; col < NBRICKS_PER_ROW; col++){

		brick = new GRect((BRICK_WIDTH +BRICK_SEP)* col,
		BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) *row
		,BRICK_WIDTH,BRICK_HEIGHT);
		brick.setFilled(true);

		switch(row){
		case 0: brick.setColor(Color.RED); break;
		case 1: brick.setColor(Color.RED); break;
		case 2: brick.setColor(Color.ORANGE); break;
		case 3: brick.setColor(Color.ORANGE); break;
		case 4: brick.setColor(Color.YELLOW); break;
		case 5: brick.setColor(Color.YELLOW); break;
		case 6: brick.setColor(Color.GREEN); break;
		case 7: brick.setColor(Color.GREEN); break;
		case 8: brick.setColor(Color.CYAN); break;
		case 9: brick.setColor(Color.CYAN); break;
		default: break;
		}

		add(brick);
		}
		}
		}

		private void buildPaddle(){

		paddle = new GRect(WIDTH / 2 - PADDLE_WIDTH /2,
		HEIGHT - 2 * PADDLE_Y_OFFSET, PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
		}

		public void mouseMoved(MouseEvent e){

		if(e.getX() >= 0 && e.getX() < (WIDTH - PADDLE_WIDTH)){
		paddle.setLocation(e.getX(), HEIGHT - 2 * PADDLE_Y_OFFSET);
		}else if( e.getX() >=( WIDTH - PADDLE_WIDTH)){
		paddle.setLocation(WIDTH - PADDLE_WIDTH, HEIGHT - 2 * PADDLE_Y_OFFSET);
		}
		}

		private void buildBall(){
		ball = new GOval( WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS,
		2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
		}

		private void moveBall(){

		vx = rgen.nextDouble(1.0,3.0);
		if(rgen.nextBoolean(0.5)) vx = -vx;
		vy = 3.0;

		while(true){
		ball.move(vx, vy);
		pause(TIME_DELAY);
		checkWalls();
		GObject collider = getCollidingObject();

		if(collider == paddle){
		vy = -vy;
		}else if(collider != null && collider != paddle){
		remove(collider);
		vy = -vy;
		}
		}
		}

		private void checkWalls(){
		if(ball.getX() <= 0){
		vx = -vx;
		}else if( ball.getX() + 2 * BALL_RADIUS >= WIDTH){
		vx = -vx;
		}else if(ball.getY() <= 0){
		vy = -vy;
		}else if(ball.getY() + 2 * BALL_RADIUS >= HEIGHT){
		vy = -vy;
		}
		}

		private GObject getCollidingObject(){

		if(getElementAt(ball.getX(), ball.getY()) != null ){
		return getElementAt(ball.getX(), ball.getY());
		}else if(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null){
		return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		}else if(getElementAt(ball.getX() , ball.getY()+ 2 * BALL_RADIUS) != null){
		return getElementAt(ball.getX() , ball.getY()+ 2 * BALL_RADIUS);
		}else if(getElementAt(ball.getX()+ 2 * BALL_RADIUS , ball.getY()+ 2 * BALL_RADIUS) != null){
		return getElementAt(ball.getX()+ 2 * BALL_RADIUS , ball.getY()+ 2 * BALL_RADIUS);
		}

		return null;
		}

		 RandomGenerator rgen = RandomGenerator.getInstance();
		}



	


