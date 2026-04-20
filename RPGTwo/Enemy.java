package RPGTwo;

public class Enemy extends Character{
    
    int attack;
    double critRate;
    double dodgeRate;
    

    public Enemy(String name , int hp , int attack,double critRate , double dodgeRate){
        super(name, hp);

        this.attack = attack;
        this.critRate = critRate;//クリティカル
        this.dodgeRate = dodgeRate;//回避率
    }

    public void attack(Player player){
        System.out.println(this.name + "の攻撃！");

        if (Math.random() < player.dodgeRate) {
            System.out.println("miss!!");
            return;
        }

        int damage = this.attack + (int)(Math.random() * 5);

        if (Math.random() < this.critRate) {
            
            damage *= 2;
            System.out.println("クリティカル！");
        }

        player.takeDamage(damage);
    }

    //public String getName(){
        //  return name;
      //}
}

  //public void takeDamage(int damage){
    //    hp -= damage;

      //  if (hp < 0) {
        //    hp = 0;
        //}
        //System.out.println(this.name + "は" + damage + "のダメージ！");
    //}

    //public boolean isAlive(){
      //  return hp > 0;
    //}
