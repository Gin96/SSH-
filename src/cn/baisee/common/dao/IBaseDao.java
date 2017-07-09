package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	
	/**
	 * 公用保存方法
	 * @param entity
	 */
	public void sava(Object entity);
	
	/**
	 * 公用更新方法
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * 保存或者更新
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	
	/**
	 * 查询
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid);
	
	/**
	 * 删除方法，删除先要查询到
	 * @param did
	 * @return 
	 */
	public void delete(Serializable did);
	
	/**
	 * 删除多个方法
	 * @param dids
	 */
	public void deltetMall(Serializable... dids);
	
	/**
	 * HQL分页
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params);
	
	/**
	 * HQL总条数查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params);
	
	/**
	 * 查询单一的当前对象
	 * 
	 * @param hql  查询Hql
	 * @param args  Hql对应的参数
	 * @return
	 */
	public Object queryHqlUnique(String hql, Serializable... args);
	
	/**
	 * HQL查询所有数据
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> queryAll(String hql,Serializable... params);

	/**
	 * 更新
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHQL(String hql,Serializable...args);
}
