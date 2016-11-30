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

public class jGetFreeProxyList {
    
	
	public static void main(String[] args) {
		jGetFreeProxyList jGetFreeProxyList = new jGetFreeProxyList(
			new jGetFreeProxyListListener(){
				void process(int percent){

				}
				void done(ArrayList testedProxies){

				}
			}
		);
		jGetFreeProxyList.run();
	};
  
}

/*


 */