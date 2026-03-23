package game;
import java.util.ArrayList;
//ゲームのデータ管理のクラス、追加表示など

public class GameManager {
    private ArrayList<Game> games = new ArrayList<>();

    public void addGame(String name){
        Game game = new Game();
        game.name = name;
        game.isDone = false;
        games.add(game);
    }

    public void showGame(){
        if (games.isEmpty()) {
            System.out.println("まだクリアしたゲームはありません");
        }else{
            for(int i = 0; i < games.size(); i++){
                Game game = games.get(i);
                String status = game.isDone ? "[クリア]" : "[未完]";
                System.out.println((i + 1) + " "  + status + " " + game.name);
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
        game.isDone = true;
    }
    public void deleteGame(int index){
        if (!isValidIndex(index)) {
            System.out.println("存在しないゲームです");
            return;
        }
        games.remove(index -1);
    }
}
