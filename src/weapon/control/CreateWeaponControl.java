package weapon.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.GeneralView;
import weapon.view.CreateWeaponView;
import datas.TableWeapon;
import datas.Weapon;

/**
 * The control of the management of weapon
 * 
 * @author taeghen
 * @version 1.0
 */
public class CreateWeaponControl implements ActionListener {

	private CreateWeaponView createWeaponView;
	private GeneralView generalView;

	public CreateWeaponControl(GeneralView generalView) {
		this.generalView = generalView;
		this.createWeaponView = new CreateWeaponView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.createWeaponView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.createWeaponView.addButton)
			this.createWeapon();// refresh the create weapons page
		else if (source == this.createWeaponView.cancelButton)
			this.changeView(2);// go management of weapons
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}

	/**
	 * Create a waepon and add him in the data weapon.out
	 */
	private void createWeapon() {
		Weapon weapon = new Weapon();
		if (this.createWeaponView.getValueDamageMinimum() <= this.createWeaponView
				.getValueDamageMaximum()
				&& this.createWeaponView.getName().length() > 0) {
			weapon.setName(this.createWeaponView.getName());
			weapon.setDamagesMinimum(this.createWeaponView
					.getValueDamageMinimum());
			weapon.setDamagesMaximum(this.createWeaponView
					.getValueDamageMaximum());
			weapon.setPrice(this.createWeaponView.getValuePrice());
			weapon.setType(this.createWeaponView.getValueType());
			try {
				TableWeapon tableWeapon = new TableWeapon();
				tableWeapon.load();
				tableWeapon.addWeapon(weapon);
				tableWeapon.save();
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon was added and saved", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				this.changeView(5);// refresh the panel
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon was not be saved", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon cannot be create", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this.generalView,
					"They are a error with the damages or the name.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
