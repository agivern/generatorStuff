// ©Drinkor Juin 2012, document sous Licence CC-BY-NC-ND : http://creativecommons.org/licenses/by-nc-nd/2.0/fr/legalcode
package datas;

import java.io.Serializable;

/**
 * Weapon are class which gives the structure of weapons
 * 
 * @author drinkor
 * @version 1.0
 */
public class Weapon extends Stuff implements Serializable {
	private static final long serialVersionUID = 119053274108642668L;
	private int damagesMinimum;
	private int damagesMaximum;

	public Weapon(int damagesMinimum, int damagesMaximum, int type, int price,
			String name) {
		super(type, price, name);
		this.damagesMinimum = damagesMinimum;
		this.damagesMaximum = damagesMaximum;

	}

	public Weapon() {
	}

	public int getDamagesMinimum() {
		return damagesMinimum;
	}

	public int getDamagesMaximum() {
		return damagesMaximum;
	}

	public void setDamagesMinimum(int damagesMinimum) {
		this.damagesMinimum = damagesMinimum;
	}

	public void setDamagesMaximum(int damagesMaximum) {
		this.damagesMaximum = damagesMaximum;
	}

}
