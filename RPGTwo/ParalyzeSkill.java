package RPGTwo;

public class ParalyzeSkill extends Skill{
    
    public ParalyzeSkill(){
        super("麻痺攻撃", 5, TargetType.SINGLE_ENEMY);
    }

    @Override
    public void use(Character user, Character target){

        if (target.hasStatus("麻痺")) {
            System.out.println("しかし効果はなかった！");
        }else{
            System.out.println(user.name + "の麻痺攻撃！");
            target.takeDamage(5);
            target.statusEffects.add(new StatusEffect("麻痺", 2, 0));
            System.out.println(target.name + "は麻痺した！");
        }
    }
}
