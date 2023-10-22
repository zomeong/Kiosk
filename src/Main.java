import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Menu> mainMenuList = new ArrayList<>();
    public static Map<String, Products> totalProducts = new HashMap<>();
    public static Order order = new Order();
    public static int totalPrice;

    public static void displayMainMenu(){
        // 메인 메뉴 출력
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("Starbucks에 오신 것을 환영합니다.");
        System.out.println("(* 모든 입력은 번호로 해주세요 *)");
        System.out.println("\n[ MENU ] ");

        int i = 1;
        for(Menu m : mainMenuList)      // 메뉴 리스트 출력
            System.out.println(i++ + ". " + m.getName() + " | " + m.getScript());

        System.out.println("\n[ ORDER ] ");
        System.out.println(i++ + ". " + order.getName() + " | " + order.getScript());
        System.out.println(i + ". " + "Cancel" + " | " + "진행중인 주문을 취소합니다.");

        inputMenu();      // 메뉴 입력
    }

    public static void displayHiddenMenu(){
        // 총 주문 현황 확인
        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("[ 총 판매금액 현황 ]");
        System.out.println("현재까지 총 판매된 금액은 [ \u20A9 " + totalPrice + " ] 입니다.\n");

        System.out.println("[ 총 판매상품 목록 현황 ]");
        for(Products p : totalProducts.values())
            System.out.println("- " + p.getName() + " | \u20A9 " + p.getPrice() + " | " + p.getCount() + "개");

        System.out.print("\n1. Back\n입력 : ");
        if(sc.nextInt() == 1) displayMainMenu();
    }

    public static void inputMenu(){
        // 메뉴 입력
        System.out.print("\n메뉴 번호를 선택해주세요 : ");
        int select = sc.nextInt();

        if(select == 0) {
            displayHiddenMenu();                            // 히든 메뉴 출력 (총 주문 현황)
        }
        else if(select == 1 || select == 2 || select == 3 || select == 4) {
            Menu m = mainMenuList.get(select - 1);          // 해당 메뉴 객체 선택
            order.addList(m.displayMenu());                 // 상품 메뉴 리스트 출력 -> 상품 선택 -> 상품 객체 return -> 장바구니 추가
        }
        else if(select == 5) {
            order.displayOrder();                           // 장바구니 출력
        }
        else if(select == 6){
            System.out.println("\n---------------------------------------------------------------------------\n");
            System.out.println("진행하던 주문을 취소하시겠습니까?");
            System.out.print("\n1.Yes  2.No\n입력 : ");
            select = sc.nextInt();

            if(select == 1){
                order.clearOrder();                         // 장바구니 비우기
                System.out.println("\n진행하던 주문이 취소되었습니다.");
            }
            displayMainMenu();
        }
        else{
            System.out.println("잘못된 입력입니다.");
            inputMenu();                                   // 잘못된 입력일 경우 재실행
        }
    }

    public static void main(String[] args) {

        // main menu list
        Menu coffeeMenu = new Menu("Coffee", "스타벅스만의 고지대에서 생산된 아라비카 커피");
        Menu smoothieMenu = new Menu("Smoothie", "과일을 갈아 만든 블렌디드와 샷을 넣은 프라푸치노");
        Menu dessertMenu = new Menu("Dessert", "다양한 종류의 디저트");
        Menu breadMenu = new Menu("Bread", "간단하고 든든한 빵");
        mainMenuList.add(coffeeMenu);
        mainMenuList.add(smoothieMenu);
        mainMenuList.add(dessertMenu);
        mainMenuList.add(breadMenu);

        // coffee menu
        ArrayList<Products> cm = coffeeMenu.menuList;
        cm.add(new Products("Espresso", "향기로운 크레마 층과 바디 층, 하트 층으로 이루어진 커피", 4000));
        cm.add(new Products("Americano", "에스프레소와 물을 섞은 커피", 4500, "Ice", 500, "Hot", 0));
        cm.add(new Products("Caffe Mocha", "초콜릿 모카 시럽과 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료", 5500, "모카 시럽 추가", 500, "기본", 0));
        cm.add(new Products("Hazelnut Latte", "고소한 마롱, 헤이즐넛과 블론드 에스프레소가 만나 가을을 느낄 수 있는 음료", 6500,"Ice", 500, "Hot", 0));
        cm.add(new Products("Glazed Latte", "가을 시즌 대표 음료! 짙고 풍부한 커피와 달콤하고 부드러운 글레이즈드 폼의 조화", 6500,"Ice", 500, "Hot", 0));
        cm.add(new Products("Cold Brew", "콜드 브루에 더해진 바닐라 크림으로 깔끔하면서 달콤한 콜드 브루", 5800));
        cm.add(new Products("Macchiato", "바닐라 시럽과 따뜻한 스팀 밀크 위에 우유 거품을 얹고 에스프레소를 부은 음료", 5900, "카라멜 시럽 추가", 500, "바닐라 시럽 추가", 500, "기본", 0));

        // smoothie menu
        ArrayList<Products> sm = smoothieMenu.menuList;
        sm.add(new Products("Java Chip Frappuccino", "커피, 모카 소스, 초콜릿 칩이 어우러진 프라푸치노", 6300, "샷 추가", 500, "기본", 0));
        sm.add(new Products("Malcha Frappuccino", "말차의 맛과 향을 시원하고 부드럽게 즐길 수 있는 프라푸치노", 6500, "진하게", 300, "기본", 0, "연하게", -300));
        sm.add(new Products("Mango Blended", "망고 패션프루트 주스에 바나나가 들어간 블렌디드", 5400, "Tall size", 0, "Grande size", 800));
        sm.add(new Products("Peach Blended", "복숭아에 요거트가 더해져 가볍고 상큼한 과일 블렌디드", 6100, "Tall size", 0, "Grande size", 800));
        sm.add(new Products("Yogurt Blended", "요거트와 딸기 과육이 상큼하게 어우러진 과일 요거트 블렌디드", 6300, "Tall size", 0, "Grande size", 800));

        // dessert menu
        ArrayList<Products> dm = dessertMenu.menuList;
        dm.add(new Products("Cheese Cake", "고온에서 짧게 구워 겉면은 스모키하고 속은 크리미한 바스크 치즈 케이크", 5900));
        dm.add(new Products("Vanilla Macaron", "겉은 바삭하고 속은 쫄깃한 달콤한 바닐라 맛의 마카롱", 2700));
        dm.add(new Products("Peanut Madeleine", "고소한 땅콩을 넣어 구운 마들렌에 달콤한 초콜릿을 코팅한 마들렌", 3900));
        dm.add(new Products("Chocolate Cookie", "진한 다크 초콜릿과 고소한 피칸이 들어있는 쿠키", 4500));

        // bread menu
        ArrayList<Products> bm = breadMenu.menuList;
        bm.add(new Products("Plain Bagel", "고온에서 데치고 짧게 구워 내 쫄깃하고 촉촉한 플레인 베이글", 3300));
        bm.add(new Products("Classic Scone", "프랑스산 고급 버터로 만든 담백한 스콘", 4000));
        bm.add(new Products("Ciabatta", "바삭한 치아바타에 바비큐 소스 치킨, 베이컨, 치즈가 어우러진 치아바타", 5800));
        bm.add(new Products("Egg Sandwich", "식빵 사이에 고소한 에그 스프레드를 넣은 부드러운 샌드위치", 4400));
        bm.add(new Products("English Muffin", "간편하게 즐길 수 있는 잉글리쉬 머핀 샌드위치", 4200));

        // 메인 메뉴 실행
        displayMainMenu();
    }
}
