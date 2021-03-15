package project2package;

import java.util.*;

public class FavoriteHolder 
{
	public static SortedSet<String> favArtists = new TreeSet<String>();
	public static SortedSet<String> favGenres = new TreeSet<String>();	
	public static String playerName = "";
	
	public static void LoadFavorites()
	{
		String fav = PlayerPrefs.getString(PlayerPrefs.FAVORITE_GENRES);
		if(fav != "")
		{
			for(String s : fav.split("\n"))
			{
				favGenres.add(s);
			}
		}
		
		fav = PlayerPrefs.getString(PlayerPrefs.FAVORITE_ARTISTS);
		if(fav != "")
		{	
			for(String s : fav.split("\n"))
			{
				favArtists.add(s);
			}
		}		
	
	}
	
	public static void LoadPlayerData()
	{
		playerName = PlayerPrefs.getString(PlayerPrefs.PLAYER_NAME);
	}
		
	public static void LoadAll()
	{
		LoadFavorites();
		LoadPlayerData();
	}
	
}
