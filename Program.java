import java.util.ArrayList;
import java.util.Collections;

public class Program {
	
	public static void main(String args[]) {
		
		//minimum and maximum word length
		WordList wordlist = new WordList("WordList.txt", 5, 10);
		
		//size of board (need to fill in by entering letters)
		Board board = new Board(4, wordlist);
		
		//print in a board format
		System.out.println(board.toString());
		
		//initially searching for all the words
		ArrayList<String> words = board.find();
		
		//sorting words alphabetically and by length
		Collections.sort(words, new WordComparator());
//		System.out.println(words.size() + "");
		
		//sorting the found words
		ScoredTrie tree = new ScoredTrie(words);
		words = tree.getSortedWords();
		
		//printing sorted words
		for (String s:words) {
			System.out.println(s);
		}
	}

}
