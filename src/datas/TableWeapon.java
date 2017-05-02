// ©Drinkor Juin 2012, document sous Licence CC-BY-NC-ND : http://creativecommons.org/licenses/by-nc-nd/2.0/fr/legalcode
package datas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The class with the tables of weapon
 * 
 * @author drinkor
 * @version 1.0
 */
public class TableWeapon {
	Hashtable<String, Weapon> tableWeapon;
	private static String WEAPON_FILE = "weaponDatas.out";

	public TableWeapon() {
		this.tableWeapon = new Hashtable<String, Weapon>();
	}

	/**
	 * Method for add a weapon in the table
	 * 
	 * @param weapon
	 *            the weapon who has add in the table
	 * @throws IllegalArgumentException
	 *             exception if adding failed
	 */
	public void addWeapon(Weapon weapon) throws IllegalArgumentException {
		if (weapon != null) {
			if (!this.tableWeapon.containsKey(weapon.getName()))
				try {
					this.tableWeapon.put(weapon.getName(), weapon);
				} catch (NullPointerException e) {
					throw new IllegalArgumentException();
				}
			else
				throw new IllegalArgumentException();
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Method for modify a weapon in the table
	 * 
	 * @param weapon
	 *            the weapon who has modified in the table
	 * @throws IllegalArgumentException
	 *             exception if the modification failed
	 */
	public void putWeapon(Weapon weapon) throws IllegalArgumentException {
		if (weapon != null) {
			try {
				this.tableWeapon.put(weapon.getName(), weapon);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Delete a weapon of the table
	 * 
	 * @param weaponName
	 *            The name of the weapon which must be deleted
	 * @throws IllegalArgumentException
	 *             exception if deleting failed
	 */
	public void deleteWeapon(String weaponName) throws IllegalArgumentException {
		try {
			this.tableWeapon.remove(weaponName);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Getter to the weapon in the table
	 * 
	 * @param weaponNamethe
	 *            weapon's name of the weapon return
	 * @return Weapon return the weapon referenced by the name enter
	 * @throws IllegalArgumentException
	 *             exception if weapon not exist
	 */
	public Weapon getWeapon(String weaponName) throws IllegalArgumentException {
		Weapon weapon = new Weapon();
		try {
			weapon = this.tableWeapon.get(weaponName);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		return weapon;
	}

	/**
	 * Save the weapon table in weaponDatas.out
	 * 
	 * @throws IOException
	 *             exception if saving failed
	 */
	public void save() throws IOException {
		try {
			FileOutputStream file = new FileOutputStream(WEAPON_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(this.tableWeapon);
			file.close();
		} catch (Exception e) {
			throw new IOException();
		}
	}

	/**
	 * Load weaponDatas.out in the weapon table
	 * 
	 * @throws IOException
	 *             exception if saving failed
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException {
		File file = new File(WEAPON_FILE);
		if (!file.exists())
			file.createNewFile();
		try {
			FileInputStream fis = new FileInputStream(WEAPON_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object readObject = ois.readObject();
			this.tableWeapon = (Hashtable<String, Weapon>) readObject;
			fis.close();
		} catch (ClassNotFoundException e) {
			throw new IOException();
		} catch (IOException e) {
			// File empty, but do nothing
		}
	}

	/**
	 * return a enumeration of key in the tableWeapon
	 * 
	 * @return Enumeration<String> return the enumeration of key
	 */
	public Enumeration<String> getKey() {
		return this.tableWeapon.keys();
	}
}
