import java.util.LinkedHashMap;
import java.util.Map;

public class Products extends Menu {
    private int price;
    private int count = 1;
    public Map<String, Integer> options = null;

    public Products(String name, String script, int price) {
        super(name, script);
        this.price = price;
    }
    public Products(String name, String script, int price, Object...options) {
        super(name, script);
        this.price = price;

        // options를 Object 타입 가변 인자로 받아서 각각 String과 Integer로 형변환 후 map에 저장
        this.options =  new LinkedHashMap<>();
        for(int i = 0; i < options.length; i += 2) {
            String key = (String) options[i];
            Integer value = (Integer) options[i + 1];
            this.options.put(key, value);
        }
    }

    public void addCount(){
        this.count++;
    }
    public int getPrice(){
        return this.price;
    }
    public int getCount(){
        return this.count;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setCount(int count){
        this.count = count;
    }
}
