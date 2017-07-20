package cn.baisee.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.vo.PageVo;
import cn.baisee.service.IAuthorResourcesService;

import com.opensymphony.xwork2.ModelDriven;

@Results({
	@Result(name="toList",location="/WEB-INF/jsp/admin/author/res/toList.jsp"),
	@Result(name="toAdd",location="/WEB-INF/jsp/admin/author/res/add.jsp"),
	@Result(name="steptoAdd",type="redirectAction",params={"namespace","/res","actionName","toAdd","res.id","%{res.id}"}),
	@Result(name="steptoList",type="redirectAction",params={"namespace","/res","actionName","toList"})
})
@ParentPackage("authorPackage")
@Namespace("/res")
@Controller
public class AuthorResourcesAction implements ModelDriven<PageVo>{

	@Resource(name="authorResourcesServiceImpl")
	private IAuthorResourcesService resService;
	private AuthorResources res;
	private PageVo pageVo;

	@Action("testExc")
	public String testExc() throws Exception{ 
		if(1==1){
			throw new RuntimeException("sjka");
		}
		return "login";
	}
	
	
	/**
	 * 分页显示
	 * @return
	 */
	@Action(value="toList")
	public String toList(){
		pageVo=resService.toList(pageVo);
		return "toList";
	}
	
	/**
	 * 跳转到add.jsp
	 * 未添加直接跳转，如果是添加子节点，则根据封装的res直接显示到add.jsp页面
	 * 添加成功，添加成功则有id，根据id再次查询显示在页面，可以再次对数据操作，直接更新
	 * @return
	 */
	@Action(value="toAdd")
	public String toAdd(){
		if(res!=null && res.getId()!=null){
			res=resService.get(res.getId());
		}		
		return "toAdd";
	}
	
	/**
	 * 保存或更新数据，之后再次跳转到add.jsp页面可以显示数据，或者进行二次更新，自动回显
	 * @return
	 */
	@Action(value="add")
	public String add(){
		res.setCreateTs(new Date());
		resService.saveOrUpdate(res);
		return "toAdd";//之前是steptoAdd
	}
	
	/**
	 * 递归算法
	 * 	  递归算法是一种直接或者间接地调用自身算法的过程。
	 * 删除节点->判断是否存在子节点->不存在直接删除
	 * 						 ->存在，查询子节点中是否存在子节点->不存在，删除子节点，删除节点
	 * 						 ->子节点存在节点，循环
	 * @return
	 */
	@Action(value="delete")
	public String delete(){
		resService.delete(res.getId());
		return "steptoList";
	}
	
	/**
	 * 查询出所有资源显示在角色授权管理页面
	 * @return
	 */
	@Action("all")
	public void all(){
		//查询出的资源树
		String results=resService.queryAll();
		try {
			//数处对象
			HttpServletResponse response=ServletActionContext.getResponse();
			//设置输出格式
			response.setContentType("text/html;charset=utf-8");
			//PrintWriter是一种过滤流，也叫处理流，能对字节流（OutputStream）
			//和字符流(Writer)进行处理
			PrintWriter writer=response.getWriter();
			writer.write(results);
			writer.flush();
			System.out.println(results);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public AuthorResources getRes() {
		return res;
	}

	public void setRes(AuthorResources res) {
		this.res = res;
	}

}
