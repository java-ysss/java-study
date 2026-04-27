package RPGTwo;

public class Revive extends Skill{
    public Revive(){
        super("ザオラル", 30, TargetType.DEAD_ALLY);
    }

    @Override
    public void use(Character user, Character target){


        if (target == null || target.isAlive()) {
            System.out.println("蘇生対象が正しくありません");
            return; //retunはここでメソッド終了、voidだから
        }


        target.hp = target.maxHp / 2;

        System.out.println(target.name + "は復活した！");


    }
}
