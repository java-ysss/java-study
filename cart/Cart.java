package cart;
//CartItemをリストで管理し合計金額などを計算するクラス managerみたいなクラス

import java.util.ArrayList; //リストの実装　(実際に動くもの) 実際に立てた家
import java.util.List; // リストの設計図　(何ができるかのルール) 家の間取り図

public class Cart {
    private List<CartItem> items; // CarItemをまとめて管理するクラス

    // コンストラクタ：空のリストを用意する

    public Cart() {
        items = new ArrayList<>();
    }

    // 商品をカートに追加する
    public void addItem(Product product, int quantity) {
        CartItem item = new CartItem(product, quantity);
        items.add(item);
    }

    // 合計金額を計算する
    public int getTotalPrice() {
        int total = 0;
        for (CartItem item : items) {// itemsからの中身を一つずつitemという名前で取り出すという意味
            total += item.getSubtotal(); // 今のtotalに足して上書き(＋＝)
        }
        return total;
    }

    // カートの中身を表示する
    public void showItems() {
        for (CartItem item : items) {
            System.out.println(
                    item.getProduct().getName() +
                            " x " + item.getQuantity() +
                            " = " + item.getSubtotal() + "円");
        }
    }

    public void removeItem(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {//**取り出した item の商品名**」と「**削除したい商品名**」を比較
                items.remove(item);
                break;
            }
        }
    }
}
