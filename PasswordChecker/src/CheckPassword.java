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
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the CSE department criteria for
     * a valid password. Prints an appropriate message to the given output
     * stream.
     * 
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        if (s.length() < 6) {
            out.println("String must be at least 6 character long.");
        }
        int checker = 0;
        if (containsUpperCaseLetter(s)) {
            checker++;
        }
        if (containsLowerCaseLetter(s)) {
            checker++;
        }
        if (containsDigit(s)) {
            checker++;
        }
        if (checker < 3) {
            out.println("Invalid password.");
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     * 
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }

        }
        return false;
    }

    /**
     * 
     * @param s
     *            string and whatnot
     * @return returns true or false
     */
    private static boolean containsLowerCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return true;
            }

        }
        return false;
    }

    /**
     * 
     * @param s
     *            string that is being passed through
     * @return true or false
     */

    private static boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }

        }
        return false;
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

        out.println("Enter password: ");
        String password = in.nextLine();

        checkPassword(password, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
