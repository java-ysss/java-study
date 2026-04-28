package RPGTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean bossAppeared = false;// ボスはまだでていない
        boolean gameOver = false; // ゲームオーバーではない

        Player[] party = {
                new Player("勇者", 150, 30, 20, 0.2, 0.1, 15),
                new Player("魔法使い", 110, 60, 10, 0.2, 0.1, 10)
        };

        party[0].addSkill(new FireSlash());// スキル追加
        party[1].addSkill(new Meteor());
        party[1].addSkill(new Heal());
        party[1].addSkill(new ManaHeal());
        party[0].addSkill(new PoisonSkill());
        party[0].addSkill(new ParalyzeSkill());
        party[1].addSkill(new HealAll());
        party[1].addSkill(new Revive());

        Enemy[] enemies = {
                new Enemy("スライム", 80, 9, 10, 0.1, 0.06, 9),
                new Enemy("ゴブリン", 90, 15, 12, 0.1, 0.06, 15)
        };
        enemies[0].addSkill(new ParalyzeSkill());
        enemies[1].addSkill(new PoisonSkill());

        Boss boss = new Boss("ゴーレム", 250, 50, 20, 0.1, 0.1, 5);

        System.out.println("戦闘開始！");

        while (!gameOver && hasAliveParty(party) && (hasAliveEnemy(enemies) || boss.isAlive())) {
            // ゲームオーバー or パーティが生きている AND（雑魚が生きてる OR ボスが生きてる）

            showStatus(party, enemies, boss, bossAppeared);

            // コマンド入力フェーズ、アクションを保存する箱

            ArrayList<Action> actions = new ArrayList<>();

            for (Player player : party) {

                if (!player.isAlive()) { continue; }
                
                Action action = player.slectAction(scanner, enemies, boss, bossAppeared, party);
                if (action != null) {
                    actions.add(action);
                }
            }

            // 敵のコマンドを追加
            if (!bossAppeared) {
                for (Enemy enemy : enemies) {
                    if (enemy.isAlive()) {
                        Player target = getAlivePartyMember(party);
                        actions.add(new Action(enemy, "攻撃", target, null));
                    }
                }
            } else {
                if (boss.isAlive()) {
                    actions.add(new Action(boss, "ボス攻撃", null, null));
                }
            }



            // 【行動フェーズ】 speed順に並び変えて実行
            actions.sort((a, b) -> b.actor.speed - a.actor.speed);
            // a b を逆にすると遅い順になる


            System.out.println("--- 行動フェーズ ---");

            for (Action action : actions) {
                action.execute(party, enemies, boss, bossAppeared);
            }



            // ターン終了時の状態異常処理

            applyAllStatusEffects(party, enemies, boss, bossAppeared);

            // ボス出現判定

            if (checkBossAppear(bossAppeared, enemies)) {
                bossAppeared = true;
            }

            // 勝敗判定
            if (checkGameEnd(party, enemies, boss, bossAppeared)) {
                break; // breakはメイン側で処理をする メソッドの中 ×
            }

        }
    }

    // =============================================================

    public static boolean hasAliveEnemy(Enemy[] enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                return true;
            }
        }
        return false;
    }

    // ステータス表示メソッド
    public static void showStatus(Player[] party, Enemy[] enemies, Boss boss, boolean bossAppeared) {
        System.out.println("======================");

        for (int i = 0; i < party.length; i++) {
            System.out.println((i + 1) + ":" + party[i].name + " HP : " + party[i].hp +
                    " MP : " + party[i].mp);
        }

        // 敵orボス表示
        if (!bossAppeared) {// ボスはまだ出ていないなら
            for (int i = 0; i < enemies.length; i++) {
                System.out.println((i + 1) + ":" + enemies[i].name + " HP : " + enemies[i].hp +
                        " MP : " + enemies[i].mp);
            }

        } else {
            System.out.println("BOSS : " + boss.name + " HP : " + boss.hp + " MP : " + boss.mp);
        }

        System.out.println("======================");
    }

    // 状態異常判定メソッド
    public static void applyAllStatusEffects(Player[] party, Enemy[] enemies,Boss boss, boolean bossAppeared) {
        for (Player p : party) {
            if (p.isAlive()) {
                p.applyStatusEffects();// 全員分処理
            }
        }

        if (!bossAppeared) {
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    enemy.applyStatusEffects();
                }
            }
        }else{
            boss.applyStatusEffects(); //ボスの状態異常処理
        }
    }

    // ボス出現判定メソッド
    public static boolean checkBossAppear(boolean bossAppeared, Enemy[] enemies) {
        if (!bossAppeared && !hasAliveEnemy(enemies)) {
            // 「ボスがまだ出ていない」かつ「生きてる敵もいない」
            System.out.println("ボスが現れた！！！");
            return true; // ボス出現
        }
        return false; // まだ
    }

    // 勝敗判定メソッド
    // trueを返したらゲーム終了、falseなら続行
    public static boolean checkGameEnd(Player[] party, Enemy[] enemies, Boss boss, boolean bossAppeared) {

        if (bossAppeared && !boss.isAlive()) {
            System.out.println("ボスを倒した！");
            return true; // ゲーム終了
        }

        if (!hasAliveParty(party)) {
            System.out.println("ゲームオーバー。。。");
            return true; // ゲーム終了
        }

        return false; // まだ続く
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

//行動フェーズ書き換える前
 //if (!action.actor.isAlive()) {
       //               continue;
         //         } // 行動前に死んでいたらスキップ

           //     if (!action.actor.canAct()) {
             //       continue;
               /// } // 麻痺チェック

          //      if (action.type.equals("攻撃")) {

           //         if (action.target != null && action.target.isAlive())
                        //ターゲットが存在してて、かつ生きているなら
             //           {action.actor.attack(action.target);}

               // } else if (action.type.equals("防御")) {

                 //   ((Player) action.actor).defend();
                    //「action.actor を Player として扱って、defend() を呼ぶ」
                   // System.out.println(action.actor.name + "防御した！");

             //   } else if (action.type.equals("スキル")) {
               ///     Skill skill = action.skill;
             //       Player skillUser = (Player) action.actor;

               //     if (skillUser.mp < skill.mpCost) {
                 //       System.out.println("MPが足りません!");
                   //     continue;
                    //}

              //      skillUser.mp -= skill.mpCost;

                //    if (skill.targetType == TargetType.ALL_ENEMIES) {
                  //      System.out.println(skillUser.name + "は" + skill.name + "を使った！");
                    //    if (!bossAppeared) {
                      //      for (Enemy enemy : enemies) {
                        //        if (enemy.isAlive()) {
                          //          skill.use(skillUser, enemy);
                            //    }
                            //}
                //        } else {
                  //          skill.use(skillUser, boss);
                  //      }
                 //   } else if (skill.targetType == TargetType.ALL_ALLIES) {
                   //     for (Player p : party) {
                     //       if (p.isAlive()) {
                       //         skill.use(skillUser, p);
                         //   }
                //        }
                 //   } else if (skill.targetType == TargetType.SELF) {

                   //     skill.use(skillUser, null);

                  //  } else {
                    //    if (action.target != null) {
                      //      skill.use(skillUser, action.target);
                     //   }
                 //   }
               // } else if (action.type.equals("ボス攻撃")) {
                 //   boss.takeTurn(Arrays.asList(party));
                //}
