package cn.baisee.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Ehcache工具类
 * @author Administrator
 *
 */
public class EhcacheUtil {
	
	//缓存管理器
	private CacheManager manager;
	
	//缓存工具类
	private static EhcacheUtil ehCache;

	//构造函数
	public EhcacheUtil() {
		manager=CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream("ehcache.xml"));
	}
	
	//单例模式
	public static EhcacheUtil getInstance(){
		if(ehCache==null){
			ehCache=new EhcacheUtil();
		}
		return ehCache;
	}
	
	/**
	 * 存放一个缓存
	 * @param cacheName	缓存名称
	 * @param key 缓存Key
	 * @param value 缓存值
	 */
	public void put(String cacheName,String key,Object value){
		Cache cache=manager.getCache(cacheName);
		Element element=new Element(key, value);
		cache.put(element);
	}
	
	/**
	 * 获取缓存里某个元素
	 * @param cacheName 缓存名称
	 * @param key	缓存内容的key
	 * @return
	 */
	public Object get(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
	
	/**
	 * 获取缓存	 
	 * @param cacheName
	 * @return
	 */
	public Cache get(String cacheName){
		return manager.getCache(cacheName);
	}
	
	/**
	 * 清除当前所有缓存
	 * @param cacheName
	 */
	public void remove(String cacheName){
		Cache cache=manager.getCache(cacheName);
		cache.removeAll();
	}
	
	
	/*public static void main(String[] args){
		*//**
		 * Ehcache管理器
		 *//*
		CacheManager cm=CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//获取一个缓存
		Cache cache=cm.getCache("simpleCode");
		//创建一个元素,Element
		Element el=new Element("test", "222");
		//存放一个元素，每一个元素都是Element
		cache.put(el);
		//根据存放的key值获取存放的元素
		Element element=cache.get("test");
		System.out.println(element.getValue());
		//清除所有缓存
		cache.removeAll();
	}*/
}
