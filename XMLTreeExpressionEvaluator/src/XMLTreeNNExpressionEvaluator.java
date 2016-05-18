import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 * 
 * @author Put your name here
 * 
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     * 
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * {@code [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]}
     * </pre>
     * @ensures <pre>
     * {@code evaluate = [the value of the expression]}
     * </pre>
     */
    private static NaturalNumber evaluate(XMLTree exp) {

        NaturalNumber left = new NaturalNumber2();
        NaturalNumber right = new NaturalNumber2();

        //simplest case
        if (exp.label().equals("times")) {

            left = evaluate(exp.child(0));
            right = evaluate(exp.child(1));
            left.multiply(right);

        } else if (exp.label().equals("divide")) {

            left = evaluate(exp.child(0));
            right = evaluate(exp.child(1));
            //division by 0 - report fatal error to console
            if (right.isZero()) {
                Reporter.fatalErrorToConsole("--Cannot divide by zero--");
            }
            left.divide(right);

        } else if (exp.label().equals("minus")) {

            left = evaluate(exp.child(0));
            right = evaluate(exp.child(1));
            if (left.compareTo(right) < 0) {
                Reporter.fatalErrorToConsole("--Cannot subtract a number by an even larger  number--");
            }
            left.subtract(right);

        } else if (exp.label().equals("plus")) {

            left = evaluate(exp.child(0));
            right = evaluate(exp.child(1));
            left.add(right);

        } else { // if the node is simply just a number then this part of the selection structure will be ran

            String number = exp.attributeValue("value");
            left = new NaturalNumber2(number);

        }

        return left;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
