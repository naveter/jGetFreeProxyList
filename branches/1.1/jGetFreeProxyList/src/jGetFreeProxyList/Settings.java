/*
 * jGetFreeProxyList - to get a list of tested free proxies to java program
 * 
 * Permission is granted to copy, distribute and/or
 * modify  this  document under  the  terms  of the
 * GNU General Public License
 * 
 * @author: ilya.gulevskiy
 * @email: mstorage.project@gmail.com
 * @date: 2016
 */
package jGetFreeProxyList;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Class contain settings for library. Can be adjusted by consumer.
 */
public class Settings {
	
	protected static String Version = "1.1";
	
	/**
	 * List of public urls to try to connect with proxies. At least 5.
	 * Every time them will choose randomly.
	 */
    public static ArrayList<java.net.URL>  TestByUrls = new ArrayList<>();
	
	/**
	 * List of public urls, where possible to find proxies.
	 * Pay attention, now it works only for format IP:port. 
	 * That means, in received HTML pages parser will looking for strings like 80.128.34.22:80
	 */
    public static ArrayList<InfoUrl> GetProxyUrls = new ArrayList<>();
	
	/**
     * How much threads have to be for test proxies
     */
    public static int AmountThreads = 5;
	
	/**
     * How long await end of work GetProxy threads, in seconds
     */
	public static int AwaitGetProxy = 20;
	
	/**
     * How long await end of work TestProxy threads, in seconds.
     */
	public static int AwaitTestProxy = 600;
    
    /**
     * Capacity of ProxiesQueue, must be equals or greater than AmountThreads
     */
    public static int CapacityProxiesQueue = 50;
    
    /**
     * TimeZone for current mashine
     */
    public static TimeZone TimeZone = Calendar.getInstance().getTimeZone();
    
    /**
     * How long await when connect to URL, in seconds
     */
    public static int URLConnectionTimeOut = 5;
    
    /**
     * User agent for HTTP query
     */
    public static String UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    
    /**
     * Whether debug tool is enabled
     */
    public static boolean EnableDebug = true;
	
	static {
		// Fill statements by default
		try {
                        
			TestByUrls.clear();
			TestByUrls.add(new URL("http://google.com"));
			TestByUrls.add(new URL("http://www.amazon.com/"));
			TestByUrls.add(new URL("http://www.facebook.com/"));
			TestByUrls.add(new URL("http://edition.cnn.com/"));
			TestByUrls.add(new URL("http://www.bbc.com/news"));
			
			GetProxyUrls.clear();
            GetProxyUrls.add(
                new InfoUrl(new URL("http://awmproxy.com/freeproxy.php"))
            );
            
            // Too big list
//			GetProxyUrls.add(new InfoUrl(
//                new URL("http://www.prime-speed.ru/proxy/free-proxy-list/all-working-proxies.php")
//            ));
//			GetProxyUrls.add(new InfoUrl(new URL("http://samair.ru/proxy/")));
//			GetProxyUrls.add(new InfoUrl(new URL("http://proxydb.net/")));
			GetProxyUrls.add(
                new InfoUrl(new URL("http://www.atomintersoft.com/products/alive-proxy/proxy-list"))
            );
            
		}
		catch(MalformedURLException e) {
			// With that urls it is impossible
		}
		
	}
	
	public static String getVersion(){
		return Version;
	}
	
}
