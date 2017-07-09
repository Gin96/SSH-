package cn.baisee.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Ehcache������
 * @author Administrator
 *
 */
public class EhcacheUtil {
	
	//���������
	private CacheManager manager;
	
	//���湤����
	private static EhcacheUtil ehCache;

	//���캯��
	public EhcacheUtil() {
		manager=CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream("ehcache.xml"));
	}
	
	//����ģʽ
	public static EhcacheUtil getInstance(){
		if(ehCache==null){
			ehCache=new EhcacheUtil();
		}
		return ehCache;
	}
	
	/**
	 * ���һ������
	 * @param cacheName	��������
	 * @param key ����Key
	 * @param value ����ֵ
	 */
	public void put(String cacheName,String key,Object value){
		Cache cache=manager.getCache(cacheName);
		Element element=new Element(key, value);
		cache.put(element);
	}
	
	/**
	 * ��ȡ������ĳ��Ԫ��
	 * @param cacheName ��������
	 * @param key	�������ݵ�key
	 * @return
	 */
	public Object get(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
	
	/**
	 * ��ȡ����	 
	 * @param cacheName
	 * @return
	 */
	public Cache get(String cacheName){
		return manager.getCache(cacheName);
	}
	
	/**
	 * �����ǰ���л���
	 * @param cacheName
	 */
	public void remove(String cacheName){
		Cache cache=manager.getCache(cacheName);
		cache.removeAll();
	}
	
	
	/*public static void main(String[] args){
		*//**
		 * Ehcache������
		 *//*
		CacheManager cm=CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		//��ȡһ������
		Cache cache=cm.getCache("simpleCode");
		//����һ��Ԫ��,Element
		Element el=new Element("test", "222");
		//���һ��Ԫ�أ�ÿһ��Ԫ�ض���Element
		cache.put(el);
		//���ݴ�ŵ�keyֵ��ȡ��ŵ�Ԫ��
		Element element=cache.get("test");
		System.out.println(element.getValue());
		//������л���
		cache.removeAll();
	}*/
}
