package cn.baisee.dao;

import java.util.List;

import cn.baisee.common.dao.IBaseDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.User;

/**
 * �û����ݷ��ʲ�
 * @author Administrator
 *
 */
public interface IUserDao extends IBaseDao<User> {

	public void saveUserRole(String userId,String roleIds);
	
	public List<AuthorResources> queryUserAuthorTree(String userId) ;
}
