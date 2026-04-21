package RPGTwo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import RPG.practis;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player[] party = {
                new Player("勇者", 150, 30, 20, 0.2, 0.1),
                new Player("魔法使い", 110, 60, 10, 0.2, 0.1)
        };

        party[0].addSkill(new FireSlash());// スキル追加
        party[1].addSkill(new Meteor());
        party[1].addSkill(new Heal());
        party[1].addSkill(new ManaHeal());
        party[0].addSkill(new PoisonSkill());
        party[0].addSkill(new ParalyzeSkill());

        Enemy[] enemies = {
                new Enemy("スライム", 80, 9, 10, 0.1, 0.06),
                new Enemy("ゴブリン", 90, 15, 12, 0.1, 0.06)
        };
        enemies[0].addSkill(new ParalyzeSkill());
        enemies[1].addSkill(new PoisonSkill());

        System.out.println("戦闘開始！");

        while (hasAliveParty(party) && hasAliveEnemy(enemies)) {

            System.out.println("======================");

            for (int i = 0; i < party.length; i++) {
                System.out.println((i + 1) + ":" + party[i].name + " HP : " + party[i].hp +
                        " MP : " + party[i].mp);
            }

            for (int i = 0; i < enemies.length; i++) {
                System.out.println((i + 1) + ":" + enemies[i].name + " HP : " + enemies[i].hp +
                        " MP : " + enemies[i].mp);
            }

            System.out.println("======================");

            for (Player player : party) {

                if (!player.isAlive()) { // 死んでいたらスキップ
                    continue;
                }

                System.out.println("1: 攻撃  2: 防御  3:スキル");
                int select = 0;

                try {
                    select = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("数字を入力してください");
                    scanner.next();
                    continue;
                }
                if (select <= 0 || select >= 4) {
                    System.out.println("1 ~ 3 で入力してください");
                    continue;
                }
                int skillChoice = -1;

                if (select == 3) {
                    for (int i = 0; i < player.skills.size(); i++) {
                        System.out.println((i + 1) + ":" + player.skills.get(i).name
                                + "( MP : " + player.skills.get(i).mpCost + ")");
                    }
                    skillChoice = scanner.nextInt() - 1;

                    if (skillChoice < 0 || skillChoice >= player.skills.size()) {
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

                    if (!player.canAct()) {// 動けなければスキップ
                        continue;
                    }
                    player.attack(enemies[target]);

                } else if (select == 2) {
                    if (!player.canAct()) {
                        continue;
                    }
                    player.defend();

                } else if (select == 3) {
                    Skill skill = player.skills.get(skillChoice);// 選んだ番号のスキルをリストから取り出す

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

                        if (!player.canAct()) {
                            continue;
                        }
                        skill.use(player, enemies[target]);

                    } else if (skill.targetType == TargetType.ALL_ENEMIES) {
                        skill.use(player, enemies);
                    } else if (skill.targetType == TargetType.SELF) {
                        skill.use(player, (Enemy) null);
                    }
                }
            }

            // 敵のターン
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    if (!enemy.canAct()) {
                        continue; // 行動スキップ
                    }
                    Player target = getAlivePartyMember(party);//一人選んで
                    enemy.takeAction(target);//その人に攻撃
                }

            }

            // ターン終了時の状態異常処理

            //hero.applyStatusEffects();

            for(Player player : party){
                if (player.isAlive()) {
                    player.applyStatusEffects();//全員分処理
                }
            }

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

    public static boolean hasAliveParty(Player[] party) {
        for (Player player : party) {
            if (player.isAlive()) {
                return true; // 一人でも生きていたらtrueを返して終了
            }
        }
        return false;
    }

    // 生きているメンバーを集めてランダムに1人返すメソッド
    public static Player getAlivePartyMember(Player[] party) {
        ArrayList<Player> alive = new ArrayList<>();

        for (Player hero : party) {
            if (hero.isAlive()) {
                alive.add(hero);// 生きている人を集める
            }
        }
        // 集めた中からランダムで一人返す
        return alive.get((int) (Math.random() * alive.size()));
    }

}

// if (skill instanceof Meteor) {//instanceof は調べた後に処理を変える
// skill.use(hero, enemies);//全体
// }else{
// skill.use(hero, enemies[target]);//そのスキルをheroが選んだ敵に使う(単体)
// }