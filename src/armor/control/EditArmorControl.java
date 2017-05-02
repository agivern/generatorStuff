package armor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.GeneralView;
import armor.view.EditArmorView;
import datas.Armor;
import datas.TableArmor;

/**
 * The control of the home
 * 
 * @author taeghen
 * @version 1.0
 */
public class EditArmorControl extends MouseAdapter implements ActionListener {
	private EditArmorView editArmorView;
	private GeneralView generalView;

	public EditArmorControl(GeneralView generalView) {
		this.generalView = generalView;
		this.editArmorView = new EditArmorView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.editArmorView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.editArmorView.modifyButton)
			this.modify();// go to modify armor page
		else if (source == this.editArmorView.deleteButton)
			this.delete();// delete the armor with confirmation and refresh the
							// page
		else if (source == this.editArmorView.applyButton)
			this.editArmorView.refresh(0, 0,
					this.editArmorView.getSpinnerPriceMin(),
					this.editArmorView.getSpinnerPriceMax(),
					this.editArmorView.getSpinnerPhysiqueResistMin(),
					this.editArmorView.getSpinnerPhysiqueResistMax(),
					this.editArmorView.getSpinnerMagicResistMin(),
					this.editArmorView.getSpinnerMagicResistMax(),
					this.editArmorView.getValueType());
		// refresh the information

		else if (source == this.editArmorView.cancelButton)
			this.changeView(3);// go management of armor
	}

	public void mouseClicked(MouseEvent e) {
		this.editArmorView.refresh(this.editArmorView.getScrollPane(),
				this.editArmorView.list.getSelectedIndex(),
				this.editArmorView.getSpinnerPriceMin(),
				this.editArmorView.getSpinnerPriceMax(),
				this.editArmorView.getSpinnerPhysiqueResistMin(),
				this.editArmorView.getSpinnerPhysiqueResistMax(),
				this.editArmorView.getSpinnerMagicResistMin(),
				this.editArmorView.getSpinnerMagicResistMax(),
				this.editArmorView.getValueType());
	}

	/**
	 * Ask a confirmation for delete the element selected After the delete, the
	 * page is refreshed
	 */
	private void delete() {
		if (JOptionPane.showConfirmDialog(this.editArmorView.getPanel(),
				"Do you want delete this armor ?", "Delete confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
			try {
				String key = (String) this.editArmorView.list
						.getSelectedValue();

				TableArmor tableArmor = new TableArmor();
				tableArmor.load();
				tableArmor.deleteArmor(key);
				tableArmor.save();
				this.editArmorView.refresh(0, 0,
						this.editArmorView.getSpinnerPriceMin(),
						this.editArmorView.getSpinnerPriceMax(),
						this.editArmorView.getSpinnerPhysiqueResistMin(),
						this.editArmorView.getSpinnerPhysiqueResistMax(),
						this.editArmorView.getSpinnerMagicResistMin(),
						this.editArmorView.getSpinnerMagicResistMax(),
						this.editArmorView.getValueType());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The armor cannot be deleted", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}

	private void modify() {
		try {
			String key = (String) this.editArmorView.list.getSelectedValue();

			TableArmor tableWeapon = new TableArmor();
			tableWeapon.load();
			Armor armor = tableWeapon.getArmor(key);
			this.generalView.setPanelModifyArmor(armor);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
