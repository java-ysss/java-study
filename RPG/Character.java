package RPG;

//共通の親クラス、名前、HP、攻撃

//protectedは子クラスOK　privateはだめ

public class Character {
    protected String name;
    protected int hp;
    protected int attack;

    public Character(String name, int hp , int attack){
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public void attack(Character target){
        target.hp -= this.attack;
        System.out.println(this.name + "の攻撃！");
        System.out.println(target.name + "に" + this.attack + "のダメージ！");
    }
}
