package cn.baisee.service;

import java.util.List;

import cn.baisee.entity.SimpleCode;

public interface ISimpleCodeService {

	/**
	 * ��ѯ���м򵥴���
	 * @return
	 */
	public List<SimpleCode> queryAll();
	/**
	 * ����codeֵ��ѯSimpleCode
	 * @param code
	 * @return
	 */
	public SimpleCode queryByCode(String code);
}
