import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	private Scanner input = new Scanner(System.in);
	private int size;
	private WordList wordlist;
	private String[][] board;
	private ArrayList<String> foundWords;
	private boolean[][] letterUsed;

	//initializes the board with the right size and allows the user to fill it with letters
	public Board(int size, WordList wordlist) {
		this.size = size;
		this.wordlist = wordlist;	
		this.board = new String[size][size];
		
		System.out.println("Fill the board (no spaces)");
		String fill = input.next().toUpperCase();
//		String fill = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int a = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				board[i][j] = "" + fill.charAt(a++);				
			}
		}
	}

	//puts the board array into a format that can be printed (in a grid)
	public String toString() {
		String printBoard = "";
		for(int i = 0; i < size; i++) {			
			for(int j = 0; j < size; j++) {					
				printBoard = printBoard + board[i][j] + "\t";	
			}
			
			printBoard = printBoard + "\n";
		}
		
		return printBoard;
	}
	
	//looks through the array to find words
	public ArrayList<String> find(){
		this.foundWords = new ArrayList<String>();
		for(int i = 0; i < size; i++) {			
			for(int j = 0; j < size; j++) {
				//a matching array to mark whether a letter has been used in a word
				this.letterUsed = new boolean[size][size];
				letterUsed[i][j] = true;
				find(i, j, board[i][j]);	
			}
		}
		
		return foundWords;
	}
	
	//checks all potential letters (connected to a previous word)
	public void find(int i, int j, String word){
		
		if(j < size-1 && letterUsed[i][j+1] == false)  //right
			addWords(i, j+1, word);
		
		if(i < size-1 && j < size-1 && letterUsed[i+1][j+1] == false) //down right
			addWords(i+1, j+1, word);
		
		
		if (i > 0 && j < size-1 && letterUsed[i-1][j+1] == false) //up right
			addWords(i-1, j+1, word);
		

		if(i < size-1 && letterUsed[i+1][j] == false) //down
			addWords(i+1, j, word);
		

		if(i > 0 && letterUsed[i-1][j] == false) //up
			addWords(i-1, j, word);
		
		
		if (i < size-1 && j > 0 && letterUsed[i+1][j-1] == false) //down left
			addWords(i+1, j-1, word);
		

		if (j > 0 && letterUsed[i][j-1] == false) //left
			addWords(i, j-1, word);
		

		if (i > 0 && j > 0 && letterUsed[i-1][j-1] == false) //up left
			addWords(i-1, j-1, word);
		
		letterUsed[i][j] = false; //marks each letter as unused when the word can no longer continue
	}
	
	//checks if string is in wordlist, adds to arraylist of found words, and adjusts matching array
	public void addWords(int i, int j, String word) {
		//ensures not to check past the max word length
		if (word.length() >= wordlist.getLongestWordLength()) {
			return;
		}
		
		//marks the last letter as used
		letterUsed[i][j] = true;
		String newWord = word + board[i][j];
		
		if (wordlist.contains(newWord)){
			foundWords.add(newWord);
		}
		
		find(i, j, newWord);
	}
}

