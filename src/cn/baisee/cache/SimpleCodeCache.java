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
 * �򵥴��뻺��
 * @Component����ͨ����ʵ������spring������
 * @author Administrator
 *
 */
@Component
public class SimpleCodeCache {

	@Resource(name="simpleCodeServiceImpl")
	private ISimpleCodeService simpleCodeService;
	
	//@PostConstruct��spring�Զ����õ�ǰ����
	@PostConstruct
	public void init(){
		//��ѯ���еĽ����
		List<SimpleCode> results=simpleCodeService.queryAll();
		//������𣬸�SimpleCode���з�������
		Map<String,List<SimpleCode>> maps=new HashMap<String, List<SimpleCode>>();
		
		//��Ż���
		for(SimpleCode item:results){
			System.out.println(item.getValue());
			EhcacheUtil.getInstance().put("simpleCode", item.getCode(), item);
			
			//������ǰ����ļ������ʲô
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
		//���������з��໺��
		Iterator<String> keys=maps.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next();
			EhcacheUtil.getInstance().put("simpleCode", key, maps.get(key));
		}
		System.out.println("��ʼ���������ˣ�");
	}
}
