package RPGTwo;

public class Mega extends Player {
    public Mega() {
        super("魔法使い", 110, 60, 10, 0.2, 0.1, 10);

        addSkill(new Mera());
        addSkill(new Meteor());
        addSkill(new Heal());
        addSkill(new HealAll());
        addSkill(new ManaHeal());
        addSkill(new Revive());
    }
}
