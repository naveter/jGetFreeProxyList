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

import jGetFreeProxyList.jGetFreeProxyList;
import java.util.Map;
import java.util.Set;



/**
 * Filler to queue will add task to queue as it will need
**/
public class QueueProducer extends WorkThread {
    
	@Override
    public void run() {
        
        for(ProxyItem pi : this.Main.RawProxies.values()){
            try {
                this.Main.ProxiesQueue.put(pi);
            }
            catch(InterruptedException e) {
                
            }
        }
        
    }
    
    public QueueProducer(jGetFreeProxyList parent) {
		super(parent);
    }
}
