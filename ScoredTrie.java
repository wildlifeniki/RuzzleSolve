import java.util.ArrayList;

public class ScoredTrie {

	ArrayList<String> words;
	Node root;

	
	public ScoredTrie(ArrayList<String> words) {
		this.words = words;
	}
	
	public ArrayList<String> getSortedWords() {
		this.root = new Node();
		//fill tree
		for (String s: words) {
			root.insert(s);
		}
		//empty tree
		ArrayList<String> bestWords = new ArrayList<String>();
		root.getBestWords("", bestWords);
		
		return bestWords;
	}
}
