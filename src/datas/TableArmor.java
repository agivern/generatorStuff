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
 * The class with the tables of armor
 * 
 * @author drinkor
 * @version 1.0
 */
public class TableArmor {
	Hashtable<String, Armor> tableArmor;
	private static String ARMOR_FILE = "armorDatas.out";

	public TableArmor() {
		this.tableArmor = new Hashtable<String, Armor>();
	}

	/**
	 * Method for add a armor in the table
	 * 
	 * @param armor
	 *            the armor who has add in the table
	 * @throws IllegalArgumentException
	 *             exception if adding failed
	 */
	public void addArmor(Armor armor) throws IllegalArgumentException {
		if (armor != null) {
			if (!this.tableArmor.containsKey(armor.getName()))
				try {
					this.tableArmor.put(armor.getName(), armor);
				} catch (NullPointerException e) {
					throw new IllegalArgumentException();
				}
			else
				throw new IllegalArgumentException();
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Method for modify a armor in the table
	 * 
	 * @param armor
	 *            the armor who has modified in the table
	 * @throws IllegalArgumentException
	 *             exception if the modification failed
	 */
	public void putArmor(Armor armor) {
		if (armor != null) {
			try {
				this.tableArmor.put(armor.getName(), armor);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Delete a armor of the table
	 * 
	 * @param armorName
	 *            The name of the armor which must be deleted
	 * @throws IllegalArgumentException
	 *             exception if deleting failed
	 */
	public void deleteArmor(String armorName) throws IllegalArgumentException {
		try {
			this.tableArmor.remove(armorName);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Getter to the armor in the table
	 * 
	 * @param armorName
	 *            the armor's name of the armor return
	 * @return Armor return the armor referenced by the name enter
	 * @throws IllegalArgumentException
	 *             exception if armor not exist
	 */
	public Armor getArmor(String armorName) throws IllegalArgumentException {
		Armor armor = new Armor();
		try {
			armor = this.tableArmor.get(armorName);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		return armor;
	}

	/**
	 * Save the armor table in armorDatas.out
	 * 
	 * @throws IOException
	 *             exception if saving failed
	 */
	public void save() throws IOException {
		try {
			FileOutputStream file = new FileOutputStream(ARMOR_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(this.tableArmor);
			file.close();
		} catch (Exception e) {
			throw new IOException();
		}
	}

	/**
	 * Load armorDatas.out in the armor table
	 * 
	 * @throws IOException
	 *             exception if saving failed
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException {
		File file = new File(ARMOR_FILE);
		if (!file.exists())
			file.createNewFile();
		try {
			FileInputStream fis = new FileInputStream(ARMOR_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object readObject = ois.readObject();
			this.tableArmor = (Hashtable<String, Armor>) readObject;
			fis.close();
		} catch (ClassNotFoundException e) {
			throw new IOException();
		} catch (IOException e) {
			// File empty, but do nothing
		}
	}

	/**
	 * return a enumeration of key in the tableArmor
	 * 
	 * @return Enumeration<String> return the enumeration of key
	 */
	public Enumeration<String> getKey() {
		return this.tableArmor.keys();
	}
}
