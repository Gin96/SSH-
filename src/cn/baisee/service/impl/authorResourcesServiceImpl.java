package cn.baisee.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IAuthorResourcesDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.vo.PageVo;
import cn.baisee.service.IAuthorResourcesService;

/**
 * 用户业务逻辑层
 * @author Administrator
 *
 */
@Service
@Transactional
public class authorResourcesServiceImpl implements IAuthorResourcesService{

	@Resource(name="authorResourcesDaoImpl")
	private IAuthorResourcesDao authorResourcesDao;

	@Override
	public PageVo toList(PageVo vo) {
		if(vo !=null && vo.getPage()==null){
			vo.setPage(1);
		}
		String hql="from AuthorResources where 1=1";
		String hqlCount="select count(*) from AuthorResources where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(vo.getParams()!=null){
			if(vo.getParams().get("parentId")!=null && !vo.getParams().get("parentId").equals("")){
				hql+="and parentId like ?";
				hqlCount+="and parentId like ?";
				params.add(Integer.valueOf(vo.getParams().get("parentId")));
			}
			
		}
		//查询分页信息
		List<Object> results=authorResourcesDao.queryHqlList(hql, vo.getStartIndex(), vo.getPageSize(), params);
		vo.setList(results);
		//总条数
		Integer count=authorResourcesDao.queryHqlCount(hqlCount, params);
		vo.setTotal(count);
		return vo;
	}

	/**
	 * 保存或者更新
	 */
	@Override
	public void saveOrUpdate(AuthorResources entity) {
		authorResourcesDao.saveOrUpdate(entity);
	}

	/**
	 * 根据qid查询
	 */
	@Override
	public AuthorResources get(Serializable qid) {
		return authorResourcesDao.get(qid);
	}

	@Override
	public void delete(Serializable did) {
		//判断是否有子节点
		List<AuthorResources> childs=authorResourcesDao.queryAll("from AuthorResources where parentId=?", did);
		if(childs.size()>0){
			for (AuthorResources item :childs) {
				delete(item.getId());
			}
		}else{
			authorResourcesDao.delete(did);
		}
	}

	
}
