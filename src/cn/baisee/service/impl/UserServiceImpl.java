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
	 * �û���¼
	 */
	@Override
	public User login(String uname, String upass) {
		return (User) userDao.queryHqlUnique("from User where uname=? and upass=?", uname,upass);
	}
	
	/**
	 * ��ѯȨ����
	 */
	@Override
	public String queryUserAuthorTree(String userId) {
		//��ѯ�õ������
		List<AuthorResources> results=userDao.queryUserAuthorTree(userId);
		//����һ����Դ���ĸ��ӹ�ϵ,key����Դ�ĸ��ڵ�
		Map<Integer,List<AuthorResources>> resultMap=new HashMap<Integer, List<AuthorResources>>();
		//ѭ������
		for(AuthorResources item:results){
			//��ȡ��ǰ�ڵ�ĸ�������Ƿ�Ϊ��
			List<AuthorResources> parents=resultMap.get(item.getParentId());
			if(parents ==null){
				parents=new ArrayList<AuthorResources>();
				parents.add(item);
			}else{
				parents.add(item);
			}
			//����ǰ���������
			resultMap.put(item.getParentId(),parents);
		}
		//JSONArray
		JSONArray array=new JSONArray();
		//������
		buildTree(null, array, resultMap);
		return array.toString();
	}

	/**
	 * ������
	 * @param 	parentId ��ǰ�ڵ�ĸ��ڵ�ID
	 * @param	array  ����JsonArray�Ķ�����һ���ģ����ڴ�Ž�array
	 */
	public void buildTree(Integer parentId, JSONArray array,Map<Integer,List<AuthorResources>> resultMap) {
		//�����ӽڵ�
		List<AuthorResources> childs=null;
		//��ѯ�Ƿ�Ϊ���ڵ�
		if(parentId==null){//���ڵ�
			childs=resultMap.get(null);
		}else{
			childs=resultMap.get(parentId);
		}
		for(AuthorResources child:childs){
			//��ǰ�ڵ�Ϊchild
			//��ѯ��ǰ�ڵ��Ƿ����ӽڵ�
			List<AuthorResources> cchilds=resultMap.get(child.getId());
			if(cchilds!=null && cchilds.size()>0){
				JSONArray childArray=new JSONArray();
				buildTree(child.getId(), childArray,resultMap);
				
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
	 * �û������ҳ
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
		//��ѯ��ҳ��Ϣ
		List<Object> results=userDao.queryHqlList(hql, vo.getStartIndex(), vo.getPageSize(), params);
		vo.setList(results);
		//������
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
