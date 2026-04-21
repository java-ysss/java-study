package RPGTwo;

public class ManaHeal extends Skill{
    
    public ManaHeal(){
        super("マナヒール", 5, TargetType.SELF);
    }
        @Override
        public void use(Character user, Character target){

            if (user.mp < mpCost) {
                System.out.println("MPが足りません!");
                return;
            }

            user.mp -= mpCost;

            int recover = 20;
            int before = user.mp;

            user.mp = Math.min(user.mp + recover, user.maxMp);
            //AとBを比べて小さい方を返す,MPが最大値を超えないようにする処理

            int actualRecover = user.mp - before;

            System.out.println(user.name + "はMPを" + actualRecover + "回復した！");
        
    }
}
