import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 * 
 * @author Jordan Lance McMillan
 * 
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    private static NaturalNumber ONE = new NaturalNumber2(1);
    private static NaturalNumber TWO = new NaturalNumber2(2);

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     * 
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates {@code n}
     * @requires <pre>
     * {@code r >= 2}
     * </pre>
     * @ensures <pre>
     * {@code n ^ (r) <= #n < (n + 1) ^ (r)}
     * </pre>
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        NaturalNumber lowEnough = n.newInstance();

        NaturalNumber tooHigh = n.newInstance();
        tooHigh.copyFrom(n);
        tooHigh.increment();

        NaturalNumber guess = n.newInstance();
        guess.copyFrom(n);
        guess.divide(TWO);

        NaturalNumber diff = n.newInstance();
        diff.copyFrom(tooHigh);
        diff.subtract(lowEnough);

        while (diff.compareTo(ONE) > 0) {
            NaturalNumber guessPower = n.newInstance();
            guessPower.copyFrom(guess);
            guessPower.power(r);

            if (n.compareTo(guessPower) < 0) {
                tooHigh.transferFrom(guess);
            } else {
                lowEnough.transferFrom(guess);
            }
            //the next four lines diff is just acting as a temp variable
            diff.copyFrom(tooHigh);
            diff.subtract(lowEnough);
            diff.divide(TWO);
            diff.add(lowEnough);
            guess.transferFrom(diff);

            diff.copyFrom(tooHigh);
            diff.subtract(lowEnough);
        }

        n.transferFrom(lowEnough);
    }

    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final int[] numbers = { 0, 1, 13, 1024, 189943527, 0, 1, 13, 4096,
                189943527, 0, 1, 13, 1024, 189943527, 82, 82, 82, 82, 82, 9,
                27, 81, 243, 143489073 };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15 };
        final int[] results = { 0, 1, 3, 32, 13782, 0, 1, 2, 16, 574, 0, 1, 1,
                1, 3, 9, 4, 3, 2, 1, 3, 3, 3, 3, 3 };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }
}
