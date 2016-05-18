import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 * 
 * @author P. Bucci
 */
public final class powerMethod {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private powerMethod() {
        // no code needed here
    }

    /**
     * Returns {@code n} to the power {@code p}.
     * 
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires <pre>
     * {@code Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0}
     * </pre>
     * @ensures <pre>
     * {@code power = n ^ (p)}
     * </pre>
     */

    private static int power(int n, int p) {
        int a = 0;
        int currentNum = n;

        if (p == 1) {
            a = n;
        }
        while (p > 1) {
            a = currentNum * n;
            p--;
            if (p > 1) {
                currentNum = a;
            }
        }
        return a;
    }

    private static int root(int n, int r) {
        int lowEnough = 0;
        int tooHigh = n + 1;
        int guess = (tooHigh - lowEnough) / 2;
        while (tooHigh - lowEnough > 1) {
            if (n < power(guess, r)) {
                guess /= 2; // guess = 5, guess = 2
                tooHigh = (tooHigh - lowEnough) / 2; //tooHigh = 10, tooHigh = 5

            } else {
                guess = tooHigh / 2;
                lowEnough = (tooHigh - lowEnough) / 2; // tooLow = 2
            }
        }

        return guess;
    }

    private static int root(int n, int r) {
        le = 0;
        th n+1;
        guess= x/2;
        
        while ((th-le)> 1) {
            if(n < power(guess, r)) {
                th = guess;
            }
        }else {
            le = guess;
        }
        guess = ((th - le)/2) + le;

        return le;
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

        output.println("enter a number");
        int n = input.nextInteger();
        output.println("Enter a number that's being raised: ");
        int r = input.nextInteger();

        int a = root(n, r);
        output.println(n + "^" + "1/" + r + " = " + a);
        input.close();
        output.close();
    }

}
