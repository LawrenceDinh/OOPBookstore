
/**Class to update and maintain the choice selected 
 * by the Dashboard. Used to determine which UI to launch.
 * Choice is saved as int. 
 * 
 * @author LUAT DINH
 *
 */

public class DashboardChoiceSelected {

	private int choice;
	
	public DashboardChoiceSelected(int i) {
		choice = i;
	}
	
	public void setChoice(int i) {
		choice = i;
	}
	
	public int getChoice () {
		return choice;
	}
}
