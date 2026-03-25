package game;

public class RPGGame extends Game implements Genreable{//これが継承 と、インターフェイス
    private int level;


    //オーバーライド
    public String getGenreName(){
         return "[RPG] Lv." + level;
    }

    public RPGGame(String name,int level){
        super(name);
        this.level = level;

    }
    public int getLevel(){
        return level;
    }
}
