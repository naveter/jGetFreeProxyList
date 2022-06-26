# jGetFreeProxyList

**jGetFreeProxyList** - library to get a list of tested free proxies to java code.

For example, your app have to send a lot of HTTP queries for some hosts, but you dont want do it with one IP address to refuse yours ban by IP. You import jGetFreeProxyList into your app, run it in other thread, set up settings and set handlers. Afterwards jGetFreeProxyList receives list of free proxies from sites you set up in settings, make test for every IP and give you list of tested proxies into yours java code. Amount of threads and timeouts can be set up too.

**WHATS NEW IN v.1.1:**

* added feature to set up RegExp pattern to parse page with proxies;
* now, it is possible to stop running process;
* handlers for exceptions became more reliable and deeper;
* errors collection became more detalized;
* development regime is avaliable now.

**How it works**

1. You set up list of HTTP-pages where publish free proxies;
1. You set up how make html-parsing of this pages;
1. jGetFreeProxyList get this pages, parse it and build list of unchecked proxies;
1. jGetFreeProxyList checks every proxy through popular sites you set up;
1. jGetFreeProxyList return in your app list of checked proxies.

**NOTICE! This was my first work with Java, I did It without best Java practices!**

See [Wiki](https://github.com/naveter/jGetFreeProxyList/wiki) for more details.

