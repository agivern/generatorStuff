package weapon.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.GeneralView;
import weapon.view.ManageWeaponView;

/**
 * The control of the management of weapon
 * 
 * @author taeghen
 * @version 1.0
 */
public class ManageWeaponControl implements ActionListener {

	private ManageWeaponView manageWeaponView;
	private GeneralView generalView;

	public ManageWeaponControl(GeneralView generalView) {
		this.generalView = generalView;
		this.manageWeaponView = new ManageWeaponView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.manageWeaponView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.manageWeaponView.addButton)
			this.changeView(5);// go create weapons
		else if (source == this.manageWeaponView.editButton)
			this.changeView(6);// go edit weapons
		else if (source == this.manageWeaponView.backButton)
			this.changeView(1);// go home of management
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}
}
