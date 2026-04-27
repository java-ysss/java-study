package RPGTwo;

public class HealAll extends Skill{
    
    public HealAll(){
        super("ベホマラー", 25, TargetType.ALL_ALLIES);
    }

    @Override
    public void use(Character user, Character target){

        int heal = 30;

        int before = target.hp;

        target.hp += heal;

        if (target.hp > target.maxHp) {
            target.hp = target.maxHp;
        }

        int actualHeal = target.hp - before;

        System.out.println(target.name + "は" + actualHeal + "を回復した！");

     
          
        }
    }

//責任分離は大切、この中でMP消費すると人数分消費する可能性がある
//スキルの部分だけをクラスで出来ると拡張もしやすくなる