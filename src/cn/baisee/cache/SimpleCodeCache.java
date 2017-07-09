package cn.baisee.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.baisee.entity.SimpleCode;
import cn.baisee.service.ISimpleCodeService;
import cn.baisee.utils.EhcacheUtil;

/**
 * 简单代码缓存
 * @Component：普通对象实例化到spring容器中
 * @author Administrator
 *
 */
@Component
public class SimpleCodeCache {

	@Resource(name="simpleCodeServiceImpl")
	private ISimpleCodeService simpleCodeService;
	
	//@PostConstruct：spring自动调用当前方法
	@PostConstruct
	public void init(){
		//查询所有的结果集
		List<SimpleCode> results=simpleCodeService.queryAll();
		//根据类别，给SimpleCode进行分类整理
		Map<String,List<SimpleCode>> maps=new HashMap<String, List<SimpleCode>>();
		
		//存放缓存
		for(SimpleCode item:results){
			System.out.println(item.getValue());
			EhcacheUtil.getInstance().put("simpleCode", item.getCode(), item);
			
			//分析当前代码的简单类别是什么
			List<SimpleCode> list=maps.get(item.getCodeType());
			if(list==null){
				list=new ArrayList<SimpleCode>();
				list.add(item);
				maps.put(item.getCodeType(), list);
			}else{
				list.add(item);
				maps.put(item.getCodeType(), list);
			}
		}
		//根据类别进行分类缓存
		Iterator<String> keys=maps.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next();
			EhcacheUtil.getInstance().put("simpleCode", key, maps.get(key));
		}
		System.out.println("初始化调用我了！");
	}
}
