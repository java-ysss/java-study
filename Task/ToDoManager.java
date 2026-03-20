package Task;

import java.util.ArrayList;

public class ToDoManager {
    private ArrayList<ToDo> todos = new ArrayList<>();

    public void addToDo(String name) {
        ToDo todo = new ToDo();
        todo.name = name;
        todo.isDone = false;
        todos.add(todo);
    }

    public void showToDo(){
        if (todos.isEmpty()) {
            System.out.println("タスクはありません");
        }else{
            for(int i = 0; i < todos.size(); i++){
                ToDo todo = todos.get(i);
                System.out.println((i + 1) + " : " + todo.name);
            }
        }
    }
}