JAVAC=javac
JFLEX=jflex
#JFLEX=/home/fsong/jflex/bin/jflex

all: Token.class Lexer.class Scanner.class Doc.class Post.class Query.class Retriever.class Score.class Word.class DocId.class Indexer.class Posting.class

%.class: %.java
	$(JAVAC) $^

clean:
	rm -f *.class *~
