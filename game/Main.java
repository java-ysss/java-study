package game;

import java.util.InputMismatchException;
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

            int select = 0;
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("1 ~ 5 で入力をお願いします");
                scanner.nextLine();
                continue;
            }

            if (select == 1) {
                System.out.println("-> ジャンルを選んでください");
                System.out.print("1. 通常 ");
                System.out.print("2. RPG ");

                int genre = 0;
                int maxGenre = 2;
                
                try{
                    genre = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("数字で入力してください");
                    scanner.nextLine();
                    continue;
                }

                if (genre < 1 || genre > maxGenre) {
                    System.out.println("1か" + maxGenre + "で入力してください");
                    continue;
                }

                System.out.println("ゲーム名を入力してください");
                String name = scanner.next();

                if (genre == 1) {
                    manager.addGame(name);
                } else if (genre == 2) {
                    System.out.println("レベルを入力してください");

                    int level = 0;
                    try {
                        level = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("数字を入力してください");
                        scanner.nextLine();
                        continue;
                    }

                    manager.addRPGGame(name, level);
                }

            } else if (select == 2) {
                System.out.println("ゲームを表示");
                manager.showGame();

            } else if (select == 3) {
                System.out.println("何番のゲームをクリア済みにしますか？");
                int index = 0;
                try{
                    index = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("数字を入力してください");
                    scanner.nextLine();
                    continue;
                }
                manager.completeGame(index);

            } else if (select == 4) {
                System.out.println("何番のゲームを削除しますか？");
                int index = 0;
                try{
                    index = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("数字を入力してください");
                    scanner.nextLine();
                }
                manager.deleteGame(index);

            } else if (select == 5) {
                System.out.println("終了します");
                break;
            } else {
                System.out.println("1 ~ 5の数字を入力してください");
            }

        }

    }

}
