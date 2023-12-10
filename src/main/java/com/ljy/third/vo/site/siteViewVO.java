package com.ljy.third.vo.site;

import com.ljy.third.vo.VOobject;

public class siteViewVO extends VOobject {
	
	private String siteCode;
	private String postCode;
	private int viewNum;
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

}
