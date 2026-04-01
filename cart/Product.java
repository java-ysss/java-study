package cart;
//商品データを持つクラス
public class Product {
    private String name;
    private int price;
    private int stock;
    private String category;

    public Product(String name, int price, int stock, String category){
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getStock(){
        return stock;
    }
    public String getCategory(){
        return category;
    }
}
