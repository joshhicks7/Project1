package project1package;
import java.util.*;



public class Library
{
	final String PLAYLIST_DESTINATION = "project1package\\test_Tracks.csv";	
	private List<Song> mySongs;
	
	private Set<String> artists = new HashSet<String>();
	private Set<String> genres = new HashSet<String>();
	
	
	public Library()
	{
		this.mySongs = new ArrayList<Song>();
	}

	public Library(List<Song> mySongs)
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
	
	//Removes song by just song
	public void removeSong(Song song)
	{
		if(mySongs.contains(song))
		{
			mySongs.remove(mySongs.indexOf(song));
		}
	}
	
	public void removeSongsByArtist(String artist)
	{
		while(findSongsByArtistName(artist) != null)
		{
			for(Song s : mySongs)
			{
				if(s.getArtist_name().toLowerCase().equals(artist.toLowerCase()))
				{
					removeSong(s);
					break;
				}
			}
		}
		
	}
	
	public Song findSongById(int song_id)
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
	
	public List<Song> findSongsByArtistName(String artist)
	{
		List<Song> ss = new ArrayList();
		
		mySongs.forEach(x->{if(x.getArtist_name().toLowerCase().contains(artist.toLowerCase())) {ss.add(x);}});
		if(ss.size() == 0 )
		{
			return null;
		}
		return ss;
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
	
	public Iterator<Song> iterator()
	{
		return mySongs.iterator();
	}
	
	//Returns a list of all songs
	public List<Song> getSongs()
	{
		return this.mySongs;
	}
	
	//Returns a set of all genres
	public Set<String> getGenres()
	{
		return this.genres;
	}
	
	//Returns a set of all artists
	public Set<String> getArtists()
	{		
		return this.artists;		
	}
}


