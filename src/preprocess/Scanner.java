package preprocess;
/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Scanner {
	private Lexer lexer = null;
	
	public Scanner( Lexer lexer ) {
		this.lexer = lexer; 
	}
	
	public Token getNextToken() throws java.io.IOException {
		return lexer.yylex();
	}
	
	/** addWord2Doc
	 * 	add a word to the DocId, Title, or Body of a document
	 * @param tag where the word is going
	 * @param word to be added
	 * @param doc which will recieve the word
	 */
	public void addWord2Doc(int section, String word, Doc doc) {
		if(section == Doc.DOCID)
			doc.add2Docid(word);
		else if(section == Doc.TITLE)
			doc.add2Title(word);
		else doc.addWord(word);
	}
	
	/** addCompundWord2Doc
	 * add hyphenated and apostrophized words to a doc.
	 * checks if it is compound of at most 3 words and if one is less than 3 chars lengthwise.
	 * 
	 * @param tag
	 * @param word
	 * @param doc
	 */
	public void addCompoundWord2Doc(int section, String word, Doc doc) {
		String[] tokens = word.split("['-]");
		boolean split = true;
		for(int i = 0; i < tokens.length; i++)
			split = split && (tokens[i].length() > 3);
		
		if(split || (tokens.length > 3)) {
			for(int i = 0; i < tokens.length; i++)
				addWord2Doc(section, tokens[i], doc);
		} else addWord2Doc(section, word, doc);
	}
	
	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(new Lexer(new FileReader(args[0])));
			Token tok = null;
			ArrayList<Doc> collection = new ArrayList<Doc>();
			Doc doc = null;
			String tag = null;
			int section = Doc.BODY;
			while( (tok=scanner.getNextToken()) != null )
				switch (tok.m_type) {
					case Token.OPENTAG:
						//create a new document if tag DOC was found
						tag = tok.m_value.split(" ", 2)[0];
						if(tag.equalsIgnoreCase("doc"))
							doc = new Doc();
						else if(tag.equalsIgnoreCase("docno"))
							section = Doc.DOCID;
						else if(tag.equalsIgnoreCase("headline"))
							section = Doc.TITLE;
						break;
					case Token.CLOSETAG:
						//close document if tag DOC was found and add it to the collection
						tag = tok.m_value.split(" ", 2)[0];
						if(tag.equalsIgnoreCase("doc")) {
							doc.closeDoc();
							collection.add(doc);
						}
						else if(tag.equalsIgnoreCase("docno") ||
								tag.equalsIgnoreCase("headline"))
							section = Doc.BODY;
						break;
					case Token.NUMBER:
						//ignore numbers
						break;
					case Token.APOSTROPHIZED:
					case Token.HYPHENATED:
						if(section == Doc.DOCID)
							scanner.addWord2Doc(section, tok.m_value.toLowerCase(), doc);
						else 
							scanner.addCompoundWord2Doc(section, tok.m_value.toLowerCase(), doc);
						break;
					case Token.WORD:
						scanner.addWord2Doc(section, tok.m_value.toLowerCase(), doc);
						break;
					case Token.PUNCTUATION:
						//ignore punctuation
						break;
					default:
						//ignore errors 
						break;
				}

			BufferedWriter bf = new BufferedWriter(new FileWriter(args[1]));
			Iterator<Doc> it = collection.iterator();
			while(it.hasNext()) 
				bf.write(it.next().toString());
			
			bf.close();
		}
		catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}
	}
}
