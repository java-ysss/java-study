package RPGTwo;

public class PoisonSkill extends Skill {

    public PoisonSkill() {
        super("毒攻撃", 5, TargetType.SINGLE_ENEMY);

    }

    @Override
    public void use(Player user, Enemy target) {

        if (user.mp < mpCost) {
            System.out.println("MPが足りません!");
            return;
        }

        user.mp -= mpCost;

        target.takeDamage(10);

        if (target.hasStatus("毒")) {
            System.out.println("しかし効果はなかった！");
        } else {
            // ここが重要
            System.out.println(user.name + "の" + name + "!!!");
            target.statusEffects.add(new StatusEffect("毒", 3, 5));
            System.out.println(target.name + "は毒状態になった！");

        }//これで重複しない
    }
}
