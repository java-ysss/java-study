package RPGTwo;

public class Meteor extends Skill {

    public Meteor() {
        super("メテオ", 15, TargetType.ALL_ENEMIES);
    }

    @Override
    public void use(Character user, Character target) {

        
        //user.mp -= mpCost;

        int damage = user.attack + (int) (Math.random() * 5);

        damage = applyCritical(user, damage);

        target.takeDamage(damage);

    }
}
