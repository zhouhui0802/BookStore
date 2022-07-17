package com.zh.bean;

import java.util.List;

/*
 * ��ҳ��Ϣ��Ϣ��װ��
 */

public class Page<T> {

	// ��ǰҳ��ҳ��
	private int pageNum;
	
	// ���ݶ���ļ���
	private List<T> list;
	
	// �ܼ�¼��
	private int totalRecord;

	// ��ҳ��
	private int totalPageCount;
	
	// ��ʾ�ĵ�һ��ҳ��
	private int firstPageNum;
	
	// ��ʾ�����һ��ҳ��
	private int lastPageNum;

	// һҳ��ʾ�ļ�¼��
	public static final int PAGE_RECORD = 3;
	
	// ��ʾ��ҳ����
	public static final int DISPLAY_PAGE_COUNT = 5; // ����

	public Page(int pageNum, List<T> list, int totalRecord) {
		
		 //1  ��ҳ���ͼ����Ϣ   �ܼ�¼��
		super();
		this.pageNum = pageNum;
		this.list = list;
		this.totalRecord = totalRecord;

		//�����ܵ�ҳ������
		this.totalPageCount = (totalRecord+PAGE_RECORD-1)/PAGE_RECORD;
		
		//ȷ����һҳ�����һҳ  ��������������ʱ�򿴿�  todo
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
