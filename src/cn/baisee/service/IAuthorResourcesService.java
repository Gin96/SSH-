package cn.baisee.service;

import java.io.Serializable;

import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.vo.PageVo;

public interface IAuthorResourcesService {

	/**
	 * 资源列表查询
	 * @param vo
	 * @return
	 */
	public PageVo toList(PageVo vo);
	
	/**
	 * 保存或者更新
	 * @param entity
	 */
	public void saveOrUpdate(AuthorResources entity);
	
	/**
	 * 
	 * 根据qid查询对象
	 * @param qid
	 * @return
	 */
	public AuthorResources get(Serializable qid);
	
	/**
	 * 根据did删除对象
	 * @param did
	 */
	public void delete(Serializable did);
}
