package com.ljy.third.vo;

public class SysCodeVO {
	
	//TEMPLATEFIELD_TABLE, TEMPLATEFIELDCODE_TABLE 기본 키
	private String code;
	
	//TEMPLATEFIELDCODE_TABLE field
	private String temCodeHead;
	private int temCodeNum;
	private String temFieldName;
	private String temFieldCharge;
	private int sysOutNum;
	
	//TEMPLATEFIELD_TABLE field
	private String formName;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTemCodeHead() {
		return temCodeHead;
	}
	public void setTemCodeHead(String temCodeHead) {
		this.temCodeHead = temCodeHead;
	}
	public int getTemCodeNum() {
		return temCodeNum;
	}
	public void setTemCodeNum(int temCodeNum) {
		this.temCodeNum = temCodeNum;
	}
	public String getTemFieldName() {
		return temFieldName;
	}
	public void setTemFieldName(String temFieldName) {
		this.temFieldName = temFieldName;
	}
	public String getTemFieldCharge() {
		return temFieldCharge;
	}
	public void setTemFieldCharge(String temFieldCharge) {
		this.temFieldCharge = temFieldCharge;
	}
	public int getSysOutNum() {
		return sysOutNum;
	}
	public void setSysOutNum(int sysOutNum) {
		this.sysOutNum = sysOutNum;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	

}
