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
 * Use in pool of threads to find proxies from concrete url
**/
public class GetProxy extends WorkThread {
	
	@Override
    public void run() {
        System.out.println("StateControl.run() begin");
        
        try {
            java.util.Random randomGenerator = new java.util.Random();
            Thread.sleep(randomGenerator.nextInt(100)*100);
        }
        catch(InterruptedException e) {
            
        }
        
        this.Main.GetProxyCounter.incrementAndGet();
        System.out.println("StateControl.run() end");
    }
    
    public GetProxy(jGetFreeProxyList parent) {
		super(parent);
    }
	
}
