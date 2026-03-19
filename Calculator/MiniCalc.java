package Calculator;

import java.util.Scanner;
import java.util.ArrayList;

class MiniCals {
    public static void main(String[] args) {
        CalcHistory historyManager = new CalcHistory();//履歴管理クラスのインスタンス

        //ArrayList<String> history = new ArrayList<>();クラスに任せるからいらない
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true) {

            String[] numbers = { "足し算", "引き算", "掛け算", "割り算", "履歴表示", "履歴削除", "最後の履歴削除", "履歴番号削除", "終了" };

            int selectResult = selectOperation(scanner, numbers);

            if (selectResult == 5) {
                System.out.println("計算履歴： ");
                historyManager.showHistory();
                continue;
            }
            if (selectResult == 6) {
                history.clear();
                System.out.println("履歴を削除しました");
                continue;
            }
            if (selectResult == 7) {
                if (history.isEmpty()) {
                    System.out.println("削除できる履歴がありません");
                } else {
                    history.remove(history.size() - 1);
                    System.out.println("最後の履歴を削除しました");
                }
                continue;
            }
            if (selectResult == 8) {
                System.out.println("削除する履歴番号を入力してください");
                int index = scanner.nextInt();
                index = index - 1;
                if (index < 0 || index >= history.size()) {
                    System.out.println("その履歴番号がありません");
                }else{
                    history.remove(index);
                }
                continue;
            }

            if (selectResult == 9) {
                System.out.println("プログラムを終了します");
                break;
            }

            double num1 = getNumber(scanner);
            double num2 = getNumber(scanner);

            double result = testOperation(num1, num2, selectResult);

            count++;

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
            //historyManager.addHistory(record);
            resultNumber(result);

            System.out.println("現在の計算回数：" + count + "です");

        }

    }

    // 入力メソッド
    public static double getNumber(Scanner scanner) {
        System.out.print("数字を入力してください: ");
        while (true) {

            if (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();
                return number;
            } else {
                System.out.println("数字を入力してください");
                scanner.next();
            }
        }
    }

    // 計算方法選択メソッド
    public static int selectOperation(Scanner scanner, String[] numbers) {
        System.out.println("-------------------------------");
        System.out.println("計算方法を選んでください");

        for (int i = 0; i < numbers.length; i++) {
            System.out.println((i + 1) + ":" + numbers[i]);
        }
        int number = scanner.nextInt();

        return number;
    }

    // 計算メソッド
    public static double testOperation(double num1, double num2, int selectResult) {
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
                System.out.println("1 ~ 9 を選んでください");
                return 0;
        }
    }

    // 計算結果出力メソッド
    public static void resultNumber(double result) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("計算結果は" + result + "です");
    }
}