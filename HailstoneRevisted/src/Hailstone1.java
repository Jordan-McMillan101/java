import components.naturalnumber.NaturalNumber;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 * 
 * @author Put your name here
 * 
 */
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     * 
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates {@code out.content}
     * @requires <pre>
     * {@code n > 0 and out.is_open}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content * [the Hailstone series starting with n]}
     * </pre>
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {

    }

    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.println("Please enter a positive number(i.e., a natual number greater than zero): ");

        myMethod();
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
