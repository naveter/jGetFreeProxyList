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

import static jGetFreeProxyList.Settings.TestByUrls;
import jGetFreeProxyList.jGetFreeProxyList;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * Use in pool of threads to find out whether concrete proxy valid or not
 */
class TestProxy extends WorkThread {
	
	@Override
    public void run() {
        
        while(true && false == this.Main.IsStopped.get()) {
            ProxyItem pi = null;
            
            try{
                pi = this.Main.ProxiesQueue.poll(100, TimeUnit.MILLISECONDS);
                if (null == pi) break;
            }
            catch(InterruptedException e){
                continue;
            }
            
            pi.LastChecked = null;
            pi.RespondMilliSeconds = 0;
            
            // If stop() is called
            if (true == this.Main.IsStopped.get()) break;
            
            try {
                
                java.util.Random randomGenerator = new java.util.Random();
                int index = randomGenerator.nextInt(Settings.TestByUrls.size())-1;
                URL iu = Settings.TestByUrls.get(index);
                
                long start_time = System.currentTimeMillis();

                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(pi.Ip, pi.Port));
                HttpURLConnection connection =(HttpURLConnection)iu.openConnection(proxy);

                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("User-Agent", Settings.UserAgent);
                connection.setReadTimeout((Settings.URLConnectionTimeOut*1000));
                InputStream response = connection.getInputStream();

                long end_time = System.currentTimeMillis();
                long difference = end_time-start_time;
                
                pi.LastChecked = new Date();
                pi.RespondMilliSeconds = difference;
                this.Main.TestedProxies.addIfAbsent(pi);
            }
            catch(Exception e) {
                
            }
			
			this.Main.TestProxyCounter.getAndIncrement();
            
            System.out.println("TestProxy finished: " + pi.toString());
        }
        
        System.out.println("TestProxy thread stopped");
        
    }
    
    public TestProxy(jGetFreeProxyList parent) {
		super(parent);
    }
	
	
	
}
