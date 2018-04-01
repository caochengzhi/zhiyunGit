package com.chengzhi.scdp.system.manager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ehcache缓存工具类
 * @author beisi
 *
 */
@Component
public class EhCacheUtil {
	@Autowired
	private CacheManager ehcacheManager;

	public void put(String cacheName, String key, Object value) { 
		Cache cache = ehcacheManager.getCache(cacheName); 
		Element element = new Element(key, value); 
		cache.put(element); 
	} 

	public Object get(String cacheName, String key) { 
		Cache cache = ehcacheManager.getCache(cacheName); 
		Element element = cache.get(key); 
		return element == null ? null : element.getObjectValue(); 
	} 

	public Cache get(String cacheName) { 
		return ehcacheManager.getCache(cacheName); 
	} 

	public void remove(String cacheName, String key) { 
		Cache cache = ehcacheManager.getCache(cacheName); 
		cache.remove(key); 
	} 

}
