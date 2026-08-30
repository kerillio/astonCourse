package Step1;

import java.util.Map;

public final class module1 {
    private final String name;
    private final int id;
    private final Map <String, String> map;

    public module1 (String name, int id, Map <String, String> map) {
        this.name = name;
        this.id = id;
        this.map = map;
    }

    public String getName() {
        return this.name;
    }

    public int getId () {
        return this.id;
    }

    public Map <String, String> getMap() {
        return Map.copyOf(this.map);
    }

}
