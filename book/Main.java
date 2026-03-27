package book;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager manager = new BookManager();

        while (true) {
            System.out.println("1. 漫画を追加");
            System.out.println("2. 小説を追加");
            System.out.println("3. 追加した本を表示");
            System.out.println("4. 既読にする");
            System.out.println("5. 本を削除");
            System.out.println("6. 終了");

            int select = 0;

            try {
                select = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("1 ~ 6 の数字を入力してください");
                scanner.nextLine();
                continue;
            }

            if (select == 1) {
                System.out.println("作者を入力してください");
                String author = scanner.next();

                System.out.println("タイトルを入力してください");
                String title = scanner.next();

                System.out.println("巻数を入力してください");
                int volume = 0;
                try {
                    volume = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("数字を入力してください");
                    scanner.nextLine();
                }
                manager.addManga(title, author, volume);

            }

            if (select ==2) {
                System.out.println("作者を入力してください");
                String author = scanner.next();

                System.out.println("タイトルを入力してください");
                String title = scanner.next();

                manager.addBook(title, author);
            }
            if (select == 3) {
                System.out.println("本を表示");
                manager.showBook();
            }
            if (select == 4) {
                System.out.println("どの本を既読にしますか");
                int index = 0;
                try{
                    index = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("追加したタイトルの数字を入力してください");
                    scanner.nextLine();
                    continue;
                }
                manager.readBook(index - 1);
                System.out.println("選択した本を既読にしました");
            }

            if (select == 5) {
                System.out.println("どの本を削除しますか");
                manager.listBook();
                int index = 0;
                try{
                    index = scanner.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("リスト内の数字を入力してください");
                    scanner.nextLine();
                    continue;
                }
                manager.removeBook(index - 1);
            }
            if (select == 6) {
                System.out.println("終了します");
                break;
            }
        }
    }
}
