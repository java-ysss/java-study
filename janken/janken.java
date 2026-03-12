package janken;
import java.util.Random;
import java.util.Scanner;

class Janken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

    

        String[] hands = { "グー", "チョキ", "パー" };
        int win = 0;
        int lose = 0;

        while (win < 3 && lose < 3) {

            int player = playerHand(scanner);

            int cpu = cpuHand(rand);
            int result = judge(player, cpu);
            int game = showResult(result, hands, player, cpu);

            if (game == 0) {
                continue;
            }else if (game == 1) {
                win++;
            }else{
                lose++;
            }
           

        }
        System.out.println("現在：" + win + "勝" + lose + "敗");
        if (win > lose)

        {
            System.out.println("あなたの勝利です！");
        } else {
            System.out.println("負けてしまいました。。。");
        }
    }

    public static int playerHand(Scanner scanner) {
        System.out.print("グー：０ チョキ：１ パー：２ で数字を入力");
        int number = scanner.nextInt();
        while (number < 0 || number > 2) {
            System.out.println("0 ~ 2を入力してください");
            number = scanner.nextInt();
        }

        return number;
    }
    ///ＣＰＵの手
    public static int cpuHand(Random rand) {
        return rand.nextInt(3);
    }
    ///判定めそっど
    public static int judge(int player, int cpu) {
        return (cpu - player + 3) % 3;
    }
///お互いの手
    public static void showHand(int player, int cpu, String[] hands) {
        System.out.println("あなた：" + hands[player]);
        System.out.println("相手：" + hands[cpu]);
    }
////結果表示
    public static int showResult(int result, String[] hands, int player, int cpu) {
        System.out.println("じゃんけん.....");
        System.out.println("ぽん！！！");
        showHand(player, cpu, hands);



        if (result == 1) {
            System.out.println("=================================");
            System.out.println("あなたの勝ち！");
            return 1;
        } else if (result == 2) {
            System.out.println("=================================");
            System.out.println("相手の勝ち");
            return 2;
        } else {
            System.out.println("--------------------");
            System.out.println("あいこでしょ！");
            return 0;
        }
    }
}


