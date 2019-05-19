/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	
	
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	private GImage karel;
	private GImage parachute;
	private GLabel incorrectWords;
	
	int[] frequencyTable;
	
	private int nGuesses=7;
	
	private String secretWord;
	private String secretWordDashed = "";
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	public void run() {
		
		setUpGame();
	}
	
	public void init() {
		add(canvas);
	}
	
	
	private void updateGameState(String dashedWord, int nGuesses, String incorrectlyGuessedLetters) {
		canvas.removeAll();
		drawBackGroundImage();
		
		drawParachute();
		

		if (nGuesses == 0) {
			drawFlippedKarel();
		} else {
			drawKarel();
			drawStrings(nGuesses);
		}
		
		drawPartiallyGuessedLetters(dashedWord);
		drawIncorrectlyGuessedLetters(incorrectlyGuessedLetters);
	}
	
	
	
	
	private void drawBackGroundImage() {
		GImage bg = new GImage("background.jpg");
		bg.setSize(canvas.getWidth(), canvas.getHeight());
		canvas.add(bg,0,0);
		
	}
	
	private void drawPartiallyGuessedLetters(String word) {
		
		
		GLabel partiallyGuessedWords;
		partiallyGuessedWords = new GLabel(word);
		partiallyGuessedWords.setFont(PARTIALLY_GUESSED_FONT);
		canvas.add(partiallyGuessedWords, (canvas.getWidth() - partiallyGuessedWords.getWidth()) / 2 , PARTIALLY_GUESSED_Y);
	}
	
	private void drawIncorrectlyGuessedLetters(String word) {
		incorrectWords = new GLabel(word);
		incorrectWords.setFont(INCORRECT_GUESSES_FONT);
		canvas.add(incorrectWords, (canvas.getWidth() - incorrectWords.getWidth()) / 2 , INCORRECT_GUESSES_Y);
	}
	
	private void drawStrings(int n) {
		
		int i;
		GLine strings;
		int spacing = PARACHUTE_WIDTH/n;
		
		for (i=0; i<n; i++) {
			
			
			
			if (n == 1) {
				strings = new GLine( karel.getX() + karel.getWidth() / 2  , karel.getY() , parachute.getX() + (PARACHUTE_WIDTH/2),parachute.getY() + parachute.getHeight());
			}
			
			else {
				strings = new GLine( karel.getX() + karel.getWidth() / 2  , karel.getY() , parachute.getX() + (i * spacing) + (PARACHUTE_WIDTH/n - spacing/2),parachute.getY() + parachute.getHeight());
			}
		
			canvas.add(strings);
		}
	}
	

	private void setUpGame() {
		
		String userGuessedWord="";
		String incorrectlyGuessedLetters = "";
		String userResponse = "";
		
		println("Welcome to Hangman");
		secretWord = getRandomWord();
		secretWordDashed = generateDashedWord("", secretWord, "");
		
		int[] frequencyAI = FindTwentyLargest(frequencyTable);
		int currentIndexOfFrequencyAI = 0;
		
		
		while (true) {
			
			
			updateGameState(secretWordDashed,nGuesses, incorrectlyGuessedLetters);
			
			
			if (checkGameFinish(nGuesses)) {
				printLostMessage();
				break;
			}
			
			
			if (secretWordDashed.indexOf("-") == -1) {
				printWonMessage();
				break;
			}
			
			printGameInfo(secretWord, secretWordDashed, nGuesses);
			
			userResponse = readLine("Do you want computer to try  Y/N ?");
			
			if (userResponse.charAt(0) == 'Y' || userResponse.charAt(0) == 'y') {
				
				if (currentIndexOfFrequencyAI == 20) {
					println("I ran out of options");
					return;
				}
				int charIndex = frequencyAI[currentIndexOfFrequencyAI];
				userGuessedWord = "";
				userGuessedWord += alphabet.charAt(charIndex);
				currentIndexOfFrequencyAI++;
			} else {
				userGuessedWord = readLine("Your guess: ");
			}
			
			
			
			
			if (checkIfWordExists(secretWord, userGuessedWord)) {
				println("That guess is correct");
				secretWordDashed = generateDashedWord(userGuessedWord,secretWord, secretWordDashed);
				
			} else {
				incorrectlyGuessedLetters += Character.toUpperCase(userGuessedWord.charAt(0));
			
				nGuesses--;
				println("There are no " + Character.toUpperCase(userGuessedWord.charAt(0)) + "'s" + " in the word");
			}
			
			
		}
	
	}
	
	
	private void printGameInfo(String secretWord, String secretWordDashed, int n) {
		println("The secret word is " + secretWord);
		println("Your word now looks like this: " + secretWordDashed);
		println("You have " + nGuesses + " guesses left");
	}
	
	private void printLostMessage() {
		println("You're complete hung");
		println("The word was: " + secretWord);
		
	}
	
	private void printWonMessage() {
		println("You win");
		println("The word was: " + secretWord);
	}
	
	
	private boolean checkGameFinish(int n) {
		if (n==0) {
			return true;
		}
		return false;
	}
	
	
	private boolean checkIfWordExists(String originalWord , String wordGuessed) {
		
		char guessedCharacter = wordGuessed.charAt(0);
		
		if (!Character.isLetter(guessedCharacter)) {
			return false;
		}
		
		for (int i = 0; i<originalWord.length(); i++) {
			if (originalWord.charAt(i) == Character.toUpperCase(guessedCharacter)) {
				return true;
			}
		}
		return false;
	}
	
	
	private String generateDashedWord(String characterGuessed, String originalWord, String dashedWord) {
		String output = "";
		int i;
		int wordL = originalWord.length();
		
		
		char guessedCharacter = characterGuessed.length() != 0 ? Character.toUpperCase(characterGuessed.charAt(0)) : ':';
		
		
		for (i = 0; i<wordL; i++) {
			char ch = originalWord.charAt(i);
			
			if (ch == guessedCharacter) {
				output += guessedCharacter;
			} else if (dashedWord.length() != 0 && Character.isLetter(dashedWord.charAt(i))) {
				output += dashedWord.charAt(i);
			} else {
				output += "-";
			}
		}
		
		return output;
	}
	
	
	private void drawParachute() {
		parachute = new GImage("parachute.png");
		parachute.setSize(PARACHUTE_WIDTH,PARACHUTE_HEIGHT);
		canvas.add(parachute,(canvas.getWidth() - PARACHUTE_WIDTH) / 2,PARACHUTE_Y );
	}
	
	private void drawKarel() {
		karel = new GImage("karel.png");
		karel.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(karel, (canvas.getWidth() - KAREL_SIZE ) / 2,KAREL_Y);
		
	}
	
	private void drawFlippedKarel() {
		GImage flippedKarel = new GImage("karelFlipped.png");
		flippedKarel.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(flippedKarel, (canvas.getWidth() - KAREL_SIZE) / 2, KAREL_Y);
	}
	
	
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game. It randomly 
	 * selects from among 10 choices.
	 */
	private String getRandomWord() {
		int index = rg.nextInt(10);
		
		ArrayList<String> listOfWords = new ArrayList<String>();
		frequencyTable = initFrequencyTable();
		
	
		
		try {
			Scanner sc = new Scanner(new File("HangmanLexicon.txt"));
			while(sc.hasNext()) {
				String line = sc.nextLine();
				countLetterFrequencies(frequencyTable,line);
				listOfWords.add(line);
			}
			sc.close();
		} catch(IOException e) {
			println("Opps the file didn't open");
		}
		
		
		int size = listOfWords.size();
		String randomWord = listOfWords.get(rg.nextInt(size));
	
		return randomWord;
	}
	
	/* Initializes the frequency table to contain zeros */
	private int[] initFrequencyTable() {
		int[] frequencyTable = new int[26];
		for (int i = 0; i < 26; i++) {
			frequencyTable[i] = 0;
		}
		return frequencyTable;
	}
	
	/* Counts the letter frequencies in a line of text */
	private void countLetterFrequencies(int[] table, String line) {
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (Character.isLetter(ch)) {
				int index = Character.toUpperCase(ch) - 'A';
				frequencyTable[index]++;
			}
		}
		
	}
	
	private int[] FindTwentyLargest(int[] table) {
		
		int i;
	    int large[] = new int[20];
		int max = 0, index;
		
	    for (int j = 0; j < 20; j++) {
	        max = table[0];
	        index = 0;
	        for (i = 1; i < table.length; i++) {
	            if (max < table[i]) {
	                max = table[i];
	                index = i;
	            }
	        }
	        large[j] = index;
	        table[index] = Integer.MIN_VALUE;
	    }
	    
	    return large;
	}
}
	

