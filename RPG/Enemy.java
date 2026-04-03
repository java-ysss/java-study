package RPG;

public class Enemy extends Character{ //継承

    private int exp;
    
    public Enemy(String name, int hp , int attack,double dodgeRate,int exp){
        super(name, hp, attack,dodgeRate);

        this.exp = exp;

    }

    public int getExp(){
        return exp;
    }
}
