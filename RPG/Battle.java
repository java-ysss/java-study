package RPG;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Battle {

    public static void startBattle(Player[] party, Enemy[] enemies, Scanner scanner) {

        while (true) {

            // =====状態表示=====
            System.out.println("===========[状態]============");

            // パーティー表示
            for (Player player : party) {
                if (player.isAlive()) {
                    System.out.println(player.getName() + "  HP: " + player.getHp() + " MP : " + player.getMp());
                }
            }

            // 敵を表示
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {

                    System.out.println(enemy.getName() + "  HP: " + enemy.getHp());
                }
            }
            System.out.println("============================");

            // ======➀入力ターン======

            // ➀プレイヤーコマンド入力
            for (Player player : party) {
                if (player.isAlive()) {
                    player.chooseAction(enemies, party, scanner);
                }
            }

            // ➁敵の行動決定
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.chooseAction(party);
                }
            }

            // ターン順リスト作成
            ArrayList<Character> turnOrder = new ArrayList<>();

            for (Player player : party) { // リストにプレイヤー追加
                if (player.isAlive()) {
                    turnOrder.add(player);
                }
            }

            for (Enemy enemy : enemies) { // リストに敵を追加1

                if (enemy.isAlive()) {
                    turnOrder.add(enemy);
                }
            }

            turnOrder.sort((a, b) -> b.speed - a.speed); // スピードが高い順に並べる
            // tunOrderはキャラを並び替える

            // ======行動実行====== 保存していた行動を実行するところ

            for (Character character : turnOrder) {

                if (!character.isAlive()) {//キャラが生きていないなら
                    continue;              //今のループ処理をやめて、次のループに行く
                }

                //↓ 行動前に勝敗チェックを追加
                boolean enemyAllDead = true;
                for(Enemy enemy : enemies){//enemiesの中身を1つずつ取り出して、enemyという名前で使う
                    if (enemy.isAlive()) {
                        enemyAllDead = false; 
                        break;
                    }
                }
                boolean playerAllDead = true;
                for(Player player : party){//全部に同じ処理をしたいときに使う
                    if (player.isAlive()) {
                        playerAllDead = false;
                        break;
                    }
                }

                if (enemyAllDead || playerAllDead) {
                    break;//どちらかが全滅したとき行動を止める
                }

                character.performAction();
            }

            // =======勝敗判定========

            boolean allEnemyDead = true;

            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    allEnemyDead = false;
                }
            }

            if (allEnemyDead) {
                System.out.println("すべての敵を倒した！");

                for (Enemy enemy : enemies) {
                    for (Player player : party) {
                        if (player.isAlive()) {
                            player.gainExp(enemy.getExp());
                        } // 倒した敵の数だけループして、生きているプレイヤーに経験値を配ります
                    }
                }
                break;
            }

            boolean allPlayerDead = true;

            for (Player player : party) {
                if (player.isAlive()) {
                    allPlayerDead = false;
                }
            }

            if (allPlayerDead) {
                System.out.println("ゲームオーバー．．．");
                break;
            }

        }

    }

    public static int inputNumber(Scanner scanner) {

        int number = 0;

        while (true) {

            try {
                number = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {

                System.out.println("数字を入力してください");
                scanner.next(); // 入力された文字を捨てる
            }

        }

        return number;
    }

}