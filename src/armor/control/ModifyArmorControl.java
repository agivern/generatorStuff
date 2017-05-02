package armor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.GeneralView;
import armor.view.ModifyArmorView;
import datas.Armor;
import datas.TableArmor;

/**
 * The control of the armor management
 * 
 * @author taeghen
 * @version 1.0
 */
public class ModifyArmorControl implements ActionListener {

	private ModifyArmorView modifyArmorView;
	private GeneralView generalView;
	private Armor armor;

	public ModifyArmorControl(GeneralView generalView, Armor armor) {
		this.generalView = generalView;
		this.armor = armor;
		this.modifyArmorView = new ModifyArmorView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.modifyArmorView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.modifyArmorView.applyButton)
			this.createArmor();// refresh the create armors page
		else if (source == this.modifyArmorView.cancelButton)
			this.changeView(8);// go edit armor's page
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}

	/**
	 * Create a armor and add him in the data armorData.out
	 */
	private void createArmor() {
		Armor armor = new Armor();
		armor.setName(this.modifyArmorView.getName());
		armor.setMagicResist(this.modifyArmorView.getValueMagicResist());
		armor.setPhysiqueResist(this.modifyArmorView.getValuePhysiqueResist());
		armor.setPrice(this.modifyArmorView.getValuePrice());
		armor.setType(this.modifyArmorView.getValueType());
		try {
			TableArmor tableArmor = new TableArmor();
			tableArmor.load();
			tableArmor.putArmor(armor);
			tableArmor.save();
			JOptionPane.showMessageDialog(this.generalView,
					"The armor was modified and saved", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			this.changeView(8);// refresh the panel
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.generalView,
					"The armor was not be saved", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this.generalView,
					"The armor cannot be modify", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public Armor getArmor() {
		return this.armor;
	}
}
