package project2package;
import java.util.*;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileHandler
{
	public static final String PLAYLIST_DESTINATION = "project2package\\test_Tracks.csv";	
	public static final String IMAGE_STRING = "project2package\\ear.png";
	
	String contents;
	int line;
	public static String pattern = "";
	
	public static String readFile(String file)
	{
		String contents = "";
		
		Scanner input;
		try {
			input = new Scanner(new File(file));			
			int num = 0;
			while(input.hasNextLine())
			{
				String next = input.nextLine();
				{
					contents += next + '\n';
				}
				
			}
			
			input.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		return contents;
		
	}
		
	public static BufferedImage readImage(String file)
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static String readFile(String file, int maxNum)
	{
		String contents = "";
		
		Scanner input;
		try {
			input = new Scanner(new File(file));			
			int num = 0;
			while(input.hasNextLine() && num++ < maxNum)
			{
				String next = input.nextLine();
				//if(next.toLowerCase().contains(pattern.toLowerCase()))
				{
					contents += next + '\n';
				}
			}
			
			input.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contents;
		
	}
	
	public static void deletePart(String file, String string_part)
	{
		String contents = readFile(file);
		
		contents = contents.replace(string_part, "");
		
		writeFile(file, contents);
	}
	
	public static void writeFile(String file_name, String contents)
	{		
		try {
			FileWriter writer = new FileWriter(file_name);
			writer.write(contents);
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
