package book;
//本のデータを入れて置くクラス
public class Book {
    private String name;
    private boolean isDone;
//フィールドは箱

    public Book(String name) {//これはコンストラクタ
        this.name = name;
        this.isDone = false;
    }

    public String getName() { //getはprivateだから値を取り出すための窓口
        return name;
    }

    public boolean isDone() {//is系のgetはisのままでいい慣習的に
        return isDone;
    }

    public void setDone(boolean done) {//後からと外から値を変えたいときにsetを使う、
        isDone = done;
    }

}
