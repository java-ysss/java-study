package Ppp;

public class Mera extends Skill{
    
    public Mera(){
        super("メラ", 7); //Skillのコンストラクタを呼んでる
    }

    @Override
    public boolean use(Character user, Character target){

        if (user.mp < getMpCost()) {
            System.out.println("MPが足りません！");
            return false;
        }

        user.mp -= getMpCost();

        int damage = 15 + (int)(Math.random() * 4);

        target.takeDamage(damage);

        return true;
    }


}
