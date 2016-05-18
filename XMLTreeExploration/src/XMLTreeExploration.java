import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 * 
 * @author Put your name here
 * 
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
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

        XMLTree xml = new XMLTree1(
                "http://xml.weather.yahoo.com/forecastrss/43210.xml");
        //out.println(xml.toString());

        xml.isTag();
        if (!xml.isTag()) {
            out.println("label of the root is a text.");
        } else {
            out.println("root is a tag.");
        }
        xml.display();

        // child section 
        XMLTree channel = xml.child(0);
        out.println("# of children channel has: " + channel.numberOfChildren());
        XMLTree title = xml.child(0).child(1);
        out.println("# of children title has: " + title.numberOfChildren());
        XMLTree titleText = xml.child(0).child(1).child(0);
        out.println("The label of titleText: " + titleText.label());
        out.println("The label of the titleText(with one line of code): "
                + xml.child(0).child(1).child(0));
        //attributes section
        XMLTree astronomy = xml.child(0).child(11);
        if (xml.hasAttribute("sunset")) {
            out.println(xml.attributeValue("sunset"));
            out.println(xml.attributeValue("sunrise"));

        }

        in.close();
        out.close();
    }
}
