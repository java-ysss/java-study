package RPG;

//共通の親クラス、名前、HP、攻撃

//protectedは子クラスOK　privateはだめ

public class Character {
    protected String name;
    protected int hp;
    protected int attack;
    protected int maxHp; 
    protected double dodgeRate;// 回避率

    public Character(String name, int hp, int attack,double dodgeRate) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.maxHp = hp;
        this.dodgeRate = dodgeRate;
    }

    public void attack(Character target) {//攻撃する側
       
        System.out.println(this.name + "の攻撃！");

        int damages = this.attack;

        if (Math.random() < 0.2) {
            damages *= 2;
            System.out.println("クリティカル！！！");
        }
        target.takeDamage(damages);
        System.out.println(target.name + "の残りHP : " + target.hp);
    }

    public void takeDamage(int damage){//攻撃を受ける側

        if (Math.random() < this.dodgeRate) {
            System.out.println(this.name + "は攻撃を回避した！"); //攻撃を受ける側だから回避コマンド
            return;
        }

        this.hp -= damage;

        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(damage + "のダメージ");
    }



    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }
}
