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
    private String name = "Order";
    private String script = "장바구니를 확인 후 주문합니다.";
    Scanner sc = new Scanner(System.in);

    public void displayOrder(){
        // 장바구니 출력
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("[ Orders ]");

        // 장바구니가 비어있을 경우 주문이 되지 않음
        if(orderList.isEmpty()) {
            System.out.println("장바구니가 비어있습니다.\n상품 선택 후 주문 해주세요\n(3초 후 메뉴판으로 돌아갑니다.)");
            waitMain();     // 3초 후 메뉴판 실행
        }
        else {
            // 장바구니 내역 출력
            for (Products p : orderList)
                System.out.println(p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getCount() + "개 | " + p.getScript());

 //           System.out.println("\n옵션 추가 금액 : \u20A9 " + optionPrice);

            System.out.println("\n[ Total ]");
            System.out.println("\u20A9 " + totalPrice);

            System.out.print("\n1.Order  2.Main menu");
            inputOrder();       // 주문 입력
        }
    }

    public void inputOrder(){
        // 주문 입력
        System.out.print("\n입력 : ");
        int select = sc.nextInt();

        if(select == 1){
            Main.totalPrice += this.totalPrice;     // 총 주문금액 업데이트

            for(Products p : orderList){            // 총 주문내역 업데이트
                if(Main.totalProducts.containsValue(p)){                    // 내역에 이미 존재하면
                    Products listP = Main.totalProducts.get(p.getName());   // 해당 객체를 찾음
                    listP.setCount(listP.getCount() + p.getCount());        // count 업데이트
                    listP.setPrice(listP.getPrice() + p.getPrice());        // price 업데이트
                }
                else Main.totalProducts.put(p.getName(), p);                // 존재하지 않으면 put
            }

            clearOrder();                           // 장바구니 초기화

            System.out.println("\n---------------------------------------------------------------------------\n");
            System.out.println("주문이 완료되었습니다!\n");
            System.out.println("대기번호는 [ " + waitingNum++ +" ] 번 입니다.");
            System.out.println("(3초 후 메뉴판으로 돌아갑니다.)");
            waitMain();     // 3초 후 메뉴판 실행
        }
        else if(select == 2) {
            Main.displayMainMenu();
        }
        else{
            System.out.println("잘못된 입력 입니다.");
            inputOrder();
        }
    }

    public void waitMain(){
        // 3초 후 메뉴판 실행
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            Main.displayMainMenu();
        }, 3, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    public void addList(Products p){
        // displayMenu()에서 전달받은 상품 객체 장바구니에 추가
        String name = p.getName();
        int price = p.getPrice();

        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println(p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getScript());

        if(p.options != null){                  // 옵션이 존재할 경우
            System.out.println("\n위 메뉴의 어떤 옵션으로 추가하시겠습니까?");

            int i = 1;
            for(String key : p.options.keySet())    // 옵션 출력
                System.out.println(i++ + ". " + key + " (+ \u20A9 " + p.options.get(key) + ")");

            System.out.print("\n옵션 선택 : ");
            int op_select = sc.nextInt();
            System.out.println("\n---------------------------------------------------------------------------\n");

            int j = 1;
            for(String key : p.options.keySet()){
                if(op_select == j++){                               // map에서 op_select번째 값 탐색
                    name += "(" + key + ")";           // name을 상품 이름+(옵션 이름)으로 저장
                    price += p.options.get(key);         // 현재 상품 값인 price에 옵션 값을 더해 저장
                    String script = p.getScript();

  //                  optionPrice += p.options.get(key);              // 전체 옵션 추가금 업데이트

                    // 주문 목록에 존재하면 해당 객체 반환, 존재하지 않으면 생성
                    p = null;
                    for (Products find_p : orderList) {
                        if (find_p.getName().equals(name)) {
                            p = find_p;
                            break;
                        }
                    }
                    if(p == null) p = new Products(name, script, price);
                    break;
                }
            }
            System.out.println(p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getScript());
        }

        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.print("1.Yes  2.No\n입력 : ");
        int select = sc.nextInt();

        if(select == 1) {
            if(orderList.contains(p)) p.addCount();     // 장바구니에 동일 상품 존재시 conunt++
            else orderList.add(p);                      // 없으면 추가

            totalPrice += p.getPrice();
            System.out.println(p.getName() +" 장바구니에 추가되었습니다.");
        }
        else if(select == 2) {
            System.out.println("선택이 취소됩니다.");
        }
        else{
            System.out.println("잘못된 입력 입니다.");
        }
        Main.displayMainMenu();
    }


    public void clearOrder(){
        orderList.clear();
        totalPrice = 0;
        optionPrice = 0;
    }

    public String getName(){
        return this.name;
    }

    public String getScript(){
        return this.script;
    }
}