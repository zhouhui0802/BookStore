package com.zh.bean;

import java.util.List;

/*
 * 分页信息信息封装类
 */

public class Page<T> {

	// 当前页的页码
	private int pageNum;
	
	// 数据对象的集合
	private List<T> list;
	
	// 总记录数
	private int totalRecord;

	// 总页数
	private int totalPageCount;
	
	// 显示的第一个页码
	private int firstPageNum;
	
	// 显示的最后一个页码
	private int lastPageNum;

	// 一页显示的记录数
	public static final int PAGE_RECORD = 3;
	
	// 显示的页码数
	public static final int DISPLAY_PAGE_COUNT = 5; // 奇数

	public Page(int pageNum, List<T> list, int totalRecord) {
		
		 //1  分页后的图书信息   总记录数
		super();
		this.pageNum = pageNum;
		this.list = list;
		this.totalRecord = totalRecord;

		//计算总的页码数量
		this.totalPageCount = (totalRecord+PAGE_RECORD-1)/PAGE_RECORD;
		
		//确定第一页和最后一页  等数据量上来的时候看看  todo
		if(totalPageCount<DISPLAY_PAGE_COUNT) {
			firstPageNum = 1;
			lastPageNum = totalPageCount;
		} else {
			
			firstPageNum = pageNum-DISPLAY_PAGE_COUNT/2;
			lastPageNum = pageNum+DISPLAY_PAGE_COUNT/2;
			
			if(firstPageNum<1) {
				firstPageNum = 1;
				lastPageNum = DISPLAY_PAGE_COUNT;
			}
			
			if(lastPageNum>totalPageCount) {
				lastPageNum = totalPageCount;
				firstPageNum = totalPageCount-DISPLAY_PAGE_COUNT+1;
			}
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public List<T> getList() {
		return list;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getFirstPageNum() {
		return firstPageNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public int getPageRecord() {
		return PAGE_RECORD;
	}

	public int getDisplayPageCount() {
		return DISPLAY_PAGE_COUNT;
	}
}
