package book;

import java.util.ArrayList;

public class BookManager {
    public ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title,String author){
        Book book = new Book(title,author);
        books.add(book);
    }
    public void addManga(String title,String author,int volume){
        Manga manga = new Manga(title,author,volume);
        books.add(manga);
    }
    public void showBook(){
        for(int i = 0; i < books.size(); i++){
            String status = books.get(i).isDone() ? "既読" : "未読";

            if (books.get(i) instanceof Manga) {// Mangaかどうか確認
                Manga manga = (Manga) books.get(i);//Mangeとして取り出す
                System.out.println((i + 1) + " : " + books.get(i) + "[" + status + "]" + "[" + manga.getVolume() + "]");
            }else{
                System.out.println((i + 1) + " : " + books.get(i) + "[" + status + "]");
            }
            
        }
    }
    public void readBook(int index){
        if (index >= 0 && index < books.size()) {
            books.get(index).setDone(true);
        }else{
            System.out.println("その番号は存在しません");
        }
    }
    public void listBook(){
        for(int i = 0; i < books.size(); i++){
            System.out.println((i + 1 )+ ":" + books.get(i));
        }
    }
    public void removeBook(int index){
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            System.out.println("削除しました");
        }else{
            System.out.println("その番号の本は存在しません");
        }
    }

}
        
