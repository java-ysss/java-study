package Ppp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        Player player = new Player("勇者", 180, 35, 20);

        player.addSkill(new Mera());

        Enemy enemy = new Enemy("スライム", 60, 5, 10);


        while (player.isAlive() && enemy.isAlive()) {

            //ステータス表示
            System.out.println("===========================");

            System.out.println(player.name + "  " + " HP : " + player.hp + " MP : " + player.mp);

            System.out.println(enemy.name + "  " + " HP : " + enemy.hp + " MP : " + enemy.mp);

            System.out.println("===========================");


            //行動選択

            System.out.println("1 : 攻撃 2 : 防御 3 : スキル");
            int choice = 0;

            try{
                choice = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("数字を入力してください");
                scanner.next();
                return;
            }

            if (choice < 0 || choice >= 4) {
                System.out.println("1 ~ 3 で入力してください");
                continue;
            }

            if (choice == 1) {

                player.attack(enemy);

            }
            else if (choice == 2) {
                player.defend();
            }
            else if (choice == 3) {

                player.useSkill(scanner, enemy);
            }

            //敵の攻撃

            if (enemy.isAlive()) {
                enemy.attack(player);
            }

            //ターン終了

            player.endTurn();
            enemy.endTurn();
            

            //勝利判定
            if (!enemy.isAlive()) {
                System.out.println("すべての敵を倒しました！");
                break;
            }
            if (!player.isAlive()) {
                System.out.println("ゲームオーバー。。。");
                break;
            }
        }

    }
}
