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

/**
 * Structure to represent info about servers when possible to find proxies
 * and how parse theirs page.
 */
public class InfoUrl {
    public java.net.URL Url;
	
	/**
	 * PregMach how to find proxy
	 */
    protected String PatternString = "([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\s*:\\s*([0-9]+)";
	
    public InfoUrl(java.net.URL url) {
		if (null == url) {
			throw new NullPointerException("InfoUrl has to be defined");
		}
		
		this.Url = url;
    }
	
    @Override
    public String toString(){
        return this.Url.getHost();
    }
}
