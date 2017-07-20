package cn.baisee.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IAuthorResourcesDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.vo.PageVo;
import cn.baisee.service.IAuthorResourcesService;

/**
 * �û�ҵ���߼���
 * @author Administrator
 *
 */
@Service
@Transactional
public class authorResourcesServiceImpl implements IAuthorResourcesService{

	@Resource(name="authorResourcesDaoImpl")
	private IAuthorResourcesDao authorResourcesDao;

	@Override
	public PageVo toList(PageVo vo) {
		if(vo !=null && vo.getPage()==null){
			vo.setPage(1);
		}else{
			vo=new PageVo();
		}
		String hql="from AuthorResources where 1=1";
		String hqlCount="select count(*) from AuthorResources where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(vo.getParams()!=null){
			if(vo.getParams().get("parentId")!=null && !vo.getParams().get("parentId").equals("")){
				hql+="and parentId like ?";
				hqlCount+="and parentId like ?";
				params.add(Integer.valueOf(vo.getParams().get("parentId")));
			}
			
		}
		//��ѯ��ҳ��Ϣ
		List<Object> results=authorResourcesDao.queryHqlList(hql, vo.getStartIndex(), vo.getPageSize(), params);
		vo.setList(results);
		//������
		Integer count=authorResourcesDao.queryHqlCount(hqlCount, params);
		vo.setTotal(count);
		return vo;
	}

	/**
	 * ������߸���
	 */
	@Override
	public void saveOrUpdate(AuthorResources entity) {
		authorResourcesDao.saveOrUpdate(entity);
	}

	/**
	 * ����qid��ѯ
	 */
	@Override
	public AuthorResources get(Serializable qid) {
		return authorResourcesDao.get(qid);
	}

	@Override
	public void delete(Serializable did) {
		//�ж��Ƿ����ӽڵ�
		List<AuthorResources> childs=authorResourcesDao.queryAll("from AuthorResources where parentId=?", did);
		if(childs.size()>0){
			for (AuthorResources item :childs) {
				delete(item.getId());
			}
		}else{
			authorResourcesDao.delete(did);
		}
	}

	


	
	/**
	 * ��ѯ������Դ
	 */
	@Override
	public String queryAll() {
		JSONArray array=new JSONArray();
		buildTree(null, array);
		return array.toString();
	}

	/**
	 * ������
	 * @param 	parentId ��ǰ�ڵ�ĸ��ڵ�ID
	 * @param	array  ����JsonArray�Ķ�����һ���ģ����ڴ�Ž�array
	 */
	public void buildTree(Integer parentId, JSONArray array) {
		//�����ӽڵ�
		List<AuthorResources> childs=null;
		//��ѯ�Ƿ�Ϊ���ڵ�
		if(parentId==null){//���ڵ�
			childs=authorResourcesDao.queryAll("from AuthorResources where parentId is null");
		}else{
			childs=authorResourcesDao.queryAll("from AuthorResources where parentId=?",parentId);
		}
		for(AuthorResources child:childs){
			//��ǰ�ڵ�Ϊchild
			//��ѯ��ǰ�ڵ��Ƿ����ӽڵ�
			List<AuthorResources> cchilds=authorResourcesDao.queryAll("from AuthorResources where parentId =?", child.getId());
			if(cchilds.size()>0){
				JSONArray childArray=new JSONArray();
				buildTree(child.getId(), childArray);
				
				JSONObject childObject=JSONObject.fromObject(child);
				//�൱��map.put��ֵ��(key,value),����Ϊchildren���ӽڵ�childArray����
				//�������һ�����ڵ�childObject����
				childObject.element("children", childArray);
				array.add(childObject);
			}else{
				//û���ӽڵ�
				array.add(JSONObject.fromObject(child));
			}
		}
	}
	
	/**
	 * JSON��ʽ��
	 */
//	public static void main(String[] args){
//		
//		//һ������
//		JSONObject object=JSONObject.fromObject(new AuthorResources());
//		System.out.println(object.toString());
//		//һ������
//		JSONArray array=new JSONArray();
//		array.add(object);
//		System.out.println(array.toString());
//	}
}
