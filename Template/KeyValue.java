import java.util.StringTokenizer;

//class which store property information
public class KeyValue {

	private String key;
	private String value;
	
	public KeyValue(String input) // constructor to parse key and value from property file
	{
		StringTokenizer token = new StringTokenizer(input,"="); // parse key and value with delimiter "="
		this.key = token.nextToken().trim(); // eliminate white space leading and trailing string input
		this.value = token.nextToken().trim();
	}
	public KeyValue(String key ,String value) // constructor to get parsed date information
	{
		this.key = key;
		this.value = value;
	}
	// accessor
	public String getKey()
	{
		return key;
	}
	public String getValue()
	{
		return value;
	}
}
