/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package offline;

public class Posting {

	private int did;
	private int tf;
	private Posting next;
	
	public Posting (int did) {
		this.did = did;
		this.tf = 1;
		this.next = null;
	}
	
	public int gettf() {
		return tf;
	}
	
	public int getdid() {
		return did;
	}
	
	/**Add an entry to a list. If they share document id, increase the term
	 * frequency and copy the list from entry, if not add the entry as your
	 * next element.
	 * 
	 * @param e
	 */
	public void add(Posting e) {
		if(this.did == e.did) {
			this.tf = e.tf+1;
			this.next = e.next;
		} else this.next = e;
	}
	
	/**Recursively check the size of the list.
	 * 
	 * @return the size of the List
	 */
	public int size() {
		if(this.next == null)
			return 1;
		else return next.size() + 1;
	}
	
	/**Recursively build a string of entries in ascending order.
	 * 
	 * @return String of entries.
	 */
	public String toString() {
		if(this.next == null)
			return did + "\t" + tf + "\n";
		else
			return next.toString() + did + "\t" + tf + "\n";
	}
}
