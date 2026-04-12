package RPG;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character { // 継承
    private int recovery;
    private int level; // 今のレベル
    private int exp; // 現在の経験値
    private int expToLevelUp; // 次のレベルに必要な経験値
    private Enemy[] savedEnemies; //performActionで使うために保存

    public Player(String name, int hp, int attack, int recovery, double dodgeRate, int fullAttack, int speed,
            double critRate, int mp) {
        super(name, hp, attack, dodgeRate, fullAttack, speed, critRate, mp);
        this.recovery = recovery;

        this.level = 1;
        this.exp = 0;
        this.expToLevelUp = 100;

    }

    public void heal() {
        this.hp += this.recovery;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        System.out.println(this.name + "は" + this.recovery + "回復した！");
    }

    public void skillAttack(Character target) { // スキル攻撃
        System.out.println(this.name + "のスキル攻撃！");

        int damage = fullAttack;

        target.takeDamage(damage);
    }

    public void gainExp(int amount) {
        System.out.println(amount + "の経験値を獲得！");
        this.exp += amount;

        if (this.exp >= this.expToLevelUp) {
            levelUp();
        }
    }

    public void levelUp() {

        this.level++;
        // this.exp = 0; これは簡易版
        this.exp -= this.expToLevelUp; // これは繰り越しができる
        this.expToLevelUp += 90;

        System.out.println("レベルが" + this.level + " lv に上がりました！");

        this.maxHp += 20;
        this.hp = this.maxHp; // 全回復する
        this.attack += 5;

        System.out.println("HPが" + this.maxHp + "に" + "攻撃力が" + this.attack + "に！");

    }

    public void chooseAction(Enemy[] enemies, Player[] party, Scanner scanner) {
        // プレイヤーが何をするか決めるところ,まだ攻撃しない

        this.savedEnemies = enemies; // 保存

        System.out.println(this.name + "の行動");

        System.out.println("1 : 攻撃 2 : 防御 3 : スキル");

        action = Battle.inputNumber(scanner);

        if (action == 1) {
            target = chooseTarget(enemies, scanner);
        }

        if (action == 3) {
            // スキル一覧を表示
            for (int i = 0; i < skills.size(); i++) {
                System.out.println((i + 1) + ":" + skills.get(i).getName()
                        + "MP : " + skills.get(i).getMpCost());
            }
            int skillIndex = Battle.inputNumber(scanner) - 1; // 0始まりに変換

            // 範囲チェック↓
            while (skillIndex < 0 || skillIndex >= skills.size()) {
                System.out.println("もう一度入力してください");
                skillIndex = Battle.inputNumber(scanner) - 1;
            }

            action = 300 + skillIndex; // 300番台でスキル番号を保存 (例：３００ = スキル０番)
            // target = chooseTarget(enemies, scanner); //ターゲットも選ぶ

            Skill selected = skills.get(skillIndex);

            // ↓ 味方対象か敵対象で選択先を切り替える
            if (selected.isAoe()) {

                // 全体攻撃はターゲット選択不要
                target = null;
            } else if (selected.isTargetAlly()) {

                target = chooseTarget(party, scanner); // 味方から選ぶ

            } else {

                target = chooseTarget(enemies, scanner); // 敵から選ぶ
            }
        }

    }

    @Override
    public void performAction() { // 実際に行動するメソッド

        if (action == 1) {
            System.out.println(this.name + "の攻撃！");

            attack(target);
        } else if (action == 2) {
            defend();
        } else if (action >= 300) {
            int skillIndex = action - 300; // 300を引いてスキル番号を取り出す

            Skill selected = skills.get(skillIndex);

            if (selected.isAoe()) {
                useSkill(skillIndex,savedEnemies); //全員に渡す
            }else{
                useSkill(skillIndex, new Character[] { target }); // 単体スキル用
            }
        }
    }

    // ↓は攻撃対象のキャラ
    public Character chooseTarget(Character[] targets, Scanner scanner) {
        // 攻撃するキャラを選ぶ,そのキャラを返す

        ArrayList<Integer> aliveList = new ArrayList<>(); // 死んだ敵を選べない

        int displayIndex = 1;

        for (int i = 0; i < targets.length; i++) {

            if (targets[i].isAlive()) {

                System.out.println(displayIndex + ":" + targets[i].getName());

                aliveList.add(i);

                displayIndex++;
            }
        }

        int choice = Battle.inputNumber(scanner) - 1;

        while (choice < 0 || choice >= aliveList.size()) { // 入力範囲チェック もし数字が変だっ
            // // 変な数字の間,入力し続けるという意味になる。
            System.out.println("もう一度入力してください");

            choice = Battle.inputNumber(scanner) - 1; // こっちは新しい入力を受け取るため
        }

        int targetIndex = aliveList.get(choice);

        return targets[targetIndex];
    }

    public int getMp() {
        return mp;
    }

}
