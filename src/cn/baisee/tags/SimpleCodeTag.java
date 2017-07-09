package cn.baisee.tags;

import java.util.List;

import sun.java2d.pipe.SpanShapeRenderer.Simple;
import cn.baisee.entity.SimpleCode;
import cn.baisee.service.ISimpleCodeService;
import cn.baisee.utils.ContextUtil;
import cn.baisee.utils.EhcacheUtil;

/**
 * �Զ���EL�ı��ʽ
 * @author Administrator
 *
 */
public class SimpleCodeTag {

	/**
	 * ����CodeType������Ӧ��ֵ
	 * @param codeType
	 * @return
	 */
	public static List<SimpleCode> getCodeByType(String codeType){
		List<SimpleCode> results=(List<SimpleCode>) EhcacheUtil.getInstance().get("simpleCode",codeType);
		return results;
	}
	
	
	/**
	 * ���ݴ�����Code������Ӧ��ֵ
	 * @param code
	 * @return
	 */
	public static String formatSimpleCode(String code){
	
		/**
		 * ȥ���ݿ��ѯ����ȡSpring���ص������Ķ����ٸ��������Ļ�ȡService����
		 */
	/*	ISimpleCodeService codeService=(ISimpleCodeService) ContextUtil.getBean("simpleCodeServiceImpl");
		SimpleCode sc=codeService.queryByCode(code);
		if(sc!=null){
			return sc.getValue();
		}
		*/
		
		/**
		 * ȥ�������ѯ
		 */
		SimpleCode scode= (SimpleCode) EhcacheUtil.getInstance().get("simpleCode", code);
		if(scode!=null){
			return scode.getValue();
		}
		
		return "��ɫ�û�";
	}
	
	
}
