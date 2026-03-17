package Calculator;

import java.util.Scanner;

class MiniCals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = { "足し算", "引き算", "掛け算" };

        int num1 = getNumber(scanner);
        int num2 = getNumber(scanner);

        int selectResult = selectOperation(scanner,numbers);

        int result = testOperation(num1, num2, selectResult);

        resultNumber(result);

     

    }

    // 入力メソッド
    public static int getNumber(Scanner scanner) {
        System.out.print("数字をに入力してください: ");
        int number = scanner.nextInt();

        return number;
    }

    // 計算方法選択メソッド
    public static int selectOperation(Scanner scanner,String[] numbers) {

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

            default:
                System.out.println("1 ~ 3 を選んでください");
                return 0;
        }
    }

    // 計算結果出力メソッド
    public static void resultNumber(int result) {
        System.out.println("計算結果は" + result + "です");
    }

}
