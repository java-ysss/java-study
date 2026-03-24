package game;
//ゲーム１つのデータを保存するクラス
public class Game {
    private String name;
    private boolean isDone;

    public Game(String name){ //コンストラクタ
        this.name = name;
        this.isDone = false;
    }
    

    public String getName(){
        return name;
    }
    public boolean isDone(){
        return isDone;
    }
    public void setDone(boolean done){
        isDone = done;
    }
}
