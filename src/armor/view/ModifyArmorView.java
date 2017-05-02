package armor.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import armor.control.ModifyArmorControl;
import datas.Armor;

/**
 * The view for modify a armor and save in a file
 * 
 * @author taeghen
 * @version 1.0
 */
public class ModifyArmorView {
	public JButton applyButton;
	public JButton cancelButton;
	private JLabel labelArmorName;
	private JSpinner spinnerPrice;
	private JSpinner spinnerMagicResist;
	private JSpinner spinnerPhysiqueResist;
	private JComboBox<String> comboBoxType;
	private JPanel panel;
	private JPanel panelCenter;
	private JLabel labelTitle;
	private JLabel labelName;
	private JLabel labelType;
	private JLabel labelPrice;
	private Vector<String> componentBox;

	private ModifyArmorControl modifyArmorControl;

	public ModifyArmorView(ModifyArmorControl modifyArmorControl) {
		this.modifyArmorControl = modifyArmorControl;
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
		this.panelCenter = new JPanel();
		this.panelCenter.setLayout(new GridLayout(6, 2, 10, 10));
		Armor armor = this.modifyArmorControl.getArmor();

		this.labelTitle = new JLabel("Modify your armor", JLabel.CENTER);
		this.labelName = new JLabel("Armor name");
		this.labelType = new JLabel("Armor type");
		this.labelPrice = new JLabel("Armor price");
		this.labelArmorName = new JLabel(armor.getName());
		this.spinnerMagicResist = new JSpinner(new SpinnerNumberModel(
				armor.getMagicResist(), 1, 999, 1));
		this.spinnerPhysiqueResist = new JSpinner(new SpinnerNumberModel(
				armor.getPhysiqueResist(), 1, 999, 1));
		this.createComponentBox();
		this.comboBoxType = new JComboBox<String>(this.componentBox);
		this.comboBoxType.setMaximumRowCount(5);
		this.comboBoxType.setSelectedIndex(armor.getType());

		this.spinnerPrice = new JSpinner(new SpinnerNumberModel(
				armor.getPrice(), 1, 9999, 5));
		this.applyButton = new JButton("Apply");
		this.cancelButton = new JButton("Cancel");

		this.panelCenter.add(this.labelName);
		this.panelCenter.add(this.labelArmorName);
		this.panelCenter.add(new JLabel("Magical resistance"));
		this.panelCenter.add(this.spinnerMagicResist);
		this.panelCenter.add(new JLabel("Physical resistance"));
		this.panelCenter.add(this.spinnerPhysiqueResist);
		this.panelCenter.add(this.labelType);
		this.panelCenter.add(this.comboBoxType);
		this.panelCenter.add(this.labelPrice);
		this.panelCenter.add(this.spinnerPrice);
		this.panelCenter.add(this.applyButton);
		this.panelCenter.add(this.cancelButton);

		this.panel.add(this.labelTitle, BorderLayout.NORTH);
		this.panel.add(panelCenter, BorderLayout.CENTER);

		this.applyButton.addActionListener(this.modifyArmorControl);
		this.cancelButton.addActionListener(this.modifyArmorControl);
	}

	/**
	 * Create the component for the ComboBox with the name of armor's type
	 */
	private void createComponentBox() {
		this.componentBox = new Vector<String>();
		this.componentBox.add("Helm");
		this.componentBox.add("Vest");
		this.componentBox.add("Gloves");
		this.componentBox.add("Leggings");
		this.componentBox.add("Boots");
	}

	public int getValueMagicResist() {
		return (Integer) this.spinnerMagicResist.getValue();
	}

	public int getValuePhysiqueResist() {
		return (Integer) this.spinnerPhysiqueResist.getValue();
	}

	public int getValuePrice() {
		return (Integer) this.spinnerPrice.getValue();
	}

	public int getValueType() {
		return (Integer) this.comboBoxType.getSelectedIndex();
	}

	public String getName() {
		return this.labelArmorName.getText();
	}
}
