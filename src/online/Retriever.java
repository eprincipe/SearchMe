/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package online;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import offline.DocId;

public class Retriever {

	private DocId[] docs;
	private Word[] words;
	private Post[] posts;

	/**Imports data from the offline indexation.
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void inpuData() throws NumberFormatException, IOException {
		
		BufferedReader file;
		String[] buffer;
		int total;
		
		//Initializing DocIds.
		file = new BufferedReader(new FileReader("docids.txt"));
		total = Integer.parseInt(file.readLine());
		docs = new DocId[total];
		for(int i = 0; i < total; i++) {
			buffer = file.readLine().split("\t");
			docs[i] = new DocId(buffer[0], Integer.parseInt(buffer[1]), buffer[2]);
		}
		file.close();
		
		//Initializing Dictionary
		file = new BufferedReader(new FileReader("dictionary.txt"));
		total = Integer.parseInt(file.readLine());
		words = new Word[total];
		int cumulative = 0;
		for(int i = 0; i < total; i++) {
			buffer = file.readLine().split("\t");
			words[i] = new Word(buffer[0], cumulative);
			cumulative += Integer.parseInt(buffer[1]);
		}
		file.close();
		
		//Initializing Postings
		file = new BufferedReader(new FileReader("postings.txt"));
		total = Integer.parseInt(file.readLine());
		posts = new Post[total];
		for(int i = 0; i < total; i++) {
			buffer = file.readLine().split("\t");
			posts[i] = new Post(Integer.parseInt(buffer[0]), Integer.parseInt(buffer[1]));
		}
		file.close();
	}
	
	/**Search linearly for the dictionary index of a given word.
	 * 
	 * @param Name to be searched for.
	 * @return index of the Word in the dictionary.
	 * @throws WordNotFoundException
	 */
	public int getIndex(String str) {
		int ret = -1;
		for(int i = 0; i < words.length; i++)
			if(words[i].getWord().equalsIgnoreCase(str))
				return i;
		return ret;
	}

	/** Calculate the score 
	 * 
	 * @param q
	 * @return
	 * @throws WordNotFoundException 
	 */
	public ArrayList<Score> search(Query query) {
		int wordIndex, df, entry;
		double idf, q;
		
		ArrayList<Score> ret = new ArrayList<Score>(docs.length);
		for(int i = 0; i < docs.length; i++)
			ret.add(new Score(i));
		
		for(Map.Entry<String, Integer> e : query.entrySet()) {
			wordIndex = getIndex(e.getKey());
			
			if(wordIndex == -1) 
				System.err.println(e.getKey() + " is absent from the dictionary.");
			else {
				//initializing document frequency for the term
				entry = words[wordIndex].getEntry();
				df = wordIndex == words.length-1?
						posts.length - entry:
						words[wordIndex+1].getEntry() - entry;
				
				//initializing q for the term
				idf = Math.log((double)docs.length/df);
				q = e.getValue()*idf;
				
				for(int i = 0; i < df; i++)
					//saving symmetry for each doc as tf_ij * idf_j * q_j
					ret.get(posts[entry+i].getDoc()).add2Score(posts[entry+i].getTf() * idf * q);
			}
		}
		Collections.sort(ret);
		return ret;
	}
	
	public static void main(String[] args) {
		
		try {
			Retriever console = new Retriever();
			console.inpuData();
			
			//Initializing console for queries
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String[] buffer;
			Query q;
			ArrayList<Score> r;
			String format;
			
			while(true) {
				buffer = in.readLine().split("[ \t]");
				
				//stopping criteria
				if(buffer.length == 1 && (buffer[0].equalsIgnoreCase("q")
						|| buffer[0].equalsIgnoreCase("quit")))
					System.exit(0);
				
				//initializing bag of words.
				q = new Query();
				for(int i = 0; i < buffer.length; i++)
					q.put(buffer[i]);
				
				//performing the scoring
				r = console.search(q);
				
				//selecting the 10 best
				for(int i = 0; i < 10; i++) {
					if(r.get(r.size()-i-1).getScore() != 0) {
						format = String.format("%.2f",  r.get(r.size()-i-1).getScore());
						System.out.println(format + ": " + console.docs[r.get(r.size()-i-1).getDoc()].getTitle());
					}
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Data unfit for parsing into numbers.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error opening of reading files.");
		}
	}
}
