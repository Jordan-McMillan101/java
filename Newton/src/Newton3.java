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
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * 
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        double r = x;
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
        out.println("Enter the allowed error: ");
        double epsilon = in.nextDouble();
        String answer = "y"; //initialized the string variable to y so it goes into the while loop.
        while (answer.equals("y")) {
            out.print("Enter positive number(double): ");
            double x = in.nextDouble();
            double a = sqrt(x, epsilon);
            out.println(a);
            out.println("Do you wish to calculate another square root?(y/n)");
            answer = in.nextLine();
        }

        in.close();
        out.close();
    }

}
