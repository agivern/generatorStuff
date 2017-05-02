package armor.view;

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

import armor.control.EditArmorControl;
import datas.Armor;
import datas.TableArmor;

/**
 * The view of the home with a menu
 * 
 * @author taeghen
 * @version 1.0
 */
public class EditArmorView {
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
	private JSpinner spinnerPhysiqueResistMin;
	private JSpinner spinnerPhysiqueResistMax;
	private JSpinner spinnerMagicResistMin;
	private JSpinner spinnerMagicResistMax;
	private JComboBox<String> comboBoxType;
	private Vector<String> componentBox;
	private EditArmorControl editArmorControl;
	private TableArmor tableArmor;

	private JScrollPane scrollPane;

	public EditArmorView(EditArmorControl editArmorControl) {
		this.editArmorControl = editArmorControl;
		this.tableArmor = new TableArmor();
		try {
			this.tableArmor.load();
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
		this.labelTitle = new JLabel("Edit your armors");
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
	 * @param physiqueResistMin
	 * @param physiqueResistMax
	 * @param magicResistMin
	 * @param magicResistMax
	 * @param type
	 */
	public void refresh(int posiScroll, int select, int priceMin, int priceMax,
			int physiqueResistMin, int physiqueResistMax, int magicResistMin,
			int magicResistMax, int type) {
		this.panel.removeAll();
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		try {
			this.tableArmor.load();
		} catch (IOException e) {
		}
		this.labelTitle = new JLabel("Edit your armors");
		this.labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.initFilter();
		this.initList();
		this.initButton();
		this.spinnerPriceMin.setValue(priceMin);
		this.spinnerPriceMax.setValue(priceMax);
		this.spinnerPhysiqueResistMin.setValue(physiqueResistMin);
		this.spinnerPhysiqueResistMax.setValue(physiqueResistMax);
		this.spinnerMagicResistMin.setValue(magicResistMin);
		this.spinnerMagicResistMax.setValue(magicResistMax);
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
		JLabel labelDamageMinimum = new JLabel("Physical resist minimum");
		JLabel labelDamageMaximum = new JLabel("Magical resist maximum");
		JLabel labelPrice = new JLabel("Price");
		JLabel labelType = new JLabel("Types");
		this.applyButton = new JButton("Apply");
		this.applyButton.addActionListener(this.editArmorControl);
		this.applyButton.setMaximumSize(new Dimension(100, 20));
		this.applyButton.setMinimumSize(new Dimension(100, 20));
		this.applyButton.setPreferredSize(new Dimension(100, 20));
		this.applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.spinnerPhysiqueResistMin = new JSpinner(new SpinnerNumberModel(1,
				1, 999, 1));
		this.spinnerPhysiqueResistMax = new JSpinner(new SpinnerNumberModel(
				999, 1, 999, 1));
		this.spinnerMagicResistMin = new JSpinner(new SpinnerNumberModel(1, 1,
				999, 1));
		this.spinnerMagicResistMax = new JSpinner(new SpinnerNumberModel(999,
				1, 999, 1));
		this.spinnerPriceMin = new JSpinner(new SpinnerNumberModel(1, 1, 9999,
				1));
		this.spinnerPriceMax = new JSpinner(new SpinnerNumberModel(9999, 1,
				9999, 1));

		this.createComponentBox();
		this.comboBoxType = new JComboBox<String>(this.componentBox);
		this.comboBoxType.setMaximumRowCount(4);

		panelDamageMinimum.add(this.spinnerPhysiqueResistMin);
		panelDamageMinimum.add(new JLabel(" ", JLabel.CENTER));
		panelDamageMinimum.add(this.spinnerPhysiqueResistMax);
		panelDamageMaximum.add(this.spinnerMagicResistMin);
		panelDamageMaximum.add(new JLabel(" ", JLabel.CENTER));
		panelDamageMaximum.add(this.spinnerMagicResistMax);
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

		JLabel labelList = new JLabel("Armors list");
		labelList.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.dlm = new DefaultListModel<String>();
		this.list = new JList<String>(dlm);
		this.scrollPane = new JScrollPane(this.list,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setBounds(new Rectangle(10, 10, 450, 80));
		this.list.addMouseListener(this.editArmorControl);
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

		this.modifyButton.addActionListener(this.editArmorControl);
		this.deleteButton.addActionListener(this.editArmorControl);
		this.cancelButton.addActionListener(this.editArmorControl);

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
		filterInfoPanel.setLayout(new GridLayout(5, 2, 10, 10));
		JLabel labelInformation = new JLabel("Information");
		if (dlm.getSize() > 0) {
			Armor armor = this.tableArmor.getArmor((String) this.list
					.getSelectedValue());

			labelInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
			JLabel labelName = new JLabel("Name");
			JLabel labelNameInfo = new JLabel(armor.getName());
			JLabel labelPhysicalResistance = new JLabel("Physical resistance");
			JLabel labelMagicalResistance = new JLabel("Magical resistance");
			JLabel labelPhysicalResistanceInfo = new JLabel(
					String.valueOf(armor.getPhysiqueResist()));
			JLabel labelMagicalResistanceInfo = new JLabel(String.valueOf(armor
					.getMagicResist()));
			JLabel labelPrice = new JLabel("Price");
			JLabel labelPriceInfo = new JLabel(String.valueOf(armor.getPrice()));
			JLabel labelType = new JLabel("Types");
			JLabel labelTypeInfo = new JLabel(
					(String) this.comboBoxType.getSelectedItem());
			filterInfoPanel.add(labelName);
			filterInfoPanel.add(labelNameInfo);
			filterInfoPanel.add(labelPhysicalResistance);
			filterInfoPanel.add(labelPhysicalResistanceInfo);
			filterInfoPanel.add(labelMagicalResistance);
			filterInfoPanel.add(labelMagicalResistanceInfo);
			filterInfoPanel.add(labelPrice);
			filterInfoPanel.add(labelPriceInfo);
			filterInfoPanel.add(labelType);
			filterInfoPanel.add(labelTypeInfo);

		} else {

			labelInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
			JLabel labelName = new JLabel("Name");
			JLabel labelNameInfo = new JLabel();
			JLabel labelPhysicalResistance = new JLabel("Physical resistance");
			JLabel labelMagicalResistance = new JLabel("Magical resistance");
			JLabel labelPhysicalResistanceInfo = new JLabel();
			JLabel labelMagicalResistanceInfo = new JLabel();
			JLabel labelPrice = new JLabel("Price");
			JLabel labelPriceInfo = new JLabel();
			JLabel labelType = new JLabel("Types");
			JLabel labelTypeInfo = new JLabel();
			filterInfoPanel.add(labelName);
			filterInfoPanel.add(labelNameInfo);
			filterInfoPanel.add(labelPhysicalResistance);
			filterInfoPanel.add(labelPhysicalResistanceInfo);
			filterInfoPanel.add(labelMagicalResistance);
			filterInfoPanel.add(labelMagicalResistanceInfo);
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
		this.componentBox.add("Helm");
		this.componentBox.add("Vest");
		this.componentBox.add("Gloves");
		this.componentBox.add("Leggings");
		this.componentBox.add("Boots");
	}

	/**
	 * Update the dlm for show the weapon
	 */
	private void updateList() {
		this.dlm.removeAllElements();
		for (Enumeration<String> e = this.tableArmor.getKey(); e
				.hasMoreElements();) {
			String nameArmor = e.nextElement();
			Armor armor = this.tableArmor.getArmor(nameArmor);
			if (armor.getMagicResist() >= this.getSpinnerMagicResistMin()
					&& armor.getMagicResist() <= this
							.getSpinnerMagicResistMax()
					&& armor.getType() == this.getValueType()
					&& armor.getPhysiqueResist() >= this
							.getSpinnerPhysiqueResistMin()
					&& armor.getPhysiqueResist() <= this
							.getSpinnerPhysiqueResistMax()
					&& armor.getPrice() >= this.getSpinnerPriceMin()
					&& armor.getPrice() <= this.getSpinnerPriceMax())
				this.dlm.addElement(nameArmor);
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

	public int getSpinnerPhysiqueResistMin() {
		return (Integer) spinnerPhysiqueResistMin.getValue();
	}

	public int getSpinnerPhysiqueResistMax() {
		return (Integer) spinnerPhysiqueResistMax.getValue();
	}

	public int getSpinnerMagicResistMin() {
		return (Integer) spinnerMagicResistMin.getValue();
	}

	public int getSpinnerMagicResistMax() {
		return (Integer) spinnerMagicResistMax.getValue();
	}

	public int getScrollPane() {
		return scrollPane.getVerticalScrollBar().getModel().getValue();
	}

	public int getValueType() {
		return (Integer) this.comboBoxType.getSelectedIndex();
	}
}
