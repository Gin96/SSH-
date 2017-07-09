package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * ����Daoʵ��
 * 	��ɾ��ģ���ҳ(HQL,SQL) HQL���£�SQL����
 * @author Administrator
 *
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {

	protected HibernateTemplate template;

	public abstract void setTemplate(HibernateTemplate template) ;
	
	public abstract Class<T> getClazz();
	
	/**
	 * ���ñ��淽��
	 * @param entity
	 */
	public void sava(Object entity){
		template.save(entity);
	}
	
	/**
	 * ���ø��·���
	 * @param entity
	 */
	public void update(Object entity){
		template.update(template);
	}
	
	/**
	 * ������߸���
	 * @param entity
	 */
	public void saveOrUpdate(Object entity){
		template.saveOrUpdate(entity);
	}
	
	/**
	 * ��ѯ
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid){
		return template.get(getClazz(), qid);
	}
	
	/**
	 * ɾ��������ɾ����Ҫ��ѯ��
	 * @param did
	 */
	public void delete(Serializable did){
		template.delete(get(did));
	}
	
	/**
	 * ɾ���������
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
	 * HQL��ҳ
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params){
		//��ȡ�󶨵���ǰ�̵߳�session
		Session session=template.getSessionFactory().getCurrentSession();
		//������ѯ���
		Query query=session.createQuery(hql);
		//���ò���
		if(params!=null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i)); 
			}
		}
		//���÷�ҳ����
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		//�õ���ѯ���
		
		return query.list();
	}
	
	/**
	 * HQL��������ѯ
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params){
		//��ȡ�󶨵���ǰ�̵߳�session
		Session session=template.getSessionFactory().getCurrentSession();
		//������ѯ���
		Query query=session.createQuery(hql);
		//���ò���
		if(params!=null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i)); 
			}
		}
		//�õ���ѯ���
		Object count=query.uniqueResult();
		return Integer.valueOf(String.valueOf(count));
	}
	
	/**
	 * ��ѯ��һ�ĵ�ǰ����
	 * 
	 * @param hql  ��ѯHql
	 * @param args  Hql��Ӧ�Ĳ���
	 * @return
	 */
	public Object queryHqlUnique(String hql, Serializable... args) {
		// ��ȡ��ǰSpring���Ƶ�Session
		Session session = template.getSessionFactory().getCurrentSession();
		// ������ѯ
		Query query = session.createQuery(hql);
		// ���ò�ѯ����
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		// ��ѯ���������
		Object object = query.uniqueResult();

		return object;
	}
	
	/**
	 * HQL��ѯ��������
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> queryAll(String hql,Serializable... params){
		//��ȡ�󶨵���ǰ�̵߳�session
		Session session=template.getSessionFactory().getCurrentSession();
		//������ѯ���
		Query query=session.createQuery(hql);
		//���ò���
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]); 
			}
		}
		//�õ���ѯ���
		List<T> all=query.list();
		return all;
	}

	/**
	 * ����
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHQL(String hql,Serializable...args){
		//��ȡ��ǰ�߳�Session
		Session session=template.getSessionFactory().getCurrentSession();
		//������ѯ
		Query query=session.createQuery(hql);
		//���ò�ѯ����
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		int row=query.executeUpdate();
		return row;
	}
}
