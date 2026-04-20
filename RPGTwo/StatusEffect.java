package RPGTwo;
//状態異常
public class StatusEffect {
    
    String name;
    int duration; //継続ターン
    int damage; //ダメージ量

    public StatusEffect(String name, int duration , int damage){
        this.name = name;
        this.duration = duration;
        this.damage = damage;
    }

    public void apply(Character target){

        if (name.equals("毒")) {
            target.hp -= damage;
            System.out.println(target.name + "は毒で" + damage + "ダメージ！");
        }
        if (name.equals("麻痺")) {
            System.out.println(target.name + "は痺れている…");
        }
        duration--;//これをしないと永続的にかかってしまう
    }

    public boolean isFinished(){
        return duration <= 0;
    }
    
}
