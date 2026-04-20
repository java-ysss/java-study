package RPGTwo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player hero = new Player("勇者", 150, 30, 20, 0.2, 0.1);

        hero.addSkill(new FireSlash());// スキル追加
        hero.addSkill(new Meteor());
        hero.addSkill(new Heal());
        hero.addSkill(new ManaHeal());
        hero.addSkill(new PoisonSkill());
        hero.addSkill(new ParalyzeSkill());

        Enemy[] enemies = {
                new Enemy("スライム", 80, 10, 0.1, 0.06),
                new Enemy("ゴブリン", 90, 12, 0.1, 0.06)
        };

        System.out.println("戦闘開始！");

        while (hero.isAlive() && hasAliveEnemy(enemies)) {

            System.out.println("======================");

            System.out.println(hero.name + " HP : " + hero.hp + " MP : " + hero.mp);

            for (int i = 0; i < enemies.length; i++) {
                System.out.println((i + 1) + ":" + enemies[i].name + " HP : " + enemies[i].hp);
            }

            System.out.println("======================");

            System.out.println("1: 攻撃  2: 防御  3:スキル");
            int select = 0;

            try {
                select = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("数字を入力してください");
                scanner.next();
                continue;
            }
            int skillChoice = -1;

            if (select == 3) {
                for (int i = 0; i < hero.skills.size(); i++) {
                    System.out.println((i + 1) + ":" + hero.skills.get(i).name
                            + "( MP : " + hero.skills.get(i).mpCost + ")");
                }
                skillChoice = scanner.nextInt() - 1;

                if (skillChoice < 0 || skillChoice >= hero.skills.size()) {
                    System.out.println("無効な選択");
                    return;
                }
            }

            if (select == 1) {
                System.out.println("攻撃する敵を選んでください");

                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i].isAlive()) {
                        System.out.println((i + 1) + ":" + enemies[i].name);
                    }
                }

                int target = scanner.nextInt() - 1;

                // 敵が生きているかどうか
                if (target < 0 || target >= enemies.length || !enemies[target].isAlive()) {
                    System.out.println("その敵は選べない！");
                    continue;
                }

                if (!hero.canAct()) {
                    continue;
                }
                hero.attack(enemies[target]);

            } else if (select == 2) {
                hero.defend();

            } else if (select == 3) {
                Skill skill = hero.skills.get(skillChoice);// 選んだ番号のスキルをリストから取り出す

                if (skill.targetType == TargetType.SINGLE_ENEMY) {
                    System.out.println("攻撃する敵を選んでください");

                    for (int i = 0; i < enemies.length; i++) {
                        if (enemies[i].isAlive()) {
                            System.out.println((i + 1) + ":" + enemies[i].name);

                        }
                    }

                    int target = scanner.nextInt() - 1;

                    if (target < 0 || target >= enemies.length || !enemies[target].isAlive()) {
                        System.out.println("その敵は選べない！");
                        continue;
                    }

                    if (!hero.canAct()) {
                        continue;
                    }
                    skill.use(hero, enemies[target]);

                } else if (skill.targetType == TargetType.ALL_ENEMIES) {
                    skill.use(hero, enemies);
                } else if (skill.targetType == TargetType.SELF) {
                    skill.use(hero, (Enemy) null);
                }
            }

            // 敵のターン
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {

                    if (!enemy.canAct()) {
                        continue; // 行動スキップ
                    }
                    enemy.attack(hero);
                }
            }

            // ターン終了時の状態異常処理

            hero.applyStatusEffects();

            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.applyStatusEffects();
                }
            }

            if (!hasAliveEnemy(enemies)) {
                System.out.println("すべての敵を倒した！");
                break;
            }
        }

    }

    public static boolean hasAliveEnemy(Enemy[] enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                return true;
            }
        }
        return false;
    }
}

// if (skill instanceof Meteor) {//instanceof は調べた後に処理を変える
// skill.use(hero, enemies);//全体
// }else{
// skill.use(hero, enemies[target]);//そのスキルをheroが選んだ敵に使う(単体)
// }