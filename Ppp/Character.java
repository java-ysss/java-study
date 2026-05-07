package Ppp;

public class Character {
    protected String name;
    protected int hp;
    protected int mp;
    protected int attack;
    boolean isDefending;
    protected boolean isPoisoned;
    protected int poisonCount;

    public Character(String name, int hp, int mp, int attack) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
        this.isDefending = false;// 防御してない
        this.isPoisoned = false; // 毒じゃない

    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {

        int finalDamage = damage;

        if (isDefending) {
            finalDamage /= 2;
        }

        System.out.println(this.name + "は" + finalDamage + "のダメージ！");

        this.hp -= finalDamage;

        if (this.hp < 0) {
            hp = 0;
        }

    }

    public void attack(Character target) {

        System.out.println(this.name + "の攻撃！");

        target.takeDamage(this.attack);
    }

    public void defend() {
        System.out.println(this.name + "は防御の構えを見せている");
        isDefending = true;

    }

    public void poison() {

        if (isPoisoned) {
            System.out.println("もう毒になっている");
        } else {
            isPoisoned = true;

            System.out.println(this.name + "は毒になった！");

            poisonCount += 2;
        }

    }

    public void endTurn() {

        isDefending = false;

        int poisonDmage = 7;

        if (isPoisoned && poisonCount > 0) {
            this.hp -= poisonDmage;
            System.out.println(this.name + "は毒で" + poisonDmage + "のダメージ");

            poisonCount--;
        } else if (poisonCount == 0) {
            isPoisoned = false;
            System.out.println("毒が消えた！");
        }

    }
}
