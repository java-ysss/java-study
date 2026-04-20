package RPGTwo;

public abstract class Skill { //必ず継承して使う前提のクラス
    String name; //全スキルクラス共通のフィールド
    int mpCost;
    TargetType targetType;

    public Skill(String name, int mpCost,TargetType targetType){ 
        this.name = name;
        this.mpCost = mpCost;
        this.targetType = targetType;
    }

    //単体攻撃（enemy が一体だから）
    public abstract void use(Player user , Enemy target);
    //「スキルを使う処理は必ず作ってね」という強制ルール
    //子クラスで必ず @Override して実装しなければコンパイルエラーになる


    //全体攻撃（デフォルトでは使えない）
    public void use(Player user, Enemy[] targets){
        System.out.println("このスキルは全体攻撃ではない！");
    }

    //共通クリティカル処理
    protected int applyCritical(Player user, int damage){
        if (Math.random() < user.critRate) {
            System.out.println("会心の一撃！");
            return damage * 2;
        }

        return damage;
    }
}
