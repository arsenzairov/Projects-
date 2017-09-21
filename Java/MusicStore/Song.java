
public class Song {
	// Constructor 
	
	public Song(String songName, String bandName, double songPrice){
		title = songName;
		band = bandName;
		price = songPrice;
	}
	
	public String getSongName(){
		return title;
	}
	
	public String getBandName(){
		return band;
	}
	
	public void setPrice(double songPrice){
		price = songPrice;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String toString(){
		return ("\"" + title + "\" by" + band
				+ " costs $" + price); // slash means \ double quote
	}
	
	private String title;
	private String band;
	private double price;
	
}
