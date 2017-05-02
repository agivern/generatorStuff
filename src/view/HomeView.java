package view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.HomeControl;

/**
 * The view of the home with a menu
 * 
 * @author taeghen
 * @version 1.0
 */
public class HomeView {
	public JButton weaponButton;
	public JButton armorButton;
	private HomeControl homeControl;
	private JPanel panel;
	private JPanel panelBox;
	private JLabel label;

	public HomeView(HomeControl homeControl) {
		this.homeControl = homeControl;
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

		this.weaponButton = new JButton("Weapons management");
		this.armorButton = new JButton("Armors management");
		this.label = new JLabel("What do you want to manage?");
		this.buttonSize(this.weaponButton);
		this.buttonSize(this.armorButton);

		this.addCompoment(this.label);
		this.addCompoment(this.weaponButton);
		this.addCompoment(this.armorButton);

		this.weaponButton.addActionListener(this.homeControl);
		this.armorButton.addActionListener(this.homeControl);

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
