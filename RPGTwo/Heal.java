package RPGTwo;

public class Heal extends Skill{
    
    public Heal(){
        super("ヒール", 8,TargetType.SINGLE_ALLY);
    }

    @Override
    public void use(Character user, Character target){


        //targetがnullなら自分に（安全装置） ↓

        Character realTarget = (target == null) ? user : target;

//分解するとこう/
            ///     if (target == null) {
            //          realTarget = user;
            //         }else {
           //          realTarget = target;}
           //targetが無いなら自分を対象にする ↑

        int heal = 30;

        int before = realTarget.hp;

        realTarget.hp += heal;

        if (realTarget.hp > realTarget.maxHp) {
            realTarget.hp = realTarget.maxHp;
        }

        int actualHeal = realTarget.hp - before;

        System.out.println(realTarget.name + "は" + actualHeal + "回復した！");
    }}
