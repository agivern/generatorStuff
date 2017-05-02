package armor.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.GeneralView;
import armor.view.CreateArmorView;
import datas.Armor;
import datas.TableArmor;

/**
 * The control of the armor management
 * 
 * @author taeghen
 * @version 1.0
 */
public class CreateArmorControl implements ActionListener {

	private CreateArmorView createArmorView;
	private GeneralView generalView;

	public CreateArmorControl(GeneralView generalView) {
		this.generalView = generalView;
		this.createArmorView = new CreateArmorView(this);
	}

	/**
	 * A getter for return the panel of the homView
	 * 
	 * @return JPanel return the panel's homeView
	 */
	public JPanel getPanel() {
		return this.createArmorView.getPanel();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.createArmorView.addButton)
			this.createArmor();// refresh the create armor's page
		else if (source == this.createArmorView.cancelButton)
			this.changeView(3);// go management of armor
	}

	private void changeView(int numView) {
		this.generalView.setPanel(numView);
	}

	/**
	 * Create a armor and add him in the data armor.out
	 */
	private void createArmor() {
		Armor armor = new Armor();
		if (this.createArmorView.getValueMagicResist() <= this.createArmorView
				.getValuePhysiqueResist()
				&& this.createArmorView.getName().length() > 0) {
			armor.setName(this.createArmorView.getName());
			armor.setMagicResist(this.createArmorView.getValueMagicResist());
			armor.setPhysiqueResist(this.createArmorView
					.getValuePhysiqueResist());
			armor.setPrice(this.createArmorView.getValuePrice());
			armor.setType(this.createArmorView.getValueType());
			try {
				TableArmor tableArmor = new TableArmor();
				tableArmor.load();
				tableArmor.addArmor(armor);
				tableArmor.save();
				JOptionPane.showMessageDialog(this.generalView,
						"The armor was added and saved", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				this.changeView(7);// refresh the panel
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The armor was not be saved", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this.generalView,
						"The armor cannot be create", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this.generalView,
					"They are a error with the name.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
