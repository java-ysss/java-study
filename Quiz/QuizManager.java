package Quiz;

//クイズを管理するクラス(A.問題の管理・出題・採点)
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

public class QuizManager {
    private ArrayList<Quiz> quizzes = new ArrayList<>();

    private int win;
    private int lose;

    public void addQuiz() {
        quizzes.add(new Quiz("主人公は？", "政", "河了貂", "信", "壁", 3, "元下僕の少年"));
        quizzes.add(new Quiz("合従軍の際、退いた国は？", "韓", "魏", "趙", "斉", 4, "海に面した国"));
        quizzes.add(new Quiz("王騎将軍の矛は誰が引き継いだ？", "信", "謄", "録鳴未", "同金", 1, "飛信隊の隊長"));
        quizzes.add(new Quiz("ヒョウコウ将軍は何型の武将？", "知略", "本能", "感覚", "天才", 2, "戦場で生まれ落ちた"));
        quizzes.add(new Quiz("飛信隊を命名したのは？", "王騎", "ヒョウコウ", "謄", "政", 1, "秦国の大将軍"));

    }

    public void shuffleQuizzes() {
        Collections.shuffle(quizzes);
    }

    public void showQuiz(int index, Scanner scanner) {

        Quiz quiz = quizzes.get(index);// 入力された問題・回答・答えをquizという変数に入れている、そしてランダムだと選んでもらう必要がないからindex

        System.out.println(" Q : " + quiz.getQuestion());
        System.out.println(" 1 . " + quiz.getSelect1());
        System.out.println(" 2 . " + quiz.getSelect2());
        System.out.println(" 3 . " + quiz.getSelect3());
        System.out.println(" 4 . " + quiz.getSelect4());

        int userAnswer = 0;
        boolean hintUsed = false;// ヒントは使ってない

        while (true) {
            System.out.println("答えを選択してね!(ヒントが必要なら 0 を入力)");

            try {
                userAnswer = scanner.nextInt();
                if (userAnswer == 0) {
                    if (!hintUsed) {// ヒントはまだ使ってないなら
                        System.out.println("ヒント : " + quiz.getHint());
                        hintUsed = true;// ヒントは使ったってこと
                    } else {
                        System.out.println("ヒントはもう使ってるよ");
                    }
                    continue;// ヒント後はループを続ける
                }
                if (userAnswer >= 1 && userAnswer <= 4)
                    break;//１行目までは｛｝なしでもいい
                System.out.println("0 ~ 4 の数字を選んでね");

            } catch (InputMismatchException e) {
                System.out.println("1 ~ 4 の数字の中から選んでね");
                scanner.nextLine();
                continue;
            }
        }

        if (userAnswer == quiz.getAnswer()) {
            System.out.println("正解です！");
            win++;
            // usedList.add(select);
        } else {
            System.out.println("不正解です！");
            lose++;
            // usedList.add(select);
        }

    }

    public String getRank() {
        if (win == 5) {
            return ("大将軍 ★ 全問正解！！！");
        }
        if (win == 4) {
            return ("五千人将");
        }
        if (win == 3) {
            return ("三千人将");
        }
        if (win == 2) {
            return ("千人将");
        }
        if (win == 1) {
            return ("百人将");
        }
        return "新兵";
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getQuezCount() {
        return quizzes.size();
    }
}
