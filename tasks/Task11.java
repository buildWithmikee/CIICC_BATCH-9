public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private double price;

    // Constructor
    public Book(String title, String author, int yearPublished, double price) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
    }

    // toString() to print book details
    public String toString() {
        return "Title: \"" + title + "\"\n"
             + "Author: \"" + author + "\"\n"
             + "Year Published: " + yearPublished + "\n"
             + "Price: $" + price;
    }
}

public class Task11 {
    public static void main(String[] args) {

        Book b1 = new Book("Java Programming", "MayJoan Rubio", 1999, 39.99);
        Book b2 = new Book("Python Basics", "Melvin Paculan", 2020, 29.99);
        Book b3 = new Book("C++ Essentials", "Miccah Ruela", 2019, 49.99);

        System.out.println("Book 1:");
        System.out.println(b1);
        System.out.println();

        System.out.println("Book 2:");
        System.out.println(b2);
        System.out.println();

        System.out.println("Book 3:");
        System.out.println(b3);
    }
}
