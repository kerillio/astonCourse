package Step1.Module1;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main (String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "World");
        ImmutableClass test1 = new ImmutableClass("Kirill", 1, map);

        System.out.println(test1.getId());
        System.out.println(test1.getName());
        System.out.println(test1.getMap());

        map.put("another", "string");
        System.out.println(test1.getMap());

    }
}