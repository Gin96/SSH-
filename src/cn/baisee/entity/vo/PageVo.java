package cn.baisee.entity.vo;

import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageVo {
	
	//当前第几页
	private Integer page=1;
	//显示数据
	private List<? extends Object> list;
	//一共多少数据
	private Integer total;
	//每页多少条数据
	private Integer pageSize=10;
	//可能用到的参数
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
	//获取查询开始位置
	public Integer getStartIndex(){
		Integer startIndex=(page-1)*pageSize;
		return startIndex;
	}

	//获取总页数
	public Integer getTotalPage(){
		Integer totalPage=(total/pageSize)+(total%pageSize==0? 0 : 1);
		return totalPage;
	}
}
