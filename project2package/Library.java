package project2package;
import java.util.*;



public class Library
{
	private List<Song> mySongs;	
	private SortedSet<String> artists = new TreeSet<String>();
	private SortedSet<String> genres = new TreeSet<String>();
		
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
	
	public Library findSongsByArtistName(String artist)
	{
		List<Song> ss = new ArrayList();
		
		mySongs.forEach(x->{if(x.getArtist_name().toLowerCase().contains(artist.toLowerCase())) {ss.add(x);}});
		if(ss.size() == 0 )
		{
			return null;
		}
		return new Library(ss);
	}
	
	public Library findSongsByGenre(String genre)
	{
		List<Song> ss = new ArrayList();
		
		mySongs.forEach(x->{if(x.getGenre().toLowerCase().contains(genre.toLowerCase())) {ss.add(x);}});
		if(ss.size() == 0 )
		{
			return null;
		}
		return new Library(ss);
	}
	
	public Library findSongsByAlbum(String album)
	{
		List<Song> ss = new ArrayList();
		
		mySongs.forEach(x->{if(x.getAlbum_title().toLowerCase().contains(album.toLowerCase())) {ss.add(x);}});
		if(ss.size() == 0 )
		{
			return null;
		}
		return new Library(ss);
	}
	
	public Library findSongsByYear(String year)
	{
		List<Song> ss = new ArrayList();
		
		mySongs.forEach(x->{if((x.getYear_created() + "").toLowerCase().contains(year.toLowerCase())) {ss.add(x);}});
		if(ss.size() == 0 )
		{
			return null;
		}
		return new Library(ss);
	}
		
	public Library getByAll(String comp)
	{
		Library l = new Library();
		
		for(Song s : mySongs)
		{
			if(s.toString().toLowerCase().contains(comp.toLowerCase()))
			{
				l.addSong(s);
			}
		}
		
		return l;
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
		FileHandler.writeFile(FileHandler.PLAYLIST_DESTINATION, this.toString());
	}
	
	public void loadPlaylist()
	{
		String contents = FileHandler.readFile(FileHandler.PLAYLIST_DESTINATION);
		String[] ar = contents.split("\n");
		genres = new TreeSet<String>();
		
		for(String st : ar)
		{
			Song s = new Song(st);
			genres.add(s.getGenre());
			artists.add(s.getArtist_name());
			
			addSong(s);			
		}		
		System.out.print(genres.toArray());
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

	public void add(Library rhs)
	{		
		if(rhs == null)return;
		if(rhs.hasSong())
		{
			Iterator<Song> it = rhs.iterator();		
			while(it.hasNext())
			{
				addSong(it.next());
			}
		}		
		
	}

	public void Play(Song s)
	{
		
	}

}


