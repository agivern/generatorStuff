package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.GeneralView;
import view.HomeView;

/**
 * The control of the home
 * 
 * @author taeghen
 * @version 1.0
 */
public class HomeControl implements ActionListener {
	private HomeView homeView;
	private GeneralView generalView;

	public HomeControl(GeneralView generalView) {
		this.generalView = generalView;
		this.homeView = new HomeView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.homeView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.homeView.weaponButton)
			this.changeView(2);// go management of weapons
		else if (source == this.homeView.armorButton)
			this.changeView(3);// go management of armors
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}
}
