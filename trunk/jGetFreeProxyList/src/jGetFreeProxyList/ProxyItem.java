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


public class ProxyItem {
    public InetAddress Ip;
    public int Port;
    public int RespondSeconds;
    public DateTime LastChecked;
    public boolean isChecked() {
		return true;
    }
    
    public String toString() {
		return "";
    }
}
