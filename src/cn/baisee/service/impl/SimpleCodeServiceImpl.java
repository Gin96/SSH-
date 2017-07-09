package cn.baisee.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.baisee.dao.ISimpleCodeDao;
import cn.baisee.entity.SimpleCode;
import cn.baisee.service.ISimpleCodeService;

@Service
@Transactional
public class SimpleCodeServiceImpl implements ISimpleCodeService{

	@Resource(name="simpleCodeDaoImpl")
	private ISimpleCodeDao simpleCodeDao;
	
	/**
	 * ����Code��ѯ��Ӧ��simple����
	 * @param code
	 * @return
	 */
	public SimpleCode queryByCode(String code){
		String hql="from SimpleCode where code=?";
		SimpleCode sc=(SimpleCode) simpleCodeDao.queryHqlUnique(hql, code);
		return sc;
	}

	/**
	 * ��ѯ���м򵥴���
	 */
	@Override
	public List<SimpleCode> queryAll() {
		String hql="from SimpleCode";
		List<SimpleCode> list=simpleCodeDao.queryAll(hql, null);
		return list;
	}
}
