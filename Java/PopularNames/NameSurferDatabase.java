package namesurferr;
import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurferDatabase implements NameSurferConstants {
	/* Constructor: NameSurferDataBase(filename) */
	/**
	* Creates a new NameSurferDataBase and initializes it using the
	* data in the specified file. The constructor throws an error
	* exception if the requested file does not exist or if an error
	* occurs as the file is being read.
	*/
	public NameSurferDatabase(String filename) {

	try{
	BufferedReader rd = new BufferedReader(new FileReader(filename));

	while(true){
	String line = rd.readLine();
	if(line == null) break;
	NameSurferEntry entry = new NameSurferEntry(line);
	namesDB.put(entry.getName(), entry);

	}
	rd.close();
	}catch(IOException ex){
	throw new ErrorException(ex);
	}
}

	/* Method: findEntry(name) */
	/**
	* Returns the NameSurferEntry associated with this name, if one
	* exists. If the name does not appear in the database, this
	* method returns null.
	*/
	public NameSurferEntry findEntry(String name) {
	// You need to turn this stub inameSurferEntry findEntry(Sto a real implementation //
	name = checkName(name);

	return namesDB.get(name) ;

	}

	private String checkName(String name){

	char firstLetter = name.charAt(0);
	if(Character.isLowerCase(firstLetter)){
	firstLetter = Character.toUpperCase(firstLetter);
	}

	String otherLetters = name.substring(1);
	otherLetters = otherLetters.toLowerCase();

	name = firstLetter + otherLetters;
	return name;
	}

	private HashMap<String, NameSurferEntry> namesDB = new HashMap<String,NameSurferEntry>();
}
