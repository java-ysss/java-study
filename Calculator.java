import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = { "1:足し算", "2:引き算", "3:掛け算", "4:割り算" };
        String[] history = new String[100];
        int historyIndex = 0;

        while (true) {

            int num1 = getNumber(scanner);
            int num2 = getNumber(scanner);

            int operation = selectOperation(numbers, scanner);
            System.out.println(operation + "を選択しました");

            int calculateResult = calculate(num1, num2, operation);
            String result = showResult(calculateResult, num1, num2, operation);

            history[historyIndex] = result;
            historyIndex++;

            int finish = finishCode(scanner);
            if (finish == 2) {
                break;
            }

        }

        System.out.println("計算結果");

        for(int i = 0; i < historyIndex; i++){
            System.out.println(history[i]);
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
        while (true) {
            int select = scanner.nextInt();
            if (select >= 1 && select <= 4) {

                return select;
            }

            System.out.println("1 ~ 4の間で入力してください");
        }
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
    public static String showResult(int calculateResult, int num1, int num2, int operation) {
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

        String  result = (num1 + "" + symbol + "" + num2 + " = " + calculateResult);
      
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
