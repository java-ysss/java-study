package RPGTwo;

import java.util.ArrayList;

public class Player extends Character{

    ArrayList<Skill> skills = new ArrayList<>();//スキルを入れる箱
    
    int mp;
    int maxMp;
    int attack;
    double critRate;
    double dodgeRate;

    public Player(String name, int hp,int mp, int attack,double critRate , double dodgeRate){
        super(name, hp); //親のコンストラクタ

        this.maxHp = hp;
        this.mp = mp;
        this.maxMp = mp;
        this.attack = attack;
        this.critRate = critRate;
        this.dodgeRate = dodgeRate;
    }

    public void attack(Enemy enemy){
        System.out.println(this.name + "の攻撃！");

        //回避(相手の回避率を使う)
        if (Math.random() < enemy.dodgeRate) { //10%
            System.out.println("攻撃が外れた！");
            return; //外れたら処理終了
        }

        //基本ダメージにブレを持たせる
        int damage = attack + (int)(Math.random() * 3); //(int)は少数切り捨て

        //クリティカル
        if (Math.random() < this.critRate) {//20%
            
            damage *= 2;
            System.out.println("会心の一撃！");
        }

        enemy.takeDamage(damage); //ダメージを与える
    }

    public void defend(){
        isDefending = true;
        System.out.println(this.name + "は防御の構えをした！");
    }


    public void addSkill(Skill skill){
        skills.add(skill);
    }

}

//　↓　これは共通のキャラクタークラスに

   //public void takeDamage(int damage){
     //   if (isDefending) {
       //     damage /= 2;
         //   isDefending = false;
           // System.out.println("防御した！ダメージを半減");
        //}

        //hp -= damage;
        //System.out.println(this.name +  "は" + damage + "のダメージ！");
    //}

    //public boolean isAlive(){
      //  return hp > 0;
    //}
