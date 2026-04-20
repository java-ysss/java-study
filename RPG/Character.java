package RPG;

//共通の親クラス、名前、HP、攻撃

import java.util.ArrayList;
import java.util.List;

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
    protected int mp; //mp
    protected int maxMp; // mpの最大値

    protected List<Skill> skills = new ArrayList<>();
    //これで各キャラ複数のスキルを持てるようになる


    public Character(String name, int hp, int attack,double dodgeRate,int fullAttack,int speed,double critRate,int mp) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.maxHp = hp;
        this.dodgeRate = dodgeRate;
        this.fullAttack = fullAttack;
        this.speed = speed;
        this.critRate = critRate;
        this.mp = mp;
        this.maxMp = mp;
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

    //スキルを登録する（MainやFactoryで使う）
    public void addSkill(Skill skill){
        skills.add(skill);
    }

    //スキルを使う（MPチェックして実行）
    public boolean useSkill(int index, Character[] targets){
        if (index < 0 || index >= skills.size()) {
            System.out.println("そのスキルはない！");
            return false;
        }
        Skill skill = skills.get(index);

        if (mp < skill.getMpCost()) {
            System.out.println("MPが足りない!!");
            return false;
        }

        mp -= skill.getMpCost(); // MPを消費
        skill.execute(this, targets);// スキル実行
        return true;
    }

    //Character 共通のhealメソッド(HealSkillから呼ばれる)
    public void heal(int amout){
        this.hp += amout;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
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
