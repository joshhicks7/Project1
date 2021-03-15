package project2package;

import java.io.*;
import java.util.stream.Stream;

public class PlayerPrefs 
{
	public static final String FAVORITE_SONGS = "favesong.txt";
	public static final String FAVORITE_GENRES = "favegenre.txt";
	public static final String FAVORITE_ARTISTS = "faveartists.txt";
	public static final String PLAYER_NAME = "playername.txt";
	
	public static String getString(String fileName)
	{
		return FileHandler.readFile(fileName);
	}
	
	public static void setString(String fileName,String contents)
	{
		FileHandler.writeFile(fileName, contents);
	}
}
