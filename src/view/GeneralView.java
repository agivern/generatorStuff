package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import weapon.control.CreateWeaponControl;
import weapon.control.EditWeaponControl;
import weapon.control.ManageWeaponControl;
import weapon.control.ModifyWeaponControl;
import armor.control.CreateArmorControl;
import armor.control.EditArmorControl;
import armor.control.ManageArmorControl;
import armor.control.ModifyArmorControl;
import control.HomeControl;
import datas.Armor;
import datas.Weapon;

/**
 * The launcher of the application
 * 
 * @author taeghen
 * @version 1.0
 */
public class GeneralView extends JFrame {
	private static final long serialVersionUID = -5292919705170864782L;
	private JPanel panel;

	public GeneralView() {
		this.panel = new JPanel();
		this.setPanel(1);
		this.setTitle("Datas managers");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Change the Panel who has show
	 * 
	 * @param newPanel
	 *            the num of the new panel
	 */
	public void setPanel(int newPanel) {
		this.remove(this.panel);
		switch (newPanel) {
		// the home page
		case 1:
			this.panel.removeAll();
			HomeControl homeControl = new HomeControl(this);
			this.panel.add(homeControl.getPanel());
			this.setResizable(true);
			this.setSize(300, 200);
			this.setResizable(false);
			break;
		// the managements of weapons page
		case 2:
			this.panel.removeAll();
			ManageWeaponControl manageWeaponControl = new ManageWeaponControl(
					this);
			this.panel.add(manageWeaponControl.getPanel());
			this.setResizable(true);
			this.setSize(300, 200);
			this.setResizable(false);
			break;

		// the managements of armors page
		case 3:
			this.panel.removeAll();
			ManageArmorControl manageArmorControl = new ManageArmorControl(this);
			this.panel.add(manageArmorControl.getPanel());
			this.setResizable(true);
			this.setSize(300, 200);
			this.setResizable(false);
			break;

		// Create weapons's page
		case 5:
			this.panel.removeAll();
			CreateWeaponControl createWeaponControl = new CreateWeaponControl(
					this);
			this.panel.add(createWeaponControl.getPanel());
			this.setResizable(true);
			this.setSize(350, 240);
			this.setResizable(false);
			break;

		// Edit weapons's page
		case 6:
			this.panel.removeAll();
			EditWeaponControl editWeaponControl = new EditWeaponControl(this);
			this.panel.add(editWeaponControl.getPanel());
			this.setResizable(true);
			this.setSize(700, 425);
			this.setResizable(false);
			break;

		// Create armors's page
		case 7:
			this.panel.removeAll();
			CreateArmorControl createArmorControl = new CreateArmorControl(this);
			this.panel.add(createArmorControl.getPanel());
			this.setResizable(true);
			this.setSize(350, 280);
			this.setResizable(false);
			break;

		// Edit armors's page
		case 8:
			this.panel.removeAll();
			EditArmorControl editArmorControl = new EditArmorControl(this);
			this.panel.add(editArmorControl.getPanel());
			this.setResizable(true);
			this.setSize(700, 450);
			this.setResizable(false);
			break;
		}
		this.add(this.panel);
		this.repaint();
		this.validate();
	}

	public void setPanelModifyWeapon(Weapon armor) {
		// modify weapon page
		this.panel.removeAll();
		ModifyWeaponControl modifyWeaponControl = new ModifyWeaponControl(this,
				armor);
		this.panel.add(modifyWeaponControl.getPanel());
		this.setResizable(true);
		this.setSize(350, 240);
		this.setResizable(false);
	}

	public void setPanelModifyArmor(Armor armor) {
		// modify armor page
		this.panel.removeAll();
		ModifyArmorControl modifyArmorControl = new ModifyArmorControl(this,
				armor);
		this.panel.add(modifyArmorControl.getPanel());
		this.setResizable(true);
		this.setSize(350, 280);
		this.setResizable(false);
	}
}
