package RPG;

public class Player extends Character{ //継承
    private int recovery;
    private int level; //今のレベル
    private int exp; //現在の経験値
    private int expToLevelUp; //次のレベルに必要な経験値
    


    public Player(String name, int hp, int attack,int recovery,double dodgeRate,int fullAttack,int speed){
        super(name, hp, attack,dodgeRate,fullAttack,speed);
        this.recovery = recovery;

        this.level = 1;
        this.exp = 0;
        this.expToLevelUp = 100;
    }

    public void heal(){
        this.hp += this.recovery;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        System.out.println(this.name + "は" + this.recovery + "回復した！");
    }


    public void skillAttack(Character target){ //スキル攻撃
        System.out.println(this.name + "のスキル攻撃！");
        

        int damage = this.attack * 2;

        target.takeDamage(damage);
    }

    

    public void gainExp(int amount){
        System.out.println(amount + "の経験値を獲得！");
        this.exp += amount;

        if (this.exp >= this.expToLevelUp) {
            levelUp();
        }
    }

    public void levelUp(){
        
        this.level++;
        // this.exp = 0; これは簡易版
        this.exp -= this.expToLevelUp; //これは繰り越しができる
        this.expToLevelUp += 90;

        System.out.println("レベルが" + this.level + " lv に上がりました！");

        this.maxHp += 20;
        this.hp = this.maxHp; //全回復する
        this.attack += 5;

        System.out.println("HPが" + this.maxHp + "に" + "攻撃力が" + this.attack + "に！");

    }

}


