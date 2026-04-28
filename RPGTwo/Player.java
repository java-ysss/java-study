package RPGTwo;

import java.util.InputMismatchException;
import java.util.Scanner;

import RPG.practis;

//import java.util.ArrayList;
//import java.util.Scanner;

public class Player extends Character {

    public Player(String name, int hp, int mp, int attack, double critRate, double dodgeRate, int speed) {
        super(name, hp, mp, attack, dodgeRate, critRate, speed);

        this.maxHp = hp;
        this.maxMp = mp;
        this.critRate = critRate;
        this.dodgeRate = dodgeRate;
    }

    public void defend() {
        isDefending = true;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public Action slectAction(Scanner scanner, Enemy[] enemies, Boss boss, boolean bossAppeared,Player[] party) {

        System.out.println("○" + this.name + "のコマンド");
        System.out.println("【1: 攻撃  2: 防御  3:スキル】");

        int select = 0;

        try {
            select = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("数字を入力してください");
            scanner.next();
            return null; // continueの代わり、nullを返して「無効」を伝える。
        }
        if (select <= 0 || select >= 4) {
            System.out.println("1 ~ 3 で入力してください");
            return null;
        }
        switch (select) {
            case 1 -> {
                return selectAttack(scanner, enemies, boss, bossAppeared);
            }
            case 2 -> {
                return new Action(this, "防御", null, null);
            }
            case 3 -> {
                return selectSkill(scanner, enemies, boss, bossAppeared,party);
            }
        }
        return null;
    }
    // Main では continue で「もう一度入力させる」ができた
    // でも別クラスのメソッドの中では continue は使えない
    // なので null を返して「無効な入力だった」と伝える
    // Main 側で null チェックする

    private Action selectAttack(Scanner scanner, Enemy[] enemies, Boss boss, boolean bossAppeared) {
        // ↑
        // selectAction() の中からしか呼ばれない
        // 外から直接呼ばれる必要がないので private
        System.out.println("攻撃する敵を選んでください");

        // ボスがまだ出ていない場合
        if (!bossAppeared) {

            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i].isAlive()) {
                    System.out.println((i + 1) + ":" + enemies[i].name);
                }
            }

            int target = scanner.nextInt() - 1;

            // 敵が生きているかどうか
            if (target < 0 || target >= enemies.length || !enemies[target].isAlive()) {
                System.out.println("その敵は選べない！");
                return null;
            }
            return new Action(this, "攻撃", enemies[target], null);
            // Action というクラスは「設計図
            // new Action(...) で
            // 「この設計図を元に実体を1つ作る
            // 作った実体を return で返す

        } else {
            // ボスしかいない場合
            return new Action(this, "攻撃", boss, null);
        }
    }

    public Action selectSkill(Scanner scanner,Enemy[] enemies, Boss boss, boolean bossAppeared,Player[] party) {
        // スキル一覧を表示
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ":" + skills.get(i).name
                    + "( MP :" + skills.get(i).mpCost + " )");
        }
        int skillChoice = scanner.nextInt() - 1;

        if (skillChoice < 0 || skillChoice >= skills.size()) {
            System.out.println("無効な選択");
            return null;
        }

        Skill skill = skills.get(skillChoice);

        if (mp < skill.mpCost) {
            System.out.println("MPが足りません！");
            return null;
        }

        // 対象を選ぶ
        Character skillTarget = selectSkillTarget(scanner,skill, enemies, boss, bossAppeared, party);

        return new Action(this, "スキル", skillTarget, skill);
    }

    public Character selectSkillTarget(Scanner scanner, Skill skill, Enemy[] enemies, Boss boss, boolean bossAppeared,
            Player[] party) {

        if (skill.targetType == TargetType.SINGLE_ENEMY) {
            if (!bossAppeared) {
                System.out.println("攻撃する相手を選んでください");

                for (int i = 0; i < enemies.length; i++) {
                    if (enemies[i].isAlive()) {
                        System.out.println((i + 1) + ":" + enemies[i].name);
                    }
                }
                int target = scanner.nextInt() - 1;

                if (target < 0 || target >= enemies.length || !enemies[target].isAlive()) {
                    System.out.println("その敵は選べない！");
                    return null;
                }
                return enemies[target];
            } else {
                return boss;
            }
        } else if (skill.targetType == TargetType.SINGLE_ALLY) {

            System.out.println("対象を選んでください");
            
            for (int i = 0; i < party.length; i++) {
                if (party[i].isAlive()) {
                    System.out.println((i + 1) + ":" + party[i].name + " HP : " + party[i].hp);
                }
            }

            int allyTarget = scanner.nextInt() - 1;

            if (allyTarget < 0 || allyTarget >= party.length || !party[allyTarget].isAlive()) {
                System.out.println("無効な対象です");
                return null;
            }
            return party[allyTarget];

        } else if (skill.targetType == TargetType.DEAD_ALLY) {
            System.out.println("蘇生対象を選んでください");
            boolean hasDeadAlly = false; // 死んでいる味方が一人でもいるかどうか
                                         // 最初はいない!
            for (int i = 0; i < party.length; i++) {
                if (!party[i].isAlive()) {
                    System.out.println((i + 1) + ":" + party[i].name);
                    hasDeadAlly = true; // 死んでいる人を見つけたら「いる」に変える
                }
            }
            if (!hasDeadAlly) {
                System.out.println("蘇生対象がいません");
                return null;
            }

            int deadTarget = scanner.nextInt() - 1;

            if (deadTarget < 0 || deadTarget >= party.length || party[deadTarget].isAlive()) {
                System.out.println("無効な対象です");
                return null;
            }
            return party[deadTarget];
        }

        //ALL_ENEMIES / ALL_ALLIES / SELF はターゲット選択不要
        return null;
    }
}
