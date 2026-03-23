package Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoManager {
    private ArrayList<ToDo> todos = new ArrayList<>();

    public void addToDo(String name,Priority priority) {
        ToDo todo = new ToDo();
        todo.name = name;
        todo.isDone = false;
        todo.priority = priority;
        todos.add(todo);
    }

    // 表示メソッド
    public void showToDo() {
        if (todos.isEmpty()) {
            System.out.println("タスクはありません");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                ToDo todo = todos.get(i);
                String status = todo.isDone ? "[完了]" : "[未完]";
                String priority = switch (todo.priority){
                    case HIGH -> "[高]";
                    case MEDIUM -> "[中]";
                    case LOW -> "[低]";
                };
                System.out.println((i + 1) + "  " + status + "  " + priority  + " : " + todo.name);
            }
        }
    }

    // 範囲判定メソッド？
    private boolean isValidIndex(int index) {
        return index > 0 && index <= todos.size();
    }

    // 完了メソッド
    public void completeToDo(int index) {
        if (!isValidIndex(index)) {
            System.out.println("存在しないタスク番号です");
            return;
        }
        ToDo todo = todos.get(index - 1);
        todo.isDone = true;
    }

    

    // 削除メソッド
    public void deleteToDo(int index) {
        if (!isValidIndex(index)) {
            System.out.println("存在しないタスク番号です");
            return;
        }
        todos.remove(index - 1);
    }
    //保存
    public void saveToDo(){
        try(FileWriter writer = new FileWriter("todos.txt")){
            for(ToDo todo : todos){
                writer.write(todo.name + "," + todo.isDone + "," + todo.priority + "\n");
            } 
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("保存に失敗しました");
        }
    }

    //読み込み
    public void loadToDo(){
        File file = new File("todos.txt");
        if (!file.exists()) return;

        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                ToDo todo = new ToDo();
                todo.name = parts[0];
                todo.isDone = Boolean.parseBoolean(parts[1]);
                todo.priority = Priority.valueOf(parts[2]);
                todos.add(todo);
            }

        }catch (IOException e){
            System.out.println("読み込みに失敗しました");
        }
    }
}