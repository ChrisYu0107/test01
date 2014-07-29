package com.zjpii.zjkfweb.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.zjpii.zjkfweb.constants.Constants;



public class Pager implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int pageSize = Constants.DEFAULT_PAGE_SIZE; // 每页的记录数

	private long start; // 当前页第一条数据在List中的位置,从0开始

	private Object items; // 当前页中存放的记录,类型一般为List

	private long totalCount; // 总记录数
	
	private String sortType; // 排序类型
	
	private String sortDesc; // 排序字段


	
	/**
	 * 构造方法，只构造空页.
	 */
	public Pager() {
		this(0, 0, Constants.DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法.
	 *
	 * @param start	 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize  本页容量
	 * @param data	  本页包含的数据
	 */
	public Pager(long start, long totalSize, int pageSize, Object items) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.items = items;
	}

	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Object getItems() {
		return items;
	}
	
	/**
	 * 取排序字段
	 */
	public String getSortDesc() {
		return sortDesc;
	}

	/**
	 * 取排序类型
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 *
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, Constants.DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}	

	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 *获取当前页面的第一条纪录是总纪录的第几条 
	 *
	 */
	public long getFristPos(int pageNo){
		return ((pageNo - 1) * pageSize + 1);
	}
	
	/**
	 *获取总页数,主要是因为分页的修改方便
	 *
	 */	
	public long getLastPos(int pageNo){
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;		
	}
}