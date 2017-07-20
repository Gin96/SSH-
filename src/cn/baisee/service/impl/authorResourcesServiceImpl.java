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
 * 用户业务逻辑层
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
		//查询分页信息
		List<Object> results=authorResourcesDao.queryHqlList(hql, vo.getStartIndex(), vo.getPageSize(), params);
		vo.setList(results);
		//总条数
		Integer count=authorResourcesDao.queryHqlCount(hqlCount, params);
		vo.setTotal(count);
		return vo;
	}

	/**
	 * 保存或者更新
	 */
	@Override
	public void saveOrUpdate(AuthorResources entity) {
		authorResourcesDao.saveOrUpdate(entity);
	}

	/**
	 * 根据qid查询
	 */
	@Override
	public AuthorResources get(Serializable qid) {
		return authorResourcesDao.get(qid);
	}

	@Override
	public void delete(Serializable did) {
		//判断是否有子节点
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
	 * 查询所有资源
	 */
	@Override
	public String queryAll() {
		JSONArray array=new JSONArray();
		buildTree(null, array);
		return array.toString();
	}

	/**
	 * 构建树
	 * @param 	parentId 当前节点的父节点ID
	 * @param	array  构建JsonArray的对象，上一级的，用于存放进array
	 */
	public void buildTree(Integer parentId, JSONArray array) {
		//声明子节点
		List<AuthorResources> childs=null;
		//查询是否为根节点
		if(parentId==null){//根节点
			childs=authorResourcesDao.queryAll("from AuthorResources where parentId is null");
		}else{
			childs=authorResourcesDao.queryAll("from AuthorResources where parentId=?",parentId);
		}
		for(AuthorResources child:childs){
			//当前节点为child
			//查询当前节点是否还有子节点
			List<AuthorResources> cchilds=authorResourcesDao.queryAll("from AuthorResources where parentId =?", child.getId());
			if(cchilds.size()>0){
				JSONArray childArray=new JSONArray();
				buildTree(child.getId(), childArray);
				
				JSONObject childObject=JSONObject.fromObject(child);
				//相当于map.put存值，(key,value),把名为children的子节点childArray数组
				//存放在上一级父节点childObject对象
				childObject.element("children", childArray);
				array.add(childObject);
			}else{
				//没有子节点
				array.add(JSONObject.fromObject(child));
			}
		}
	}
	
	/**
	 * JSON格式化
	 */
//	public static void main(String[] args){
//		
//		//一个对象
//		JSONObject object=JSONObject.fromObject(new AuthorResources());
//		System.out.println(object.toString());
//		//一个数组
//		JSONArray array=new JSONArray();
//		array.add(object);
//		System.out.println(array.toString());
//	}
}
