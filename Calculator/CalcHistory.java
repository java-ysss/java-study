package Calculator;
import java.util.ArrayList;

public class CalcHistory {
    ArrayList<String> history = new ArrayList<>();
    
    public void addHistory(String record){
        history.add(record);
    }

    public void showHistory(){
        if (history.isEmpty()) {
            System.out.println("まだ履歴はありません");
        }else{
            for(int i = 0; i < history.size(); i++){
                System.out.println((i + 1) + " : " + history.get(i));
            }
        }
    }
}