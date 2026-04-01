package cart;
//カートの内容を確定して注文するクラス
public class Order {
    private Cart cart;
    private String date;
    private String status;
    
    public Order(Cart cart,String date){
        this.cart = cart;
        this.date = date;
        this.status = "注文済み";
    }
    
    public void showOrder(){
        System.out.println("注文日" + date);
        cart.showItems();
        System.out.println("合計" + cart.getTotalPrice() + "円");
        System.out.println("状態: " + status);
    }
}
