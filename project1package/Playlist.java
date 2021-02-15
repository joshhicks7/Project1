package project1package;
import java.util.*;



public class Playlist
{
	final String PLAYLIST_DESTINATION = "project1package\\test_Tracks.csv";	
	private List<Song> mySongs;
	
	private Set<String> artists = new HashSet<String>();
	private Set<String> genres = new HashSet<String>();
	
	
	public Playlist()
	{
		this.mySongs = new ArrayList<Song>();
	}

	public Playlist(List<Song> mySongs)
	{
		this.mySongs = mySongs;
	}

	public void addSong(Song song)
	{
		if(!mySongs.contains(song))
		{
			mySongs.add(song);
		}
	}

	public void removeSong(Song song)
	{
		if(mySongs.contains(song))
		{
			mySongs.remove(mySongs.indexOf(song));
		}
	}
	
	public Song findSong(int song_id)
	{
		for(int i = 0; i < mySongs.size(); i++)
		{
			if(mySongs.get(i).getTrack_id() == song_id)
			{
				return mySongs.get(i);
			} 
		}
		
		return null;
	}
	
	public String toString()
	{
		String st = "";
		
		for(Song s : mySongs)
		{
			st += s + "\n";
		}
		return st;
	}
	
	public boolean hasSong()
	{
		return !mySongs.isEmpty();
	}
	
	public void savePlaylist()
	{
		FileHandler.writeFile(PLAYLIST_DESTINATION, this.toString());
	}
	
	public void loadPlaylist()
	{
		String contents = FileHandler.readFile(PLAYLIST_DESTINATION);
		String[] ar = contents.split("\n");
		
		for(String st : ar)
		{
			Song s = new Song(st);
			genres.add(s.getGenre());
			artists.add(s.getArtist_name());
			addSong(s);			
		}		
	}
	
	public static void CheckPattern(String pattern, int... def)
	{
		/*
		 *  for(String st : ar)
		 
		{
			for(int i = 0; i < def.length; i++)
			{
				String s = st.split(",")[def[i]];
				if(s.toLowerCase().contains(pattern.toLowerCase()))
				{
					p.addSong(new Song(st));
				}
			}
			
			
		}
		*/
		
	}
	
	public List<Song> getSongs()
	{
		return this.mySongs;
	}
	
	public Set<String> getGenres()
	{
		return this.genres;
	}
	
	public Set<String> getArtists()
	{		
		return this.artists;		
	}
}


