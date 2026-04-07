package RPG;

public class Enemy extends Character{ //継承

    private int exp;
    private boolean isDead = false; //敵が倒されたか　falseは生きてる
    
    public Enemy(String name, int hp , int attack,double dodgeRate,int exp,int fullAttack,int speed){
        super(name, hp, attack,dodgeRate,fullAttack,speed);

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

    public void takeTurn(Character target){
        int action = (int)(Math.random() * 100); // 確率調整で100%として

        if (action < 70) {
            attack(target); //70%で攻撃
        }else{
            defend();
        }
        
    }
}
