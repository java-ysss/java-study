package RPG;

public class Player extends Character{ //継承
    private int recovery;

    public Player(String name, int hp, int attack,int recovery){
        super(name, hp, attack);
        this.recovery = recovery;
    }

    public void heal(){
        this.hp += this.recovery;
        System.out.println(this.name + "は回復した！");
        System.out.println(this.name + "は" + this.recovery + "回復した！");
    }

}


