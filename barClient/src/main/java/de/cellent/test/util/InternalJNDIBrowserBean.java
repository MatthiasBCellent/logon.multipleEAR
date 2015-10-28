package de.cellent.test.util;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * Browsing the JNDI tree on startup of the server.
 *
 * TODO think of a "reload" as on every deployment the server has to be
 * restarted. Maybe a caching framework might help (some kind of onEviction
 * listener) or the app server should fire an event on deployment. for the
 * moment this is enough ...
 *
 * @author mbohnen
 *
 */
@Singleton
//@Startup
public class InternalJNDIBrowserBean {

    private static final Logger LOG = Logger.getLogger(InternalJNDIBrowserBean.class);

    private InitialContext ctx;
    private String ctxName = "java:jboss/exported";

    private HashMap<String, String> cache = new HashMap<String, String>();

    public InternalJNDIBrowserBean() {
    	this.readContext();
    }

    
//    @PostConstruct
    private void readContext() {
    	LOG.info("Browsing JNDI tree");
        try {
        	ctx = new InitialContext();
            NamingEnumeration<NameClassPair> names = ctx.list(ctxName);

            // as long as we have something on this level
            while (names.hasMore()) {
                NameClassPair next = names.next();
                String name = next.getName();
                String clazzName = next.getClassName();

                // do we have a sub-context?
                if (clazzName.equals("javax.naming.Context")) {
                    // this first-slash issue
                    if (ctxName.equals("")) {
                        ctxName = name;
                    } else {
                        ctxName = ctxName + "/" + name;
                    }
                    // read sub-context recursively
                    this.readContext();
                } else {
                    String lookupString = ("ejb:" + ctxName + "/" + name);
                    LOG.debug("Caching: " + clazzName + " -> " + lookupString);
                    cache.put(clazzName, lookupString);
                }
                if (!names.hasMore())
                    ctxName = "";
            }
            LOG.info("JNDI tree contains: " + this.cache.size() + " entries");
            for (String key : this.cache.keySet()) {
            	LOG.info("Key: " + key + " -> " + this.cache.get(key));	
			}
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    // checking the cache and deliver ... or throw an exception
    public String getLookupString(Class clazz) throws NamingException {
        String ret = this.cache.get(clazz.getName());

        // maybe we had a deployment which isn't cached yet
        if (null == ret) {
            LOG.debug(clazz.getSimpleName() + " not found in cache going for reload of cache");
            this.readContext();
            ret = this.cache.get(clazz.getName());
            if (null == ret) {
                throw new NamingException(clazz.getSimpleName() + " not bound to JNDI");
            }
        }
        return ret;
    }
}
