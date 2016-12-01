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


/**
 * Control thread to check state of work and inform consumers
**/
public class StateControl extends WorkThread {
	
	@Override
    public void run() {
        while(true) {
            // Ask for condition every second
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {}
        
//            int diffGetProxy = Settings.GetProxyUrls.size() - this.Main.GetProxyCounter.get();
//            this.Main.GetProxyCounter.incrementAndGet();
        }
    }
    
    public StateControl(jGetFreeProxyList parent) {
		super(parent);
    }
}
