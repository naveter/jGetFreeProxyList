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
import java.util.ArrayList;

/**
 * Class contain settings for library. Can be adjusted by consumer.
 */
public class Settings {
	
	/**
	 * List of public urls to try to connect with proxies. At least 5.
	 * Every time them will choose randomly.
	 */
    public static ArrayList<java.net.URL>  TestByUrls = new ArrayList<>();
	
	/**
	 * List of public urls, where possible to find proxies.
	 * Pay attention, now it works only for format IP:port. 
	 * That means, in received HTML pages parser will locking for strings like 80.128.34.22:80
	 */
    public static ArrayList<InfoUrl> GetProxyUrls = new ArrayList<>();
	
	// How much threads have to be for test proxies
    public static int AmountThreads = 5;
	
	// How long await end of work GetProxy threads
	public static int AwaitGetProxy = 20;
	
	// How long await end of work TestProxy threads
	public static int AwaitTestProxy = 600;
	
	static {
		// Fill statements by default
		try {
			TestByUrls.add(new URL("https://google.com"));
			TestByUrls.add(new URL("https://www.amazon.com/"));
			TestByUrls.add(new URL("https://www.facebook.com/"));
			TestByUrls.add(new URL("http://edition.cnn.com/"));
			TestByUrls.add(new URL("http://www.bbc.com/news"));
			
			GetProxyUrls.add(new InfoUrl(new URL("http://www.prime-speed.ru/proxy/free-proxy-list/all-working-proxies.php")));
			GetProxyUrls.add(new InfoUrl(new URL("http://awmproxy.com/freeproxy.php")));
			GetProxyUrls.add(new InfoUrl(new URL("https://proxy-list.org/russian/index.php")));
			GetProxyUrls.add(new InfoUrl(new URL("http://samair.ru/proxy/")));
			GetProxyUrls.add(new InfoUrl(new URL("http://proxydb.net/")));
			
			// TODO: test in future
			// GetProxyUrls.add(new InfoUrl(new URL("http://www.atomintersoft.com/products/alive-proxy/proxy-list")));
			// GetProxyUrls.add(new InfoUrl(new URL("http://www.freeproxy.ru/download/lists/goodproxy.txt")));
			 
		
		}
		catch(MalformedURLException e) {
			// With that urls it is impossible
		}
		
	}
	
}