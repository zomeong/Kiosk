import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Menu> mainMenuList = new ArrayList<Menu>();
    public static Order order = new Order();

    public static void printMainMenu(){
        // menu list 출력
        System.out.println("\nStarbucks에 오신 것을 환영합니다.");
        System.out.println("\n[ MENU ] ");
        int i = 1;
        for(Menu m : mainMenuList) {
            System.out.println(i++ + ". " + m.getName() + " | " + m.getScript());

        }
        System.out.println("\n[ ORDER ] ");
        System.out.println(i++ + ". " + order.getName() + " | " + order.getScript());
        System.out.println(i++ + ". " + "Cancel" + " | " + "진행중인 주문을 취소합니다.");


        // 메뉴 입력
        System.out.print("\n메뉴를 입력해주세요 : ");
        String select = sc.nextLine();

        if(select.equals("Order")){
            order.printOrder();                             // 장바구니 출력
        }
        else if(select.equals("Cancel")){
            System.out.println("진행하던 주문을 취소하시겠습니까?");
            System.out.print("\n1.확인  2.취소\n입력 : ");
            select = sc.nextLine();

            if(select.equals("확인")){
                order.cancelOrder();
                System.out.println("진행하던 주문이 취소되었습니다.");
                printMainMenu();
            }
        }
        else {
            for(Menu m : mainMenuList) {
                if(m.getName().equals(select))              // 입력된 값과 동일한 상품 메뉴 검색
                    order.addList(m.printMenu());          // 상품 메뉴 출력 후 장바구니에 추가
            }
        }
    }

    public static void main(String[] args) {

        // main menu list
        Menu coffeeMenu = new Menu("Coffee", "스타벅스만의 고지대에서 생산된 아라비카 커피");
        Menu smoothieMenu = new Menu("Smoothie", "과일을 갈아 만든 블렌디드와 샷을 넣은 프라푸치노");
        Menu dessertMenu = new Menu("Dessert", "다양한 종류의 디저트");
        mainMenuList.add(coffeeMenu);
        mainMenuList.add(smoothieMenu);
        mainMenuList.add(dessertMenu);

        // coffee menu
        coffeeMenu.menuList.add(new Products("Americano", "에스프레소와 뜨거운 물을 섞은 커피", 4500));
        coffeeMenu.menuList.add(new Products("Caffe Mocha", "초콜릿 모카 시럽과 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료", 5500));
        coffeeMenu.menuList.add(new Products("Hazelnut Latte", "고소한 마롱, 헤이즐넛과 블론드 에스프레소가 만나 가을을 느낄 수 있는 음료", 6500));
        coffeeMenu.menuList.add(new Products("Glazed Latte", "가을 시즌 대표 음료! 짙고 풍부한 커피와 달콤하고 부드러운 글레이즈드 폼의 조화", 6500));

        // smoothie menu
        smoothieMenu.menuList.add(new Products("Java Chip Frappuccino", "커피, 모카 소스, 초콜릿 칩이 어우러진 프라푸치노", 6300));
        smoothieMenu.menuList.add(new Products("Malcha Frappuccino", "말차 본연의 맛과 향을 시원하고 부드럽게 즐길 수 있는 프라푸치노", 6500));
        smoothieMenu.menuList.add(new Products("Mango Blended", "망고 패션프루트 주스에 바나나가 들어간 블랜디드", 5400));
        smoothieMenu.menuList.add(new Products("Peach Blended", "복숭아에 요거트가 더해져 가볍고 상큼한 과일 블랜디드", 6100));

        // dessert menu
        dessertMenu.menuList.add(new Products("Cheese Cake", "고온에서 짧게 구워 겉면은 스모키하고 속은 크리미한 바스크 치즈 케이크", 5900));
        dessertMenu.menuList.add(new Products("Vanilla Macaron", "겉은 바삭하고 속은 쫄깃한 달콤한 바닐라 맛의 마카롱", 2700));
        dessertMenu.menuList.add(new Products("Peanut Madeleine", "고소한 땅콩을 넣어 구운 마들렌에 달콤한 초콜릿을 코팅한 마들렌", 3900));
        dessertMenu.menuList.add(new Products("Chocolate Cookie", "진한 다크 초콜릿과 고소한 피칸이 들어있는 쿠키", 4500));


        // 메인 메뉴 실행
        printMainMenu();
    }
}
