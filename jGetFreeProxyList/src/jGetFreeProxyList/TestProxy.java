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
import jGetFreeProxyList.jGetFreeProxyList;
import java.util.concurrent.TimeUnit;


/**
 * Use in pool of threads to find out whether concrete proxy valid or not
**/
public class TestProxy extends WorkThread {
	
	@Override
    public void run() {
        
        System.out.println("TestProxy started");
        
        while(true) {
            this.Main.TestProxyCounter.getAndIncrement();

            try {
                ProxyItem pi = this.Main.ProxiesQueue.poll(100, TimeUnit.MILLISECONDS);
                if (null == pi) break;
                
                System.out.println("TestProxy got "+ pi.toString());
                
                java.util.Random randomGenerator = new java.util.Random();
                Thread.sleep(randomGenerator.nextInt(100)*100);
                
                pi.LastChecked = DateTime.now(Settings.TimeZone);
                pi.RespondSeconds = 1;
                this.Main.TestedProxies.addIfAbsent(pi);
            }
            catch(InterruptedException e) {

            }
        }
        
        System.out.println("TestProxy stopped");
    }
    
    public TestProxy(jGetFreeProxyList parent) {
		super(parent);
    }
	
	
	
}
