package Calculator;
import java.util.Scanner;
import java.util.ArrayList;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>();

        String[] numbers = { "1:足し算", "2:引き算", "3:掛け算", "4:割り算", "5:履歴を見る", "6:履歴削除" };

        while (true) {

            double num1 = getNumber(scanner);
            double num2 = getNumber(scanner);

            int operation = selectOperation(numbers, scanner);
            if (operation == 5) {
                System.out.println("履歴");
                for (int i = 0; i < history.size(); i++) {
                    System.out.println(history.get(i));
                }
                continue;
            }
            if (operation == 6) {
                history.clear();
                System.out.println("履歴を削除しました");
                continue;
            }

            System.out.println(operation + "を選択しました");

            double calculateResult = calculate(num1, num2, operation);
            String result = showResult(calculateResult, num1, num2, operation);
            history.add(result);

            int finish = finishCode(scanner);
            if (finish == 2) {
                break;
            }

        }

        System.out.println("計算結果");

        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
        }

    }

    // 数字を入力させる
    public static double getNumber(Scanner scanner) {
        while (true) {

            System.out.print("数字を入力してください：");

            if (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();
                return number;
            }

            System.out.println("数字を入力してください");
            scanner.next();
        }
    }

    // 計算方法を選ぶ
    public static int selectOperation(String[] numbers, Scanner scanner) {
        System.out.println("計算方法を選んでね");

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
        while (true) {
            int select = scanner.nextInt();
            if (select >= 1 && select <= 6) {

                return select;
            }

            System.out.println("1 ~ 6 の間で入力してください");
        }
    }

    // 計算メソッド
    public static double calculate(double num1, double num2, int operation) {

        switch (operation) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                if (num2 == 0) {
                    System.out.println("０では割れません");
                    return 0;
                }
                return num1 / num2;
            default:
                return 0;
        }
    }

    // 計算結果メソッド
    public static String showResult(double calculateResult, double num1, double num2, int operation) {
        String symbol = "";

        switch (operation) {
            case 1:
                symbol = "+";
                break;
            case 2:
                symbol = "-";
                break;
            case 3:
                symbol = "*";
                break;
            case 4:
                symbol = "/";
                break;
        }

        String result = (num1 + " " + symbol + " " + num2 + " = " + calculateResult);

        System.out.println(result);
        return result;
    }

    // 終了メソッド
    public static int finishCode(Scanner scanner) {
        System.out.println("もう一度計算しますか？");
        System.out.println("1:はい 2:終了");
        while (true) {
            int choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                return choice;
            }
            System.out.println("１か２で入力してください");
        }
    }

}
