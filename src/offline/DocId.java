/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package offline;

public class DocId {

	private String did;
	private int start;
	private String title;
	
	public DocId(String did, int start) {
		this.start = start;
		this.did = did;
	}
	
	public DocId(String did, int start, String title) {
		this.did = did;
		this.start = start;
		this.title = title;
	}

	public String getDid() {
		return did;
	}

	public int getStart() {
		return start;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		return did + "\t" + start + "\t" + title;
	}
}
