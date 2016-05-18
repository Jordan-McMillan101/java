import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program is going to take in a text document and with the document it
 * will then create an HTML file that will hold a table with each word and with
 * each word it will have the number of times it occurs in the document.
 * 
 * @author Jordan L. McMillan
 */
public final class WordCounter {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Default constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     * 
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces {@code strSet}
     * @ensures <pre>
     * {@code strSet = entries(str)}
     * </pre>
     */
    private static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";

        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (!strSet.contains(character)) {
                strSet.add(character);
            }

        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     * 
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires <pre>
     * {@code 0 <= position < |text|}
     * </pre>
     * @ensures <pre>
     * {@code nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)}
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int endIndex = position;

        if (!separators.contains(text.charAt(position))) {
            while (endIndex < text.length()
                    && !separators.contains(text.charAt(endIndex))) {

                endIndex++;

            }
        } else {
            while (endIndex < text.length()
                    && separators.contains(text.charAt(endIndex))) {
                endIndex++;

            }

        }

        String wordOrSeparator = "";
        wordOrSeparator = text.substring(position, endIndex);
        return wordOrSeparator;

    }

    /**
     * Fills the Map with the terms and definitions and also the queue with the
     * terms. at the end the terms in the queue are sorted in lexicographic
     * order.
     * 
     * @param textFile
     *            the text file that the user wants to input into the program
     * @param allWordsInOrder
     *            map containing the terms as the keys and their definitions as
     *            the value
     * 
     * 
     */
    private static void fillQue(String textFile, Queue<String> allWordsInOrder) {

        SimpleReader dotTextFile = new SimpleReader1L(textFile);

        final String separatorStr = " \t, .-";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        while (!dotTextFile.atEOS()) {
            int position = 0;
            String str = dotTextFile.nextLine();
            String testStr = str.toLowerCase();

            while (position < testStr.length()) {
                String token = nextWordOrSeparator(testStr, position,
                        separatorSet);
                if (!separatorSet.contains(token.charAt(0))) { //if word then add word to que

                    allWordsInOrder.enqueue(token);
                }
                position += token.length();
            }
        }

        Comparator<String> cs = new StringLT();
        allWordsInOrder.sort(cs);

        dotTextFile.close();
    }

    /**
     * this method will fill in the map with the words and the amount of times
     * occur in the text file.
     * 
     * @param allWordsInOrder
     *            this is a queue of all the words in the text file
     * @param wordsInOrder
     *            this is a queue of all the DIFFERENT words in the text file
     * @param wordsAndCounts
     *            this is a map of the words and their number of occurrences
     */
    private static void fillMapWithCounts(Queue<String> allWordsInOrder,
            Queue<String> wordsInOrder, Map<String, Integer> wordsAndCounts) {

        while (allWordsInOrder.length() != 0) {
            String word = allWordsInOrder.dequeue();

            if (wordsAndCounts.hasKey(word)) {
                int currentCount = wordsAndCounts.value(word);
                currentCount++;
                wordsAndCounts.replaceValue(word, currentCount);

            } else {
                int count = 1;
                wordsAndCounts.add(word, count);

                wordsInOrder.enqueue(word);
            }

        }

    }

    /**
     * this method will create an html page with a table that will contain each
     * word and its number of occurrences.
     * 
     * @param htmlFile
     *            this is the output file
     * @param textFile
     *            this is the input file
     * @param wordsAndCounts
     *            this is the map of words and the number of their occurrences
     * @param wordsInOrder
     *            this is the queue of all the different words
     */
    private static void createHtmlPage(String htmlFile, String textFile,
            Map<String, Integer> wordsAndCounts, Queue<String> wordsInOrder) {

        SimpleWriter htmlPage = new SimpleWriter1L(htmlFile + "index.html");

        htmlPage.println("<html>");
        htmlPage.println("<head>");
        htmlPage.println("<title>" + "Words Counted in " + textFile
                + "</title>");
        htmlPage.println("</head>");
        htmlPage.println("<body>");
        htmlPage.println("<h2>" + "Words Counted in " + textFile + "</h2>");
        htmlPage.println("<hr />");
        htmlPage.println("<table border=\"1\">");
        htmlPage.println("<tr>");
        htmlPage.println("<th>Words</th>");
        htmlPage.println("<th>Counts</th>");

        while (wordsInOrder.length() != 0) {
            htmlPage.println("<tr>");
            htmlPage.print("<td>");

            String word = wordsInOrder.dequeue();
            htmlPage.print(word);

            htmlPage.println("</td>");
            htmlPage.print("<td>");

            htmlPage.print(wordsAndCounts.value(word));

            htmlPage.println("</td>");
            htmlPage.println("</tr>");

        }
        htmlPage.println("</table>");
        htmlPage.println("</body");
        htmlPage.println("</html");

        htmlPage.close();

    }

    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader consoleIn = new SimpleReader1L();
        SimpleWriter consoleOut = new SimpleWriter1L();

        //prompt user for input and output file names
        consoleOut
                .print("Please enter the full path of a .txt file to be read in: ");
        String textFile = consoleIn.nextLine() + ".txt/";
        consoleOut
                .println("Next, enter the name of the new .html file to be created: ");
        String htmlFile = consoleIn.nextLine() + ".html";

        Map<String, Integer> wordsAndCounts = new Map1L<>();
        Queue<String> allWordsInOrder = new Queue1L<String>();
        Queue<String> wordsInOrder = new Queue1L<String>();
        //first fill que with every word from the textFile
        fillQue(textFile, allWordsInOrder);
        //fill the map with words and also a second que with words only appearing once
        fillMapWithCounts(allWordsInOrder, wordsInOrder, wordsAndCounts);

        createHtmlPage(htmlFile, textFile, wordsAndCounts, wordsInOrder);

        consoleIn.close();
        consoleOut.close();

    }

}
