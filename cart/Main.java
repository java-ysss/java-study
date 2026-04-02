package cart;
//全体の流れを作るクラス
public class Main {
    public static void main(String[] args){

        Product apple = new Product("リンゴ",200,10,"スイーツ");
        Product banana = new Product("バナナ",150,15,"スイーツ"); 

        Cart cart = new Cart();
        cart.addItem(apple,3);
        cart.addItem(banana,2);

        cart.removeItem(apple);

        Order order = new Order(cart, "2024-03-20");
        order.showOrder();
    }
}
