package book;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager manager = new BookManager();

        while (true) {
            System.out.println("1. 本を追加");
            System.out.println("2. 本を表示");
            System.out.println("3. 既読にする");
            System.out.println("4. 本を削除");
            System.out.println("5. 終了");

            int select = 0;

            try{
                select = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("1 ~ 5 の数字を入力してください");
                scanner.nextLine();
                continue;
            }

            if (select == 1) {
                System.out.println("ジャンルを選んでください");
            }
            if (select == 2) {
                
            }
            if (select == 3) {
                
            }
            if (select == 4) {
                
            }
            if (select == 5) {
                System.out.println("終了します");
                break;
            }
        }
    }
}
