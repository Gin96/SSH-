package cn.baisee.service;

import cn.baisee.entity.User;
import cn.baisee.entity.vo.PageVo;

/**
 * �û�ҵ���߼���
 * @author Administrator
 *
 */
public interface IUserService {

	/**
	 * �û���¼
	 * @param uname
	 * @param upass
	 * @return
	 */
	public User login(String uname,String upass);
	
	/**
	 * �û���Ϣ��ҳ
	 */
	public PageVo toList(PageVo vo);
	
}
