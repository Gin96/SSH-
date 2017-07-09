package cn.baisee.service;

import java.util.List;

import cn.baisee.entity.SimpleCode;

public interface ISimpleCodeService {

	/**
	 * 查询所有简单代码
	 * @return
	 */
	public List<SimpleCode> queryAll();
	/**
	 * 根据code值查询SimpleCode
	 * @param code
	 * @return
	 */
	public SimpleCode queryByCode(String code);
}
