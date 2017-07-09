package cn.baisee.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.ISimpleCodeDao;
import cn.baisee.entity.SimpleCode;

@Repository
public class SimpleCodeDaoImpl extends BaseDaoImpl<SimpleCode> implements ISimpleCodeDao{

	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}

	@Override
	public Class<SimpleCode> getClazz() {
		return SimpleCode.class;
	}


}
