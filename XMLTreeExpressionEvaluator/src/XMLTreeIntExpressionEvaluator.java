import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 * 
 * @author Put your name here
 * 
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int num1 = 0;
        int num2 = 0;
        int result = 0;

        //simplest case when the node is just a number and not an operator
        if (exp.label().equals("number")) {
            String number = exp.attributeValue("value");
            num1 = Integer.parseInt(number);
            result = num1;
        } else {
            if (exp.label().equals("times")) {

                num1 = evaluate(exp.child(0));
                num2 = evaluate(exp.child(1));
                result = num1 * num2;

            } else if (exp.label().equals("divide")) {

                num1 = evaluate(exp.child(0));
                num2 = evaluate(exp.child(1));
                result = num1 / num2;

            } else if (exp.label().equals("minus")) {

                num1 = evaluate(exp.child(0));
                num2 = evaluate(exp.child(1));
                if (num1 > num2) {
                    result = num1 - num2;
                } else {
                    result = num2 - num1;
                }

            } else if (exp.label().equals("plus")) {

                num1 = evaluate(exp.child(0));
                num2 = evaluate(exp.child(1));
                result = num1 + num2;
            }
        }

        return result;
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
