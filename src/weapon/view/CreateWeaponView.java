package weapon.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import weapon.control.CreateWeaponControl;

/**
 * The view for create a weapon and save in a file
 * 
 * @author taeghen
 * @version 1.0
 */
public class CreateWeaponView {
	public JButton addButton;
	public JButton cancelButton;
	private JTextField textFieldName;
	private JSpinner spinnerPrice;
	private JSpinner spinnerDamageMinimum;
	private JSpinner spinnerDamageMaximum;
	private JComboBox<String> comboBoxType;
	private JPanel panel;
	private JPanel panelDamage;
	private JPanel panelCenter;
	private JLabel labelTitle;
	private JLabel labelName;
	private JLabel labelDamage;
	private JLabel labelType;
	private JLabel labelPrice;
	private Vector<String> componentBox;

	private CreateWeaponControl createWeaponControl;

	public CreateWeaponView(CreateWeaponControl createWeaponControl) {
		this.createWeaponControl = createWeaponControl;
		this.init();
	}

	public JPanel getPanel() {
		return this.panel;
	}

	/**
	 * initialize the panel and attach reaction
	 */
	public void init() {
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panelDamage = new JPanel();
		this.panelDamage.setLayout(new GridLayout(1, 3));
		this.panelCenter = new JPanel();
		this.panelCenter.setLayout(new GridLayout(5, 2, 10, 10));

		this.labelTitle = new JLabel("Create your weapon", JLabel.CENTER);
		this.labelName = new JLabel("Weapon name");
		this.labelDamage = new JLabel("weapon damages");
		this.labelType = new JLabel("Weapon type");
		this.labelPrice = new JLabel("Weapon price");
		this.textFieldName = new JTextField();
		this.spinnerDamageMinimum = new JSpinner(new SpinnerNumberModel(1, 1,
				999, 1));
		this.spinnerDamageMaximum = new JSpinner(new SpinnerNumberModel(1, 1,
				999, 1));
		this.createComponentBox();
		this.comboBoxType = new JComboBox<String>(this.componentBox);
		this.comboBoxType.setMaximumRowCount(6);
		this.spinnerPrice = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 5));
		this.addButton = new JButton("Create");
		this.cancelButton = new JButton("Cancel");

		this.panelDamage.add(this.spinnerDamageMinimum);
		this.panelDamage.add(new JLabel("-", JLabel.CENTER));
		this.panelDamage.add(this.spinnerDamageMaximum);

		this.panelCenter.add(this.labelName);
		this.panelCenter.add(this.textFieldName);
		this.panelCenter.add(this.labelDamage);
		this.panelCenter.add(this.panelDamage);
		this.panelCenter.add(this.labelType);
		this.panelCenter.add(this.comboBoxType);
		this.panelCenter.add(this.labelPrice);
		this.panelCenter.add(this.spinnerPrice);
		this.panelCenter.add(this.addButton);
		this.panelCenter.add(this.cancelButton);

		this.panel.add(this.labelTitle, BorderLayout.NORTH);
		this.panel.add(panelCenter, BorderLayout.CENTER);

		this.addButton.addActionListener(this.createWeaponControl);
		this.cancelButton.addActionListener(this.createWeaponControl);
	}

	/**
	 * Create the component for the ComboBox with the name of weapon's type
	 */
	private void createComponentBox() {
		this.componentBox = new Vector<String>();
		this.componentBox.add("Sword");
		this.componentBox.add("Two-handed Sword");
		this.componentBox.add("Staff");
		this.componentBox.add("Axe");
		this.componentBox.add("Hammer");
		this.componentBox.add("Bow");
		this.componentBox.add("Crossbow");
		this.componentBox.add("Wand");
		this.componentBox.add("Spear");
		this.componentBox.add("Bottle");
	}

	public int getValueDamageMinimum() {
		return (Integer) this.spinnerDamageMinimum.getValue();
	}

	public int getValueDamageMaximum() {
		return (Integer) this.spinnerDamageMaximum.getValue();
	}

	public int getValuePrice() {
		return (Integer) this.spinnerPrice.getValue();
	}

	public int getValueType() {
		return (Integer) this.comboBoxType.getSelectedIndex();
	}

	public String getName() {
		return this.textFieldName.getText();
	}
}
