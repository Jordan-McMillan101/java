import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 * 
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Returns the number of digits of {@code n}.
     * 
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures <pre>
     * {@code numberOfDigits = [number of digits of n]}
     * </pre>
     */
    private static int numberOfDigits(NaturalNumber n) {

        return 0;
    }

    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        NaturalNumber num = new NaturalNumber2(123);
        output.println("sum of digits: " + num);

        input.close();
        output.close();
    }

}
