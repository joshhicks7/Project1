package project1package;
import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class FileHandler
{
	
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
