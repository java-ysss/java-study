package RPGTwo;

public class Slime extends Enemy{
    public Slime(){
        super("スライム", 80, 9, 10, 0.1, 0.06, 9);

        addSkill(new Mera());
    }
}
