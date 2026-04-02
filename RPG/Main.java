package RPG;

public class Main {
    public static void main(String[] args){

        Player hero = new Player("勇者", 100, 20, 10);
        Enemy su = new Enemy("スライム", 50, 10);

        hero.attack(su);
        su.attack(hero);
        hero.heal();

        hero.showStatus();
        su.showStatus();
    }
}
