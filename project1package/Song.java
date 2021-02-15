package project1package;


public class Song
{
	private int track_id = 0;
	private String artist_name;
	
	public int getTrack_id() {
		return track_id;
	}

	public void setTrack_id(int track_id) {
		this.track_id = track_id;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTrack_title() {
		return track_title;
	}

	public void setTrack_title(String track_title) {
		this.track_title = track_title;
	}

	public String getAlbum_title() {
		return album_title;
	}

	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}

	public int getYear_created() {
		return year_created;
	}

	public void setYear_created(int year_created) {
		this.year_created = year_created;
	}

	public double getArtist_longitude() {
		return artist_longitude;
	}

	public void setArtist_longitude(double artist_longitude) {
		this.artist_longitude = artist_longitude;
	}

	private String genre;
	private String track_title;
	private String album_title;
	private int year_created;
	private double artist_longitude;
	
	public Song()
	{
		track_id = 0;
		artist_name = "";
		genre = "";
		track_title = "";
		album_title = "";
		year_created = 0;
		artist_longitude = 0;

	}

	public Song(int track_id, String artist_name, String genre, String track_title,  String album_title, int year_created, double artist_longitude)
	{
		this.track_id = track_id;
		this.artist_name = artist_name;
		this.genre = genre;
		this.track_title = track_title;
		this.album_title = album_title;
		this.year_created = year_created;
		this.artist_longitude = artist_longitude;
	}
	
	public Song(String songString)
	{
		String[] ar = songString.split(",");
		this.track_id = Integer.parseInt(removeSpaces(ar[0]));
		this.artist_name = ar[1];
		this.genre = ar[2];
		this.track_title = ar[3];
		this.album_title = ar[4];
		this.year_created = Integer.parseInt(removeSpaces(ar[5]));
		this.artist_longitude = Double.parseDouble(removeSpaces(ar[6]));
	}
	
	public String toString()
	{
		return track_id + "," + artist_name + "," + genre + "," + track_title + ',' + album_title + ',' + year_created + ',' + artist_longitude;
	}
	
	//Fix this later
	public boolean equals(Object other)
	{
		if(!(other instanceof Song))
		{
			return false;
		}
		else 
		{
			Song s = (Song)other;
			
			if(s.getTrack_id() == track_id)
			{
				return true;
			}	
				
		}		
		
		return false;
	}
	
	
	public String removeSpaces(String string)
	{
		String s = "";
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) != ' ')
			{
				s += string.charAt(i);
			}
		}
		return s;
	}
	
	public int getId()
	{
		return this.getId();
	}
}

