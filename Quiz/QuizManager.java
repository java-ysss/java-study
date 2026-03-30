package Quiz;

//クイズを管理するクラス(A.問題の管理・出題・採点)
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuizManager {
    ArrayList<Quiz> quizzes = new ArrayList<>();

    private int win;
    private int lose;

    public void addQuiz() {
        quizzes.add(new Quiz("主人公は？", "政", "河了貂", "信", "壁", 3));
        quizzes.add(new Quiz("合従軍の際、退いた国は？", "韓", "魏", "趙", "斉", 4));
        quizzes.add(new Quiz("王騎将軍の矛は誰が引き継いだ？", "信", "謄", "録鳴未", "同金", 1));
        quizzes.add(new Quiz("ヒョウコウ将軍は何型の武将？", "知略", "本能", "感覚", "天才", 2));
        quizzes.add(new Quiz("飛信隊を命名したのは？", "王騎", "ヒョウコウ", "謄", "政", 1));

    }

    public void showQuiz(int select, Scanner scanner, ArrayList<Integer> usedList) {

        Quiz quiz = quizzes.get(select - 1);// 入力された問題・回答・答えをquizという変数に入れてい

        System.out.println(" Q : " + quiz.getQuestion());
        System.out.println(" 1 . " + quiz.getSelect1());
        System.out.println(" 2 . " + quiz.getSelect2());
        System.out.println(" 3 . " + quiz.getSelect3());
        System.out.println(" 4 . " + quiz.getSelect4());

        int userAnswer = 0;
        while (true) {
            System.out.println("答えを入力してね");
            try {
                userAnswer = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("1 ~ 4 の数字の中から選んでね");
                scanner.nextLine();
                continue;
            }
        }

        if (userAnswer == quiz.getAnswer()) {
            System.out.println("正解です！");
            win++;
            usedList.add(select);
        } else {
            System.out.println("不正解です！");
            lose++;
        }

    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }
}
