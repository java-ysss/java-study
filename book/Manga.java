package book;

//継承とインターフェイス
//機能をもらうとこのルールは必ず持てという契約書的な

public class Manga extends Book implements BookGenreable {
    private String author; // 作者
    private int volume; // 巻数

    public String getGenreName() {
        return "[漫画]" + getVolume() + "巻  著者 : " + getAuthor();
    }

    public Manga(String name, int volume, String author) {
        super(name);
        this.volume = volume;
        this.author = author;
    }

    public int getVolume() {
        return volume;
    }

    public String getAuthor() {
        return author;
    }
}
