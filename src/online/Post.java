/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package online;

public class Post {

	private int doc;
	private int tf;
	
	public Post(int doc, int tf) {
		super();
		this.doc = doc;
		this.tf = tf;
	}
	
	public int getDoc() {
		return doc;
	}
	
	public int getTf() {
		return tf;
	}

	@Override
	public String toString() {
		return "doc=" + doc + ", tf=" + tf;
	}
}
