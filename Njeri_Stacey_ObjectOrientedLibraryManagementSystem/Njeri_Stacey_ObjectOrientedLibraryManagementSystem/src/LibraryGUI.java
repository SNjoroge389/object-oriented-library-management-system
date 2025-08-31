import javax.swing.*;
import java.awt.*;

// Handles the Graphical User Interface
public class LibraryGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LibraryGUI() {
        setTitle("Object-Oriented Library Management System");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);
        setVisible(true);

        // Login action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = Library.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Welcome " + username);
                user.showDashboard(this);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });
    }

    // Member dashboard
    public void showMemberDashboard(Member member) {
        JFrame frame = new JFrame("Member Dashboard - " + member.getUsername());
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton logoutButton = new JButton("Logout");

        borrowButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter book title to borrow:");
            for (Book b : Library.getBooks()) {
                if (b.getTitle().equalsIgnoreCase(title) && !b.isBorrowed()) {
                    b.borrow();
                    JOptionPane.showMessageDialog(frame, "Book borrowed successfully");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not available");
        });

        returnButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter book title to return:");
            for (Book b : Library.getBooks()) {
                if (b.getTitle().equalsIgnoreCase(title) && b.isBorrowed()) {
                    b.returned();
                    JOptionPane.showMessageDialog(frame, "Book returned successfully");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found or not borrowed");
        });

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "You have logged out");
            frame.dispose();
        });

        frame.setLayout(new GridLayout(3, 1));
        frame.add(borrowButton);
        frame.add(returnButton);
        frame.add(logoutButton);

        frame.setVisible(true);
    }

    // Admin (Librarian) dashboard
    public void showAdminDashboard(Librarian librarian) {
        JFrame frame = new JFrame("Admin Dashboard - " + librarian.getUsername());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JButton addBookButton = new JButton("Add Book");
        JButton addMemberButton = new JButton("Add Member");
        JButton viewBooksButton = new JButton("View All Books");
        JButton viewMembersButton = new JButton("View All Members");
        JButton logoutButton = new JButton("Logout");

        addBookButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter book title:");
            String author = JOptionPane.showInputDialog("Enter book author:");
            Library.addBook(title, author);
            JOptionPane.showMessageDialog(frame, "Book added successfully");
        });

        addMemberButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Enter new member username:");
            String password = JOptionPane.showInputDialog("Enter new member password:");
            Library.addMember(username, password);
            JOptionPane.showMessageDialog(frame, "Member added successfully");
        });

        viewBooksButton.addActionListener(e -> {
            StringBuilder list = new StringBuilder("Books:\n");
            for (Book b : Library.getBooks()) {
                list.append(b).append("\n");
            }
            JOptionPane.showMessageDialog(frame, list.toString());
        });

        viewMembersButton.addActionListener(e -> {
            StringBuilder list = new StringBuilder("Members:\n");
            for (User u : Library.getUsers()) {
                if (u instanceof Member) {
                    list.append(u.getUsername()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(frame, list.toString());
        });

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "You have logged out");
            frame.dispose();
        });

        frame.setLayout(new GridLayout(5, 1));
        frame.add(addBookButton);
        frame.add(addMemberButton);
        frame.add(viewBooksButton);
        frame.add(viewMembersButton);
        frame.add(logoutButton);

        frame.setVisible(true);
    }
}
