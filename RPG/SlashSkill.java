package RPG;

public class SlashSkill extends Skill{

    public SlashSkill(){
        super("なぎ払い", 20,false,true);//敵対象,複数攻撃
    }

    @Override
    public void execute(Character user, Character[] targets){
        System.out.println(user.getName() + "なぎ払いを使った！");

        for(Character target : targets){
            if (target.isAlive()) {
                int damage = 40;
                target.takeDamage(damage);
            }
        }
    }
}
