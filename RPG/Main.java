package RPG;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player hero = new Player("勇者", 500, 25, 10, 0.2, 20);
        Enemy[] enemies = {
                new Enemy("スライム", 50, 10, 0.1, 20, 2),
                new Enemy("ゴブリン", 80, 15, 0.1, 40, 7),
                new Enemy("ドラゴン", 200, 30, 0.05, 200, 12)
        };

        while (true) {
            ArrayList<Integer> aliveList = new ArrayList<>();

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

            System.out.println("1: 攻撃  2: 回復 3: 全体攻撃");
            int choice = 0;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("数字の入力をお願いします");
                scanner.nextLine();
                continue;
            }
            if (choice == 1 || choice == 2 || choice == 3) {

                if (choice == 1) {
                    System.out.println("攻撃する敵を選んでください");
                    int targets = 0;

                    try {
                        targets = scanner.nextInt() - 1; // 選んでもらった数字でも配列上は-1

                        if (targets >= 0 && targets < aliveList.size()) {

                        } else {
                            System.out.println("正しい数字を選んでください");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("数字から選んでください");
                        scanner.nextLine();
                        continue;
                    }
                    int targetIndex = aliveList.get(targets);

                    if (targets >= 0 && targets < enemies.length) {

                        if (enemies[targetIndex].getHp() > 0) {

                            hero.attack(enemies[targetIndex]);

                            if (enemies[targetIndex].getHp() <= 0) {
                                System.out.println(enemies[targetIndex].getName() + "を倒しました！");
                                hero.gainExp(enemies[targetIndex].getExp());
                            }
                        } else {
                            System.out.println("その敵はもう倒しています");
                            continue;
                        }
                    } else {
                        System.out.println("正しい番号を選んでください");
                        continue;
                    }
                } else if (choice == 2) {
                    hero.heal();

                } else if (choice == 3) {

                    hero.allAttack(enemies);

                    for (Enemy enemy : enemies) {

                            if (enemy.checkDead()) {

                                hero.gainExp(enemy.getExp());
                            }
                    }
                }
            } else {
                System.out.println("1 ~ 3 を選んでください");
                continue;
            }

            // 敵全員の攻撃
            for (Enemy enemy : enemies) {
                if (enemy.getHp() > 0) {
                    enemy.attack(hero);
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

        }

    }
}