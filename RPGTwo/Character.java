package RPGTwo;

import java.util.ArrayList;

public abstract class Character {

    String name;
    int hp;
    int maxHp;
    boolean isDefending;

    ArrayList<StatusEffect> statusEffects = new ArrayList<>();
    // <>の中身はクラスとかの型

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
    }

    public void takeDamage(int damage) {

        if (isDefending) {
            damage /= 2;
            isDefending = false;
            System.out.println(this.name + "は防御の構えをしている");
        }

        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }

        System.out.println(this.name + "は" + damage + "のダメージ！");
    }

    public boolean isAlive() {// hpが０より上なら生きている
        return hp > 0;
    }

    public void applyStatusEffects() {//そのキャラにかかってる状態異常を処理するメソッド

        for (int i = 0; i < statusEffects.size(); i++) {
            StatusEffect effect = statusEffects.get(i);

            effect.apply(this);
            //thisはこのメソッドを使っているキャラ


            if (effect.isFinished()) {//その状態異常が終わったか確認
                System.out.println(name + "の" + effect.name + "が解除された！");
                statusEffects.remove(i); //リストから削除
                i--; //これがないとだめ
            }
        }
    }

    public boolean hasStatus(String name){ //毒があるか、あればtrue
        
        for(StatusEffect effect : statusEffects){
            if (effect.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
    //　↓　行動できるか判定メソッド
    public boolean canAct(){
        for(StatusEffect effect : statusEffects){//リストの中を全部チェックするということ
            if (effect.name.equals("麻痺")) {
                
                //50%で行動不能
                if (Math.random() < 0.5) {
                    System.out.println(name + "は麻痺して動けない!");
                    return false;
                }
            }
        }
        return true;
    }
}

//equalsは文字列の中身が同じかどうかを比較するメソッド。
// ❌ これはダメ（中身ではなくアドレスを比較してしまう）
//if (effect.name == name)

// ✅ これが正しい（中身を比較する）
//if (effect.name.equals(name))
