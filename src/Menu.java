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

    public Products displayMenu() {
        Scanner sc = new Scanner(System.in);

        // 상품 메뉴판 출력
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("[ " + this.name + " MENU ] ");
        int i = 1;
        for (Products p : menuList)
            System.out.println(i++ + ". " + p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getScript());

        // 상품 입력
        System.out.print("\n상품 번호를 입력해주세요 : ");
        int select = sc.nextInt();
        Products p = menuList.get(select - 1);      // 선택한 상품 객체 return
        return p;
    }
}
