import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = { "1:足し算", "2:引き算", "3:掛け算", "4:割り算" };

        while (true) {

            int num1 = getNumber(scanner);
            int num2 = getNumber(scanner);

            int operation = selectOperation(numbers, scanner);
            System.out.println(operation + "を選択しました");

            int calculateResult = calculate(num1, num2, operation);
            showResult(calculateResult);

            int finish = finishCode(scanner);
                if (finish == 2) {
                    break;
                }

        }

    }

    // 数字を入力させる
    public static int getNumber(Scanner scanner) {
        System.out.print("数字を入力してください：");
        int number = scanner.nextInt();
        return number;
    }

    // 計算方法を選ぶ
    public static int selectOperation(String[] numbers, Scanner scanner) {
        System.out.println("計算方法を選んでね");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
        int select = scanner.nextInt();
        return select;
    }

    // 計算する
    public static int calculate(int num1, int num2, int operation) {

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

    // 計算結果
    public static void showResult(int calculateResult) {
        System.out.println("計算結果は：" + calculateResult);
    }

    // 終了メソッド
    public static int finishCode(Scanner scanner){
        System.out.println("もう一度計算しますか？");
        System.out.println("1:はい 2:終了");
        int choice = scanner.nextInt();
        return choice;
    }

}
