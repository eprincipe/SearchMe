/*************************************
 * Created by Emanuel Principe for
 * the second Assignment of NLP in W19
 * 
 *************************************/
package preprocess;

class Token {

  public final static int ERROR = 0;
  public final static int CLOSETAG = 1;
  public final static int OPENTAG = 2;
  public final static int NUMBER = 3;
  public final static int APOSTROPHIZED = 4;
  public final static int HYPHENATED = 5;
  public final static int WORD = 6;
  public final static int PUNCTUATION = 7;

  public int m_type;
  public String m_value;
  public int m_line;
  public int m_column;
  
  Token (int type, String value, int line, int column) {
    m_type = type;
    m_value = value;
    m_line = line;
    m_column = column;
  }

  public String toString() {
    switch (m_type) {
      case CLOSETAG:
        return "CLOSE-"+m_value;
      case OPENTAG:
        return "OPEN-"+m_value;
      case NUMBER:
	return "NUMBER("+m_value+")";
      case APOSTROPHIZED:
        return "APOSTROPHIZED("+m_value+")";
      case HYPHENATED:
        return "HYPHENATED("+m_value+")";
      case WORD:
        return "WORD("+m_value+")";
      case PUNCTUATION:
        return "PUNCTUATION("+m_value+")";
      case ERROR:
        return "ERROR(" + m_value + ")";
      default:
        return "UNKNOWN(" + m_value + ")";
    }
  }
}

