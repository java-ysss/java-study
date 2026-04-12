package RPG;

public class HealSkill extends Skill{

    public HealSkill(){
        super("ヒール",8,true,false); //trueは味方対象,単体攻撃
    }

    @Override
    public void execute(Character user, Character[] targets){
        Character target = targets[0];
        int heal = 25;

        System.out.println(user.getName() + "は" + name + "を使った！");

         target.heal(heal);

        System.out.println(target.getName() + "は" + heal + "回復した！");
        System.out.println(target.getName() + "の残りHP: " + target.getHp());
    }
}
