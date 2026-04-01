package cart;
//商品を何個カートに入れたかを表すクラス,レシート的な
public class CartItem {
    private Product product; // どの商品か
    private int quantity; // 何個か

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity; //個数
    }
    public Product getProduct(){
        return product;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getSubtotal(){
        return product.getPrice() * quantity; //値段に個数をかけている
    }
}
