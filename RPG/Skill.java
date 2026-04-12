package RPG;


//スキルの共通ルールだけ決める親クラス

public abstract class Skill {

    //abstract classにすることで「このクラス単体ではインスタンス化できない」＝設計図専用クラスになる
    
    protected String name;
    protected int mpCost;
    protected boolean targetAlly; //trueなら味方対象
    protected boolean isAoe; //trueなら全体攻撃

    public Skill(String name, int mpCost,boolean targetAlly,boolean isAoe){

        this.name = name;
        this.mpCost = mpCost;
        this.targetAlly = targetAlly;
        this.isAoe = isAoe;

    }

    public boolean isTargetAlly(){
        return targetAlly;
    }

    public boolean isAoe(){
        return isAoe;
    }
    //抽象メソッド：サブクラスごとに処理が違うので中身は書かない
    //例：ファイアはダメージ、ヒールは回復 共通化できないから中身のないメソッド
    public abstract void execute(Character user, Character[] targets);

    //↓はスキル情報を外から取得する用

    public String getName(){
        return name;
    }

    public int getMpCost(){
        return mpCost;
    }
}
