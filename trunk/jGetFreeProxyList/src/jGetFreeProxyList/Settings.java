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
	 * List of public urls, when possible to find proxies.
	 * Pay attention, now it works only for format IP:port. 
	 * That means, in received HTML pages parser will locking for strings like 80.128.34.22:80
	 */
    public static ArrayList<InfoUrl> GetProxyUrls = new ArrayList<>();
	
	// How much threads have to be for test proxies
    public static int AmountThreads = 5;
	
	static {
		// Fill statements by default
		try {
			TestByUrls.add(new URL("https://google.com"));
			TestByUrls.add(new URL("https://www.amazon.com/"));
			TestByUrls.add(new URL("https://www.facebook.com/"));
			TestByUrls.add(new URL("http://edition.cnn.com/"));
			TestByUrls.add(new URL("http://www.bbc.com/news"));
		}
		catch(MalformedURLException e) {
			// With that urls it is impossible
		}
	}
}
