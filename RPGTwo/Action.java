package RPGTwo;

import java.util.Arrays;

//誰が何をするか保存するクラス

public class Action {
    Character actor; //誰が
    String type; //何をする（攻撃、防御、スキル）
    Character target; // 誰に
    Skill skill; //スキルの場合どのスキルを使うのか

    public Action(Character actor,String type,Character target, Skill skill){

        this.actor = actor;
        this.type = type;
        this.target = target;
        this.skill = skill;
    }

    public void execute(Player[] party, Enemy[]  enemies,Boss boss,boolean bossAppeared){

        //死んでいる or 麻痺ならスキップ
        if (!actor.isAlive() || !actor.canAct()) {return;}

        switch (type) { //breakを書き忘れる心配ない形
            case "攻撃" -> executeAttack();
            case "防御" -> executeDefend();
            case "スキル" -> executeSkill(party,enemies,boss,bossAppeared);
            case "ボス攻撃" -> executeBossAttack(party);
        }
    }

    private void executeAttack(){
        if (target != null && target.isAlive()) {
            actor.attack(target);
        }
    }

    private void executeDefend(){
        ((Player) actor).defend();
        System.out.println(actor.name + "は防御した！");
    }

    private void executeSkill(Player[] party, Enemy[]  enemies,Boss boss,boolean bossAppeared){
        Player skillUser = (Player)actor;

        //// MP再チェック（自分より速い味方がMPを使っていた場合があるため）
        if (skillUser.mp < skill.mpCost) {
            System.out.println("MPが足りません");
            return;
        }

        skillUser.mp -= skill.mpCost;
        System.out.println(skillUser.name + "は" + skill.name + "を使った！");

        switch (skill.targetType) {
            case ALL_ENEMIES ->{
                if (!bossAppeared) {
                    for(Enemy enemy : enemies){
                        if (enemy.isAlive()) {skill.use(skillUser, enemy);}
                    }
                }else{
                    skill.use(skillUser, boss);
                }
            }
            case ALL_ALLIES -> {
                for(Player p : party){
                    if (p.isAlive()) {skill.use(skillUser, p);}
                }
            }
            case SELF -> skill.use(skillUser, null);
                
            default -> { // SINGLE_ENEMY / SINGLE_ALLY / DEAD_ALLY
                if (target != null) {skill.use(skillUser, target);}
            }
        }
    }

    private void executeBossAttack(Player[] party){
        ((Boss)actor).takeTurn(Arrays.asList(party));
    }
}
