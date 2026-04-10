package RPG;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character { // 継承
    private int recovery;
    private int level; // 今のレベル
    private int exp; // 現在の経験値
    private int expToLevelUp; // 次のレベルに必要な経験値
    private int mp; //mp
    private int maxMp; // 最大値

    public Player(String name, int hp, int attack, int recovery, double dodgeRate, int fullAttack, int speed,
            double critRate,int mp) {
        super(name, hp, attack, dodgeRate, fullAttack, speed, critRate);
        this.recovery = recovery;

        this.level = 1;
        this.exp = 0;
        this.expToLevelUp = 100;
        this.mp = mp;
        this.maxMp = mp;
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

    public void chooseAction(Enemy[] enemies, Scanner scanner){ 
        //プレイヤーが何をするか決めるところ,まだ攻撃しない

        System.out.println(this.name + "の行動");

        System.out.println("1 : 攻撃 2 : 防御 3 : スキル");

        action = Battle.inputNumber(scanner);

        if (action == 1) {
            target = chooseTarget(enemies, scanner);
        }

    }

    @Override
    public void performAction(){ // 実際に行動するメソッド

        if (action == 1) {
            System.out.println(this.name + "の攻撃！");

            attack(target);
        }
        else if (action == 2) {
            defend();
        }
    }

  
                                    //↓は攻撃対象のキャラ
    public Character chooseTarget(Character[] targets, Scanner scanner) { 
        // 攻撃するキャラを選ぶ,そのキャラを返す

      
        ArrayList<Integer> aliveList = new ArrayList<>(); //死んだ敵を選べない

        int displayIndex = 1;

        for (int i = 0; i < targets.length; i++) {

            if (targets[i].isAlive()) {

                System.out.println(displayIndex + ":" + targets[i].getName());

                aliveList.add(i);

                displayIndex++;
            }
        }

        int choice = Battle.inputNumber(scanner) - 1;

        while (choice < 0 || choice >= aliveList.size()) { // 入力範囲チェック もし数字が変だったら
                                                            //変な数字の間,入力し続けるという意味になる。
            System.out.println("もう一度入力してください");

            choice = Battle.inputNumber(scanner) - 1;  //こっちは新しい入力を受け取るため
        }

        int targetIndex = aliveList.get(choice);

        return targets[targetIndex];
    }

    //魔法

    public void fire(Enemy enemy){

        if (mp < 10) {
            System.out.println("MPが足りない!");
            return;
        }

        mp -= 10;

        int damage = 30;

        System.out.println(this.name + "はファイアを使った！");

        enemy.takeDamage(damage);
    }

    public int getMp(){
        return mp;
    }

}
