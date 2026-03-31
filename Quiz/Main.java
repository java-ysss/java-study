package Quiz;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

//入出力のメインクラス(A.ゲームの進行)

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager manager = new QuizManager();
        ArrayList<Integer> usedList = new ArrayList<>();

        manager.addQuiz();

        System.out.println("キングダムの問題スタート！！");
        int count = 0;

        while (count < 5 && manager.getWin() < 5) {

            System.out.println("==================================");
            System.out.print("1 ~ 5 の数字を順番に選んでね : ");
            int select = 0;
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("1 ~ 5 の中から数字を選んでください");
                scanner.nextLine();
                continue;
            }
            if (select >= 1 && select <= manager.getQuezCount()) {
                if (usedList.contains(select)) {
                    System.out.println("その問題はもう選んでます。");
                } else {
                    manager.showQuiz(select, scanner, usedList);
                    count++;
                }
            }

        }
        System.out.println("正解数は" + manager.getWin());
        System.out.println("不正解の回数は" + manager.getLose());
        System.out.println("あなたの称号は" + "【 " +  manager.getRank() + " 】");
    }
}
