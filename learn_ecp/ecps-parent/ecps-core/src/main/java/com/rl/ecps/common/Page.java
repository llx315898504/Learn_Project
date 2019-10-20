package com.rl.ecps.common;

import java.util.List;

/**
 * 分页对象类
 * 
 * @author 86150
 *
 */
public class Page {

	// 当前页
	private Integer pageNo = 1;

	// 每页记录数
	private Integer pageSize = 5;

	// 指定条件下的总记录数
	private Integer totalCount = 0;

	// 指定条件下的总页数
	private Integer totalPage = 0;

	// 查询的开始行号
	private Integer startNum = 0;

	// 查询的结束行号
	private Integer endNum = 0;

	// 查询结果集
	private List<?> resultlist;
	
	/**
	 * 计算总页数
	 * 
	 * @return
	 */
	public Integer getTotalPage() {
		totalPage=totalCount/pageSize;
		if(totalCount==0||totalCount%pageSize!=0){
			totalPage++;
		}
		return totalPage;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<?> resultlist) {
		this.resultlist = resultlist;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	/**
	 *  计算开始行号方法
	 * 
	 * @return
	 */
	public Integer getStartNum() {
		startNum=(pageNo - 1) *pageSize;
		return startNum;
	}

	/**
	 *  计算结束行号方法
	 * 
	 * @return
	 */
	public Integer getEndNum() {
		endNum=pageSize * pageNo + 1;
		return endNum;
	}
}