package RPGTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends Enemy{
    private int maxHp;

    public Boss(String name, int hp, int mp , int attack,double critRate,double dodgeRate,int speed){
        super(name, hp, mp, attack, critRate, dodgeRate,speed);

        this.maxHp = hp;
    }

    @Override
    public void takeTurn(List<Player> party){//プレイヤーの集まりを渡している,
    // リストにすることで全員からターゲット選べる

        System.out.println("【 " + name + " のターン 】");

        List<Player> alive = new ArrayList<>();
        //「Player型のオブジェクトを複数入れられるListを作って、aliveという変数に入れる。中身は最初は空。」

        for(Player player : party){
            if (player.isAlive()) {
                alive.add(player);
            }
        }

        if (alive.isEmpty()) {//aliveが空ならここで処理が終わる
            return;
        }

        Player target = alive.get(new Random().nextInt(alive.size()));
        //new Random().nextInt() は 0以上、size未満 の数を返す

        if (hp < maxHp / 2) {
            System.out.println(name + "は怒り狂った！");
            System.out.println(name + "の攻撃！");
            target.takeDamage(this.attack);
            target.takeDamage(this.attack / 2);
        }else{
            System.out.println(name + "の攻撃！");
            target.takeDamage(this.attack);
        }
    }
}
//List は「リストとして使う」という約束で、
//ArrayList は「実際にどうやってリストを実現するか」の実装です。
// 「型は List、実体は ArrayList」という書き方は Java では定番のパターン