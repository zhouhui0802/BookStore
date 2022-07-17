package com.zh.bean;

/*
 * ��ѯ������Ϣ��װ��
 */

public class ConditionBook {

	private String minprice;
	private String maxprice;
	private String categorysid;  //�鱾������
	private String pageNum;   //ҳ������

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getMinprice() {
		return minprice;
	}

	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}

	public String getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}

	public String getCategorysid() {
		return categorysid;
	}

	public void setCategorysid(String categorysid) {
		this.categorysid = categorysid;
	}

	public ConditionBook() {
		super();
	}

	public ConditionBook(String minprice, String maxprice, String categorysid, String pageNum) {
		super();
		this.minprice = minprice;
		this.maxprice = maxprice;
		this.categorysid = categorysid;
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "ConditionBook [minprice=" + minprice + ", maxprice=" + maxprice + ", categorysid="
				+ categorysid + ", pageNum=" + pageNum + "]";
	}
}
