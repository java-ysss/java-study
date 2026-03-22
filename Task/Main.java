package Task;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        ToDoManager manager = new ToDoManager();// 管理クラス
        Scanner scanner = new Scanner(System.in);
        manager.loadToDo();

        while (true) {
            // メニュー表示
            System.out.println("1 : タスク管理");
            System.out.println("2 : タスク表示");
            System.out.println("3 : 完了チェック");
            System.out.println("4 : タスク削除");
            System.out.println("5 : 終了");

            // 入力を受け取る
            int select = scanner.nextInt();

            // 選択によって処理を分ける
            if (select == 1) {
                System.out.println("タスク名を入力してください");
                String name = scanner.next();
                Priority priority = selectPriority(scanner);
                manager.addToDo(name, priority);
            }
            if (select == 2) {
                manager.showToDo();
            }
            if (select == 3) {
                System.out.print("何番のタスクを完了にしますか?");
                int index = scanner.nextInt();
                manager.completeToDo(index);
            }
            if (select == 4) {
                System.out.println("何番のタスクを削除しますか?");
                int index = scanner.nextInt();
                manager.deleteToDo(index);
            }
            if (select == 5) {
                manager.saveToDo();
                System.out.println("終了します");
                break;
            }
        }
    }

    private static Priority selectPriority(Scanner scanner) {
        System.out.println("優先度を選んでください");
        System.out.println("1 : 高");
        System.out.println("2 : 中");
        System.out.println("3 : 低");
        int input = scanner.nextInt();

        return switch (input) {
                case 1 -> Priority.HIGH;
                case 2 -> Priority.MEDIUM;
                case 3 -> Priority.LOW;
                default -> {
                    System.out.println("無効な入力です");
                    yield Priority.MEDIUM;
                }
            
            };
    }
}
