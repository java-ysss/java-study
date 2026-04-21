package RPGTwo;

public class Meteor extends Skill{
    
    public Meteor(){
        super("メテオ", 15,TargetType.ALL_ENEMIES);
    }

    @Override
    public void use(Player user , Enemy target){
        System.out.println("このスキルは単体攻撃では使えない！");
    }

    @Override
    public void use(Character user , Character[] targets){

        if (user.mp < mpCost) {
            System.out.println("MPが足りません!");
            return;
        }

        user.mp -= mpCost;

        System.out.println(user.name + "はメテオを使った！！");

        for(Character enemy : targets){
            if (enemy.isAlive()) {
                
                int damage = user.attack + (int)(Math.random() + 5);

                damage = applyCritical(user, damage);

                enemy.takeDamage(damage);
            }

        }
    }
}
