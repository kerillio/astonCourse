package Step2.Module1;

import java.util.ArrayList;

public class CustomHashMap <K, V>  {

    private ArrayList<String> array = new ArrayList<>(16);

    public void put(K key, V value) {
        int index = (array.size() - 1) & key.hashCode();

        try {
            if (array.get(index) == null)
                array.add(index, value.toString());
            else
                array.set(index, value.toString());
        } catch (IndexOutOfBoundsException e) {
            array.add(value.toString());
        }

    }

    public V get(K key) {

        int index = (array.size() - 1) & key.hashCode();
        V value = null;
        try {

            if (array.get(index) != null) value = (V) array.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    public void remove(K key) {
        int index = (array.size() - 1) & key.hashCode();
        array.remove(index);
    }

}
