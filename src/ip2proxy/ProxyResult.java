package ip2proxy; 

/**
 * 
 * 	@modify by Egon Galvani
 */

public class ProxyResult {
	private int Is_Proxy;
	private String Proxy_Ip; 
	private String Proxy_Type; 
	private String Country_Short;
	private String Country_Long;
	private String Region;
	private String City;
	private String ISP;
	
	public int getIs_Proxy() {
		return Is_Proxy;
	}

	public void setIs_Proxy(int is_Proxy) {
		Is_Proxy = is_Proxy;
	}

	public String getProxy_Ip() {
		return Proxy_Ip;
	}

	public void setProxy_Ip(String proxy_Ip) {
		Proxy_Ip = proxy_Ip;
	}

	public String getProxy_Type() {
		return Proxy_Type;
	}

	public void setProxy_Type(String proxy_Type) {
		Proxy_Type = proxy_Type;
	}

	public String getCountry_Short() {
		return Country_Short;
	}

	public void setCountry_Short(String country_Short) {
		Country_Short = country_Short;
	}

	public String getCountry_Long() {
		return Country_Long;
	}

	public void setCountry_Long(String country_Long) {
		Country_Long = country_Long;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getISP() {
		return ISP;
	}

	public void setISP(String iSP) {
		ISP = iSP;
	}
	
    @Override
    public String toString() {
    	return "Type: " + Proxy_Type
    			+ "\nCountry Short: " + Country_Short + 
    			"\nCountry Long: " + Country_Long + 
    			"\nRegion: " + Region + 
    			"\nCity: " + City + 
    			"\nISP: " + ISP; 
    }
}
