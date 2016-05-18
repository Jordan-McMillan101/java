import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class StringReassemblyTest {

    @Test
    public void combinationOverlap2() {
        String str1 = "abcd";
        String str2 = "cdxy";
        String actual1 = StringReassembly.combination(str1, str2, 2);
        assertEquals("abcdxy", actual1);
    }

    @Test
    public void combination3Overlap() {
        String str3 = "abc";
        String str4 = "abc";
        String actual2 = StringReassembly.combination(str3, str4, 3);
        assertEquals("abc", actual2);
    }

    @Test
    public void combination0Overlap() {
        String str5 = "abc";
        String str6 = "def";
        String actual3 = StringReassembly.combination(str5, str6, 0);
        assertEquals("abcdef", actual3);
    }

    @Test
    public void addToSetAvoidingSubstringsWithEmptySet() {
        Set<String> strSet = new Set1L<>();

        StringReassembly.addToSetAvoidingSubstrings(strSet, "cat");
        Set<String> actualSet = new Set1L<String>();
        actualSet.add("cat");

        assertEquals(strSet, actualSet);
        
    }

    @Test
    public void addToAvoidSubstringsWithNormalSet() {
        Set<String> strSet = new Set1L<>();
        strSet.add("a");
        strSet.add("b");
        strSet.add("c");

        StringReassembly.addToSetAvoidingSubstrings(strSet, "cat");
        Set<String> actualSet = new Set1L<String>();
        actualSet.add("b");
        actualSet.add("cat");

        assertEquals(strSet, actualSet);
    }

    @Test
    public void addToAvoidSubstringsWithNewString() {
        Set<String> strSet = new Set1L<>();
        strSet.add("a");
        strSet.add("b");
        strSet.add("c");

        StringReassembly.addToSetAvoidingSubstrings(strSet, "dog");
        Set<String> actualSet = new Set1L<String>();
        actualSet.add("b");
        actualSet.add("dog");

        assertEquals(strSet, actualSet);
    }

    @Test
    public void addToAvoidSubstringsWithReplaceAllOfSet() {
        Set<String> strSet = new Set1L<>();
        strSet.add("a");
        strSet.add("b");
        strSet.add("c");

        StringReassembly.addToSetAvoidingSubstrings(strSet, "abc");
        Set<String> actualSet = new Set1L<String>();
        actualSet.add("abc");

        assertEquals(strSet, actualSet);
    }

}
