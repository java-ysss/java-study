package RPGTwo;

public class PoisonSkill extends Skill {

    public PoisonSkill() {
        super("毒斬り", 5, TargetType.SINGLE_ENEMY);

    }

    @Override
    public void use(Character user, Character target) {


        if (target.hasStatus("毒")) {
            System.out.println("しかし効果はなかった！");
        } else {
            // ここが重要
            //System.out.println(user.name + "の" + name + "!!!");
            target.takeDamage(10);
            target.statusEffects.add(new StatusEffect("毒", 3, 5));
            System.out.println(target.name + "は毒状態になった！");

        }//これで重複しない
    }
}
