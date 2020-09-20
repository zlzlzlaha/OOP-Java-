
public class Distance {

	private String name;
	private double lat;
	private double lng;
	
	public static String getDistance(Distance a, Distance b) // to return string value of distance of country a and country b  
	{
		double result = Math.sqrt(Math.pow(a.lat-b.lat,2.0) + Math.pow(a.lng -b.lng,2.0));
		
		return Double.toString(result);// change distance value into string 
	}
	
	public Distance(String name, double lat, double lng) //constructor to get country information
	{
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String writeDistance() // to write distance information in <add info>
	{
		return "Country : "+name+"\r\n"+"latitude="+lat+"\r\n"+"longitude="+lng+"\r\n"+"--------------------";
	}
	
}
