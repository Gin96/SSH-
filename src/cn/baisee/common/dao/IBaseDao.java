package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	
	/**
	 * ���ñ��淽��
	 * @param entity
	 */
	public void sava(Object entity);
	
	/**
	 * ���ø��·���
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * ������߸���
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	
	/**
	 * ��ѯ
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid);
	
	/**
	 * ɾ��������ɾ����Ҫ��ѯ��
	 * @param did
	 * @return 
	 */
	public void delete(Serializable did);
	
	/**
	 * ɾ���������
	 * @param dids
	 */
	public void deltetMall(Serializable... dids);
	
	/**
	 * HQL��ҳ
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params);
	
	/**
	 * HQL��������ѯ
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params);
	
	/**
	 * ��ѯ��һ�ĵ�ǰ����
	 * 
	 * @param hql  ��ѯHql
	 * @param args  Hql��Ӧ�Ĳ���
	 * @return
	 */
	public Object queryHqlUnique(String hql, Serializable... args);
	
	/**
	 * HQL��ѯ��������
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> queryAll(String hql,Serializable... params);

	/**
	 * ����
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHQL(String hql,Serializable...args);
}
