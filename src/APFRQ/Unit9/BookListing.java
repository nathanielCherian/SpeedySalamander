package APFRQ.Unit9;

public class BookListing {

    private Book book;
    private double price;

    public BookListing(Book book, double price){
        this.book = book;
        this.price = price;
    }

    public void printDescription(){
        book.printBookInfo();
        System.out.println("price: $"+ price);
    }

}
