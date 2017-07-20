package cn.baisee.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.IUserDao;
import cn.baisee.entity.AuthorResources;
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

	/**
	 * 保存用户授权的角色
	 */
	@Override
	public void saveUserRole(String userId, String roleIds) {
		//先删除已经保存的
		updateSQL("delete from author_user_role where userId=?", userId);
		//查分多个资源的ID
		String[] rids=roleIds.split(",");
		for(String roleId:rids){
			//循环角色ID 保存用户角色
			updateSQL("insert into author_user_role (userId,roleId) value (?,?)", userId,roleId);
		}
		
	}

	/**
	 * 查询用户权限树
	 */
	@Override
	public List<AuthorResources> queryUserAuthorTree(String userId) {
		String sql="select DISTINCT  author_resources.* from author_user_role "
				+ "LEFT JOIN author_role_resouces on author_user_role.roleId=author_role_resouces.roleId "
				+ "LEFT JOIN author_resources on author_role_resouces.resoucesId=author_resources.id "
				+ "WHERE author_user_role.userId=?";
		Session session=template.getSessionFactory().getCurrentSession();
		//将查询结果集转换成对象，直接转
		Query query=session.createSQLQuery(sql).setResultTransformer(new AliasToBeanResultTransformer(AuthorResources.class));
		query.setParameter(0, userId);
		List<AuthorResources> results=query.list();
		return results;
	}


}
