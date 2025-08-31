// Represents a library member
public class Member extends User {

    public Member(String username, String password) {
        super(username, password);
    }

    @Override
    public void showDashboard(LibraryGUI gui) {
        gui.showMemberDashboard(this);
    }
}
