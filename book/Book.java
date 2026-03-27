package book;
//本のデータを入れて置くクラス
public class Book {
    private String title;
    private String author;
    private boolean isDone;
//フィールドは箱

    public Book(String title,String author) {//これはコンストラクタ
        this.title = title;
        this.author = author;
        this.isDone = false;
    }

    public String getTitle() { //getはprivateだから値を取り出すための窓口
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public boolean isDone() {//is系のgetはisのままでいい慣習的に
        return isDone;
    }

    public void setDone(boolean done) {//後からと外から値を変えたいときにsetを使う、
        isDone = done;
    }

    
    @Override
    public String toString(){
        return title + "(著:" + author + ")";
    }

}
