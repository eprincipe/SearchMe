/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package online;

import java.util.TreeMap;

public class Query extends TreeMap<String, Integer> {

	/**Add one word to the bag of words.
	 * 
	 * @param key
	 * @return total repetitions of that word in the bag.
	 */
	public int put(String key) {
		if(!super.containsKey(key)) {
			super.put(key, 1);
			return 1;
		}
		return super.put(key, super.get(key)+1) + 1;
	}
}