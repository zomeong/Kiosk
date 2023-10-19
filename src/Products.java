public class Products extends Menu {
    public int price;
    public Products(String name, String script, int price) {
        super(name, script);
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }
}
