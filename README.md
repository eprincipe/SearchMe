# SearchMe
Searching tool that uses Vector Space model using the inner product and Inverted Files data structure.

This program was created by Emanuel Principe as a second assignment
for the course of Topics in NLP from the university of Guelph.

It was implemented in Java as a Vector Space model using the inner
product and Inverted Files data structure.

## How to use

This progam is composed by 3 modules:
1-Preprocess: lexical analysis done by the main class Scanner.
call it with the command java Scanner < inputFile > outputFile.

2-Offline: Organize the dictionary, documents and entries tables based
on the previous outputFile and generate 3 txt documents. The main class
is the Indexer and and it takes its input name as argument. Call
it using the comand java Indexer inputFile. The outputs will be the
docids.txt, dictionary.txt, and postings.txt.

3-Online: Run with the command java Retriever. It will read from all the
aforementioned documents and wait for the user to input a sequence of
words separated by spaces and/or tabulation. AVOID USING CHARACTERS THAT
ARE NOT LETTERS OR THOSE. In case one of the words is not present in
the dictionary, an error message will pop up and the processing will
proceed. Type q or quit and Enter to leave the program. Upon entering a
query, up to 10 results will show up with their respective scores and
any score equal to zero is not displayed.

## Executed tests

Include:
-single words from the dictionary.
-multiple words from the dictionary.
-single words absent from the dictionary.
-multiple words absent from the dictionary.
-Sentence with words present and absent from the dictionary.
-Long and medium sized queries.

## Possible Improvements
Treatment of unwanted characters in queries.
GUI with a section for article content when clicked.
Improve the regular expressions for the parsing step.
Increase the dictionary.
Different methodologies for language based models.
