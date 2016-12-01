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

/**
 * Utility to get a list of tested free proxies
 */
public class jGetFreeProxyList {
	
	// Queue for threads to test proxies
    ArrayBlockingQueue<ProxyItem> ProxiesQueue;
	
	// Proxies that tested already
    ConcurrentHashMap<String, ProxyItem> TestedProxies;
	
	// Listener for cunsumer's communication
    jGetFreeProxyListListener jGetFreeProxyListListener;
	
	// How much tests was done
    AtomicInteger WorkCounter;
	
	// List of unique non tested proxies
    CopyOnWriteArrayList<ProxyItem> RawProxies;
		
	public jGetFreeProxyList(jGetFreeProxyListListener listener){
		if (null == listener) {
			throw new NullPointerException("jGetFreeProxyListListener has to be defined");
		}
		
		this.jGetFreeProxyListListener = listener;
	}
	
	/**
	 * Run process
	 * 
	 * @throws RuntimeException 
	 */
    public void run() throws RuntimeException {
		this.init();
    }
    
	/**
	 * Checking of settings data
	 * 
	 * @throws RuntimeException 
	 */
	protected void init() throws RuntimeException{
		if(0 == Settings.GetProxyUrls.size()) {
			throw new RuntimeException("GetProxyList has to be defined");
		}
		if(0 == Settings.TestByUrls.size()) {
			throw new RuntimeException("TestByUrls has to be defined");
		}
		if (Settings.AmountThreads <= 0) {
			throw new RuntimeException("AmountThreads has to be a positive number");
		}
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