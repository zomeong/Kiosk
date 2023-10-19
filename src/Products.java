import java.util.LinkedHashMap;
import java.util.Map;

public class Products extends Menu {
    public int price;
    public int count = 1;
    public Map<String, Integer> options = new LinkedHashMap<>();

    public Products(String name, String script, int price) {
        super(name, script);
        this.price = price;
    }
    public Products(String name, String script, int price, Object...options) {
        super(name, script);
        this.price = price;

        for(int i = 0; i < options.length; i+=2) {
            String key = (String) options[i];
            Integer value = (Integer) options[i + 1];
            this.options.put(key, value);
        }
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void addCount(){
        this.count++;
    }
}
