package cn.baisee.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IUserDao;
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

	
}
