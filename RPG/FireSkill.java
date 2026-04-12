package RPG;

public class FireSkill extends Skill{
    
    public FireSkill(){
        super("ファイア", 15,false,false); //falseは敵対象,単体攻撃
    }

    @Override
    public void execute(Character user, Character[] targets){
        Character target = targets[0];
        int damage = 30;


        System.out.println(user.getName() + "は" + name + "を使った！");

        target.takeDamage(damage);
    }
}
