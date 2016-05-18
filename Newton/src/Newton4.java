import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The following program will compute the square root of a given number to
 * within a relative error of no more than 0.01%. The computation will be done
 * using Newton iteration.
 * 
 * @author Jordan L. McMillan
 * 
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * 
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x, epsilon = 0.0001;
        if (x != 0.0) {
            while (Math.abs((r * r - x) / x) > epsilon) {
                r = (r + (x / r)) * 0.5;
            }
        }

        return r;

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
        //needed variable
        double answer = 0.0;

        out.print("Enter positive number(double): ");
        double x = in.nextDouble();
        //calling the method sqrt() with given number
        double a = sqrt(x);
        //while loop to check if user would like to compute another number
        while (answer >= 0.0) {
            out.println(a);
            out.println("Enter a new value to be computed(enter negative number to quit)");
            answer = in.nextDouble();
        }

        in.close();
        out.close();
    }

}
