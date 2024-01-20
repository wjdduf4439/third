package com.ljy.third.vo;

import java.io.Serializable;

import org.springframework.web.util.HtmlUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class VOobject implements Cloneable, Serializable {

	private static final long serialVersionUID = 2868410232929931052L;
    
	/** ?��?���?�??�� */
    private int pageUnit = 10;
    /** ?��?���??��?���? */
    private int pageSize = 10;
    /** ?��?��?��?���? */
    private int pageIndex = 0;
    /** 첫페?���? ?��?��?�� */
    private int firstIndex = 1;
    /** 마�?막페?���? ?��?��?�� */
    private int lastIndex = 1;
    /** ?��?���??�� ?��코드 개수 */
    private int recordCountPerPage = 10;
    /** �??�� ?���? */
    private String searchCnd ="";
    private String searchCnd1 ="";
    private String searchCnd2 ="";
    private String searchCnd3 ="";
	/** �??��?�� */
    private String searchWrd ="";
    
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public String getSearchCnd() {
		return searchCnd;
	}
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}
	public String getSearchCnd1() {
		return searchCnd1;
	}
	public void setSearchCnd1(String searchCnd1) {
		this.searchCnd1 = searchCnd1;
	}
	public String getSearchCnd2() {
		return searchCnd2;
	}
	public void setSearchCnd2(String searchCnd2) {
		this.searchCnd2 = searchCnd2;
	}
	public String getSearchCnd3() {
		return searchCnd3;
	}
	public void setSearchCnd3(String searchCnd3) {
		this.searchCnd3 = searchCnd3;
	}
	public String getSearchWrd() {
		
		//xssfilter가 vo에 맞춰줘야 하는 부분이 많고 부모 클래스를 호출해서 처리해야 하는 일이 많아 생성자로 자동으로 searchWrd의 값을 설정
    	//System.out.println("VOobject 변형 이전 검색어 " + this.searchWrd); 
    	this.searchWrd = HtmlUtils.htmlEscape(this.searchWrd);
    	//System.out.println("VOobject 변환 이후 검색어" + this.searchWrd);
		
		return searchWrd;
	}
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	
}
