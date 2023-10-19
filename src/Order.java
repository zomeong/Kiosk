import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Order {
    ArrayList<Products> orderList = new ArrayList<>();
    int totalPrice;
    int optionPrice;
    int waitingNum = 1;
    String select;
    private String name = "Order";
    private String script = "장바구니를 확인 후 주문합니다.";

    public void addList(Products p){            // 에서 전달받은 상품 객체 장바구니에 추가
        Scanner sc = new Scanner(System.in);
        int price = p.price;

        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println(p.getName() + " | \u20A9 " + p.price + " | " + p.getScript());

        if(p.options != null){                  // 옵션이 존재할 경우
            System.out.println("\n위 메뉴의 어떤 옵션으로 추가하시겠습니까?");

            int i = 1;
            for(String key : p.options.keySet())
                System.out.println(i++ + ". " + key + " (+ \u20A9 " + p.options.get(key) + ")");

            System.out.print("\n옵션 선택 : ");
            String op_select = sc.nextLine();
            System.out.println("\n---------------------------------------------------------------------------\n");

            String name = p.getName() + "(" + op_select + ")";
            price += p.options.get(op_select);
            optionPrice += p.options.get(op_select);

            System.out.println(name + " | \u20A9 " + price + " | " + p.getScript());
        }

        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.print("1.Yes  2.No\n입력 : ");
        String select = sc.nextLine();

        if(select.equals("Yes")) {
            if(orderList.contains(p)) p.addCount();     // 장바구니에 동일 상품 존재시 conunt++
            else orderList.add(p);                      // 없으면 추가

            totalPrice += price;
            System.out.println(p.getName() +" 장바구니에 추가되었습니다.");
            Main.printMainMenu();
        }
        else if(select.equals("No")){
            System.out.println("선택이 취소됩니다.");
            Main.printMainMenu();
        }
    }

    public void clearOrder(){
        orderList.clear();
        totalPrice = 0;
        optionPrice = 0;
    }
    public void printOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("\n[ Orders ]");

        if(orderList.isEmpty()) System.out.println("장바구니가 비어있습니다.");
        for(Products p : orderList)                  // 장바구니 내역 출력
            System.out.println(p.getName() + " | \u20A9 " + p.price + " | " + p.count + "개 | " + p.getScript());

        System.out.println("\n옵션 추가 금액 : \u20A9 " + optionPrice);

        System.out.println("\n[ Total ]");
        System.out.println("\u20A9 " + totalPrice);
        System.out.print("\n1.Order  2.Main menu\n입력 : ");
        select = sc.nextLine();

        if(select.equals("Order")){
            Main.totalPrice += this.totalPrice;     // 총 주문금액 업데이트

            for(Products p : orderList){            // 총 주문내역 업데이트
                if(Main.totalProducts.containsValue(p)){
                    Products listP = Main.totalProducts.get(p.getName());
                    listP.count += p.count;
                    listP.price += p.price;
                }
                else Main.totalProducts.put(p.getName(), p);
            }

            clearOrder();                           // 장바구니 초기화

            System.out.println("\n---------------------------------------------------------------------------\n");
            System.out.println("주문이 완료되었습니다!\n");
            System.out.println("대기번호는 [ " + waitingNum++ +" ] 번 입니다.");
            System.out.println("(3초 후 메뉴판으로 돌아갑니다.)");

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> {             // 3초 후 메뉴판 실행
                Main.printMainMenu();
            }, 3, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
        else if(select.equals("Main menu")){
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