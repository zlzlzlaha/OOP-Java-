import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Countries {

	private String country;
	private String lat;
	private String lng;
	
	public Countries(String country) // constructor to get Country information same with argument country from file
	{ 
		BufferedReader csvread = null;
		String[] input;
		try
		{
			csvread = new BufferedReader(new FileReader("Countries.csv")); 
			do
			{
				input = csvread.readLine().split(","); // store parsed country information with delimiter ','
			}
			while(!input[0].equals(country));// until find country information same with argument country
			
			// close file
			csvread.close();
			this.country = country;
			lat = input[1];
			lng = input[2];
			
		}
		catch(FileNotFoundException e)
		{
			e.getStackTrace();
			System.out.println("File not found");
			System.exit(0);
		}
		catch(IOException e2)
		{	
			e2.getStackTrace();
			System.out.println("File not opened");
			System.exit(0);
		}
		
		
	}
	// accessor 
	public String getCountry()
	{
		return country;
	}
	public String getLat()
	{
		return lat;
	}
	public String getLon()
	{
		return lng;
	}
}
