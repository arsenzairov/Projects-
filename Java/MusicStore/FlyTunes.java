import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import acm.program.*;

public class FlyTunes extends ConsoleProgram {
	public void run(){
	while(true) {
		int selection = getSelection();
		if (selection == QUIT) break;
		switch(selection) {
		case LIST_SONGS:
			listSongs();
			break;
		case LIST_ALBUMS:
			listAlbums();
			break;
		case ADD_SONG:
			addSong();
			break;
		case ADD_ALBUM:
			addAlbum();
			break;
		case LIST_SONGS_ON_ALBUM:
			listSongsOnAlbum();
			break;
		case UPDATE_SONG_PRICE:
			updateSongPrice();
			break;
		default:
			System.out.println("Invalid selection");
			break;
		}
	}
}
	
	private int getSelection() {
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.println("Please make a selection (0 to quit) : ");
		System.out.println("1. List all songs");
		System.out.println("2. List all albums");
		System.out.println("3. Add a song");
		System.out.println("4. Add an album");
		System.out.println("5. List songs on an album");
		System.out.println("6. Update song price");
		System.out.println("Selection: ");
		int choice = input.nextInt();
		
		return choice;
	}
		
	
	
	
	
	private void listSongs(){
		System.out.println("All songs carried by the store");
		for (int i=0; i<songs.size(); i++){
			System.out.println(songs.get(i).toString());
		}
	}
	
	
	
	private void listAlbums(){
		System.out.println("All albums carrier by the store:");
		Iterator<String> albumIt = albums.keySet().iterator();
		while (albumIt.hasNext()){
			System.out.println(albums.get(albumIt.next()).toString());
		}
	}
	
	
	// unique identfier of song is name and band
	private int findSong(String name, String band){
		int index = -1;
		for (int i=0;i<songs.size();i++){
			if(songs.get(i).getSongName().equals(name)
					&& songs.get(i).getBandName().equals(band)){
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	private Song addSong(){
		
		Scanner input = new Scanner(System.in);
		System.out.println("Song name (Enter to quit): ");
		String name = input.nextLine();
		if (name.equals("")) return null;
		
		System.out.println("Band name: ");
		String band = input.nextLine();
		int songIndex = findSong(name,band);
		if (songIndex != -1){
			System.out.println("That song is already in the store");
			return songs.get(songIndex);
		} else {
			System.out.println("Price: ");

			double price = input.nextDouble();
			Song song = new Song(name,band,price);
			songs.add(song); // add the song object to the arraylist of type songs
			System.out.println("New song added to the store. ");
			return song;
		}
	}
	
	
	private void addAlbum(){
		Scanner input = new Scanner(System.in);
		System.out.println("Album name: ");
	
		String name = input.nextLine();
		
		if(albums.containsKey(name)) {
			System.out.println("That album is already in the store");
		} else{
			System.out.println("Band name: ");
			String band = input.nextLine();
			Album album = new Album(name,band);
			albums.put(name, album);
			
			while (true){
				Song song = addSong();
				if (song == null) break;
				album.addSong(song);
			}
			System.out.println("New album added to the store");
		}

	}
	
	private void listSongsOnAlbum(){
		Scanner input = new Scanner(System.in);
		System.out.println("Album name: ");
		String name = input.nextLine();
		if (albums.containsKey(name)) {
			Iterator<Song> it = albums.get(name).getSongs();
			System.out.println(name + " contains the following songs:");
			while (it.hasNext()) {
				Song song = it.next();
				System.out.println(song.toString());
			}
		} else {
				System.out.println("No album by that name in the store.");
			}
		}
	
	
	
	private void updateSongPrice(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Song name: ");
		String name = input.nextLine();
		System.out.println("Band name: ");
		String band = input.nextLine();
		
		
		int songIndex = findSong(name,band);
		
		if (songIndex == -1){
			System.out.println("That song is not in the store. ");
		} else {
			System.out.println("New price: ");
			double price = input.nextDouble();
			songs.get(songIndex).setPrice(price);
			System.out.println("Price for " + name + "updated. ");
		}
		
	}
	
	
	
	
	private static final int QUIT =0;
	private static final int LIST_SONGS = 1;
	private static final int LIST_ALBUMS = 2;
	private static final int ADD_SONG = 3;
	private static final int ADD_ALBUM = 4;
	private static final int LIST_SONGS_ON_ALBUM = 5;
	private static final int UPDATE_SONG_PRICE = 6;
	
	
	
	// inventory of all albums carried by the store
	private HashMap<String,Album> albums = new HashMap<String,Album>();
	
	// inventory of all the songs carried by the store
	private ArrayList<Song> songs = new ArrayList<Song>();
	
	
}





// noun classes
// verbs methods 
// unique identifier to keep track of people
// Arraylist and Hashmap are the classes that implement collections 
// You can use the iterator over the collections , print elements one at a time

// One of the nouns of store is "Songs"
// Songs are usually part of "Albums"
// The info about SONG is : Name, Band, Price
// what data types you may have for this variables
// is there are any unique identifiers for the song
