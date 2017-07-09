package cn.baisee.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.baisee.entity.vo.PageVo;
import cn.baisee.service.IUserService;

@Results({
	@Result(name="toList",location="/WEB-INF/jsp/admin/user/toList.jsp")
})
@ParentPackage("struts-default")
@Namespace("/user")
@Controller
public class UserAction implements ModelDriven<PageVo>{
	
	@Resource(name="userServiceImpl")
	private IUserService userService;
	
	private PageVo pageVo;
	
	/**
	 * 用户列表查询
	 * @return
	 */
	@Action("toList")
	public String toList(){
		pageVo=userService.toList(pageVo);
		return "toList";
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
	
	
}
