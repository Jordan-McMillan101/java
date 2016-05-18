import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class CreateHTML {

    public static void main(String[] args) {

        SimpleWriter consoleOut = new SimpleWriter1L();
        consoleOut
                .println("Enter your name and I will create a webpage for you: ");
        SimpleReader in = new SimpleReader1L();
        String name = in.nextLine();

        String fileName = "mypage.html";
        SimpleWriter fileOut = new SimpleWriter1L(fileName);
        fileOut.println("<html><body>");
        fileOut.println("<h1> ZOMG no way </h1>");
        fileOut.println("Hello <b>" + name + "</b>");
        fileOut.println("</body></html>");

        fileOut.close();
        consoleOut.close();
        in.close();

    }
}