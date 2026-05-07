package RPGTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean bossAppeared = false;// ボスはまだでていない
        boolean gameOver = false; // ゲームオーバーではない

        // 出現する可能性がある敵のプール
        List<Enemy> pool = new ArrayList<>();
        pool.add(new Slime());
        pool.add(new Goblin());

        // ランダムで2 ~ 3 体選ぶ
        List<Enemy> selectedEnemies = new ArrayList<>();
        int count = 2 + new Random().nextInt(2); // //0が出たら → 2 + 0 = 2体//1が出たら → 2 + 1 = 3体
        // nextInt(2)は0か1を返すので：

        for (int i = 0; i < count; i++) {//ランダム選択
            int index = new Random().nextInt(pool.size());
            //pool.get(index)をそのまま追加するのではなく
            //新しいインスタンスを作って追加する
            Enemy selected = pool.get(index);
            try{
                selectedEnemies.add(selected.getClass().getDeclaredConstructor().newInstance());
            }catch(Exception e){
                e.printStackTrace();
            }

            //selected.getClass().getDeclaredConstructor().newInstance()は「
            // 同じクラスの新しいインスタンスを作る」という処理です。
            // これで毎回別オブジェクトになるのでA,B,Cが正しくつきます！
        }

        fixDuplicateNames(selectedEnemies);
        Enemy[] enemies = selectedEnemies.toArray(new Enemy[0]);

        Player[] party = {
                new Hero(), // HP/MP/スキル全部セット済みの勇者が入る
                new Mega()
        };

        Boss boss = new Golem();

        System.out.println("戦闘開始！");

        while (!gameOver && hasAliveParty(party) && (hasAliveEnemy(enemies) || boss.isAlive())) {
            // ゲームオーバー or パーティが生きている AND（雑魚が生きてる OR ボスが生きてる）

            showStatus(party, enemies, boss, bossAppeared);

            // コマンド入力フェーズ、アクションを保存する箱

            ArrayList<Action> actions = new ArrayList<>();

            for (Player player : party) {

                if (!player.isAlive()) {
                    continue;
                }

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
    public static void applyAllStatusEffects(Player[] party, Enemy[] enemies, Boss boss, boolean bossAppeared) {
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
        } else {
            boss.applyStatusEffects(); // ボスの状態異常処理
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

    //「同じ名前の敵が複数いたらA,B,Cをつける」メソッド
    public static void fixDuplicateNames(List<Enemy> enemies) {
        Map<String, Integer> nameCount = new HashMap<>();//「名前」→「何体いるか」を記録する箱


        for (Enemy enemy : enemies) {//全敵の名前を数える
            nameCount.put(enemy.name, nameCount.getOrDefault(enemy.name, 0) + 1);
        } //getOrDefault(key, 0)は「キーがあればその値、なければ0を返す」


        Map<String, Integer> nameIndex = new HashMap<>();//連番用のMapを作る
        //「今何番目か」を記録する箱です。

        for (Enemy enemy : enemies) { //全敵をループして名前を変える
            String originalName = enemy.name; // 変更前の名前を保存
            if (nameCount.containsKey(originalName) && nameCount.get(originalName) > 1) { 
                // ↑　2体以上いる名前だけ処理する
                int idx = nameIndex.getOrDefault(originalName, 0); //
                enemy.name = originalName + (char) ('A' + idx);
                nameIndex.put(originalName, idx + 1);
            }
        }
    }

}

// if (skill instanceof Meteor) {//instanceof は調べた後に処理を変える
// skill.use(hero, enemies);//全体
// }else{
// skill.use(hero, enemies[target]);//そのスキルをheroが選んだ敵に使う(単体)
// }

// 行動フェーズ書き換える前
// if (!action.actor.isAlive()) {
// continue;
// } // 行動前に死んでいたらスキップ

// if (!action.actor.canAct()) {
// continue;
/// } // 麻痺チェック

// if (action.type.equals("攻撃")) {

// if (action.target != null && action.target.isAlive())
// ターゲットが存在してて、かつ生きているなら
// {action.actor.attack(action.target);}

// } else if (action.type.equals("防御")) {

// ((Player) action.actor).defend();
// 「action.actor を Player として扱って、defend() を呼ぶ」
// System.out.println(action.actor.name + "防御した！");

// } else if (action.type.equals("スキル")) {
/// Skill skill = action.skill;
// Player skillUser = (Player) action.actor;

// if (skillUser.mp < skill.mpCost) {
// System.out.println("MPが足りません!");
// continue;
// }

// skillUser.mp -= skill.mpCost;

// if (skill.targetType == TargetType.ALL_ENEMIES) {
// System.out.println(skillUser.name + "は" + skill.name + "を使った！");
// if (!bossAppeared) {
// for (Enemy enemy : enemies) {
// if (enemy.isAlive()) {
// skill.use(skillUser, enemy);
// }
// }
// } else {
// skill.use(skillUser, boss);
// }
// } else if (skill.targetType == TargetType.ALL_ALLIES) {
// for (Player p : party) {
// if (p.isAlive()) {
// skill.use(skillUser, p);
// }
// }
// } else if (skill.targetType == TargetType.SELF) {

// skill.use(skillUser, null);

// } else {
// if (action.target != null) {
// skill.use(skillUser, action.target);
// }
// }
// } else if (action.type.equals("ボス攻撃")) {
// boss.takeTurn(Arrays.asList(party));
// }
