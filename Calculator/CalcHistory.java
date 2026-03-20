package Calculator;

import java.util.ArrayList;

public class CalcHistory {
    private ArrayList<String> history = new ArrayList<>();

    // 履歴を保存するところ
    public void addHistory(String record) {
        history.add(record);
    }

    public void showHistory() {

        if (history.isEmpty()) {
            System.out.println("まだ履歴はありません");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + " : " + history.get(i));
            }
        }
    }

    public void clearHistory() {
        history.clear();
        System.out.println("履歴を削除しました");
    }

    public void removeHistory() {
        if (history.isEmpty()) {
            System.out.println("削除できる履歴はありません");
        } else {
            history.remove(history.size() - 1);
            System.out.println("最後の履歴を消去しました");
        }
    }

    public void indexHistory(int index) {
        index = index - 1;
        if (index < 0 || index >= history.size()) {
            System.out.println("その履歴番号はありません");
        } else {
            history.remove(index);
        }
    }
}