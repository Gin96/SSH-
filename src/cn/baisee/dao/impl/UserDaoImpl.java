package cn.baisee.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.IUserDao;
import cn.baisee.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}

	@Override
	public Class<User> getClazz() {
		return User.class;
	}


}
