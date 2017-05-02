package weapon.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import weapon.control.ManageWeaponControl;

/**
 * The view for the management of weapons with a menu
 * 
 * @author taeghen
 * @version 1.0
 */
public class ManageWeaponView {
	public JButton addButton;
	public JButton editButton;
	public JButton backButton;
	private ManageWeaponControl manageWeaponControl;
	private JPanel panel;
	private JPanel panelBox;
	private JLabel label;

	public ManageWeaponView(ManageWeaponControl homeControl) {
		this.manageWeaponControl = homeControl;
		this.init();
	}

	public JPanel getPanel() {
		return this.panel;
	}

	/**
	 * initialize the panel and attach reaction
	 */
	private void init() {
		this.panel = new JPanel();
		this.panelBox = new JPanel();
		this.panelBox.setLayout(new BoxLayout(this.panelBox,
				BoxLayout.PAGE_AXIS));

		this.addButton = new JButton("Create weapons");
		this.editButton = new JButton("Edit weapons");
		this.backButton = new JButton("Back");
		this.label = new JLabel("What do you want to do?");
		this.buttonSize(this.addButton);
		this.buttonSize(this.editButton);
		this.buttonSize(this.backButton);

		this.addCompoment(this.label);
		this.addCompoment(this.addButton);
		this.addCompoment(this.editButton);
		this.addCompoment(this.backButton);

		this.addButton.addActionListener(this.manageWeaponControl);
		this.editButton.addActionListener(this.manageWeaponControl);
		this.backButton.addActionListener(this.manageWeaponControl);

		this.panel.add(this.panelBox);
	}

	/**
	 * Change the size of a button with x = 200 and y = 30
	 * 
	 * @param button
	 *            The button which the size must be changed
	 */
	private void buttonSize(JButton button) {
		button.setMaximumSize(new Dimension(200, 30));
		button.setMinimumSize(new Dimension(200, 30));
		button.setPreferredSize(new Dimension(200, 30));
	}

	/**
	 * Add a component to the panelBox and add after a glue with y = 10
	 * 
	 * @param component
	 *            The component which to be inserted
	 */
	private void addCompoment(Component component) {
		this.panelBox.add(component);
		this.panelBox.add(Box.createRigidArea(new Dimension(0, 10)));
	}
}