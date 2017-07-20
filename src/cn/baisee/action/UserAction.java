package cn.baisee.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import cn.baisee.entity.User;
import cn.baisee.entity.vo.PageVo;
import cn.baisee.service.IUserService;

import com.opensymphony.xwork2.ModelDriven;


@Results({
	@Result(name="toList",location="/WEB-INF/jsp/admin/user/toList.jsp"),
	@Result(name="toAuthor",location="/WEB-INF/jsp/admin/user/toAuthor.jsp"),
	@Result(name="steptoList",type="redirectAction",params={"actionName","toList"})
})
@ParentPackage("struts-default")
@Namespace("/user")
@Controller
public class UserAction implements ModelDriven<PageVo>{
	
	@Resource(name="userServiceImpl")
	private IUserService userService;
	private User user;
	private PageVo pageVo;
	
	/**
	 * �û��б��ѯ
	 * @return
	 */
	@Action("toList")
	public String toList(){
		System.out.println("fangwein12WS");
		pageVo=userService.toList(pageVo);
		return "toList";
	}
	
	/**
	 * ��ת����ɫ��Ȩ����
	 * @return
	 */
	@Action("toAuthor")
	public 	String toAuthor(){
		return "toAuthor";
	}

	/**
	 * �����û��Ľ�ɫ
	 */
	@Action("saveAuthor")
	public String saveAuthor(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String userId=request.getParameter("userId");
		String roleIds=request.getParameter("roleIds");
		userService.saveUserRole(userId, roleIds);
		return "steptoList";
	}
	
	
	@Override
	public PageVo getModel() {
		if(pageVo==null){
			pageVo=new PageVo();
		}
		return pageVo;
	}
	
	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
