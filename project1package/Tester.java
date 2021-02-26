
package project1package;

/*
 * Josh Hicks
 * CSCI 3381
 * Project 1
 */
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Tester
{
	public static void main(String[] args)
	{
		Library lib = new Library();
		//Calls the Collection class to load the playlist from the text file
		lib.loadPlaylist();
		
		//Adds a new song to the playlist
		lib.addSong(new Song(111667,"Josh", "blues", "ButterFly Boys", "Fly Away", 2020, 000));
		
		//Prints out my 111667 Song
		System.out.print(lib.findSongById(111667));
				
		//Removes song 111667 From the songs
		lib.removeSong(lib.findSongById(111667));
		
		//Prints out all songs with artist name of Josh to see if 111667 is still in the list
		lib.findSongsByArtistName("Josh").forEach(x->{System.out.println(x);});
		
		//Creates new Song
		Song s = new Song();
		s.setTrack_id(118897);
		s.setArtist_name("Josh Hopper");
		s.setAlbum_title("NSync Cover");		
		s.setTrack_title("HomeBoy Rodeo");
		s.setArtist_longitude(11.345);
		s.setGenre("Computer");		
		s.setYear_created(2015);
		System.out.println(s);
		
		//Prints all information about current song
		System.out.println(s.getTrack_id());
		System.out.println(s.getArtist_name());
		System.out.println(s.getAlbum_title());
		System.out.println(s.getYear_created());
		System.out.println(s.getArtist_name());
		System.out.println(s.getGenre());
		System.out.println(s.getArtist_longitude());
		
		lib.addSong(s);
		
		s = new Song("123456, Josh, Hip Hop, Kong Fuey, Jump, 2020, 0.354");
		
		System.out.println(s);
		
		lib.addSong(s);
		//Prints out all songs with artist name of Josh to see if 111667 is still in the list
		lib.findSongsByArtistName("Josh").forEach(x->{System.out.println(x);});
		
		//Remove all songs by Josh Armistead because he's a copier
		lib.removeSongsByArtist("Josh Armistead");
		
		//Prints out all songs with artist name of Josh to see if 111667 is still in the list
		System.out.println("Removed Josh Armistead");
		lib.findSongsByArtistName("Josh").forEach(x->{System.out.println(x);});

		/*
		int c = 0;		
		//Gets user input to select a song
		Scanner input = new Scanner(System.in);
		while(c > -1)
		{
			System.out.println("What would you like to do?");
			System.out.println("Look Up Id? (0)");
			System.out.println("Look Up Artist? (1)");
			System.out.println("Look Up Genre? (2)");
			System.out.println("Look Up Song? (3)");
			System.out.println("Look Up Album? (4)");
			System.out.println("Look Up Year? (5)");
			System.out.println("Look Up Longitude? (6)");
			System.out.println("Remove Song? (7)");

			String str = input.nextLine();		
			
			
			if(c < 0)
			{
				break;
			}
			
		}
		*/
			//Playlist p2 = new Playlist(p.getSongs());//.stream().filter(s -> (s.getId() > 1000));
			//p2.getSongs().removeIf(s -> s.getId() > 1000);
			
			//System.out.print(new Playlist((p.getSongs().stream().filter(s -> s.getTrack_id() < 100).collect(Collectors.toList()))));
			
			
			//System.out.println(String.join(",", p.getGenres()));
			//System.out.print(String.join(",", p.getArtists()));
			
			/*
			if(c[0] <= 6)
			{
				System.out.print("GO: ");
				
				//CheckPattern(input.nextLine(), c);
			}
			else
			{
				System.out.print("GO: ");
				p.removeSong(p.findSong(Integer.parseInt(input.nextLine())));
			}
			
			input.close();
			p.savePlaylist();
		}
		*/

		
	}
	

}