package RPG;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player[] party = {
                new Player("勇者", 200, 25, 10, 0.2, 50, 10, 0.3,50),
                new Player("魔法使い", 180, 15, 20, 0.1, 20, 5, 0.2,100),
                new Player("ヒーラー", 170, 10, 30, 0.15, 10, 3, 0.1,120),
                new Player("戦士", 300, 35, 5, 0.4, 60, 5, 0.1,20)
        };
        Enemy[] enemies = EnemyFactory.createEnemies();

        party[0].addSkill(new FireSkill());
        party[1].addSkill(new FireSkill());
        party[2].addSkill(new HealSkill());
        party[3].addSkill(new SlashSkill());

        Battle.startBattle(party, enemies, scanner);

    }
}