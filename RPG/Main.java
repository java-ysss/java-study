package RPG;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player hero = new Player("勇者", 500, 25, 10, 0.2, 20, 10);
        Enemy[] enemies = {
                new Enemy("スライム", 50, 10, 0.1, 20, 2, 8),
                new Enemy("ゴブリン", 80, 15, 0.1, 40, 7, 15),
                new Enemy("ドラゴン", 200, 30, 0.05, 200, 12, 2)
        };

        while (true) {
            ArrayList<Integer> aliveList = new ArrayList<>();

            // ======状態表示======
            System.out.println("===========[状態]============");

            System.out.println(hero.getName() + "の残りHP : " + hero.getHp());

            int displayIndex = 1;

            // 敵表示
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i].getHp() > 0) {
                    System.out.println(displayIndex + " : " + enemies[i].getName() +
                            "HP : " + enemies[i].getHp());
                    aliveList.add(i);// 元のインデックスiを保存
                    displayIndex++;
                }

            }
            System.out.println("============================");

            // ======入力======

            System.out.println("1: 攻撃  2: 回復 3: 全体攻撃 4: スキル");
            int select = 0;
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("数字の入力をお願いします");
                scanner.nextLine();
                continue;
            }

            int targetIndex = -1;
            int skillChoice = 0;
            int targets = 0;

            if (select == 1) {

                System.out.println("攻撃する敵を選んでください");

                try {
                    targets = scanner.nextInt() - 1; // 選んでもらった数字でも配列上は-1

                    if (targets >= 0 && targets < aliveList.size()) {

                    } else {
                        System.out.println("正しい数字を選んでください");
                        continue;
                    }

                    targetIndex = aliveList.get(targets); // 表示用の番号から、本当の配列の番号に変換してる
                    // targetIndex は 誰を攻撃するか覚えて置くメモ

                } catch (InputMismatchException e) {
                    System.out.println("数字から選んでください");
                    scanner.nextLine();
                    continue;
                }
            }

            if (select == 4) {
                System.out.println("1: 強攻撃 2: 防御");

                try {
                    skillChoice = scanner.nextInt();

                    if (skillChoice != 1 && skillChoice != 2) { // 1 でも 2 でもないならエラー
                        System.out.println("1 か 2 で選んでください");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("数字を選んでください");
                    scanner.nextLine();
                    continue;
                }

                if (skillChoice == 1) {
                    System.out.println("攻撃する敵を選んでください");

                    try {
                        targets = scanner.nextInt() - 1; // 選んでもらった数字でも配列上は-1

                        if (targets >= 0 && targets < aliveList.size()) {

                        } else {
                            System.out.println("正しい数字を選んでください");
                            continue;
                        }

                        targetIndex = aliveList.get(targets); // 表示用の番号から、本当の配列の番号に変換してる
                        // targetIndex は 誰を攻撃するか覚えて置くメモ

                    } catch (InputMismatchException e) {
                        System.out.println("数字から選んでください");
                        scanner.nextLine();
                        continue;
                    }
                }

                // ======戦闘フェーズ======

                // 敵の攻撃 heroより早い敵用
                for (Enemy enemy : enemies) {
                    if (enemy.isAlive() && enemy.speed > hero.speed) {
                        enemy.takeTurn(hero);
                    }
                }

                // プレイヤーの行動

                if (select == 1) {
                    hero.attack(enemies[targetIndex]);

                    // 敵のHPが０以下の時、かつ、初めて死んだとき
                    // 初回撃破だけを判定するため
                    if (!enemies[targetIndex].isAlive() && enemies[targetIndex].checkDead()) {
                        hero.gainExp(enemies[targetIndex].getExp());
                    }
                } else if (select == 2) {

                    hero.heal();

                } else if (select == 3) {
                    hero.allAttack(enemies);

                    for (Enemy enemy : enemies) {
                        if (!enemy.isAlive() && enemy.checkDead()) {
                            hero.gainExp(enemy.getExp());
                        }
                    }
                } else if (select == 4) {

                    if (skillChoice == 1) {
                        hero.skillAttack(enemies[targetIndex]);

                        if (!enemies[targetIndex].isAlive() && enemies[targetIndex].checkDead()) {
                            hero.gainExp(enemies[targetIndex].getExp());
                        }
                    } else if (skillChoice == 2) {
                        hero.defend();
                    }

                }

                // heroより遅い敵用

                for (Enemy enemy : enemies) {
                    if (enemy.isAlive() && enemy.speed < hero.speed) {
                        enemy.takeTurn(hero);
                    }
                }

                // 全滅✓チェック
                boolean allDesd = true;
                for (Enemy enemy : enemies) {
                    if (enemy.getHp() > 0) {
                        allDesd = false;
                    }
                }

                if (allDesd) {
                    System.out.println("すべての敵を倒した！");
                    break;
                }

                if (hero.getHp() <= 0) {
                    System.out.println("ゲームオーバー＞＜");
                    break;
                }
                hero.resetState();

                for (Enemy enemy : enemies) {
                    enemy.resetState();
                }
            }

        }
    }
}
