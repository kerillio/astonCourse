package Step2.Module1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomHashMapTest {

    CustomHashMap<Integer, String> tab = new CustomHashMap<>();



    @Test
    void putTest() {
        tab.put(1, "one");
    }

    @Test
    void getTest() {
        tab.put(1, "one");
        Assertions.assertEquals("one", tab.get(1));
    }

    @Test
    void removeTest() {
        tab.put(1, "one");
        Assertions.assertEquals("one", tab.get(1));
        tab.remove(1);
        Assertions.assertNull(tab.get(1));

    }
}