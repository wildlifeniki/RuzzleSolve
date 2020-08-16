import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	private Scanner input = new Scanner(System.in);
	private int size;
	private WordList wordlist;
	private String[][] board;
	private ArrayList<String> foundWords;
	private boolean[][] letterUsed;

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
	
	public ArrayList<String> find(){
		this.foundWords = new ArrayList<String>();
		for(int i = 0; i < size; i++) {			
			for(int j = 0; j < size; j++) {
				this.letterUsed = new boolean[size][size];
				letterUsed[i][j] = true;
				find(i, j, board[i][j]);	
			}
		}
		
		return foundWords;
	}
	
	public void find(int i, int j, String word){
		
		if(j < size-1 && letterUsed[i][j+1] == false) 
			addWords(i, j+1, word);
		
		if(i < size-1 && j < size-1 && letterUsed[i+1][j+1] == false) 
			addWords(i+1, j+1, word);
		
		
		if (i > 0 && j < size-1 && letterUsed[i-1][j+1] == false) 
			addWords(i-1, j+1, word);
		

		if(i < size-1 && letterUsed[i+1][j] == false) 
			addWords(i+1, j, word);
		

		if(i > 0 && letterUsed[i-1][j] == false) 
			addWords(i-1, j, word);
		
		
		if (i < size-1 && j > 0 && letterUsed[i+1][j-1] == false) 
			addWords(i+1, j-1, word);
		

		if (j > 0 && letterUsed[i][j-1] == false) 
			addWords(i, j-1, word);
		

		if (i > 0 && j > 0 && letterUsed[i-1][j-1] == false) 
			addWords(i-1, j-1, word);
		
		letterUsed[i][j] = false;
	}
	
	public void addWords(int i, int j, String word) {
		if (word.length() >= wordlist.getLongestWordLength()) {
			return;
		}
		
		letterUsed[i][j] = true;
		String newWord = word + board[i][j];
		
		if (wordlist.contains(newWord)){
			foundWords.add(newWord);
		}
		
		find(i, j, newWord);
	}
}

