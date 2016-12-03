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
import org.apache.commons.lang3.StringUtils;

/**
 * Utility to get a list of tested free proxies
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
            this.ExGetProxy.submit(new GetProxy(this, Settings.GetProxyUrls.get(i)));
        }
		
		System.out.println("jGetFreeProxyList.jGetFreeProxyList.run() created ExGetProxy");

		// Await until all GetProxy threads will ended
		this.ExGetProxy.shutdown();
        this.ExGetProxy.awaitTermination(Settings.AwaitGetProxy, TimeUnit.SECONDS);
		
		
		System.out.println("jGetFreeProxyList.jGetFreeProxyList.run() ExGetProxy.shutdown");
		
//		try {
//			ProxyItem pi1 = new ProxyItem(InetAddress.getByName("119.46.110.17"), 8080);
//			this.RawProxies.putIfAbsent(pi1.toString(), pi1);
//			ProxyItem pi2 = new ProxyItem(InetAddress.getByName("106.3.40.249"),8081);
//			this.RawProxies.putIfAbsent(pi2.toString(), pi2);
//			ProxyItem pi3 = new ProxyItem(InetAddress.getByName("109.87.146.155"),80);
//			this.RawProxies.putIfAbsent(pi3.toString(), pi3);
//			ProxyItem pi4 = new ProxyItem(InetAddress.getByName("112.95.241.76"),80);
//			this.RawProxies.putIfAbsent(pi4.toString(), pi4);
//			ProxyItem pi5 = new ProxyItem(InetAddress.getByName("114.80.136.112"),7780);
//			this.RawProxies.putIfAbsent(pi5.toString(), pi5);
//			ProxyItem pi6 = new ProxyItem(InetAddress.getByName("115.28.213.143"),8000);
//			this.RawProxies.putIfAbsent(pi6.toString(), pi6);
//			ProxyItem pi7 = new ProxyItem(InetAddress.getByName("116.213.211.130"),80);
//			this.RawProxies.putIfAbsent(pi7.toString(), pi7);
//			ProxyItem pi8 = new ProxyItem(InetAddress.getByName("117.59.217.236"),843);
//			this.RawProxies.putIfAbsent(pi8.toString(), pi8);
//			ProxyItem pi9 = new ProxyItem(InetAddress.getByName("209.97.203.60"),8080);
//			this.RawProxies.putIfAbsent(pi9.toString(), pi9);
//			ProxyItem pi10 = new ProxyItem(InetAddress.getByName("209.97.203.64"),8080);
//			this.RawProxies.putIfAbsent(pi10.toString(), pi10);
//		}
//		catch(UnknownHostException e) {
//			
//		}
//		
//		// If if there is alaliable proxies to test
//		if (0 == this.RawProxies.size()) {
//			this.ExStateControl.shutdown();
//			this.jGetFreeProxyListListener.process(0, 0);
//			this.jGetFreeProxyListListener.done(new ArrayList<ProxyItem>());
//			
//			throw new RuntimeException("There is no proxies were found for test");
//		}
//		
//		// Start QueueProducer
//		this.ExQueueProducer = Executors.newSingleThreadExecutor();
//		Future<?> futureExQueueProducer = this.ExQueueProducer.submit(new QueueProducer(this));
//				
//		// Starting threads to test proxies 
//		this.ExTestProxy = Executors.newFixedThreadPool(Settings.AmountThreads);
//        for(int i = 0; i < Settings.AmountThreads; i++){
//            this.ExTestProxy.submit(new TestProxy(this));
//        }
//		
//        this.ExTestProxy.shutdown();
//		this.ExQueueProducer.shutdown();
//		
//		System.out.println("jGetFreeProxyList.jGetFreeProxyList.run() Await TestProxy and QueueProducer");
//		
//		// Await until all TestProxy threads and QueueProducer will ended
//		this.ExTestProxy.awaitTermination(Settings.AwaitTestProxy, TimeUnit.SECONDS);
//		this.ExQueueProducer.awaitTermination(Settings.AwaitTestProxy, TimeUnit.SECONDS);
//		
//		System.out.println("jGetFreeProxyList.jGetFreeProxyList.run() TestProxy and QueueProducer are stopped");
//		
		// Stop StateControl
		this.ExStateControl.shutdown();

		System.out.println("jGetFreeProxyList.jGetFreeProxyList.run() ExStateControl shutdownNow");
		
		this.jGetFreeProxyListListener.process(100, 100);
//		this.jGetFreeProxyListListener.done(new ArrayList<ProxyItem>(this.TestedProxies));
        this.jGetFreeProxyListListener.done(new ArrayList<ProxyItem>(this.RawProxies.values()));
		
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
                    System.out.println(".process():" + getProxyPerc + ":" + testProxyPerc);
				}
				@Override
				public void done(ArrayList<ProxyItem> testedProxies){
                    System.out.println(".done(): " + StringUtils.join(testedProxies, ", "));
                   
				}
			}
		);
		
		try {
			jGetFreeProxyList.run();
		}
		catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
	}
    
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

