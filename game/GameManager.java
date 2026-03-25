package game;
import java.util.ArrayList;
//ゲームのデータ管理のクラス、追加表示など

public class GameManager {
    private ArrayList<Game> games = new ArrayList<>();

    public void addGame(String name){
        Game game = new Game(name);
        games.add(game);
        //game.setDone(false);これはいらないらしい
    }
    public void addRPGGame(String name,int level){
        RPGGame game = new RPGGame(name, level);
        games.add(game);
    }

    public void showGame(){
        if (games.isEmpty()) {
            System.out.println("ゲームがまだ登録されていません");
            return;
        }else{
            for(int i = 0; i < games.size(); i++){
                Game game = games.get(i);
                String status = game.isDone() ? "[クリア]" : "[未完]";
                String genre = "";
                if (game instanceof Genreable g) {
                    genre = g.getGenreName();
                }

                System.out.println((i + 1) + " "  + status + " " + genre + " " + game.getName());
            }
        }
    }
    private boolean isValidIndex(int index){
        return index > 0 && index <= games.size();
    }

    public void completeGame(int index){
        if (!isValidIndex(index)) {
            System.out.println("存在しないゲームです");
            return;
        }
        Game game = games.get(index - 1);
        game.setDone(true) ;
    }
    public void deleteGame(int index){
        if (!isValidIndex(index)) {
            System.out.println("存在しないゲームです");
            return;
        }
        games.remove(index -1);
    }
}
