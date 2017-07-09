package cn.baisee.entity;

/**
 * 消息对象
 * @author Administrator
 *
 */
public class Message {
	
	private String context;//消息内容
	
	public Message() {
		super();
	}

	public Message(String context) {
		super();
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
