package Task;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        ToDoManager manager = new ToDoManager();//管理クラス
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // メニュー表示
            System.out.println("1: タスク管理");
            System.out.println("2: タスク表示");
            System.out.println("3: 完了チェック");
            System.out.println("4: タスク削除");
            System.out.println("5: 終了");

            //入力を受け取る
            int select = scanner.nextInt();

            //選択によって処理を分ける
            if (select == 1) {
                System.out.println("タスク名を入力してください");
                String name = scanner.next();
                manager.addToDo(name);
            }
            if (select == 2) {
                manager.showToDo();
            }
            if (select == 5) {
                System.out.println("終了します");
                break;
            }
        }
    }

}
