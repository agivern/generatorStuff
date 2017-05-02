package armor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.GeneralView;
import armor.view.ManageArmorView;

/**
 * The control of the armor management
 * 
 * @author taeghen
 * @version 1.0
 */
public class ManageArmorControl implements ActionListener {

	private ManageArmorView manageArmorView;
	private GeneralView generalView;

	public ManageArmorControl(GeneralView generalView) {
		this.generalView = generalView;
		this.manageArmorView = new ManageArmorView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.manageArmorView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.manageArmorView.addButton)
			this.changeView(7);// go create armors
		else if (source == this.manageArmorView.editButton)
			this.changeView(8);// go edit armors
		else if (source == this.manageArmorView.backButton)
			this.changeView(1);// go home of management
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}
}
