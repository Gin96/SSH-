package cn.baisee.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.baisee.entity.Message;

/**
 * Message��Ϣ������
 * @author Administrator
 *
 * 	ǰ̨�Ƿ�ʹ��Struts��ǩ(ActiohnSupport)
 *  ǰ̨���JSTL��EL���ʽ��ʹ��
 */
public class MessageUtil {

	/**
	 * ����һ����Ϣ��Request����
	 */
	public static void addMessage(String message){
		//������Request��
		List<Message> errors=(List<Message>) ServletActionContext.getRequest().getAttribute("errors");
		if(errors==null){
			errors=new ArrayList<Message>();
		}
		errors.add(new Message(message));
		
		//����Message��Request��
		ServletActionContext.getRequest().setAttribute("errors", errors);
	}
}
