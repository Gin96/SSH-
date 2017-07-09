package cn.baisee.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class ContextUtil {

	/**
	 * 获取SpringWeb方式加载的上下文对象
	 * 
	 * import org.springframework.context.ApplicationContext;
	 * import org.springframework.context.support.ClassPathXmlApplicationContext;
		
		//ApplicationContext是spring的上下文对象,ClassPathXmlApplicationContext是通过类路径加载
		//ApplicationContext ctx=new ClassPathXmlApplicationContext();
	 */
	public static ApplicationContext getContext(){
		/**
		 * 在web.xml中设置了request，使用这种方法获取到request
		 * 在根据request.getSession().getServletContext()获取上下文
		 */
		HttpServletRequest request = ((ServletRequestAttributes)
				RequestContextHolder.getRequestAttributes())
				.getRequest();
		return WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	}
	

	/**
	 * 获取 Spring 上下文 中的 Bean
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	/**
	 * 获取 Spring 上下文 中的 Bean
	 * @return
	 */
	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}

	/**
	 * 获取 Spring 上下文 中的 Bean
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return getContext().getBean(beanName, clazz);
	}
	
}
