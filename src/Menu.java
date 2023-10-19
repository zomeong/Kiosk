import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public String name;
    public String script;

    public ArrayList<Products> menuList = new ArrayList<Products>();

    public String getName(){
        return this.name;
    }
    public String getScript(){
        return this.script;
    }

    public Menu(String name, String script){
        this.name = name;
        this.script = script;
    }

    public Products printMenu() {
        Scanner sc = new Scanner(System.in);

        // 상품 메뉴판 출력
        System.out.println("\n[ " + this.name + " MENU ] ");
        int i = 1;
        for (Products p : menuList)
            System.out.println(i++ + ". " + p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getScript());

        // 상품 검색
        System.out.print("\n상품을 입력해주세요 : ");
        String select = sc.nextLine();
        for (Products p : menuList)
            if (p.getName().equals(select)) return p;
        return null;
    }
}
