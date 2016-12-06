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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Utility to get a list of tested free proxies
 * 
 * @version 1.0
 */
public final class jGetFreeProxyList {
	
	// Queue for threads to test proxies
    ArrayBlockingQueue<ProxyItem> ProxiesQueue = new ArrayBlockingQueue(Settings.CapacityProxiesQueue);
	
	// Map of unique non tested proxies
    ConcurrentHashMap<String, ProxyItem> RawProxies = new ConcurrentHashMap<>();
	
	// Listener for cunsumer's communication
    jGetFreeProxyListListener jGetFreeProxyListListener;
	
	// Counter of ended GetProxy threads 
    AtomicInteger GetProxyCounter = new AtomicInteger(0);
    
    // Counter of ended tests of proxy in TestProxy
    AtomicInteger TestProxyCounter = new AtomicInteger(0);
	
	// Proxies that tested already
    CopyOnWriteArrayList<ProxyItem> TestedProxies = new CopyOnWriteArrayList<>();
    
    // Collection of errors
    AtomicReference<WorkErrors> WorkErrors = new AtomicReference<>(new WorkErrors());
	
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
	 * @throws RuntimeException - if no proxies were found
	 * @throws InterruptedException - from Executors
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
            this.ExGetProxy.submit(new GetProxy(this, Settings.GetProxyUrls.get(i)));
        }

		// Await until all GetProxy threads will ended
		this.ExGetProxy.shutdown();
        this.ExGetProxy.awaitTermination(Settings.AwaitGetProxy, TimeUnit.SECONDS);
		
		// If if there is alaliable proxies to test
		if (0 == this.RawProxies.size()) {
			this.ExStateControl.shutdown();
			this.jGetFreeProxyListListener.process(0, 0);
			this.jGetFreeProxyListListener.done(new ArrayList<ProxyItem>(),this.WorkErrors.get().get());
			
			throw new RuntimeException("There is no proxies were found for test");
		}
		
		// Start QueueProducer
		this.ExQueueProducer = Executors.newSingleThreadExecutor();
		Future<?> futureExQueueProducer = this.ExQueueProducer.submit(new QueueProducer(this));
				
		// Starting threads to test proxies 
		this.ExTestProxy = Executors.newFixedThreadPool(Settings.AmountThreads);
        for(int i = 0; i < Settings.AmountThreads; i++){
            this.ExTestProxy.submit(new TestProxy(this));
        }
		
        this.ExTestProxy.shutdown();
		this.ExQueueProducer.shutdown();
		
		// Await until all TestProxy threads and QueueProducer will ended
		this.ExTestProxy.awaitTermination(Settings.AwaitTestProxy, TimeUnit.SECONDS);
		this.ExQueueProducer.awaitTermination(Settings.AwaitTestProxy, TimeUnit.SECONDS);
				
		// Stop StateControl
		this.ExStateControl.shutdown();

		this.jGetFreeProxyListListener.process(100, 100);
		this.jGetFreeProxyListListener.done(
            new ArrayList<ProxyItem>(this.TestedProxies), this.WorkErrors.get().get()
        );
		
    }
    
	/**
	 * Checking of settings data
	 * 
	 * @throws RuntimeException - if settings is not correct
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
	
//	public static void main(String[] args) {
//		jGetFreeProxyList jGetFreeProxyList = new jGetFreeProxyList(
//			new jGetFreeProxyListListener(){
//				@Override
//				public void process(int getProxyPerc, int testProxyPerc){
//                    System.out.println(".process():" + getProxyPerc + ":" + testProxyPerc);
//				}
//				@Override
//				public void done(ArrayList<ProxyItem> testedProxies, WorkErrors errors){
//					String str = "";
//					for(ProxyItem s: testedProxies) str += s.toString();
//                    System.out.println(".done(): " + str);
//                    
//                    if (null != errors && !errors.WithoutProxies.isEmpty()){
//						String str2 = "";
//						for(InfoUrl s: errors.WithoutProxies) str += s.toString();
//                        System.out.println(".errors.WithoutProxies: " + str2);
//                    }
//                   
//				}
//			}
//		);
//		
//		try {
//			jGetFreeProxyList.run();
//		}
//		catch(InterruptedException e) {
//			System.out.println(e.getMessage());
//		}
//		
//	}
    
    /**
     * Calc percentage from given max and current value
     * 
     * @param max
     * @param now
     * @return 
     */
    static int calcPercentage(int max, int now){
        max = Math.abs(max);
        now = Math.abs(now);
        
		if (0 == max) return 0;
        if (now >= max) return 100;
        
        // mean max was multiply to 100
        max = max*100;
        now = now*100;
        int onePercent = max/100;
        int nowPercent = (now / onePercent);
        
        return nowPercent;
    }
  
}

