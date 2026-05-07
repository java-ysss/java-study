package RPGTwo;

public class Mera extends Skill{
    
    public Mera(){
        super("メラ",7,TargetType.SINGLE_ENEMY);
    }

    @Override
    public void use(Character user, Character target){

        int damage = 15 +  (int)(Math.random() * 5);

        damage = applyCritical(user, damage);

        target.takeDamage(damage);


    }
}


