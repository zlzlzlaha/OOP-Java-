import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class TravelInfoRequest {

	
	public static void main(String[] args)
	{
		KeyValue[] property = new KeyValue[20];
		Countries start_countries = null;
		Countries depart_countries = null;
		Calendar calendar = Calendar.getInstance();
		Distance disa = null;
		Distance disb = null;
		String form;
		String tmp;
		int property_number =0;
		//to get today date 
		int year = calendar.get(Calendar.YEAR);
		int mon = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		

		try
		{
			// open input output files
			BufferedReader readproperty = new BufferedReader(new FileReader("properties.txt"));
			BufferedReader readform = new BufferedReader(new FileReader("template_file.txt"));
			BufferedWriter writeform = new BufferedWriter(new FileWriter("output_file.txt"));
		
			while((tmp = readproperty.readLine())!=null) //get property from files and parse into key and value
			{				
				property[property_number++] = new KeyValue(tmp);
			}
			property[property_number] = new KeyValue("date",year+"-"+mon+"-"+day); // parse date by using KeyValue constructor

			for(int i =0 ; i<=property_number;i++) // initialize Countries class object from country value
			{
				if(property[i].getKey().equals("startcountry")) 
				{
					start_countries = new Countries(property[i].getValue()); 
				}
				else if(property[i].getKey().equals("departcountry"))
				{
					depart_countries = new Countries(property[i].getValue()); 
				}
			}
			// create Distance object and initialize it with Countries class accessor 
			disa = new Distance(start_countries.getCountry(),Double.parseDouble(start_countries.getLat()),Double.parseDouble(start_countries.getLon()));
			disb = new Distance(depart_countries.getCountry(),Double.parseDouble(depart_countries.getLat()),Double.parseDouble(depart_countries.getLon()));
			while((form = readform.readLine())!=null) // read one line from template file
			{
				if(form.indexOf("{")!=-1) // if there exist { in template file
				{	
					
					
					for(int i=0; i<=property_number ; i++) // to find key same with {key} and change it property value
					{
						if(form.indexOf(property[i].getKey())!=-1) // when there is a same key
						{
							form = form.replace("{"+property[i].getKey()+"}",property[i].getValue()); 
						}
					}
					
				}
				//when there is <add info> change it distance information
 				else if(form.indexOf("<add info>")!=-1) 
				{
					form = form.replace("<add info>","Distance of");
					writeform.write(form);
					writeform.newLine();
					writeform.write(disa.writeDistance()); 
					writeform.newLine();
					writeform.write(disb.writeDistance());
					writeform.newLine();
					form= "is\r\n"+ Distance.getDistance(disa, disb);// use Distance's static method to get distance of two countries
					
					
				}
				//print line to output file
				writeform.write(form); 
				writeform.newLine(); 
			}
			//deallocate file
			readform.close();
			readproperty.close();
			writeform.close();
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
			System.out.println("File not opend");
			System.exit(0);
		}
				
		
	}
}
