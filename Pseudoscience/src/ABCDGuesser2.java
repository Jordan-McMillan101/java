import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program will be sing the The "charming theory" asserts that the de Jager
 * formula with your four personal numbers can be used to approximate Mu within
 * a fraction of 1% relative error.
 * 
 * @author Jordan L. McMillan
 * 
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * The method deJagerFormula will take in the needed values and return the
     * answer.
     * 
     * @param a
     *            value of the first exponent
     * @param b
     *            value of the second exponent
     * @param c
     *            value of the third exponent
     * @param d
     *            value of the fourth exponent
     * @param w
     *            the first user given significant number
     * @param x
     *            the second user given significant number
     * @param y
     *            the third user given significant number
     * @param z
     *            the fourth user given significant number
     * @param nums
     *            an array of exponents
     * @return will return the answer computed from the de jager formula
     */
    private static double deJagerFormula(int a, int b, int c, int d, double w,
            double x, double y, double z, double[] nums) {
        return Math.pow(w, nums[a]) * Math.pow(x, nums[b])
                * Math.pow(y, nums[c]) * Math.pow(z, nums[d]);

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
        double currentGuess = 0.0;
        double answer = 0;
        double currentError = Double.MAX_VALUE; //setting currentError to the largest possible double value

        out.print("Please provide the positive number for the constant Mu:");
        double mu = in.nextDouble();
        out.println("Next provide four positve numbers(not including 1) that might be significant to "
                + "yourself(ie year of birth, favorite number, ect).");
        out.println("The first number: ");
        double w = in.nextDouble();
        out.println("The second number: ");
        double x = in.nextDouble();
        out.println("The third number: ");
        double y = in.nextDouble();
        out.println("and finally, the fourth number: ");
        double z = in.nextDouble();
        double exponents[] = { 0, 0, 0, 0 };
        double nums[] = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3, -1.0 / 4, 0,
                1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };
        //int variables for testing the while loops
        int a;
        int b;
        int c;
        int d;

        for (a = 0; a < 17; a++) {
            for (b = 0; b < 17; b++) {

                for (c = 0; c < 17; c++) {

                    for (d = 0; d < 17; d++) {

                        answer = deJagerFormula(a, b, c, d, w, x, y, z, nums);
                        //an if statment checks that the relative error is smaller than the previous computed error(initially it is set to the largest double number possible)
                        if (Math.abs((mu - answer) / answer) < currentError) {
                            exponents[0] = nums[a];
                            exponents[1] = nums[b];
                            exponents[2] = nums[c];
                            exponents[3] = nums[d];

                            currentGuess = answer;
                            //the currentError will continue to be set to the lowest relative error computed as if check all possibilities 
                            currentError = Math.abs((mu - answer) / answer);
                        }

                    }
                    d = 0;
                }
                c = 0;
            }
            b = 0;
        }
        //the final output information
        out.println("Initial Value = " + mu);
        out.print("Approximation = ");
        out.println(currentGuess);
        out.println("Exponents used: ");
        out.print(exponents[0]);
        out.print(", ");
        out.print(exponents[1]);
        out.print(", ");
        out.print(exponents[2]);
        out.print(", ");
        out.println(exponents[3]);

        in.close();
        out.close();

    }
}
