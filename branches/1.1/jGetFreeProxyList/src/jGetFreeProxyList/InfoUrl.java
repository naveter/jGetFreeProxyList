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

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Structure to represent info about servers when possible to find proxies
 * and how parse theirs page.
 */
public class InfoUrl {
    private java.net.URL Url;
	
	/**
	 * PregMach how to find proxy.
	 * By default represented as 
     * <code>([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\s*:\\s*([0-9]+)</code>
	 */
    private String PatternString = "([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\s*:\\s*([0-9]+)";
	
    public InfoUrl(java.net.URL url) {
		if (null == url) {
			throw new NullPointerException("InfoUrl has to be defined");
		}
		
		this.Url = url;
    }
    
    public URL getUrl() {
        return this.Url;
    }

    public String getPatternString() {
        return this.PatternString;
    }

    /**
     * Set RegExp pattern to parse page and get ProxyItem
     * 
     * @param PatternString 
     * @exception PatternSyntaxException - when set up wrong pattern string
     */
    public void setPatternString(String PatternString) {
        // Check whether PatternString is correct
        Pattern pattern = Pattern.compile(PatternString);
        
        this.PatternString = PatternString;
    }
    
    /**
     * To test how pattern will work and how it will be parse a page.
     * <p>
     * When you add custom PatternString, on the stage of test
     * you have to try yours RegExp on current Url.
     * This method will connect to Url and try to parse page 
     * for create ProxyItem and return ArrayList of them.
     * <p>
     * Be aware, if returned ArrayList is empty, that means 
     * yours RegExp setted by <code>setPatternString(String PatternString)</code>
     * is wrong and you must try other RegExp.
     * <p>
     * This method exists for refuse senseless work when will launch run() method.
     * 
     * @exception PatternSyntaxException - when set up wrong pattern string
     * @exception 
     * @return 
     */
    public ArrayList<ProxyItem> test() throws MalformedURLException, IOException {
        ArrayList<ProxyItem> list = new ArrayList<>();
        
        URLConnection connection = new URL( this.getUrl().toString() ).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("User-Agent", Settings.UserAgent);
        connection.setReadTimeout((Settings.URLConnectionTimeOut*1000));
        InputStream response = connection.getInputStream();

        String result = "";
        try (Scanner scanner = new Scanner(response)) {
            result += scanner.useDelimiter("\\a").next();
        }
        
//        Dev.out(result);

        int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;			
        Pattern pattern = Pattern.compile(this.getPatternString(), flags);
        Matcher m = pattern.matcher(result);

        while(m.find()) {
            if (!m.group(1).isEmpty() && !m.group(2).isEmpty()) {
                ProxyItem pi = new ProxyItem(
                    InetAddress.getByName(m.group(1)), Integer.decode(m.group(2))
                );
                if (!list.contains(pi)) list.add(pi);
            }
        }
        
        return list;
    }
    
    @Override
    public String toString(){
        return this.Url.getHost();
    }
}
