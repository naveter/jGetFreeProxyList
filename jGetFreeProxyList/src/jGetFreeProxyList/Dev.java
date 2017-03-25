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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Technical class
 */
public class Dev {
    private static AtomicBoolean EnableDebug = new AtomicBoolean(false);
    private static CopyOnWriteArrayList<String> Log = new CopyOnWriteArrayList<>();
    
    public static boolean getEnableDebug() {
        return Dev.EnableDebug.get();
    }

    public static void setEnableDebug(boolean b) {
        Dev.EnableDebug.set(b);
    }
    
    /**
     * Write message into log
     * @param s 
     */
    public static void out(String s) {
        Dev.Log.add(s);
        if (true == Dev.EnableDebug.get()) {
            System.out.println(s);
        }
    }
}
