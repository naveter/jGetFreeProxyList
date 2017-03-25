/*
 * jGetFreeProxyList - to get a list of tested free proxies to java program
 * 
 * Permission is granted to copy, distribute and/or
 * modify  this  document under  the  terms  of the
 * GNU General Public License
 * 
 * @author: ilya.gulevskiy
 * @email: mstorage.project@gmail.com
 * @date: 2017
 */
package jGetFreeProxyList;

import java.util.ArrayList;

/**
 * Class to save work errors
 * 
 * @version 1.1
 */
public class WorkErrors {
    
    /**
     * List InfoUrl where was found no proxies.
     */
    public ArrayList<InfoUrl> WithoutProxies = new ArrayList<>();
    
    /**
     * Errors from threads, URL-connections fails and others.
     * <p>
     * There it possible to see why tested proxies were not valid.
     */
    public ArrayList<String> Errors = new ArrayList<>();
    
    /**
     * Check wether any field is filled. Return <code>null</code> if not.
     * @return - WorkErrors or null
     */
    public WorkErrors get(){
        if (!this.WithoutProxies.isEmpty()) return this;
        if (!this.Errors.isEmpty()) return this;
        
        return null;
    }
    
}