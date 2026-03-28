package Quiz;

import java.util.Scanner;
import java.util.InputMismatchException;

//入出力のメインクラス(A.ゲームの進行)

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager manager = new QuizManager();
        manager.addQuiz();

        int win = 0;
        int lose = 0;
        while (true) {

            System.out.println("キングダムの問題・スタート！");
            System.out.println("==================================");
            int select = 0;
            try{
                select = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("1 ~ 5 の中から数字を選んでください");
                scanner.nextLine();
                continue;
            }
        
            manager.showQuiz(select,scanner);
        }
    }
}
