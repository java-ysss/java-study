package RPGTwo;

//import java.util.ArrayList;

public class Player extends Character {

    public Player(String name, int hp, int mp, int attack, double critRate, double dodgeRate) {
        super(name, hp, mp, attack, dodgeRate, critRate);

        this.maxHp = hp;
        this.maxMp = mp;
        this.critRate = critRate;
        this.dodgeRate = dodgeRate;
    }

    public void defend() {
        isDefending = true;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

}

// ↓ これは共通のキャラクタークラスに

// public void takeDamage(int damage){
// if (isDefending) {
// damage /= 2;
// isDefending = false;
// System.out.println("防御した！ダメージを半減");
// }

// hp -= damage;
// System.out.println(this.name + "は" + damage + "のダメージ！");
// }

// public boolean isAlive(){
// return hp > 0;
// }
