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

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class jGetFreeProxyList {
	// Queue for threads to test proxies
    protected ArrayBlockingQueue<ProxyItem> ProxiesQueue;
	
	// Proxies that tested already
    protected ConcurrentHashMap<String, ProxyItem> TestedProxies;
	
	// Listener for cunsumer's communication
    protected jGetFreeProxyListListener jGetFreeProxyListListener;
	
	// How much tests was done
    protected AtomicInteger WorkCounter;
	
	// List of unique non tested proxies
    protected CopyOnWriteArrayList<ProxyItem> RawProxies;
	
	// Capacity of ProxiesQueue
    protected int QueueCapacity;
	
	public jGetFreeProxyList(jGetFreeProxyListListener listener){
		if (null == listener) {
			throw new NullPointerException("jGetFreeProxyListListener has to be defined");
		}
		
		this.jGetFreeProxyListListener = listener;
	}
	
    public void run() {
    
    }
    
	
	public static void main(String[] args) {
		jGetFreeProxyList jGetFreeProxyList = new jGetFreeProxyList(
			new jGetFreeProxyListListener(){
				@Override
				public void process(int getProxyPerc, int testProxyPerc){

				}
				@Override
				public void done(ArrayList testedProxies){

				}
			}
		);
		jGetFreeProxyList.run();
	};
  
}

/*


 */