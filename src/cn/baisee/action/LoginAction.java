package cn.baisee.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.baisee.entity.User;
import cn.baisee.service.IUserService;
import cn.baisee.utils.MessageUtil;

@Results({
	@Result(name="success",location="/WEB-INF/jsp/admin/main.jsp"),
	@Result(name="error",location="/login.jsp")
})
@ParentPackage("struts-default")
@Namespace("/")
@Controller
public class LoginAction implements ModelDriven<User>{

	@Resource(name="userServiceImpl")
	private IUserService userSerivce;
	private User user;
	private String loginCode;
	
	@Action("login")
	public String login(){
		System.out.println("访问LoginAction");
		User loginUser=userSerivce.login(user.getUname(), user.getUpass());
		System.out.println(loginUser);
		//认真错误信息
		if(loginUser==null){
			MessageUtil.addMessage("用户名或密码错误！");
			return "error";
		}
		return "success";
	}
	
	@Action("queryUserAuthorTree")
	public void queryUserAuthorTree(){
		String results=userSerivce.queryUserAuthorTree("1");
		//保存权限
		ServletActionContext.getRequest().getSession().setAttribute("authorTree",results);
		try {
			//输出对象
			HttpServletResponse response=ServletActionContext.getResponse();
			//设置输出格式
			response.setContentType("text/html;charset=utf-8");
			PrintWriter write=response.getWriter();
			write.write(results);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public User getModel() {
		if(user==null){
			user=new User();
		}
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getLoginCode() {
		return loginCode;
	}


	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	
	
}
