package RPGTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Character{

    double critRate;
    double dodgeRate;
    

    public Enemy(String name , int hp ,int mp, int attack,double critRate , double dodgeRate,int speed){
        super(name, hp , mp,attack,dodgeRate,critRate,speed);
    }

    // ↓ 使えるスキルを調べる → スキルがあればランダムに使用 →　無ければ通常攻撃
    public void takeAction(Character target){

        Random rand = new Random();

        //使えるスキルを集める箱
        ArrayList<Integer> usableSkills = new ArrayList<>();

        for(int i = 0; i < skills.size(); i++){
            Skill s = skills.get(i);

            if (this.mp >= s.mpCost) {
                usableSkills.add(i);
            }
        }

        //行動決定
        if (usableSkills.size() > 0) {
            int index = usableSkills.get(rand.nextInt(usableSkills.size()));
            useSkill(index , target);
        }else{
            attack(target);
        }
    }

    public void takeTurn(List<Player> party){
        //通常敵はランダムで一人攻撃
        Player target = party.get((int)(Math.random() * party.size()));
        takeAction(target);
    }


    //public String getName(){
        //  return name;
      //}
}

  //public void takeDamage(int damage){
    //    hp -= damage;

      //  if (hp < 0) {
        //    hp = 0;
        //}
        //System.out.println(this.name + "は" + damage + "のダメージ！");
    //}

    //public boolean isAlive(){
      //  return hp > 0;
    //}
//ArrayList<String>  list = new ArrayList<>();  // 文字列のリスト
//ArrayList<Double>  list = new ArrayList<>();  // 小数のリスト
//ArrayList<Boolean> list = new ArrayList<>();  // true/falseのリスト
//Integer は　整数のみ