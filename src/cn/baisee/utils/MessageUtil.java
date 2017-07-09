package cn.baisee.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.baisee.entity.Message;

/**
 * Message消息处理工具
 * @author Administrator
 *
 * 	前台是否使用Struts标签(ActiohnSupport)
 *  前台结合JSTL与EL表达式来使用
 */
public class MessageUtil {

	/**
	 * 增加一个消息到Request当中
	 */
	public static void addMessage(String message){
		//想存放在Request里
		List<Message> errors=(List<Message>) ServletActionContext.getRequest().getAttribute("errors");
		if(errors==null){
			errors=new ArrayList<Message>();
		}
		errors.add(new Message(message));
		
		//设置Message到Request中
		ServletActionContext.getRequest().setAttribute("errors", errors);
	}
}
