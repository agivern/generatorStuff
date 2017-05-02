package armor.view;

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

import armor.control.CreateArmorControl;

/**
 * The view for modify a armor and save in a file
 * 
 * @author taeghen
 * @version 1.0
 */
public class CreateArmorView {
	public JButton addButton;
	public JButton cancelButton;
	private JTextField textFieldArmorName;
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

	private CreateArmorControl createArmorControl;

	public CreateArmorView(CreateArmorControl createArmorControl) {
		this.createArmorControl = createArmorControl;
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

		this.labelTitle = new JLabel("Modify your armor", JLabel.CENTER);
		this.labelName = new JLabel("Armor name");
		this.labelType = new JLabel("Armor type");
		this.labelPrice = new JLabel("Armor price");
		this.textFieldArmorName = new JTextField();
		this.spinnerMagicResist = new JSpinner(new SpinnerNumberModel(1, 1,
				999, 1));
		this.spinnerPhysiqueResist = new JSpinner(new SpinnerNumberModel(1, 1,
				999, 1));
		this.createComponentBox();
		this.comboBoxType = new JComboBox<String>(this.componentBox);
		this.comboBoxType.setMaximumRowCount(5);

		this.spinnerPrice = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 5));
		this.addButton = new JButton("Add");
		this.cancelButton = new JButton("Cancel");

		this.panelCenter.add(this.labelName);
		this.panelCenter.add(this.textFieldArmorName);
		this.panelCenter.add(new JLabel("Magical resistance"));
		this.panelCenter.add(this.spinnerMagicResist);
		this.panelCenter.add(new JLabel("Physical resistance"));
		this.panelCenter.add(this.spinnerPhysiqueResist);
		this.panelCenter.add(this.labelType);
		this.panelCenter.add(this.comboBoxType);
		this.panelCenter.add(this.labelPrice);
		this.panelCenter.add(this.spinnerPrice);
		this.panelCenter.add(this.addButton);
		this.panelCenter.add(this.cancelButton);

		this.panel.add(this.labelTitle, BorderLayout.NORTH);
		this.panel.add(panelCenter, BorderLayout.CENTER);

		this.addButton.addActionListener(this.createArmorControl);
		this.cancelButton.addActionListener(this.createArmorControl);
	}

	/**
	 * Create the component for the ComboBox with the name of armor's type
	 */
	private void createComponentBox() {
		this.componentBox = new Vector<String>();
		this.componentBox.add("Helm");
		this.componentBox.add("");
		this.componentBox.add("");
		this.componentBox.add("");
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
		return this.textFieldArmorName.getText();
	}
}
