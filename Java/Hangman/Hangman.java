package Hang;
import java.util.*;

import acm.program.ConsoleProgram;
import acm.util.*;

public class Hangman extends ConsoleProgram {
		
	private String secretWord;
	private String hiddenWord;
	private int guessCtr = 8;
	
	private RandomGenerator rgen = new RandomGenerator();
	private HangmanLexicon lex = new HangmanLexicon();
	
	public void run() {
		// TODO Auto-generated method stub
		setupgame();
		playGame();
	
	
	
	
	}		
	
	private void setupgame(){
		System.out.println("Welcome to Hangman!!!");
		secretWord = wordPicker();
		hiddenWord = dashedword();
		guessedcounterupdate();
	}
	
	private void playGame(){
		while(true){
			
			Scanner input = new Scanner(System.in);
			System.out.print("Your guess: ");  // get users input
			String guess = input.nextLine();
			guess = guess.toUpperCase();   // convert to uppercase
			
			while( validguess(guess) == false){
				// check if the guess is right
				System.out.print("Your guess: "); 
				guess = input.nextLine();
				guess = guess.toUpperCase();
			}
			
			checkLetter(guess);
			
			if(CheckWinLose() == false){
				wordupdate();
				guessedcounterupdate();
			}
			else{
				break;
			}
			
		}
	}
	
	private boolean CheckWinLose(){
		
		if (guessCtr == 0){
			System.out.println("You Lost .");
			System.out.println("The word was :" + secretWord);
			return true;
		}
		else if (hiddenWord.equals(secretWord)){
			System.out.println("You guessed the word: " + secretWord);
			System.out.println("You win. ");
			return true;
		}
		return false;
			
			
		
	}
	
	private void wordupdate(){
		System.out.println("The word now look like this: " + hiddenWord);
	}
	
	private void checkLetter(String guess){
		
		if (secretWord.indexOf(guess) == -1){
			// -1 means that the guess is not inside the secretWord
			guessCtr--;
			System.out.println("There are no " + guess + " in the word. ");
		}
		else {
			System.out.println("That guess is correct ");
			for(int i = 0; i < secretWord.length(); i++){
				if (secretWord.charAt(i) == guess.charAt(0) && i!=0)
					hiddenWord = hiddenWord.substring(0,i) + guess + hiddenWord.substring(i+1);
				else if (secretWord.charAt(i) == guess.charAt(0) && i==0)
					hiddenWord = guess + hiddenWord.substring(i+1);
			}
		}
	}
	
	
	private boolean validguess(String guess){
		
		if(guess.length() > 1)
			return false;
		else if (Character.isDigit(guess.charAt(0) ))
			return false;
		
		return true;  // if not a digit and not longer than 1 return true
	}
	
	private String wordPicker(){
	// created an instance of a class
		int wordIndexInt = rgen.nextInt(0,lex.getWordCount() - 1); // random value from the range of 0 to 10
		String wordSelected = lex.getWord(wordIndexInt);   // select the word , based on the random index
		return wordSelected; 
		
	}
	
	private String dashedword(){
		String result = "";
		
		for(int i =0; i< secretWord.length(); i++){
			// populate the word with dashes
			result += "-";
		}
		System.out.println("The word now looks like this: " + result);
		return result;
	}
	
	private void guessedcounterupdate(){
		System.out.println("You have " + guessCtr + " guesses Left") ;
	}
}
	



	
	
	

