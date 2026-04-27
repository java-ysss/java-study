package RPGTwo;

public class FireSlash extends Skill {
    
    public FireSlash(){
        super("火炎斬り",10,TargetType.SINGLE_ENEMY);
    }

    @Override
    public void use(Character user , Character target){
      
        int damage = user.attack + 10;

        System.out.println(user.name + "は火炎斬りを使った！");

        damage = applyCritical(user,damage);

        target.takeDamage(damage);
    }
}
