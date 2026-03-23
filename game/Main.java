package game;

import java.util.Scanner;
//ユーザーの入出力のクラス
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManager manager = new GameManager();

        while (true) {
            System.out.println("1. ゲーム追加");
            System.out.println("2. ゲーム表示");
            System.out.println("3. クリア済みにする");
            System.out.println("4. ゲームを削除");
            System.out.println("5. 終了");

            int select = scanner.nextInt();

            if (select == 1) {
                System.out.println("ゲーム名を入力してください");
                String name = scanner.next();
                manager.addGame(name);
            }
            if (select == 2) {
                System.out.println("ゲームを表示");
                manager.showGame();
            }
            if (select == 3) {
                System.out.println("何番のゲームをクリア済みにしますか？");
                int index = scanner.nextInt();
                manager.completeGame(index);
                
            }
            if (select == 4) {
                System.out.println("何番のゲームを削除しますか？");
                int index = scanner.nextInt();
                manager.deleteGame(index);
            }
            if (select == 5) {
                System.out.println("終了します");
                break;
            }

        }

    }
}
