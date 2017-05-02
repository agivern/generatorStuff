package weapon.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.GeneralView;
import weapon.view.ModifyWeaponView;
import datas.TableWeapon;
import datas.Weapon;

/**
 * The control of the management of weapon
 * 
 * @author taeghen
 * @version 1.0
 */
public class ModifyWeaponControl implements ActionListener {

	private ModifyWeaponView modifyWeaponView;
	private GeneralView generalView;
	private Weapon weapon;

	public ModifyWeaponControl(GeneralView generalView, Weapon weapon) {
		this.generalView = generalView;
		this.weapon = weapon;
		this.modifyWeaponView = new ModifyWeaponView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.modifyWeaponView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.modifyWeaponView.applyButton)
			this.createWeapon();// refresh the create weapons page
		else if (source == this.modifyWeaponView.cancelButton)
			this.changeView(6);// go management of weapons
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}

	/**
	 * Create a weapon and add him in the data weapon.out
	 */
	private void createWeapon() {
		Weapon weapon = new Weapon();
		if (this.modifyWeaponView.getValueDamageMinimum() <= this.modifyWeaponView
				.getValueDamageMaximum()) {
			weapon.setName(this.modifyWeaponView.getName());
			weapon.setDamagesMinimum(this.modifyWeaponView
					.getValueDamageMinimum());
			weapon.setDamagesMaximum(this.modifyWeaponView
					.getValueDamageMaximum());
			weapon.setPrice(this.modifyWeaponView.getValuePrice());
			weapon.setType(this.modifyWeaponView.getValueType());
			try {
				TableWeapon tableWeapon = new TableWeapon();
				tableWeapon.load();
				tableWeapon.putWeapon(weapon);
				tableWeapon.save();
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon was modified and saved", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				this.changeView(6);// refresh the panel
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon was not be saved", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon cannot be modify", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this.generalView,
					"They are a error with the damages or the name.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Weapon getWeapon() {
		return this.weapon;
	}
}
