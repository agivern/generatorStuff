// ©Drinkor Juin 2012, document sous Licence CC-BY-NC-ND : http://creativecommons.org/licenses/by-nc-nd/2.0/fr/legalcode
package datas;

import java.io.Serializable;

/**
 * Armor are class which gives the structure of armors
 * 
 * @author drinkor
 * @version 1.0
 */
public class Armor extends Stuff implements Serializable {
	private static final long serialVersionUID = 119053274108642668L;
	private int magicResist;
	private int physiqueResist;

	public Armor(int magicResist, int physiqueResist, int type, int price,
			String name) {
		super(type, price, name);
		this.magicResist = magicResist;
		this.physiqueResist = physiqueResist;

	}

	public Armor() {
	}

	public int getMagicResist() {
		return magicResist;
	}

	public int getPhysiqueResist() {
		return physiqueResist;
	}

	public void setMagicResist(int magicResist) {
		this.magicResist = magicResist;
	}

	public void setPhysiqueResist(int physiqueResist) {
		this.physiqueResist = physiqueResist;
	}

}
