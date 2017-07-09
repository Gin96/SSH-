package cn.baisee.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.IAuthorResourcesDao;
import cn.baisee.entity.AuthorResources;
/**
 * ×ÊÔ´Ê÷
 * @author Administrator
 *
 */
@Repository
public class AuthorResourcesDaoImpl extends BaseDaoImpl<AuthorResources> implements IAuthorResourcesDao{

	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}

	@Override
	public Class<AuthorResources> getClazz() {
		return AuthorResources.class;
	}

}
