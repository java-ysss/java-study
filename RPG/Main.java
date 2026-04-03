package RPG;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player hero = new Player("勇者", 100, 20, 10, 0.2);
        Enemy[] enemies = {
            new Enemy("スライム", 50, 10, 0.1, 20),
            new Enemy("ゴブリン", 80, 15, 0.1, 40),
            new Enemy("ドラゴン", 200, 30, 0.05, 200)
        };

        while (true) {

            System.out.println("===========[状態]============");

            System.out.println(hero.getName() + "の残りHP : " + hero.getHp());

             for(int i = 0; i < enemies.length; i++){
                if (enemies[i].getHp() >  0) {
                    System.out.println((i + 1) + " : " + enemies[i].getName() +
                     "HP : " + enemies[i].getHp());
                }
                
            }
            System.out.println("============================");

            System.out.println("1: 攻撃  2: 回復");
            int choice = 0;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("数字の入力をお願いします");
                scanner.nextLine();
                continue;
            }
            if (choice >= 1 && choice <= 2) {

                if (choice == 1) {
                    hero.attack(su);
                }

                if (su.getHp() <= 0) {
                    System.out.println("敵を倒した！");
                    hero.gainExp(su.getExp());
                    break;
                } else if (choice == 2) {
                    hero.heal();
                    ;
                }

            } else {
                System.out.println("1 か 2 で選んでください");
                continue;
            }

            su.attack(hero);

            if (hero.getHp() <= 0) {
                System.out.println("ゲームオーバー＞＜");
                break;
            }

        }

    }
}