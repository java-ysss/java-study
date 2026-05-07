package RPGTwo;

public class Hero extends Player{
    public Hero(){
        super("勇者", 150, 30, 20, 0.2, 0.1, 15);
        addSkill(new FireSlash());
        addSkill(new ParalyzeSkill());
        addSkill(new PoisonSkill());
        // HeroはPlayerを継承しているのでaddSkillがそのまま使える
    }
}
