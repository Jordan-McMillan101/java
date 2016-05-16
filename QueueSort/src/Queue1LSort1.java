import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * Default constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     * 
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates {@code q}
     * @requires <pre>
     * {@code q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]}
     * </pre>
     * @ensures <pre>
     * {@code (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))}
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

//        String s1 = q.dequeue();
//        String s2 = "";
//        int integer;
//        for (int i = 0; i < q.length(); i++) {
//            s2 = q.dequeue();
//            integer = order.compare(s1, s2);
//            if (integer > 0) {
//                String temp = s2;
//                s2 = s1;
//                s1 = temp;
//            }
//            q.enqueue(s2);
//        }
//        return s1;

        //"the clean way"
        String min = q.dequeue();
        Queue<String> temp = q.newInstance();
        temp.transferFrom(q); //everything will be put into the temp que
        while (temp.length() > 0) {
            String s = temp.dequeue();
            if (order.compare(s, min) < 0) { //boolean asking is s < min
                q.enqueue(min);
                min = s;
            } else {
                q.enqueue(s); //if s isn't less than min
            }
        }

        return min;

    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

    }

}