import java.util.*;

public class Album {
	
	public Album(String albumName, String bandName){
		title = albumName;
		band = bandName;
	}
	
	public String getAlbumName(){
		return title;
	}
	
	public String getBandName(){
		return band;
	}
	
	// Those things are fixed you can only get them
	// Passing song object and adds to array list
	public void addSong(Song song){
		songs.add(song);
	}
	
	// returns an iterator over all songs that are on this album
	public Iterator<Song> getSongs(){
		return songs.iterator();
	}
	
	public String toString(){
		return("\"" + title + "\" by" + band);
	}
	
	private String title;
	private String band;
	private ArrayList<Song> songs = new ArrayList<Song>();
	
}
