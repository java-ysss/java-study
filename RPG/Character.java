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


    public Character(String name, int hp, int attack,double dodgeRate,int fullAttack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.maxHp = hp;
        this.dodgeRate = dodgeRate;
        this.fullAttack = fullAttack;
    }

    public void attack(Character target) {//攻撃する側
       
        System.out.println(this.name + "の攻撃！");

        int damages = this.attack;

        if (Math.random() < 0.2) { // Math.randomは0以上1未満の数字をランダムに選んでくれる
            damages *= 2; //元の数字を残しながら二倍にできるs
            System.out.println("クリティカル！！！");
        }
        target.takeDamage(damages);
        System.out.println(target.name + "の残りHP : " + target.hp);
    }


    public void takeDamage(int damage){//攻撃を受ける側

        if (Math.random() < this.dodgeRate) {
            System.out.println(this.name + "は攻撃を回避した！"); //攻撃を受ける側だから回避コマンド
            return;
        }

        if (isDefending) {
            damage /= 2;
            System.out.println(this.name + "は防御してダメージを軽減した!");
            //isDefending = false; これがあると一回で解除される
        }

        this.hp -= damage;

        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.name + "は" + damage + "のダメージ");
        System.out.println("-------------------------------");

    }


    public void defend(){ //防御
        System.out.println(this.name + "は防御の構えをした！");
        isDefending = true;
    }
    


    public void allAttack(Character[] targets){
        System.out.println(this.name + "の全体攻撃！");

        for(Character target : targets){
            if (target.isAlive()) { //ターゲットが生きていたら
                
                int damage = this.attack / 2; //攻撃を半分にして
                target.takeDamage(damage);//ターゲットに対してtakeDmageメソッド

                System.out.println(target.name + "に" + damage + "ダメージ！！");

                if (!target.isAlive()) { // ! があるから逆の[生きていなければ]
                    System.out.println(target.name + "を倒した！");
                }
            }
        }
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

    public void setDifending(boolean isDefending){
        this.isDefending = isDefending;
    }
  
}
