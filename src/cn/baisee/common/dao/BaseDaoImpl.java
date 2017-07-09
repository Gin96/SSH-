package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * 公用Dao实现
 * 	增删查改，分页(HQL,SQL) HQL更新，SQL更新
 * @author Administrator
 *
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {

	protected HibernateTemplate template;

	public abstract void setTemplate(HibernateTemplate template) ;
	
	public abstract Class<T> getClazz();
	
	/**
	 * 公用保存方法
	 * @param entity
	 */
	public void sava(Object entity){
		template.save(entity);
	}
	
	/**
	 * 公用更新方法
	 * @param entity
	 */
	public void update(Object entity){
		template.update(template);
	}
	
	/**
	 * 保存或者更新
	 * @param entity
	 */
	public void saveOrUpdate(Object entity){
		template.saveOrUpdate(entity);
	}
	
	/**
	 * 查询
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid){
		return template.get(getClazz(), qid);
	}
	
	/**
	 * 删除方法，删除先要查询到
	 * @param did
	 */
	public void delete(Serializable did){
		template.delete(get(did));
	}
	
	/**
	 * 删除多个方法
	 * @param dids
	 */
	public void deltetMall(Serializable... dids){
		if(dids!=null){
			for (int i = 0; i < dids.length; i++) {
				template.delete(get(dids[i]));
			}
		}
	}
	
	/**
	 * HQL分页
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params){
		//获取绑定到当前线程的session
		Session session=template.getSessionFactory().getCurrentSession();
		//创建查询语句
		Query query=session.createQuery(hql);
		//设置参数
		if(params!=null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i)); 
			}
		}
		//设置分页参数
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		//得到查询结果
		
		return query.list();
	}
	
	/**
	 * HQL总条数查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params){
		//获取绑定到当前线程的session
		Session session=template.getSessionFactory().getCurrentSession();
		//创建查询语句
		Query query=session.createQuery(hql);
		//设置参数
		if(params!=null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i)); 
			}
		}
		//得到查询结果
		Object count=query.uniqueResult();
		return Integer.valueOf(String.valueOf(count));
	}
	
	/**
	 * 查询单一的当前对象
	 * 
	 * @param hql  查询Hql
	 * @param args  Hql对应的参数
	 * @return
	 */
	public Object queryHqlUnique(String hql, Serializable... args) {
		// 获取当前Spring控制的Session
		Session session = template.getSessionFactory().getCurrentSession();
		// 创建查询
		Query query = session.createQuery(hql);
		// 设置查询参数
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		// 查询单个结果集
		Object object = query.uniqueResult();

		return object;
	}
	
	/**
	 * HQL查询所有数据
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> queryAll(String hql,Serializable... params){
		//获取绑定到当前线程的session
		Session session=template.getSessionFactory().getCurrentSession();
		//创建查询语句
		Query query=session.createQuery(hql);
		//设置参数
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]); 
			}
		}
		//得到查询结果
		List<T> all=query.list();
		return all;
	}

	/**
	 * 更新
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHQL(String hql,Serializable...args){
		//获取当前线程Session
		Session session=template.getSessionFactory().getCurrentSession();
		//创建查询
		Query query=session.createQuery(hql);
		//设置查询参数
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		int row=query.executeUpdate();
		return row;
	}
}
