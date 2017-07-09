package cn.baisee.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class ContextUtil {

	/**
	 * ��ȡSpringWeb��ʽ���ص������Ķ���
	 * 
	 * import org.springframework.context.ApplicationContext;
	 * import org.springframework.context.support.ClassPathXmlApplicationContext;
		
		//ApplicationContext��spring�������Ķ���,ClassPathXmlApplicationContext��ͨ����·������
		//ApplicationContext ctx=new ClassPathXmlApplicationContext();
	 */
	public static ApplicationContext getContext(){
		/**
		 * ��web.xml��������request��ʹ�����ַ�����ȡ��request
		 * �ڸ���request.getSession().getServletContext()��ȡ������
		 */
		HttpServletRequest request = ((ServletRequestAttributes)
				RequestContextHolder.getRequestAttributes())
				.getRequest();
		return WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	}
	

	/**
	 * ��ȡ Spring ������ �е� Bean
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	/**
	 * ��ȡ Spring ������ �е� Bean
	 * @return
	 */
	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}

	/**
	 * ��ȡ Spring ������ �е� Bean
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return getContext().getBean(beanName, clazz);
	}
	
}
