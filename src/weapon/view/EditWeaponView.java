package weapon.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import weapon.control.EditWeaponControl;
import datas.TableWeapon;
import datas.Weapon;

/**
 * The view of the home with a menu
 * 
 * @author taeghen
 * @version 1.0
 */
public class EditWeaponView {
	public JButton modifyButton;
	public JButton deleteButton;
	public JButton cancelButton;
	public JButton applyButton;
	public JList<String> list;
	public DefaultListModel<String> dlm;

	private JPanel panel;
	private JPanel panelFilter;
	private JPanel panelInfo;
	private JPanel panelList;
	private JPanel panelButton;

	private JLabel labelTitle;
	private JLabel labelFilter;

	private JSpinner spinnerPriceMin;
	private JSpinner spinnerPriceMax;
	private JSpinner spinnerDamageMinimumMin;
	private JSpinner spinnerDamageMinimumMax;
	private JSpinner spinnerDamageMaximumMin;
	private JSpinner spinnerDamageMaximumMax;
	private JComboBox<String> comboBoxType;
	private Vector<String> componentBox;
	private EditWeaponControl editWeaponControl;
	private TableWeapon tableWeapon;

	private JScrollPane scrollPane;

	public EditWeaponView(EditWeaponControl editWeaponControl) {
		this.editWeaponControl = editWeaponControl;
		this.tableWeapon = new TableWeapon();
		try {
			this.tableWeapon.load();
		} catch (IOException e) {
		}
		this.panel = new JPanel();
		this.init();
	}

	public JPanel getPanel() {
		return this.panel;
	}

	/**
	 * initialize the panel and attach reaction
	 */
	private void init() {
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		this.labelTitle = new JLabel("Edit your weapons");
		this.labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.initFilter();
		this.initList();
		this.list.setSelectedIndex(0);
		this.initInfo();
		this.initButton();

		JPanel panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.LINE_AXIS));

		panelWest.add(this.panelFilter);
		panelWest.add(Box.createRigidArea(new Dimension(0, 5)));
		panelWest.add(this.panelInfo);
		panelCenter.add(panelWest);
		panelCenter.add(Box.createRigidArea(new Dimension(10, 0)));
		this.panelList.setPreferredSize(new Dimension(300, 150));
		panelCenter.add(this.panelList);

		this.panel.add(this.labelTitle);
		this.panel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panel.add(panelCenter);
		this.panel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panel.add(this.panelButton);

	}

	/**
	 * Refresh the page
	 * 
	 * @param posiScroll
	 * @param select
	 * @param priceMin
	 * @param priceMax
	 * @param damageMinMin
	 * @param damageMinMax
	 * @param damageMaxMin
	 * @param damageMaxMax
	 * @param type
	 */
	public void refresh(int posiScroll, int select, int priceMin, int priceMax,
			int damageMinMin, int damageMinMax, int damageMaxMin,
			int damageMaxMax, int type) {
		this.panel.removeAll();
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		try {
			this.tableWeapon.load();
		} catch (IOException e) {
		}
		this.labelTitle = new JLabel("Edit your weapons");
		this.labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.initFilter();
		this.initList();
		this.initButton();
		this.spinnerPriceMin.setValue(priceMin);
		this.spinnerPriceMax.setValue(priceMax);
		this.spinnerDamageMinimumMin.setValue(damageMinMin);
		this.spinnerDamageMinimumMax.setValue(damageMinMax);
		this.spinnerDamageMaximumMin.setValue(damageMaxMin);
		this.spinnerDamageMaximumMax.setValue(damageMaxMax);
		this.comboBoxType.setSelectedIndex(type);
		this.updateList();
		this.list.setSelectedIndex(select);
		this.scrollPane.getVerticalScrollBar().getModel().setValue(posiScroll);
		this.initInfo();

		JPanel panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.LINE_AXIS));

		panelWest.add(this.panelFilter);
		panelWest.add(Box.createRigidArea(new Dimension(0, 5)));
		panelWest.add(this.panelInfo);
		panelCenter.add(panelWest);
		panelCenter.add(Box.createRigidArea(new Dimension(10, 0)));
		this.panelList.setPreferredSize(new Dimension(300, 150));
		panelCenter.add(this.panelList);

		this.panel.add(this.labelTitle);
		this.panel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panel.add(panelCenter);
		this.panel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panel.add(this.panelButton);
		this.panel.repaint();
		this.panel.validate();
	}

	/**
	 * initialize the panelFilter and attach reaction this panel is private, and
	 * he uses for create the final panel
	 */
	private void initFilter() {
		this.panelFilter = new JPanel();
		this.panelFilter.setLayout(new BoxLayout(this.panelFilter,
				BoxLayout.PAGE_AXIS));
		JPanel filterInfoPanel = new JPanel();
		filterInfoPanel.setLayout(new GridLayout(4, 2, 10, 10));
		JPanel panelDamageMinimum = new JPanel();
		panelDamageMinimum.setLayout(new BoxLayout(panelDamageMinimum,
				BoxLayout.LINE_AXIS));
		JPanel panelDamageMaximum = new JPanel();
		panelDamageMaximum.setLayout(new BoxLayout(panelDamageMaximum,
				BoxLayout.LINE_AXIS));
		JPanel panelPrice = new JPanel();
		panelPrice.setLayout(new BoxLayout(panelPrice, BoxLayout.LINE_AXIS));

		this.labelFilter = new JLabel("Filter");
		this.labelFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel labelDamageMinimum = new JLabel("Damages minimum");
		JLabel labelDamageMaximum = new JLabel("Damages maximum");
		JLabel labelPrice = new JLabel("Price");
		JLabel labelType = new JLabel("Types");
		this.applyButton = new JButton("Apply");
		this.applyButton.addActionListener(this.editWeaponControl);
		this.applyButton.setMaximumSize(new Dimension(100, 20));
		this.applyButton.setMinimumSize(new Dimension(100, 20));
		this.applyButton.setPreferredSize(new Dimension(100, 20));
		this.applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.spinnerDamageMinimumMin = new JSpinner(new SpinnerNumberModel(1,
				1, 999, 1));
		this.spinnerDamageMinimumMax = new JSpinner(new SpinnerNumberModel(999,
				1, 999, 1));
		this.spinnerDamageMaximumMin = new JSpinner(new SpinnerNumberModel(1,
				1, 999, 1));
		this.spinnerDamageMaximumMax = new JSpinner(new SpinnerNumberModel(999,
				1, 999, 1));
		this.spinnerPriceMin = new JSpinner(new SpinnerNumberModel(1, 1, 9999,
				1));
		this.spinnerPriceMax = new JSpinner(new SpinnerNumberModel(9999, 1,
				9999, 1));

		this.createComponentBox();
		this.comboBoxType = new JComboBox<String>(this.componentBox);
		this.comboBoxType.setMaximumRowCount(6);

		panelDamageMinimum.add(this.spinnerDamageMinimumMin);
		panelDamageMinimum.add(new JLabel(" ", JLabel.CENTER));
		panelDamageMinimum.add(this.spinnerDamageMinimumMax);
		panelDamageMaximum.add(this.spinnerDamageMaximumMin);
		panelDamageMaximum.add(new JLabel(" ", JLabel.CENTER));
		panelDamageMaximum.add(this.spinnerDamageMaximumMax);
		panelPrice.add(this.spinnerPriceMin);
		panelPrice.add(new JLabel(" ", JLabel.CENTER));
		panelPrice.add(this.spinnerPriceMax);

		filterInfoPanel.add(labelDamageMinimum);
		filterInfoPanel.add(panelDamageMinimum);
		filterInfoPanel.add(labelDamageMaximum);
		filterInfoPanel.add(panelDamageMaximum);
		filterInfoPanel.add(labelPrice);
		filterInfoPanel.add(panelPrice);
		filterInfoPanel.add(labelType);
		filterInfoPanel.add(this.comboBoxType);
		filterInfoPanel.setSize(150, 150);

		this.panelFilter.add(this.labelFilter);
		this.panelFilter.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panelFilter.add(filterInfoPanel);
		this.panelFilter.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panelFilter.add(this.applyButton);
		this.panelFilter.add(Box.createRigidArea(new Dimension(0, 3)));
		this.panelFilter.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/**
	 * initialize the panelList and attach reaction this panel is private, and
	 * he uses for create the final panel
	 */
	private void initList() {
		this.panelList = new JPanel();
		this.panelList.setLayout(new BoxLayout(this.panelList,
				BoxLayout.PAGE_AXIS));

		JLabel labelList = new JLabel("Weapons list");
		labelList.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.dlm = new DefaultListModel<String>();
		this.list = new JList<String>(dlm);
		this.scrollPane = new JScrollPane(this.list,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setBounds(new Rectangle(10, 10, 450, 80));
		this.list.addMouseListener(this.editWeaponControl);
		this.updateList();
		this.panelList.add(labelList);
		this.panelList.add(this.scrollPane);
	}

	/**
	 * initialize the panelButton and attach reaction this panel is private, and
	 * he uses for create the final panel
	 */
	private void initButton() {
		this.panelButton = new JPanel();
		this.panelButton.setLayout(new BoxLayout(this.panelButton,
				BoxLayout.LINE_AXIS));
		this.modifyButton = new JButton("Modify");
		this.buttonSize(this.modifyButton);
		this.deleteButton = new JButton("Delete");
		this.buttonSize(this.deleteButton);
		this.cancelButton = new JButton("Cancel");
		this.buttonSize(this.cancelButton);

		this.modifyButton.addActionListener(this.editWeaponControl);
		this.deleteButton.addActionListener(this.editWeaponControl);
		this.cancelButton.addActionListener(this.editWeaponControl);

		this.panelButton.add(this.modifyButton);
		this.panelButton.add(Box.createRigidArea(new Dimension(15, 0)));
		this.panelButton.add(this.deleteButton);
		this.panelButton.add(Box.createRigidArea(new Dimension(15, 0)));
		this.panelButton.add(this.cancelButton);
	}

	/**
	 * initialize the panelInfo and attach reaction this panel is private, and
	 * he uses for create the final panel
	 */
	private void initInfo() {
		this.panelInfo = new JPanel();
		this.panelInfo.setLayout(new BoxLayout(this.panelInfo,
				BoxLayout.PAGE_AXIS));
		JPanel filterInfoPanel = new JPanel();
		filterInfoPanel.setLayout(new GridLayout(4, 2, 10, 10));
		JLabel labelInformation = new JLabel("Information");
		if (dlm.getSize() > 0) {
			Weapon weapon = this.tableWeapon.getWeapon((String) this.list
					.getSelectedValue());

			labelInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
			JLabel labelName = new JLabel("Name");
			JLabel labelNameInfo = new JLabel(weapon.getName());
			JLabel labelDamage = new JLabel("Damages");
			JLabel labelDamageInfo = new JLabel(String.valueOf(weapon
					.getDamagesMinimum())
					+ " - "
					+ String.valueOf(weapon.getDamagesMaximum()));
			JLabel labelPrice = new JLabel("Price");
			JLabel labelPriceInfo = new JLabel(
					String.valueOf(weapon.getPrice()));
			JLabel labelType = new JLabel("Types");
			JLabel labelTypeInfo = new JLabel(
					(String) this.comboBoxType.getSelectedItem());
			filterInfoPanel.add(labelName);
			filterInfoPanel.add(labelNameInfo);
			filterInfoPanel.add(labelDamage);
			filterInfoPanel.add(labelDamageInfo);
			filterInfoPanel.add(labelPrice);
			filterInfoPanel.add(labelPriceInfo);
			filterInfoPanel.add(labelType);
			filterInfoPanel.add(labelTypeInfo);

		} else {

			labelInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
			JLabel labelName = new JLabel("Name");
			JLabel labelNameInfo = new JLabel();
			JLabel labelDamage = new JLabel("Damages");
			JLabel labelDamageInfo = new JLabel();
			JLabel labelPrice = new JLabel("Price");
			JLabel labelPriceInfo = new JLabel();
			JLabel labelType = new JLabel("Types");
			JLabel labelTypeInfo = new JLabel();
			filterInfoPanel.add(labelName);
			filterInfoPanel.add(labelNameInfo);
			filterInfoPanel.add(labelDamage);
			filterInfoPanel.add(labelDamageInfo);
			filterInfoPanel.add(labelPrice);
			filterInfoPanel.add(labelPriceInfo);
			filterInfoPanel.add(labelType);
			filterInfoPanel.add(labelTypeInfo);
		}

		this.panelInfo.add(labelInformation);
		this.panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
		this.panelInfo.add(filterInfoPanel);
		this.panelInfo.add(Box.createRigidArea(new Dimension(0, 3)));
		this.panelInfo.setBorder(BorderFactory.createLineBorder(Color.black));
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

	/**
	 * Update the dlm for show the weapon
	 */
	private void updateList() {
		this.dlm.removeAllElements();
		for (Enumeration<String> e = this.tableWeapon.getKey(); e
				.hasMoreElements();) {
			String nameWeapon = e.nextElement();
			Weapon weapon = this.tableWeapon.getWeapon(nameWeapon);
			if (weapon.getDamagesMinimum() >= this.getSpinnerDamageMinimumMin()
					&& weapon.getDamagesMinimum() <= this
							.getSpinnerDamageMinimumMax()
					&& weapon.getType() == this.getValueType()
					&& weapon.getDamagesMaximum() >= this
							.getSpinnerDamageMaximumMin()
					&& weapon.getDamagesMaximum() <= this
							.getSpinnerDamageMaximumMax()
					&& weapon.getPrice() >= this.getSpinnerPriceMin()
					&& weapon.getPrice() <= this.getSpinnerPriceMax())
				this.dlm.addElement(nameWeapon);
		}
	}

	/**
	 * Change the size of a button with x = 100 and y = 30
	 * 
	 * @param button
	 *            The button which the size must be changed
	 */
	private void buttonSize(JButton button) {
		button.setMaximumSize(new Dimension(150, 30));
		button.setMinimumSize(new Dimension(150, 30));
		button.setPreferredSize(new Dimension(150, 30));
	}

	public int getSpinnerPriceMin() {
		return (Integer) spinnerPriceMin.getValue();
	}

	public int getSpinnerPriceMax() {
		return (Integer) spinnerPriceMax.getValue();
	}

	public int getSpinnerDamageMinimumMin() {
		return (Integer) spinnerDamageMinimumMin.getValue();
	}

	public int getSpinnerDamageMinimumMax() {
		return (Integer) spinnerDamageMinimumMax.getValue();
	}

	public int getSpinnerDamageMaximumMin() {
		return (Integer) spinnerDamageMaximumMin.getValue();
	}

	public int getSpinnerDamageMaximumMax() {
		return (Integer) spinnerDamageMaximumMax.getValue();
	}

	public int getScrollPane() {
		return scrollPane.getVerticalScrollBar().getModel().getValue();
	}

	public int getValueType() {
		return (Integer) this.comboBoxType.getSelectedIndex();
	}
}
