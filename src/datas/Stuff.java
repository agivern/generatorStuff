// ©Drinkor Juin 2012, document sous Licence CC-BY-NC-ND : http://creativecommons.org/licenses/by-nc-nd/2.0/fr/legalcode
package datas;

import java.io.Serializable;

/**
 * The abstract class we give the structur of the general stuff
 * 
 * @author drinkor
 * @version 1.0
 */
public abstract class Stuff implements Serializable {
	private static final long serialVersionUID = -2545874595907473776L;
	private int type;
	private int price;
	private String name;

	public Stuff(int type, int price, String name) {
		this.type = type;
		this.price = price;
		this.name = name;
	}

	public Stuff() {
	}

	public int getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}
}
