import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Program to practice recursion on {@code XMLTree}s.
 * 
 * @author Put your name here
 * 
 */
public final class XMLTreeRecursion {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeRecursion() {
    }

    /**
     * Returns the number of occurrences of the given tag in the given
     * {@code XMLTree}.
     * 
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return the number of occurrences of the given tag in the given
     *         {@code XMLTree}
     * @ensures <pre>
     * {@code tagCount =
     *    [the number of occurrences of the given tag in the given {@code XMLTree}]}
     * </pre>
     */
    private static int tagCount(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";

        int counter = 0;

        if (xml.isTag()) {
            if (xml.label().equals(tag)) {
                counter++;
            }
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                counter += tagCount(xml.child(i), tag);

            }
        }
        return counter;
    }

    /**
     * Outputs the text nodes in the given {@code XMLTree} on separate lines.
     * 
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content * [the text nodes of xml on separate lines]}
     * </pre>
     */
    private static void outputTextNodes(XMLTree xml, SimpleWriter out) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        //if istag() check the children
        //otherize its a text node so print that
        if (xml.isTag()) {
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                out.println(xml.child(i));

                outputTextNodes(xml.child(i), out);

            }

        } else {
            println();
        }

    }

    /**
     * Outputs n spaces.
     * 
     * @param n
     *            the number of spaces
     * @param out
     *            the output stream
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open and n >= 0}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content * [n spaces]}
     * </pre>
     */
    private static void outputSpaces(int n, SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert n >= 0 : "Violation of: n >= 0";

        // TODO - fill in body

    }

    /**
     * Outputs the attributes ( name="value") of the given {@code XMLTree}'s
     * root node to the given output stream.
     * 
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open and [the label of the root of xml is a tag]}
     * </pre>
     * @ensures <pre>
     * {@code out.content =
     *    #out.content * [the attributes ( name="value") of the root of xml]}
     * </pre>
     */
    private static void outputAttributes(XMLTree xml, SimpleWriter out) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert xml.isTag() : "Violation of: the label of the root of xml is a tag";
        assert out.isOpen() : "Violation of: out.is_open";

        // TODO - fill in body

    }

    /**
     * Output the XML textual representation of the given {@code XMLTree}.
     * 
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @param indentationLevel
     *            the level of indentation
     * @updates {@code out.content}
     * @requires <pre>
     * {@code out.is_open and indentationLevel >= 0}
     * </pre>
     * @ensures <pre>
     * {@code out.content = #out.content * [the XML textual representation of xml]}
     * </pre>
     */
    private static void outputXML(XMLTree xml, SimpleWriter out,
            int indentationLevel) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert indentationLevel >= 0 : "Violation of: indentationLevel >= 0";

        // TODO - fill in body

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

        out.print("Enter a URL or file name for an XML source: ");
        String url = in.nextLine();
        XMLTree xml = new XMLTree2(url);

        out.print("Enter the name of a tag: ");
        String tag = in.nextLine();
        while (!tag.equals("")) {
            int count = tagCount(xml, tag);
            out.println("The tag <" + tag + "> appears " + count + " times.");
            out.println();
            out.print("Enter the name of a tag: ");
            tag = in.nextLine();
        }

        out.println();
        out.println("The text nodes:");
        outputTextNodes(xml, out);

        // out.println();
        // out.println("The XML:");
        // outputXML(xml, out, 0);

        in.close();
        out.close();
    }

}