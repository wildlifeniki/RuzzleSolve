import java.util.ArrayList;
import java.util.Collections;

public class Program {
	
	public static void main(String args[]) {
		
		WordList wordlist = new WordList("WordList.txt", 5, 10);
		Board board = new Board(4, wordlist);
		System.out.println(board.toString());
		ArrayList<String> words = board.find();
		Collections.sort(words, new WordComparator());
//		System.out.println(words.size() + "");
		
//		do our own sorting
		ScoredTrie tree = new ScoredTrie(words);
		words = tree.getSortedWords();
		
		for (String s:words) {
			System.out.println(s);
		}
	}

}
