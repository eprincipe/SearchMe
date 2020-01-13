/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package offline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class Indexer {

	private ArrayList<DocId> docids = new ArrayList<DocId>();
	private TreeMap<String, Posting> terms;
	private BufferedReader in;
	
	private static final int TITLE = 0;
	private static final int BODY = 1;
	private int mode;
	
	public Indexer(BufferedReader in) { 
		this.in = in;
		this.docids = new ArrayList<DocId>();
		this.terms = new TreeMap<String, Posting>();
	}

	/**Gather data from the input given in the constructor.
	 * This method builds the TreeMap of terms as well as
	 * the Doc Id ArrayList.
	 * 
	 * @throws IOException
	 */
	public void generateData() throws IOException {
		String line = null;
		String[] split;
		int lineCount = 0;
		Posting newPosting = null, oldPosting = null;
		
		while ((line = in.readLine()) != null) {
			split = line.split("[ \t]");
			switch(split[0].toUpperCase()) {
				case "$DOC":
					docids.add(new DocId(split[1], lineCount));
					break;
				case "$TITLE":
					this.mode = TITLE;
					break;
				case"$BODY":
					this.mode = BODY;
					break;
				default: 
					if(mode == TITLE)
						docids.get(docids.size()-1).setTitle(line);
					else {
						for(int i = 0; i < split.length; i++) {
							newPosting = new Posting(docids.size()-1);
							oldPosting = terms.put(split[i], newPosting);
							if(oldPosting != null)
								newPosting.add(oldPosting);
						}
					}
			}
			lineCount++;
		}
	}
	
	/**Print the dictionary and postings that was created by the
	 * generateData method.
	 * 
	 * @throws IOException
	 */
	private void printTerms() throws IOException{
		BufferedWriter dictionary = new BufferedWriter(new FileWriter("dictionary.txt"));
		int tp = 0, p = 0; // total postings and individual ones.
		
		dictionary.write(terms.size() + "\n");
		
		for(Map.Entry<String, Posting> e : terms.entrySet()) {
			p = e.getValue().size();
			tp += p;
			
			dictionary.write(e.getKey() + "\t" + p);
			dictionary.newLine();
		}
		
		dictionary.close();
		
		BufferedWriter postings = new BufferedWriter(new FileWriter("postings.txt"));
		
		postings.write(tp + "\n");
		
		for (Map.Entry<String, Posting> e : terms.entrySet()) {
			postings.write(e.getValue().toString());
		}
		
		postings.close();
	}
	
	/**Print the docids ArrayList created by the generateData method.
	 * 
	 * @throws IOException
	 */
	private void printDocIds() throws IOException{
		BufferedWriter file = new BufferedWriter(new FileWriter("docids.txt"));
		Iterator<DocId> it = docids.iterator();
		
		file.write(docids.size() + "\n");
		
		while(it.hasNext()) {
			file.write(it.next().toString());
			file.newLine();
		}
		
		file.close();
	}
	
	public static void main(String[] args) {
		
		try {
			Indexer ind = new Indexer(new BufferedReader(new FileReader(args[0])));
			ind.generateData();
			ind.printTerms();
			ind.printDocIds();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
