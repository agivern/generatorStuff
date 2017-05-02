// ©Drinkor October 2012, document sous Licence CC-BY-NC-ND : http://creativecommons.org/licenses/by-nc-nd/2.0/fr/legalcode
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
 * The class with the tables of monster
 * 
 * @author drinkor
 * @version 1.0
 */
public class TableMonster {
	Hashtable<String, Personnage> tableMonster;
	private static String MONSTER_FILE = "monsterDatas.out";

	public TableMonster() {
		this.tableMonster = new Hashtable<String, Personnage>();
	}

	/**
	 * Method for add a monster in the table
	 * 
	 * @param monster
	 *            the monster who has add in the table
	 * @throws IllegalArgumentException
	 *             exception if adding failed
	 */
	public void addMonster(Personnage monster) throws IllegalArgumentException {
		if (monster != null) {
			if (!this.tableMonster.containsKey(monster.getName()))
				try {
					this.tableMonster.put(monster.getName(), monster);
				} catch (NullPointerException e) {
					throw new IllegalArgumentException();
				}
			else
				throw new IllegalArgumentException();
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Method for modify a monster in the table
	 * 
	 * @param monster
	 *            the monster who has modified in the table
	 * @throws IllegalArgumentException
	 *             exception if the modification failed
	 */
	public void putMonster(Personnage monster) throws IllegalArgumentException {
		if (monster != null) {
			try {
				this.tableMonster.put(monster.getName(), monster);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException();
			}
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Delete a monster of the table
	 * 
	 * @param monsterName
	 *            The name of the monster which must be deleted
	 * @throws IllegalArgumentException
	 *             exception if deleting failed
	 */
	public void deleteMonster(String monsterName) throws IllegalArgumentException {
		try {
			this.tableMonster.remove(monsterName);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Getter to the monster in the table
	 * 
	 * @param monsterName
	 *            the monster's name of the monster return
	 * @return monster return the monster referenced by the name enter
	 * @throws IllegalArgumentException
	 *             exception if monster not exist
	 */
	public Personnage getMonster(String monsterName) throws IllegalArgumentException {
		Personnage monster = new Personnage();
		try {
			monster = this.tableMonster.get(monsterName);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		return monster;
	}

	/**
	 * Save the monster table in monsterDatas.out
	 * 
	 * @throws IOException
	 *             exception if saving failed
	 */
	public void save() throws IOException {
		try {
			FileOutputStream file = new FileOutputStream(MONSTER_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(this.tableMonster);
			file.close();
		} catch (Exception e) {
			throw new IOException();
		}
	}

	/**
	 * Load monsterDatas.out in the monster table
	 * 
	 * @throws IOException
	 *             exception if saving failed
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException {
		File file = new File(MONSTER_FILE);
		if (!file.exists())
			file.createNewFile();
		try {
			FileInputStream fis = new FileInputStream(MONSTER_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object readObject = ois.readObject();
			this.tableMonster = (Hashtable<String, Personnage>) readObject;
			fis.close();
		} catch (ClassNotFoundException e) {
			throw new IOException();
		} catch (IOException e) {
			// File empty, but do nothing
		}
	}

	/**
	 * return a enumeration of key in the tableMonster
	 * 
	 * @return Enumeration<String> return the enumeration of key
	 */
	public Enumeration<String> getKey() {
		return this.tableMonster.keys();
	}
}