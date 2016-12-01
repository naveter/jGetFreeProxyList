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

import jGetFreeProxyList.Threads.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility to get a list of tested free proxies
 */
public final class jGetFreeProxyList {
	
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
	
	// Executors for threads
	ExecutorService ExStateControl;
	ExecutorService ExGetProxy;
	ExecutorService ExTestProxy;
	ExecutorService ExQueueProducer;
		
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
    public void run() throws RuntimeException, InterruptedException {
		this.init();
		
		// Start StateControl
		this.ExStateControl = Executors.newSingleThreadExecutor();
		Future<?> futureExStateControl = this.ExStateControl.submit(new StateControl(this));
		
		// Starting threads to get proxies 
		int cntGetProxyUrls = Settings.GetProxyUrls.size();
		this.ExGetProxy = Executors.newFixedThreadPool(cntGetProxyUrls);
		
        for(int i = 0; i < cntGetProxyUrls; i++){
            this.ExGetProxy.submit(new GetProxy(this));
        }

        this.ExGetProxy.shutdown();

		// Await until all GetProxy threads will ended
        this.ExGetProxy.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		
		// If if there is alaliable proxies to test
		if (0 == this.RawProxies.size()) {
			this.ExStateControl.shutdown();
			throw new RuntimeException("There is no proxies were found for test");
		}
		
		// Start QueueProducer
		this.ExQueueProducer = Executors.newSingleThreadExecutor();
		Future<?> futureExQueueProducer = this.ExQueueProducer.submit(new QueueProducer(this));
		
		// Starting threads to test proxies 
		this.ExTestProxy = Executors.newFixedThreadPool(Settings.AmountThreads);
		
        for(int i = 0; i < Settings.AmountThreads; i++){
            this.ExTestProxy.submit(new GetProxy(this));
        }

        this.ExTestProxy.shutdown();
		this.ExQueueProducer.shutdown();
		this.ExStateControl.shutdown();

		// Await until all TestProxy threads and QueueProducer will ended
		this.ExTestProxy.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		this.ExQueueProducer.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		this.ExStateControl.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		
		this.jGetFreeProxyListListener.process(100, 100);
		this.jGetFreeProxyListListener.done(new ArrayList<ProxyItem>(this.TestedProxies.values()));
		
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
		
		try {
			jGetFreeProxyList.run();
		}
		catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
	};
  
}

/*


 */