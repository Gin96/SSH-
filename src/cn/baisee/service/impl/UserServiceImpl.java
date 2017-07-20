package cn.baisee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IUserDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.User;
import cn.baisee.entity.vo.PageVo;
import cn.baisee.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	@Resource(name="userDaoImpl")
	private IUserDao userDao;

	/**
	 * 用户登录
	 */
	@Override
	public User login(String uname, String upass) {
		return (User) userDao.queryHqlUnique("from User where uname=? and upass=?", uname,upass);
	}
	
	/**
	 * 查询权限树
	 */
	@Override
	public String queryUserAuthorTree(String userId) {
		//查询得到结果集
		List<AuthorResources> results=userDao.queryUserAuthorTree(userId);
		//分析一下资源树的父子关系,key是资源的父节点
		Map<Integer,List<AuthorResources>> resultMap=new HashMap<Integer, List<AuthorResources>>();
		//循环分析
		for(AuthorResources item:results){
			//获取当前节点的父结果集是否为空
			List<AuthorResources> parents=resultMap.get(item.getParentId());
			if(parents ==null){
				parents=new ArrayList<AuthorResources>();
				parents.add(item);
			}else{
				parents.add(item);
			}
			//将当前结果集回填
			resultMap.put(item.getParentId(),parents);
		}
		//JSONArray
		JSONArray array=new JSONArray();
		//构建树
		buildTree(null, array, resultMap);
		return array.toString();
	}

	/**
	 * 构建树
	 * @param 	parentId 当前节点的父节点ID
	 * @param	array  构建JsonArray的对象，上一级的，用于存放进array
	 */
	public void buildTree(Integer parentId, JSONArray array,Map<Integer,List<AuthorResources>> resultMap) {
		//声明子节点
		List<AuthorResources> childs=null;
		//查询是否为根节点
		if(parentId==null){//根节点
			childs=resultMap.get(null);
		}else{
			childs=resultMap.get(parentId);
		}
		for(AuthorResources child:childs){
			//当前节点为child
			//查询当前节点是否还有子节点
			List<AuthorResources> cchilds=resultMap.get(child.getId());
			if(cchilds!=null && cchilds.size()>0){
				JSONArray childArray=new JSONArray();
				buildTree(child.getId(), childArray,resultMap);
				
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
	 * 用户管理分页
	 */
	@Override
	public PageVo toList(PageVo vo) {
		
		if(vo !=null && vo.getPage()==null){
			vo.setPage(1);
		}
		String hql="from User where 1=1";
		String hqlCount="select count(*) from User where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(vo.getParams()!=null){
			if(vo.getParams().get("uname")!=null && !vo.getParams().get("uname").equals("")){
				hql+="and uname like ?";
				hqlCount+="and uname like ?";
				params.add("%"+vo.getParams().get("uname")+"%");
			}
			
			if(vo.getParams().get("email")!=null && !vo.getParams().get("email").equals("")){
				hql+="and email like ?";
				hqlCount+="and email like ?";
				params.add("%"+vo.getParams().get("email")+"%");
			}
			
			if(vo.getParams().get("phone")!=null && !vo.getParams().get("phone").equals("")){
				hql+="and phone like ?";
				hqlCount+="and phone like ?";
				params.add("%"+vo.getParams().get("phone")+"%");
			}
		}
		//查询分页信息
		List<Object> results=userDao.queryHqlList(hql, vo.getStartIndex(), vo.getPageSize(), params);
		vo.setList(results);
		//总条数
		Integer count=userDao.queryHqlCount(hqlCount, params);
		vo.setTotal(count);
		
		return vo;
	}

	@Override
	public void saveUserRole(String userId, String roleIds) {
		if(userId != null && roleIds !=null){
			userDao.saveUserRole(userId, roleIds);
		}
	}


}
