/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;
import java.util.*;
import java.io.*;
import java.io.File;

public class NameSurferDataBase implements NameSurferConstants {

	// TODO: Add instance variables
	private HashMap<String, NameSurferEntry> nameSurferMap = new HashMap<String,NameSurferEntry>();
	
	/**
	 * Constructor: NameSurferDataBase(filename)
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		
		try {
			Scanner sc = new Scanner(new File(filename));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String name = line.split(" ")[0].toLowerCase();
				if (!nameSurferMap.containsKey(name)) {
					NameSurferEntry surfer = new NameSurferEntry(line);
					nameSurferMap.put(name, surfer);
				}
				
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}

	/**
	 * Public Method: findEntry(name)
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		
		if (nameSurferMap.get(name) == null) {
			return null;
		}
		return nameSurferMap.get(name);
	}
}

