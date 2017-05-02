// ©Drinkor October 2012, document sous Licence CC-BY-NC-ND : http://creativecommons.org/licenses/by-nc-nd/2.0/fr/legalcode
package datas;

import java.io.Serializable;

/**
 * Personnage are class which gives the structure of monster and Player
 * 
 * @author drinkor
 * @version 1.0
 */
public class Personnage implements Serializable {
	private static final long serialVersionUID = -80016465650169158L;
	String name;
	int life;
	int mana;
	int resistancePhysical;
	int resistanceMagical;
	int resistanceAlcool;
	int damageMax;
	int damageMin;

	Personnage() {
	}

	Personnage(String name, int life, int mana, int resistancePhysical,
			int resistanceMagical, int resistanceAlcool, int damageMax,
			int damageMin) {
		this.name = name;
		this.life = life;
		this.mana = mana;
		this.resistancePhysical = resistancePhysical;
		this.resistanceMagical = resistanceMagical;
		this.resistanceAlcool = resistanceAlcool;
		this.damageMax = damageMax;
		this.damageMin = damageMin;
	}

	public String getName() {
		return this.name;
	}

	public int getLife() {
		return this.life;
	}

	public int getMana() {
		return this.mana;
	}

	public int getResistancePhysical() {
		return this.resistancePhysical;
	}

	public int getResistanceMagical() {
		return this.resistanceMagical;
	}

	public int getResistanceAlcool() {
		return this.resistanceAlcool;
	}

	public int getDamageMax() {
		return this.damageMax;
	}

	public int getDamageMin() {
		return this.damageMin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setResistancePhysical(int resistancePhysical) {
		this.resistancePhysical = resistancePhysical;
	}

	public void setResistanceMagical(int resistanceMagical) {
		this.resistanceMagical = resistanceMagical;
	}

	public void setResistanceAlcool(int resistanceAlcool) {
		this.resistanceAlcool = resistanceAlcool;
	}

	public void setDamageMax(int damageMax) {
		this.damageMax = damageMax;
	}

	public void setDamageMin(int damageMin) {
		this.damageMin = damageMin;
	}
}
