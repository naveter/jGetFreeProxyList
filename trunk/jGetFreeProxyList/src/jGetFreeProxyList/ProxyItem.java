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

import hirondelle.date4j.DateTime;
import java.net.InetAddress;

/**
 * Substance wich take care about proxy's item
 */
public class ProxyItem {
    public InetAddress Ip;
    public int Port;
    
	/**
	 * How much second wait answer in last test time
	 */
    public int RespondSeconds;
	
    public DateTime LastChecked;
    
	/**
     * 
     * @param ip
     * @param port 
     */
	public ProxyItem(InetAddress ip, int port){
		if (null == ip || port <= 0) {
			throw new RuntimeException("IP or host is not valid");
		}
		
		this.Ip = ip;
		this.Port = port;
	}
	
	/**
	 * Return true if LastChecked is filled
	 * @return 
	 */
    public boolean isChecked() {
		return true;
    }
    
    public String toString() {
		return this.Ip.getHostAddress() + ":" + Integer.toString(this.Port);
    }
}
