import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import static Main.printMainMenu;

public class Order {
    ArrayList<Products> orderList = new ArrayList<Products>();
    int totalPrice;
    int waitingNum = 1;
    String select;
    public String name = "Order";
    public String script = "장바구니를 확인 후 주문합니다.";

    public void addList(Products p){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getScript());
        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.print("1.확인  2.취소\n입력 : ");
        String select = sc.nextLine();

        if(select.equals("확인")) {
            orderList.add(p);
            totalPrice += p.price;
            System.out.println(p.getName()+" 장바구니에 추가되었습니다.");
            Main.printMainMenu();
        }
        else if(select.equals("취소")){
            System.out.println("선택이 취소됩니다.");
            Main.printMainMenu();
        }
    }

    public void cancelOrder(){
        orderList.clear();
        totalPrice = 0;
    }
    public void printOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n[ Orders ]");

        for(Products p : orderList)
            System.out.println(p.name + " | \u20A9" + p.price + " | " + p.script);

        System.out.println("\n[ Total ]");
        System.out.println("\u20A9 " + totalPrice);
        System.out.print("\n1.주문  2.메뉴판\n입력 : ");
        select = sc.nextLine();

        if(select.equals("주문")){
            cancelOrder();
            System.out.println("주문이 완료되었습니다!\n");
            System.out.println("대기번호는 [ " + waitingNum++ +" ] 번 입니다.");
            System.out.println("(3초 후 메뉴판으로 돌아갑니다.)");

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> {
                // 3초 후 메뉴판 실행
                Main.printMainMenu();
            }, 3, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
        else if(select.equals("메뉴판")){
            Main.printMainMenu();
        }
    }

    public String getName(){
        return this.name;
    }

    public String getScript(){
        return this.script;
    }
}