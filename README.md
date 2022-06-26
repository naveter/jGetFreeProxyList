# jGetFreeProxyList

**jGetFreeProxyList** - library to get a list of tested free proxies to java code.

For example, your app have to send a lot of HTTP queries for some hosts, but you dont want do it with one IP address to refuse yours ban by IP. You import jGetFreeProxyList into your app, run it in other thread, set up settings and set handlers. Afterwards jGetFreeProxyList receives list of free proxies from sites you set up in settings, make test for every IP and give you list of tested proxies into yours java code. Amount of threads and timeouts can be set up too.

**WHATS NEW IN v.1.2:**

* added sorting for checked proxies by time of respond

**How it works**

1. You set up list of HTTP-pages where publish free proxies;
1. You set up how make html-parsing of this pages;
1. jGetFreeProxyList get this pages, parse it and build list of unchecked proxies;
1. jGetFreeProxyList checks every proxy through popular sites you set up;
1. jGetFreeProxyList return in your app list of checked proxies.

See [Documentation](https://github.com/naveter/jGetFreeProxyList/wiki) for more details.

