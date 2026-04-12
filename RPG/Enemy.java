package RPG;

public class Enemy extends Character{ //継承

    private int exp;
    private boolean isDead = false; //敵が倒されたか　falseは生きてる
    
    public Enemy(String name, int hp , int attack,double dodgeRate,int exp,int fullAttack,int speed,double critRate){
        super(name, hp, attack,dodgeRate,fullAttack,speed,critRate,0);

        this.exp = exp;

    }

    public int getExp(){
        return exp;
    }

    public boolean checkDead(){
        if (hp <= 0 && !isDead) { // !isDead は　死んだとき
            isDead = true;        // true = 死んだ
            return true;  //true が初めて倒したときで　false　はすでに死んでいる
        }
        return false; //　falseはまだ死んでない
    }


    public void chooseAction(Player[] party){

        action = 1; //攻撃する

        int index = (int)(Math.random() * party.length);// 0 ~  パーティー人数


        while (!party[index].isAlive()) {//そのプレイヤーは死んでいる

            index = (int)(Math.random() * party.length);// 0 ~  パーティー人数

        }//もう一度選びなおす = 生きているプレイヤーだけを通す

        target = party[index]; //ランダムなプレイヤーを攻撃対象にする
    }

    @Override
    public void performAction(){
        if (action == 1) {
            
            System.out.println(this.name + "の攻撃！");

            attack(target);
        }
    }

}
