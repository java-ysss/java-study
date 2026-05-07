package Ppp;

public abstract class Skill { //abstractがあると「そのままでは使えないクラス」
    private String name;
    private int mpCost;
    

    public Skill(String name, int mpCost){ //コンストラクタは「スキルの初期設定」
        this.name = name;
        this.mpCost = mpCost;
    }

    // abstractあり → 中身は子クラスに任せる
    public  abstract boolean use(Character user, Character target);

    //「全スキルに共通の処理（applyCriticalなど）はここに書く、
    //スキルごとに違う処理（use）は子クラスに任せる」という役割分担ができる
    
    public String getName(){
        return this.name;
    }

    public int getMpCost(){
        return this.mpCost;
    }
}
