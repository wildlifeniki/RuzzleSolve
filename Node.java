import java.util.ArrayList;

public class Node {
	
	String letter;
	int score;
	boolean isWord;
	ArrayList<Node> children = new ArrayList<Node>();
	
	public Node() {
		//only called by root node
	}
	
	public Node(String letter, int score, boolean isWord) {
		this.score = score;
		this.isWord = isWord;
		this.letter = letter;
	}
	
	public char getLetter() {
		return letter.charAt(0);
	}
	
	public void incrementScore(int score) {
		this.score += score;
	}
	
	public void insert(String word) {
		Node child = null;
		
		//check if first letter already exists
		boolean exists = false;
		for (Node c: children) {
			if (c.getLetter() == word.charAt(0)) {
				exists = true;
				child = c;
				break;
			}
		}
		
		if (exists) {
			// increment the score
			child.incrementScore(word.length());
		}
		else {
			//creates child with letter, score, isWord
			child = new Node("" + word.charAt(0), word.length(), word.length() == 1);
			//update list of children
			children.add(child);
		}
		
		//call insert on child (excluding first letter)
		if (word.length() > 1) {
			child.insert(word.substring(1));
		}
	}
	
	public void getBestWords(String lettersUsed, ArrayList<String> bestWords) {
		if (this.isWord) {
			bestWords.add(lettersUsed);
		}
		
		while (children.size() > 0) {
			//find highest score child
			Node maxChild = children.get(0);
			for(Node c: children) {
				if (c.score > maxChild.score) {
					maxChild = c;
				}
			}		
			maxChild.getBestWords(lettersUsed + maxChild.getLetter(), bestWords);
			children.remove(maxChild);
		}
	}
}
