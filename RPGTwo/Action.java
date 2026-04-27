package RPGTwo;

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
}
