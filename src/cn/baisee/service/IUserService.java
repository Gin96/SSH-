package cn.baisee.service;

import cn.baisee.entity.User;
import cn.baisee.entity.vo.PageVo;

/**
 * 用户业务逻辑层
 * @author Administrator
 *
 */
public interface IUserService {

	/**
	 * 用户登录
	 * @param uname
	 * @param upass
	 * @return
	 */
	public User login(String uname,String upass);
	
	/**
	 * 用户信息分页
	 */
	public PageVo toList(PageVo vo);
	
}
