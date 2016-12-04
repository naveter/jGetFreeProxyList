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
	private static int askPeriod = 1000;
	
	@Override
    public void run() {
		
		// Thread will finish when main thread shutdown it
        while(!this.Main.ExStateControl.isShutdown()) {
			
            // Ask for condition every N seconds
            try {
                Thread.sleep(StateControl.askPeriod);
            }
            catch(InterruptedException e) {}
        
			int percGetProxy = jGetFreeProxyList.calcPercentage(
				Settings.GetProxyUrls.size(), this.Main.GetProxyCounter.get()
			);
			
			int percTestProxy = jGetFreeProxyList.calcPercentage(
				this.Main.RawProxies.size(), this.Main.TestProxyCounter.get()
			);
			
			if (!this.Main.ExStateControl.isShutdown()) {
				this.Main.jGetFreeProxyListListener.process(percGetProxy, percTestProxy);
			}
        }
		
    }
    
    public StateControl(jGetFreeProxyList parent) {
		super(parent);
    }
}
