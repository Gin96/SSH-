package cn.baisee.tags;

import java.util.List;

import sun.java2d.pipe.SpanShapeRenderer.Simple;
import cn.baisee.entity.SimpleCode;
import cn.baisee.service.ISimpleCodeService;
import cn.baisee.utils.ContextUtil;
import cn.baisee.utils.EhcacheUtil;

/**
 * 自定义EL的表达式
 * @author Administrator
 *
 */
public class SimpleCodeTag {

	/**
	 * 根据CodeType返回相应的值
	 * @param codeType
	 * @return
	 */
	public static List<SimpleCode> getCodeByType(String codeType){
		List<SimpleCode> results=(List<SimpleCode>) EhcacheUtil.getInstance().get("simpleCode",codeType);
		return results;
	}
	
	
	/**
	 * 根据传来的Code返回相应的值
	 * @param code
	 * @return
	 */
	public static String formatSimpleCode(String code){
	
		/**
		 * 去数据库查询，获取Spring加载的上下文对象，再根据上下文获取Service对象
		 */
	/*	ISimpleCodeService codeService=(ISimpleCodeService) ContextUtil.getBean("simpleCodeServiceImpl");
		SimpleCode sc=codeService.queryByCode(code);
		if(sc!=null){
			return sc.getValue();
		}
		*/
		
		/**
		 * 去缓存里查询
		 */
		SimpleCode scode= (SimpleCode) EhcacheUtil.getInstance().get("simpleCode", code);
		if(scode!=null){
			return scode.getValue();
		}
		
		return "灰色用户";
	}
	
	
}
