/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package online;

public class Score implements Comparable<Score> {

	private double score;
	private int doc;
	
	public Score(int doc) {
		this.doc = doc;
		this.score = 0;
	}
	
	public double getScore() {
		return score;
	}

	public void add2Score(double add) {
		this.score += add;
	}

	public int getDoc() {
		return doc;
	}

	@Override
	public int compareTo(Score other) {
		if (this.score - other.score > 0)
			return 1;
		else if (this.score - other.score < 0)
			return -1;
		else return 0;
	}

	@Override
	public String toString() {
		return "doc: " + doc + ". score: " + score + ".";
	}
}
