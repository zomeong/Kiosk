import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String name;
    private String script;

    public ArrayList<Products> menuList = new ArrayList<>();

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
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("[ " + this.name + " MENU ] ");
        int i = 1;
        for (Products p : menuList)
            System.out.println(i++ + ". " + p.getName() + " | \u20A9 " + p.price + " | " + p.getScript());

        // 상품 검색
        System.out.print("\n상품을 입력해주세요 : ");
        String select = sc.nextLine();
        for (Products p : menuList)
            if (p.getName().equals(select)) return p;       // 선택한 상품 객체 return

        return null;
    }
}
