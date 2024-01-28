package com.ljy.third.service.templateZero;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.BoardDAO;
import com.ljy.third.dao.FileDAO;
import com.ljy.third.dao.FileEditorContentDAO;
import com.ljy.third.dao.templateZero.TemplateZeroDAO;
import com.ljy.third.vo.FileEditorContentVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.templateZero.TemplateZeroVO;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Service("TemplateZeroService")
public class TemplateZeroServiceImpl implements TemplateZeroService {

	@Resource(name = "TemplateZeroDAO")
	TemplateZeroDAO templateZeroDAO; 
	
	@Resource(name = "FileDAO")
	FileDAO fileDAO;
	
	@Resource(name = "FileEditorContentDAO")
	FileEditorContentDAO fileEditorContentDAO;

	@Resource(name = "BoardDAO")
	private BoardDAO mboardDAO;
	
	
	@Override
	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		//templateInfoVO.setPageIndex( templateInfoVO.getPageIndex() * templateInfoVO.getRecordCountPerPage() );
		return templateZeroDAO.selectTableFieldList(templateInfoVO);
	}

	@Override
	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		//templateInfoVO.setPageIndex( templateInfoVO.getPageIndex() * templateInfoVO.getRecordCountPerPage() );
		return templateZeroDAO.selectTableRecordList(templateInfoVO);
	}

	@Override
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordListCount(templateInfoVO);
	}
	
	@Override
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordListCount(templateZeroVO);
	}
	
	@Override
	public String selectTableRecordListMax(HashMap<String, String> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordListMax(stringJson);
	}
	
	@Override
	public String selectTableAtchFileIdMax(String siteCode) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableAtchFileIdMax(siteCode);
	}
	
	@Override
	public List<TemplateZeroVO> selectTableNoticeList(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableNoticeList(templateZeroVO);
	}	

	@Override
	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordOne(templateZeroVO);
	}

	@Override
	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroDAO.selectTableRecordRecent(templateZeroVO);
	}

	@Override
	public TemplateZeroVO selectTableECFRecordList(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		TemplateZeroVO ECFresultVO = new TemplateZeroVO();
		
		String ecfileTotalText = "";
		FileEditorContentVO mFileEditorContentVO = new FileEditorContentVO();
		mFileEditorContentVO.setSiteCode(templateZeroVO.getSiteCode());
		mFileEditorContentVO.setFid(templateZeroVO.getCode());
		
		List<FileEditorContentVO> resultF_E_C_VOList = fileEditorContentDAO.selectTableRecordList_Code(mFileEditorContentVO);
		if(resultF_E_C_VOList.size() > 0) {

			//추출한 FILE_EDITORCONTENT_TABLE의 값을 ,로 구분한 문자열로 구축해서 TemplateZeroVO에 담기
			//System.out.println("resultF_E_C_VOList Code : " + resultF_E_C_VOList.get(i).getCode());
			for(int i = 0; i < resultF_E_C_VOList.size(); i++ ) { ecfileTotalText += resultF_E_C_VOList.get(i).getCode(); ecfileTotalText += ","; }
			ecfileTotalText = ecfileTotalText.substring(0, ecfileTotalText.length() - 1);
			//System.out.println("최종 ecfileTotal : " + ecfileTotalText);
			
		}
		
		//이미첨부 기능이 없는 게시물이나 처음 화면에서 불러올때를 대비해 null대비함
		if( null != ecfileTotalText && !"".equals(ecfileTotalText)) { ECFresultVO.setEditorImage(ecfileTotalText); }
		 
		return ECFresultVO;
	}

	@Override
	public void insertTableRecord(HashMap<String, String> stringJson, HttpServletRequest req ) throws Exception {
		// TODO Auto-generated method stub
		//최종 글 내용 레코드 등록
		templateZeroDAO.insertTableRecord(stringJson);
	}
	
	@Override
	public void insertFileRecord(HashMap<String, String> stringJson) throws Exception {
		// TODO Auto-generated method stub
		//최종 글 내용 레코드 등록
		templateZeroDAO.insertFileRecord(stringJson);
	}

	@Override
	//public void updateTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception {
	public void updateTableRecord(HashMap<String, String> stringJson, HttpServletRequest req ) throws Exception {
		templateZeroDAO.updateTableRecord(stringJson);
	}
	
	@Override
	public void updateAtchFileId(HashMap<String, String> stringJson) throws Exception {
		templateZeroDAO.updateAtchFileId(stringJson);
	}


	@Override
	public void disableTableRecord(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		templateZeroDAO.disableTableRecord(stringJson);
	}

	@Override
	public void restoreTableRecord(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		templateZeroDAO.restoreTableRecord(stringJson);
	}

	@Override
	public void deleteTableRecord(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("deleteTableRecord service impl json : " + stringJson.toString());

		//공지사항 여부도 삭제하는 과정 주입하기

		FileEditorContentVO mFileEditorContentVO = new FileEditorContentVO();
		/*
		mFileEditorContentVO.setSiteCode(templateZeroVO.getSiteCode());
		mFileEditorContentVO.setFid(templateZeroVO.getCode());
		*/
		mFileEditorContentVO.setSiteCode((String) stringJson.get("siteCode"));
		mFileEditorContentVO.setFid((String) stringJson.get("code"));
		
		
		List<FileEditorContentVO> resultF_E_C_VOList = fileEditorContentDAO.selectTableRecordList_Fpath(mFileEditorContentVO);
		
		//System.out.println("추출한 fpath" + resultF_E_C_VOList.get(0).getFpath());
		for(int i = 0; i < resultF_E_C_VOList.size(); i++ ) {
			
			File file = new File(resultF_E_C_VOList.get(i).getFpath());
			
			//파일 존재 시 파일 삭제
			if( file.exists() ){ file.delete(); }
			
		}
		fileEditorContentDAO.deleteEditorContent_Fid(mFileEditorContentVO);
		
		//templateZeroVO.getB_file_id()가 ""일때가 귀찮아
		//if( !"".equals(templateZeroVO.getB_file_id())  && Integer.parseInt(templateZeroVO.getB_file_id()) > 0 ){
		if( !"".equals(stringJson.get("b_file_id")) && Integer.parseInt((String) stringJson.get("b_file_id")) > 0 ){
			 
			templateZeroDAO.deleteFileRecord(stringJson);
			
		}
		templateZeroDAO.deleteTableRecord(stringJson);
	}
	
	//파일을 기입하거나 저장할때 현재 시간과 fid, sign에 기반해서 코드를 생성
	private String makeSavingFileCode( String code, String fid, int fsign ) {

		LocalDate  savingDate = LocalDate.now();
		DateTimeFormatter savingDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		
		LocalTime  savingTime = LocalTime.now();
		DateTimeFormatter savingTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		
		return code + "_" + fid + "_" + fsign + "_" + savingDate.format(savingDateFormatter)+ "_" + savingTime.format(savingTimeFormatter);
	}
	
	
	//파일 이름을 받아서 확장자를 추출하는 메소드
	private int makeSavingFileTypeIndex( String originFileName ) {

		List<Integer> indexList = new ArrayList<Integer> ();
		String fileTypeMark = ".";
		int savingFileTypeIndex = originFileName.indexOf(fileTypeMark);
		
		while(savingFileTypeIndex != -1) {
			
			if(savingFileTypeIndex == -1) break;
			
			indexList.add(savingFileTypeIndex);
			//더이상 찾을 .이 없으면 originFileName.indexOf(의 값은 -1을 표시한다.
			savingFileTypeIndex = originFileName.indexOf(fileTypeMark, savingFileTypeIndex + fileTypeMark.length());
		}
		
		return indexList.get(indexList.size() - 1);
	}

}
