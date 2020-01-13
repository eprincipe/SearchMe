/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package online;

public class Word {

	private String word;
	private int entry;
	
	public Word(String word, int entry) {
		this.word = word;
		this.entry = entry;
	}
	public String getWord() {
		return word;
	}
	public int getEntry() {
		return entry;
	}
	
	@Override
	public String toString() {
		return "word=" + word + ", entry=" + entry;
	}
}
