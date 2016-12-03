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
import java.util.concurrent.TimeUnit;


/**
 * Use in pool of threads to find out whether concrete proxy valid or not
**/
public class TestProxy extends WorkThread {
	
	@Override
    public void run() {
        while(!this.Main.ProxiesQueue.isEmpty()) {
            this.Main.TestProxyCounter.getAndIncrement();

            try {
                ProxyItem pi = this.Main.ProxiesQueue.poll(100, TimeUnit.MILLISECONDS);
            }
            catch(InterruptedException e) {

            }
        }
    }
    
    public TestProxy(jGetFreeProxyList parent) {
		super(parent);
    }
	
	
	
}
