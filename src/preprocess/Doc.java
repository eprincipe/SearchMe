/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package preprocess;
import java.util.ArrayList;
import java.util.Iterator;

public class Doc {

	private String docid;
	private String title;
	private ArrayList<String> lines;
	private int wordCount;
	
	private static final int wordPerLine = 15;
	
	public static final int DOCID = 0;
	public static final int TITLE = 1;
	public static final int BODY = 2;
	
	public Doc() {
		this.docid = "";
		this.title = "";
		this.lines = new ArrayList<String>();
		this.wordCount = wordPerLine;
	}

	public String getDocid() {
		return docid;
	}

	public void add2Docid(String docid) {
		this.docid += (this.docid.equalsIgnoreCase("")? "": " ") + docid;
	}

	public String getTitle() {
		return title;
	}

	public void add2Title(String title) {
		this.title += (this.title.equalsIgnoreCase("")? "": " ") + title;
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public void addWord(String word) {
		if (wordCount < wordPerLine) {
			lines.set(lines.size()-1, lines.get(lines.size()-1) + " " + word); 
			wordCount++;
		} else {
			lines.add(word);
			wordCount = 1;
		}
	}
	
	public String toString() {
		String ret = "$DOC " + docid + "\n$TITLE\n" + title + "\n$BODY\n";
		
		Iterator<String> it = lines.iterator();
		while(it.hasNext())
			ret = ret + it.next() + "\n";
		
		return ret;
	}
	
	public void closeDoc() {
		if(title.equalsIgnoreCase(""))
			title = lines.get(0);
	}
}
