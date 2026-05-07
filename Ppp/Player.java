package Ppp;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player extends Character {

    List<Skill> skills = new ArrayList<>();


    public Player(String name, int hp, int mp, int attack) {
        super(name, hp, mp, attack);

    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void showSkill() {

        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + skills.get(i).getName() + "(  MP : " + skills.get(i).getMpCost() + " )");
        }
    }

    public Skill skillChoice(Scanner scanner) {

        while (true) {

            System.out.println("スキルを選んでください");
            showSkill();

            int choice = 0;
            try {
                choice = scanner.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("数字を入力してください");
                scanner.next();
                continue;
            }

            if (choice < 0 || choice >= skills.size()) {
                System.out.println("範囲内の数字を入力してください");
                continue;
            }

            return skills.get(choice);

        }

    }

    public boolean useSkill(Scanner scanner, Character target) {

        Skill skillNumber = skillChoice(scanner); // プレイヤーが選んだスキル
        boolean result = skillNumber.use(this, target);

        if (result) {
            System.out.println(this.name + "は" + skillNumber.getName() + "を使った！");
        }

        return result; // 成功 / 失敗の結果を呼び出し元に返す。

    }

}
