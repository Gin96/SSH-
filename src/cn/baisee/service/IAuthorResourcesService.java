package cn.baisee.service;

import java.io.Serializable;

import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.vo.PageVo;

public interface IAuthorResourcesService {

	/**
	 * ��Դ�б��ѯ
	 * @param vo
	 * @return
	 */
	public PageVo toList(PageVo vo);
	
	/**
	 * ������߸���
	 * @param entity
	 */
	public void saveOrUpdate(AuthorResources entity);
	
	/**
	 * 
	 * ����qid��ѯ����
	 * @param qid
	 * @return
	 */
	public AuthorResources get(Serializable qid);
	
	/**
	 * ����didɾ������
	 * @param did
	 */
	public void delete(Serializable did);
}
