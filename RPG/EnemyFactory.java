package RPG;

import java.util.Random; //ランダム機能を使うための準備


public class EnemyFactory {

    public static Enemy[] createEnemies(){ // Enemyの配列を作るメソッド、敵の集まり

        Random rand = new Random(); //ランダム機能の器械

        int count = rand.nextInt(3) + 1; //1~3体 敵の数をランダムに決める

        Enemy[] enemies = new Enemy[count]; //敵を入れる箱を作る

        for(int i = 0; i < count; i++){ //敵の数だけ繰り返す

            int type = rand.nextInt(3); //敵の種類を決める

            if (type == 0) { //Enemyクラスのコンストラクタ、配列のi番に入れる
                enemies[i] = new Enemy("スライム", 50, 10, 0.1, 20, 20, 8,0.1);
            }
            else if (type == 1) {
                enemies[i] = new Enemy("ゴブリン", 80, 15, 0.1, 40, 25, 15,0.2);
            }
            else {
                enemies[i] = new Enemy("ドラゴン", 200, 30, 0.05, 200, 50, 2,0.2);
            }
        }
        return enemies; //Enemy[]型だから、作った敵を返す
    }
}
