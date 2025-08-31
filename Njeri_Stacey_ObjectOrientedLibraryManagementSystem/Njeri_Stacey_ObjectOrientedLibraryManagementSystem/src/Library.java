import java.util.ArrayList;
import java.util.HashMap;

// Handles library data and operations
public class Library {
    private static ArrayList<Book> books = new ArrayList<>();
    private static HashMap<String, User> users = new HashMap<>();

    // Initialize with sample data
    static {
        users.put("stacey", new Member("stacey", "1234"));
        users.put("john", new Member("john", "1111"));
        users.put("mary", new Member("mary", "2222"));
        users.put("librarian", new Librarian("librarian", "admin"));

        books.add(new Book("Java Programming", "James Gosling"));
        books.add(new Book("Data Structures", "Mark Weiss"));
        books.add(new Book("Database Systems", "C. J. Date"));
        books.add(new Book("Operating Systems", "Silberschatz"));
    }

    public static User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public static void addMember(String username, String password) {
        users.put(username, new Member(username, password));
    }

    public static ArrayList<Book> getBooks() { return books; }

    public static ArrayList<User> getUsers() { return new ArrayList<>(users.values()); }
}
