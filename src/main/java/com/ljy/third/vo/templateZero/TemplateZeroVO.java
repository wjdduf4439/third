package com.ljy.third.vo.templateZero;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ljy.third.vo.VOobject;

public class TemplateZeroVO extends VOobject {

	private static final long serialVersionUID = 2868410232929931052L;
	
	private String siteCode;
	private String siteTitle;

	//primaryCode 받기용 선언
    private String primaryCode;
    
	private String code;
	private String title;
	private String context;
	private String atchFileId;
	private String writerID;
	private String del_chk;
	private String notice_chk;
	private String frstRegistPnttm;
	private String frstRegistNm;
	private String lastUpdtPnttm;
	private String lastUpdtNm;
	private int viewNum;

	private String b_file_id = "";
	private String b_file_name = "";
	private List<String> b_fileCode;
	private List<MultipartFile> b_filename;
	private String noticeSwitch = "0";
	
	//FileEditorContentVO에 전송하기 위한 에디터 내용첨부이미지 변수
	private String editorImage = "";
	private String save_editorImage = "";
	private String drop_editorImage = "";
	
	public void putPrimaryCode( String primaryCode ) {
		this.primaryCode = primaryCode;
	}
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteTitle() {
		return siteTitle;
	}
	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrimaryCode() {
		return primaryCode;
	}
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	public String getDel_chk() {
		return del_chk;
	}
	public void setDel_chk(String del_chk) {
		this.del_chk = del_chk;
	}
	public String getNotice_chk() {
		return notice_chk;
	}
	public void setNotice_chk(String notice_chk) {
		this.notice_chk = notice_chk;
	}
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	public String getFrstRegistNm() {
		return frstRegistNm;
	}
	public void setFrstRegistNm(String frstRegistNm) {
		this.frstRegistNm = frstRegistNm;
	}
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	public String getLastUpdtNm() {
		return lastUpdtNm;
	}
	public void setLastUpdtNm(String lastUpdtNm) {
		this.lastUpdtNm = lastUpdtNm;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public String getB_file_id() {
		return b_file_id;
	}
	public void setB_file_id(String b_file_id) {
		this.b_file_id = b_file_id;
	}
	public String getB_file_name() {
		return b_file_name;
	}
	public void setB_file_name(String b_file_name) {
		this.b_file_name = b_file_name;
	}
	public List<String> getB_fileCode() {
		return b_fileCode;
	}
	public void setB_fileCode(List<String> b_fileCode) {
		this.b_fileCode = b_fileCode;
	}
	public List<MultipartFile> getB_filename() {
		return b_filename;
	}
	public void setB_filename(List<MultipartFile> b_filename) {
		this.b_filename = b_filename;
	}
	public String getNoticeSwitch() {
		return noticeSwitch;
	}
	public void setNoticeSwitch(String noticeSwitch) {
		this.noticeSwitch = noticeSwitch;
	}
	public String getEditorImage() {
		return editorImage;
	}
	public void setEditorImage(String editorImage) {
		this.editorImage = editorImage;
	}
	public String getSave_editorImage() {
		return save_editorImage;
	}
	public void setSave_editorImage(String save_editorImage) {
		this.save_editorImage = save_editorImage;
	}
	public String getDrop_editorImage() {
		return drop_editorImage;
	}
	public void setDrop_editorImage(String drop_editorImage) {
		this.drop_editorImage = drop_editorImage;
	}
	
	
}
