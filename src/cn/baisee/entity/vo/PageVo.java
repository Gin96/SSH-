package cn.baisee.entity.vo;

import java.util.List;
import java.util.Map;

/**
 * ��ҳ������
 * @author Administrator
 *
 */
public class PageVo {
	
	//��ǰ�ڼ�ҳ
	private Integer page=1;
	//��ʾ����
	private List<? extends Object> list;
	//һ����������
	private Integer total;
	//ÿҳ����������
	private Integer pageSize=10;
	//�����õ��Ĳ���
	private Map<String,String> params;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public List<? extends Object> getList() {
		return list;
	}
	public void setList(List<? extends Object> list) {
		this.list = list;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	//��ȡ��ѯ��ʼλ��
	public Integer getStartIndex(){
		Integer startIndex=(page-1)*pageSize;
		return startIndex;
	}

	//��ȡ��ҳ��
	public Integer getTotalPage(){
		Integer totalPage=(total/pageSize)+(total%pageSize==0? 0 : 1);
		return totalPage;
	}
}
