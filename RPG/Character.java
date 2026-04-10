package RPG;

//共通の親クラス、名前、HP、攻撃

//protectedは子クラスOK　privateはだめ

public class Character {
    protected String name;
    protected int hp;
    protected int attack;
    protected int maxHp; 
    protected double dodgeRate;// 回避率
    protected int fullAttack; //スキル攻撃
    protected boolean isDefending; //防御
    protected int speed; //素早さ
    protected double critRate; //クリティカル率
    protected int action; //行動
    protected Character target; //攻撃対象


    public Character(String name, int hp, int attack,double dodgeRate,int fullAttack,int speed,double critRate) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.maxHp = hp;
        this.dodgeRate = dodgeRate;
        this.fullAttack = fullAttack;
        this.speed = speed;
        this.critRate = critRate;
    }

    public void attack(Character target) {//攻撃する側
       
       // System.out.println(this.name + "の攻撃！");

        int damages = this.attack;

        if (Math.random() < critRate) { // Math.randomは0以上1未満の数字をランダムに選んでくれる
            damages *= 2; //元の数字を残しながら二倍にできるs
            System.out.println("クリティカル！！！");
        }
        target.takeDamage(damages);
    }


    public void takeDamage(int damage){//攻撃を受ける側

        if (Math.random() < this.dodgeRate) {
            System.out.println(this.name + "は攻撃を回避した！"); //攻撃を受ける側だから回避コマンド
            isDefending = false;//回避しても防御は解除
            return;
        }

        if (isDefending) {
            damage /= 2;
            System.out.println(this.name + "は防御してダメージを軽減した!");
            
        }

        this.hp -= damage;

        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.name + "は" + damage + "のダメージ");
        System.out.println(this.name + "の残りHP :" + this.hp);
        System.out.println("-------------------------------");

        isDefending = false; // 防御、回避、攻撃されても、防御解除できる
    }


    public void defend(){ //防御
        System.out.println(this.name + "は守りを固めている");
        isDefending = true;
    }


     public void resetState(){ //後から値を変えないから引数はいらない
        isDefending = false; //防御の構え
    }
    


    public void allAttack(Character[] targets){
        System.out.println(this.name + "の全体攻撃！");

        for(Character target : targets){
            if (target.isAlive()) { //ターゲットが生きていたら
                
                int damage = this.attack / 2; //攻撃を半分にして
                target.takeDamage(damage);//ターゲットに対してtakeDmageメソッド

                //System.out.println(target.name + "に" + damage + "ダメージ！！");

                if (!target.isAlive()) { // ! があるから逆の[生きていなければ]
                    System.out.println(target.name + "を倒した！");
                }
            }
        }
    }

    public void performAction(){ //オーバーライドの元、空でもいいらしい

    }





    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getFullAttack(){
        return fullAttack;
    }
    public boolean isAlive(){
        return hp > 0; //HPが0より上は生きているよということ
    }

    //public void setDifending(boolean isDefending){
        //this.isDefending = isDefending; 防御メソッド/今回はほかのやつがいいらしい 
        //後から変えられてしまうから　他のにも使えるように resetのやつに
    //}

  
}
