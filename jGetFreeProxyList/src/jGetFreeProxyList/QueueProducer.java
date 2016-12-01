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
 * Filler to queue will add task to queue as it will need
**/
public class QueueProducer extends WorkThread {
	@Override
    public void run() {
    
    }
    
    public QueueProducer(jGetFreeProxyList parent) {
		super(parent);
    }
}
