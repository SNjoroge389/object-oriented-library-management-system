// Represents a librarian with admin rights
public class Librarian extends User {

    public Librarian(String username, String password) {
        super(username, password);
    }

    @Override
    public void showDashboard(LibraryGUI gui) {
        gui.showAdminDashboard(this);
    }
}
