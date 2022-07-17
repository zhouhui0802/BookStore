package com.zh.bean;

public class PageCondition {

	// ����id
	private String cid;
	// ��С�۸�
	private String minPrice;
	// ��߼۸�
	private String maxPrice;
	// �ڼ�ҳ
	private String pageNum;

	public PageCondition() {
		super();
	}

	public PageCondition(String cid, String minPrice, String maxPrice,
			String pageNum) {
		super();
		this.cid = cid;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.pageNum = pageNum;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "PageCondition [cid=" + cid + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + ", pageNum=" + pageNum + "]";
	}
	
}
