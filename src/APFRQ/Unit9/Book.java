package APFRQ.Unit9;

import java.util.ArrayList;

public class Book {

    private String title;
    private String author;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public void printBookInfo(){
        System.out.println((title + " written by " + author));
    }

    public class PictureBook extends Book{

        private String illustrator;

        public PictureBook(String title, String author, String illustrator){
            super(title, author);
            this.illustrator = illustrator;
        }

        public void printBookInfo(){
            System.out.println((title + " written by " + author + " drawn by " + illustrator));
        }

    }

    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<>();
        library.add(new Book("title", "author"));
        //library.add((new PictureBook("title1", "author1", "illustrator")));

    }

}
