package book;

//継承とインターフェイス
//機能をもらうとこのルールは必ず持てという契約書的な

public class Manga extends Book {
    private int volume; // 巻数
    //author のフィールドはいらない Bookの方にあるから

    //public String getGenreName() {
        //return "[漫画]" + getVolume() + "巻  著者 : " + getAuthor();
    //}特に使ってない。

    public Manga(String title,String author,int volume) {
        super(title,author);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    
}

