package Calculator;

import java.util.Scanner;
import java.util.ArrayList;

class MiniCals {
    public static void main(String[] args) {
        ArrayList<String> history = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String[] numbers = { "足し算", "引き算", "掛け算", "割り算", "履歴表示", "終了" };

            int num1 = getNumber(scanner);
            int num2 = getNumber(scanner);

            int selectResult = selectOperation(scanner, numbers);

            if (selectResult == 5) {
                System.out.println("計算履歴： ");
                if (history.isEmpty()) {
                    System.out.println("まだ履歴はありません");
                } else {
                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + " : " + history.get(i));
                    }
                }
                continue;
            }
            if (selectResult == 6) {
                System.out.println("プログラムを終了します");
                break;
            }

            int result = testOperation(num1, num2, selectResult);

            String operator = " ";
            switch (selectResult) {
                case 1:
                    operator = "+";
                    break;
                case 2:
                    operator = "-";
                    break;
                case 3:
                    operator = "*";
                    break;
                case 4:
                    operator = "/";
                    break;
            }

            history.add(num1 + " " + operator + " " + num2 + " = " + result);
            resultNumber(result);

        }

    }

    // 入力メソッド
    public static int getNumber(Scanner scanner) {
        System.out.print("数字を入力してください: ");
        while (true) {

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                return number;
            } else {
                System.out.println("数字を入力してください");
                scanner.next();
            }
        }
    }

    // 計算方法選択メソッド
    public static int selectOperation(Scanner scanner, String[] numbers) {

        System.out.println("計算方法を選んでください");

        for (int i = 0; i < numbers.length; i++) {
            System.out.println((i + 1) + ":" + numbers[i]);
        }
        int number = scanner.nextInt();

        return number;
    }

    // 計算メソッド
    public static int testOperation(int num1, int num2, int selectResult) {
        switch (selectResult) {

            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                if (num2 == 0) {
                    System.out.println("0では割り算できません");
                    return 0;
                }
                return num1 / num2;

            default:
                System.out.println("1 ~ 6 を選んでください");
                return 0;
        }
    }

    // 計算結果出力メソッド
    public static void resultNumber(int result) {
        System.out.println("計算結果は" + result + "です");
    }
}