package com.wright.utils;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("rawtypes")
public class Page {
	 
	private int currentPage = 1;// 当前第几页(默认第一页),---主要用于传递到前台显示
	private int totalPage;// 总页数
	private int totalCount;// 总记录数
	private int pageSize = 5;// 每页显示的记录条数(默认5条)
 
	private List content = new ArrayList();// 记录当前页中的数据条目
 
	// 所有参数都进行修改
 
	public Page(int currentPage, int totalCount, int pageSize,
			List content) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.totalPage = totalCount % pageSize == 0 ? totalCount
				/ pageSize : totalCount / pageSize + 1;
		this.content = content;
		this.currentPage = currentPage<1?1:(currentPage>totalPage?totalPage:currentPage);//如果当前页小于第一页，则停留在第一页
	}
 
	// 使用默认的当前页和每页显示记录条数
	public Page( int totalCount, List content) {
		this.totalCount = totalCount;
		this.totalPage = totalCount % pageSize == 0 ? totalCount
				/ pageSize : totalCount / pageSize + 1;
		this.content = content;
		this.currentPage = currentPage<1?1:(currentPage>totalPage?totalPage:currentPage);//如果当前页小于第一页，则停留在第一页
	}
 
	public int getcurrentPage() {
		return currentPage;
	}
 
	public void setcurrentPage(int currentPage) {
		this.currentPage = currentPage<1?1:(currentPage>totalPage?totalPage:currentPage);//如果当前页小于第一页，则停留在第一页
	}
 
	public int gettotalPage() {
		return totalPage;
	}
 
	public void settotalPage(int totalPage) {
		this.totalPage = totalCount % pageSize == 0 ? totalCount
				/ pageSize : totalCount / pageSize + 1;
	}
 
	public int getTotalCount() {
		return totalCount;
	}
 
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
 
	public int getpageSize() {
		return pageSize;
	}
 
	public void setpageSize(int pageSize) {
		this.pageSize = pageSize;
	}
 
	public List getcontent() {
		return content;
	}
 
	public void setcontent(List content) {
		this.content = content;
	}
 
}
