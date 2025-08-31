// Represents a book in the library
public class Book {
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return borrowed; }

    public void borrow() { this.borrowed = true; }
    public void returned() { this.borrowed = false; }

    @Override
    public String toString() {
        return title + " by " + author + (borrowed ? " (Borrowed)" : " (Available)");
    }
}
