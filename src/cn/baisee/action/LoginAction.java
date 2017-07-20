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
		System.out.println("����LoginAction");
		User loginUser=userSerivce.login(user.getUname(), user.getUpass());
		System.out.println(loginUser);
		//���������Ϣ
		if(loginUser==null){
			MessageUtil.addMessage("�û������������");
			return "error";
		}
		return "success";
	}
	
	@Action("queryUserAuthorTree")
	public void queryUserAuthorTree(){
		String results=userSerivce.queryUserAuthorTree("1");
		//����Ȩ��
		ServletActionContext.getRequest().getSession().setAttribute("authorTree",results);
		try {
			//�������
			HttpServletResponse response=ServletActionContext.getResponse();
			//���������ʽ
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
