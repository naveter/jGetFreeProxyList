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
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use in pool of threads to find proxies from concrete url
**/
public class GetProxy extends WorkThread {
    
    protected InfoUrl InfoUrl;
	
	@Override
    public void run() {
        System.out.println("GetProxy.run() begin");
        int cnt = 0;
        
        try {
//            java.util.Random randomGenerator = new java.util.Random();
//            Thread.sleep(randomGenerator.nextInt(100)*100);
            
            URLConnection connection = new URL( this.InfoUrl.Url.toString() ).openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setReadTimeout((Settings.URLConnectionTimeOut*1000));
            InputStream response = connection.getInputStream();

            String result = "";
            try (Scanner scanner = new Scanner(response)) {
                result += scanner.useDelimiter("\\a").next();
            }

            int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;			
            Pattern pattern = Pattern.compile(this.InfoUrl.PatternString, flags);
            Matcher m = pattern.matcher(result);

            while(m.find()) {
                if (!m.group(1).isEmpty() && !m.group(2).isEmpty()) {
                    ProxyItem pi = new ProxyItem(
                        InetAddress.getByName(m.group(1)), Integer.decode(m.group(2))
                    );
                    this.Main.RawProxies.putIfAbsent(pi.toString(), pi );
                    cnt++;
                }
            }
        }
        catch(Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
        
        this.Main.GetProxyCounter.incrementAndGet();
        
        // No proxies was found
        if(0 == cnt) {
            this.Main.WorkErrors.get().WithoutProxies.add(this.InfoUrl);
        }
        
        System.out.println("GetProxy.run() end ("+ cnt +")"+ this.InfoUrl.Url.getHost());
    }
    
    public GetProxy(jGetFreeProxyList parent, InfoUrl url) {
		super(parent);
        
        if (null == url){
            throw new NullPointerException("InfoUrl can not be null");
        }
        
        this.InfoUrl = url;
    }
	
}
