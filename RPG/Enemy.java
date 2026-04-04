package RPG;

public class Enemy extends Character{ //継承

    private int exp;
    private boolean isDead = false; //敵が倒されたか　falseは生きてる
    
    public Enemy(String name, int hp , int attack,double dodgeRate,int exp,int fullAttack){
        super(name, hp, attack,dodgeRate,fullAttack);

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
}
