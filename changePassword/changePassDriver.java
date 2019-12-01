package changePassword;

public class changePassDriver {
	public static void main(String[]args) {
	UserClass test = new UserClass(0, "bob", "pass", null);
	changePassGUI c = new changePassGUI(test);
	c.changePasswordUI();
	}
}
