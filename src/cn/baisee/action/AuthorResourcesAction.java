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
	 * ��ҳ��ʾ
	 * @return
	 */
	@Action(value="toList")
	public String toList(){
		pageVo=resService.toList(pageVo);
		return "toList";
	}
	
	/**
	 * ��ת��add.jsp
	 * δ���ֱ����ת�����������ӽڵ㣬����ݷ�װ��resֱ����ʾ��add.jspҳ��
	 * ��ӳɹ�����ӳɹ�����id������id�ٴβ�ѯ��ʾ��ҳ�棬�����ٴζ����ݲ�����ֱ�Ӹ���
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
	 * �����������ݣ�֮���ٴ���ת��add.jspҳ�������ʾ���ݣ����߽��ж��θ��£��Զ�����
	 * @return
	 */
	@Action(value="add")
	public String add(){
		res.setCreateTs(new Date());
		resService.saveOrUpdate(res);
		return "toAdd";//֮ǰ��steptoAdd
	}
	
	/**
	 * �ݹ��㷨
	 * 	  �ݹ��㷨��һ��ֱ�ӻ��߼�ӵص��������㷨�Ĺ��̡�
	 * ɾ���ڵ�->�ж��Ƿ�����ӽڵ�->������ֱ��ɾ��
	 * 						 ->���ڣ���ѯ�ӽڵ����Ƿ�����ӽڵ�->�����ڣ�ɾ���ӽڵ㣬ɾ���ڵ�
	 * 						 ->�ӽڵ���ڽڵ㣬ѭ��
	 * @return
	 */
	@Action(value="delete")
	public String delete(){
		resService.delete(res.getId());
		return "steptoList";
	}
	
	/**
	 * ��ѯ��������Դ��ʾ�ڽ�ɫ��Ȩ����ҳ��
	 * @return
	 */
	@Action("all")
	public void all(){
		//��ѯ������Դ��
		String results=resService.queryAll();
		try {
			//��������
			HttpServletResponse response=ServletActionContext.getResponse();
			//���������ʽ
			response.setContentType("text/html;charset=utf-8");
			//PrintWriter��һ�ֹ�������Ҳ�д��������ܶ��ֽ�����OutputStream��
			//���ַ���(Writer)���д���
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
