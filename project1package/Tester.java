
package project1package;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Tester
{
	public static void main(String[] args)
	{
		Playlist p = new Playlist();
		//Song(int track_id, String artist_name, String genre, String track_title,  String album_title, int year_created, double artist_longitude)
		//p.addSong(new Song(0, "josh", "butterfly_jazz", "bobby", "The Wasp", 2019, -134.234));	
		//p.addSong(new Song(1, "josh", "butterfly_jazz", "bobby", "The Wasp", 2019, -134.234));
		//p.addSong(new Song("2, jimmy, bananza, celery, buttons,2000,123.4"));
		//System.out.print(p);
		
		/*System.out.print(System.getProperty("user.dir"));
		
		String s = (FileHandler.readFile("project1package\\finalTracks.csv", 100));
		
		System.out.print(s);
		
		String[] ar = s.split("\n");
		
		for(String st : ar)
		{
			p.addSong(new Song(st));
		}
		
		System.out.print(p);
		*/
		p.loadPlaylist();
		
		int[] c = {0};
		Scanner input = new Scanner(System.in);
		while(c[0] > -1)
		{
			System.out.println("What would you like to do?");
			System.out.println("For multiples type a comma between (EX: 0,1 for artist and id)");
			System.out.println("Look Up Id? (0)");
			System.out.println("Look Up Artist? (1)");
			System.out.println("Look Up Genre? (2)");
			System.out.println("Look Up Song? (3)");
			System.out.println("Look Up Album? (4)");
			System.out.println("Look Up Year? (5)");
			System.out.println("Look Up Longitude? (6)");
			System.out.println("Remove Song? (7)");
			


			String str = input.nextLine();
			c = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
			
			
			
			if(c[0] < 0)
			{
				break;
			}
			
			//Playlist p2 = new Playlist(p.getSongs());//.stream().filter(s -> (s.getId() > 1000));
			//p2.getSongs().removeIf(s -> s.getId() > 1000);
			
			//System.out.print(new Playlist((p.getSongs().stream().filter(s -> s.getTrack_id() < 100).collect(Collectors.toList()))));
			
			
			System.out.println(String.join(",", p.getGenres()));
			System.out.print(String.join(",", p.getArtists()));
			
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
			*/

		}
		
		input.close();
		p.savePlaylist();
		
	}
	

}