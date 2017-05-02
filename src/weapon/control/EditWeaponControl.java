package weapon.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.GeneralView;
import weapon.view.EditWeaponView;
import datas.TableWeapon;
import datas.Weapon;

/**
 * The control of the home
 * 
 * @author taeghen
 * @version 1.0
 */
public class EditWeaponControl extends MouseAdapter implements ActionListener {
	private EditWeaponView editWeaponView;
	private GeneralView generalView;

	public EditWeaponControl(GeneralView generalView) {
		this.generalView = generalView;
		this.editWeaponView = new EditWeaponView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.editWeaponView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.editWeaponView.modifyButton)
			this.modify();// go to modify weapon page
		else if (source == this.editWeaponView.deleteButton)
			this.delete();// delete the weapon with confirmation and refresh the
							// page
		else if (source == this.editWeaponView.applyButton)
			this.editWeaponView.refresh(0, 0,
					this.editWeaponView.getSpinnerPriceMin(),
					this.editWeaponView.getSpinnerPriceMax(),
					this.editWeaponView.getSpinnerDamageMinimumMin(),
					this.editWeaponView.getSpinnerDamageMinimumMax(),
					this.editWeaponView.getSpinnerDamageMaximumMin(),
					this.editWeaponView.getSpinnerDamageMaximumMax(),
					this.editWeaponView.getValueType());
		// refresh the information

		else if (source == this.editWeaponView.cancelButton)
			this.changeView(2);// go management of weapons
	}

	public void mouseClicked(MouseEvent e) {
		this.editWeaponView.refresh(this.editWeaponView.getScrollPane(),
				this.editWeaponView.list.getSelectedIndex(),
				this.editWeaponView.getSpinnerPriceMin(),
				this.editWeaponView.getSpinnerPriceMax(),
				this.editWeaponView.getSpinnerDamageMinimumMin(),
				this.editWeaponView.getSpinnerDamageMinimumMax(),
				this.editWeaponView.getSpinnerDamageMaximumMin(),
				this.editWeaponView.getSpinnerDamageMaximumMax(),
				this.editWeaponView.getValueType());
	}

	/**
	 * Ask a confirmation for delete the element selected After the delete, the
	 * page is refreshed
	 */
	private void delete() {
		if (JOptionPane.showConfirmDialog(this.editWeaponView.getPanel(),
				"Do you want delete this weapon ?", "Delete confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
			try {
				String key = (String) this.editWeaponView.list
						.getSelectedValue();

				TableWeapon tableWeapon = new TableWeapon();
				tableWeapon.load();
				tableWeapon.deleteWeapon(key);
				tableWeapon.save();
				this.editWeaponView.refresh(0, 0,
						this.editWeaponView.getSpinnerPriceMin(),
						this.editWeaponView.getSpinnerPriceMax(),
						this.editWeaponView.getSpinnerDamageMinimumMin(),
						this.editWeaponView.getSpinnerDamageMinimumMax(),
						this.editWeaponView.getSpinnerDamageMaximumMin(),
						this.editWeaponView.getSpinnerDamageMaximumMax(),
						this.editWeaponView.getValueType());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The weapon cannot be deleted", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}

	private void modify() {
		try {
			String key = (String) this.editWeaponView.list.getSelectedValue();

			TableWeapon tableWeapon = new TableWeapon();
			tableWeapon.load();
			Weapon weapon = tableWeapon.getWeapon(key);
			this.generalView.setPanelModifyWeapon(weapon);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
