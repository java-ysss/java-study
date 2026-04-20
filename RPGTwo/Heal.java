package RPGTwo;

public class Heal extends Skill{
    
    public Heal(){
        super("ヒール", 8,TargetType.SELF);
    }

    @Override
    public void use(Player user, Enemy target){
        if (user.mp < mpCost) {
            System.out.println("MPが足りません!");
            return;
        }

        user.mp -= mpCost;

        int heal = 30;

        int before = user.hp;

        user.hp += heal;

        if (user.hp > user.maxHp) {
            user.hp = user.maxHp;
        }

        int actualHeal = user.hp - before;

        System.out.println(user.name + "は" + actualHeal + "回復した！");
    }
}
