import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Extension of {@code NaturalNumber2} with secondary operations implemented as
 * instance methods: add, subtract, and power.
 * 
 * @author Put your name here
 * 
 */
public final class NaturalNumberInstanceOps extends NaturalNumber2 {

    /**
     * Default constructor.
     */
    public NaturalNumberInstanceOps() {
    }

    /**
     * Constructor from {@code int}.
     * 
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberInstanceOps(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     * 
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberInstanceOps(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     * 
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberInstanceOps(NaturalNumber n) {
        super(n);
    }

    /**
     * The base used in representing {@code NaturalNumber}.
     */
    private static final int RADIX = 10;

    @Override
    public void add(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        /**
         * @decreases {@code n}
         */
        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.add(n);
        }
        thisLowDigit += nLowDigit;
        if (thisLowDigit >= 10) {
            thisLowDigit -= 10;
            this.increment();
        }
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    @Override
    public void subtract(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        assert this.compareTo(n) >= 0 : "Violation of: this >= n";

        int thisLowdigit = this.divideBy10();
        int nLowDigit = n.divideBy10();

    }

    @Override
    public void power(int p) {
        assert p >= 0 : "Violation of: p >= 0";

        // TODO - fill in body

    }

}