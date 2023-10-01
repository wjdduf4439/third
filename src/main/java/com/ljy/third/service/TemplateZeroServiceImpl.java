package com.ljy.third.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.dao.BoardDAO;
import com.ljy.third.dao.FileDAO;
import com.ljy.third.dao.FileEditorContentDAO;
import com.ljy.third.dao.TemplateZeroDAO;
import com.ljy.third.vo.FileEditorContentVO;
import com.ljy.third.vo.FileVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateZeroVO;

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
	
	//private String SAVE_PATH =  "C:/sts-bundle/workspace-sts-3.9.4.RELEASE/first/src/main/webapp/resources/image";
	private String SAVE_PATH =  "";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Override
	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("������ �������ε��� : " + templateInfoVO.getPageIndex());
		System.out.println("������ ���ڵ�ī��Ʈ : " + templateInfoVO.getRecordCountPerPage());
		
		templateInfoVO.setPageIndex( templateInfoVO.getPageIndex() * templateInfoVO.getRecordCountPerPage() );
		
		return templateZeroDAO.selectTableFieldList(templateInfoVO);
	}

	@Override
	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
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
	public void insertTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			String maxCode = templateZeroDAO.selectTableRecordListMax(templateZeroVO);
			int temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
			int tailNumber = temp;
			
			String newCode = "TZERO_";
			int i = 1;
			
			while(temp >= 10) { temp /= 10; i++; }
			
			for(int j = 0; j < 14-i ; j++) { newCode += "0"; }
			newCode += Integer.toString(tailNumber);
			
			templateZeroVO.setCode(newCode);
			
		}catch(Exception e) {
			
			templateZeroVO.setCode("TZERO_00000000000001");
			
		}
		
		
		//filecode ����
		
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			String f_code;	//atchfileid를 담을 변수
			makedir(req);	//파일이 있다면 파일경로를 먼저 지정
			
			try {	f_code = fileDAO.selectFileCodeMax().getCode();	}catch (Exception e) {	f_code = "0";	}
			
			if(f_code == null) { f_code = "0"; }
			
			f_code = f_code.replaceAll("FILE_", "");
			System.out.println("f_code : " + f_code);
			
			int temp = Integer.parseInt(f_code); temp ++;			//������ �ڵ忡 �� temp ����
			int counttemp = Integer.parseInt(templateZeroDAO.maxTableAtchFileId(templateZeroVO))+1; //최대 fid에 1을 더함
			
			templateZeroVO.setB_file_id(Integer.toString(counttemp));//file_table�� f_id�� �� ���� ���̵� ����

			List<String> newCodeList = new ArrayList<String>();		//file_table에 나올 복수형의 파일을 동시에 등록하기 위한 codelist
			String newCode = "FILE_";											//file_table에 나올 복수형의 파일을 동시에 등록하기 위한 code의 temp
			
			for(int i = 0; i < templateZeroVO.getB_filename().size(); i++) {

				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	
				
				newCode = newCode + (i+temp);					//file_0000,,,������ �ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				newCode = "FILE_";
			}
			//AtchFileid의 형식을 FileCode를 만든 다음 설정하는것으로 변경
			//AtchFileid의 형식을 숫자에서 fid _AND_ siteCode의 형식으로의 변환이 필요함
			templateZeroVO.setAtchFileId(Integer.toString(counttemp) + "_AND_" + templateZeroVO.getSiteCode());

			String originFilename = "";
			String savingFilename = "";
			int savingFileTypeIndex = 0;
			String savingFileType = "";
			
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			for(int i = 0 ; i < templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO(); mfileVO.setSiteCode(templateZeroVO.getSiteCode());
				//새로운 filevo 선언 후 sitecode에 현재 사용하고 있는 게시판 코드를 집어넣음

				//savingfname으로 파일 저장, savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾음
				originFilename = templateZeroVO.getB_filename().get(i).getOriginalFilename();
				savingFilename = makeSavingFileCode( templateZeroVO.getCode(), templateZeroVO.getB_file_id() , i);
				
				//savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾고 파일 형식을 잘라내어 저장함
				savingFileTypeIndex = makeSavingFileTypeIndex(originFilename);
				savingFileType = originFilename.substring(savingFileTypeIndex, originFilename.length());
				
				//최종 파일 저장 형식은 새로 지은 file의 이름과 파일 형식을 같이 하여 저장함
				writeFile(templateZeroVO.getB_filename().get(i), savingFilename + savingFileType);
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateZeroVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + savingFilename + savingFileType); 
				//fname는 그대로 두고, savingfname는 날짜 + 시간 + code로 설정
				mfileVO.setSavingFname(savingFilename + savingFileType);
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("----------------------작성 파일의 세부 구성요소--------------------------");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getSiteCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getSavingFname());
			System.out.println(fileVO.get(0).getFname());
			System.out.println("-------------------------------------------------------------------");
		     
		     mboardDAO.insertFileBoardDAO(fileVO);
			
		}
		
		//글내용 첨부이미지 ECFILE CODE 모음을 mFileEditorContentVO에 배열로 재구성하여 dao로 
		//FILE_EDITORCONTENT_TABLE의 EditorImageCode배열에 해당하는 FILE_EDITORCONTENT_TABLE.code필드의 값을 대상으로 하여
		//FILE_EDITORCONTENT_TABLE.fid의 값을 현재 등록하는 게시물의 코드값으로 설정 
		
		if(!"".equals(templateZeroVO.getEditorImage())){

			FileEditorContentVO mFileEditorContentVO = new FileEditorContentVO();
			mFileEditorContentVO.setEditorImageArray(templateZeroVO.getEditorImage().split(","));
			mFileEditorContentVO.setSiteCode(templateZeroVO.getSiteCode());
			mFileEditorContentVO.setFid(templateZeroVO.getCode());
			
			fileEditorContentDAO.updateEditorContentFid(mFileEditorContentVO);
			
		}
		
		//최종 글 내용 레코드 등록
		templateZeroDAO.insertTableRecord(templateZeroVO);
	}

	@Override
	public void updateTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception {
		
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			makedir(req);	//파일이 있다면 파일경로를 먼저 지정
				
			int temp = 0;
			int tailNumber;
			//String maxCode = templateZeroDAO.selectTableFileListMax(templateZeroVO);
			String maxCode = fileDAO.selectFileCodeMax().getCode();
			
			try { //������ ÷�εǾ� �������� maxcode ����(0�̸� catch)
				
				temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
				
				tailNumber = temp;
			
			}catch(Exception e) { tailNumber = Integer.parseInt(maxCode) + 1; System.out.println("ĳġ��"); } //������ ÷�εǾ� ���� ������  maxCode + 1�� ����(file table���� ���� ������ 1�� ��)
			//System.out.println("�����ڵ�ѹ� : " + tailNumber);
			
			
			
			List<String> newCodeList = new ArrayList<String>();		//������ ÷���� ����ŭ file_table�� �� "������"�ڵ� ����
			
			for(int i = 0; i < templateZeroVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//����ִ� �ڸ����ڸ�ŭ 0�� ä��
				
				newCode = newCode + (i+tailNumber);					//file_0000,,,������ �ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				newCode = "FILE_";
				
			}
			
			templateZeroVO.setB_fileCode(newCodeList);
			
			//String atchFileId = templateZeroDAO.selectTableAtchFileId(templateZeroVO);//������ ���� code�� ���� atchfileid�� ����
			String atchFileId = templateZeroVO.getAtchFileId();
			String countAtchFileId = fileDAO.selectFileIdMax();//filetable�� �ִ� atchfileid�� ã�Ƽ� ���� fileid�� ����
			
			//System.out.println("countid : " + countAtchFileId);
			
			if(countAtchFileId == null || countAtchFileId.equals("")) { System.out.println("atchfileid없음"); atchFileId = "1"; }		
			else if(templateZeroVO.getAtchFileId().equals("")) {											
				
				System.out.println("atchfileId = " + atchFileId);
				
				int countAtchFileNumber = Integer.parseInt(countAtchFileId)+1;
				
				templateZeroVO.setAtchFileId(Integer.toString(countAtchFileNumber)); //�ش� �Խù��� ī��Ʈ+1�� atchfileid�� �־���
				
			}else { System.out.println("�� atchfileid����"); }
																//atchfileid�� ������ �ش� atchfileid�� ���ľ�
			//templateZeroVO.setAtchFileId(atchFileId);			//������ �����ٰ� ���� ��츦 ����ؼ� ����
			
			
			int sign = 0;

			try { sign = fileDAO.selectFileSign(atchFileId); }catch (Exception e) {	sign = 0; }		//f_sign�ִ밪 ����
			System.out.println("fsign�� : " + sign);

			String originFilename = "";
			String savingFilename = "";
			int savingFileTypeIndex = 0;
			String savingFileType = "";
			
			List<FileVO> fileVO = new ArrayList<FileVO>();
			//System.out.println("�ڵ� : " + fileDAO.selectFileCodeMax().getCode() );
			
			for(int i = sign ; i < sign + templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO(); mfileVO.setSiteCode(templateZeroVO.getSiteCode());
				//새로운 filevo 선언 후 sitecode에 현재 사용하고 있는 게시판 코드를 집어넣음
				
				originFilename = templateZeroVO.getB_filename().get(i - sign).getOriginalFilename();
				savingFilename = makeSavingFileCode( templateZeroVO.getCode(), templateZeroVO.getB_file_id() , i);
				
				//savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾고 파일 형식을 잘라내어 저장함
				savingFileTypeIndex = makeSavingFileTypeIndex(originFilename);
				savingFileType = originFilename.substring(savingFileTypeIndex, originFilename.length());
				
				//최종 파일 저장 형식은 새로 지은 file의 이름과 파일 형식을 같이 하여 저장함
				writeFile(templateZeroVO.getB_filename().get(i - sign), savingFilename + savingFileType);
				
				mfileVO.setCode(templateZeroVO.getB_fileCode().get(sign + templateZeroVO.getB_filename().size() - i - 1));
				//update의 setfid는 기존 사용하던 fid를 사용해야함
				mfileVO.setFid(templateZeroVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + savingFilename + savingFileType); 
				mfileVO.setSavingFname(savingFilename + savingFileType);
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("----------------------작성 파일의 세부 구성요소--------------------------");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getSiteCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
			System.out.println("-------------------------------------------------------------------");
			
		    mboardDAO.insertFileBoardDAO(fileVO);
			
		}
		

		//내용첨부이미지의 "save_editorImage", "drop_editorImage"를 컨트롤러에 받아서 save_editorImage의 값의 fid를 등록시키고, drop_editorImage의 테이블 삭제 및 파일 삭제하기\
		
		if(!"".equals(templateZeroVO.getEditorImage())){
			
			//drop_editorImage작업 - selectTableRecordList_Fpath, deleteEditorContent_Fid 활용
			//drop_editorImage 배열을 작업하고 등록시켜야함
			//drop_editorImage의 fpath를 가져올 select문 만들기 - selectTableRecordList_Fpath_whereCode
			//drop_editorImage의 코드를 가지고 drop 시킬 delete문 만들기 - deleteEditorContent_Code
			
			FileEditorContentVO mFileEditorContentVO = new FileEditorContentVO();
			mFileEditorContentVO.setSiteCode(templateZeroVO.getSiteCode());
			mFileEditorContentVO.setFid(templateZeroVO.getCode());
			
			mFileEditorContentVO.setEditorImageArray(templateZeroVO.getDrop_editorImage().split(","));
			List<FileEditorContentVO> resultF_E_C_VOList = fileEditorContentDAO.selectTableRecordList_Fpath_whereCode(mFileEditorContentVO);
			
			for(int i = 0; i < resultF_E_C_VOList.size(); i++ ) {
				System.out.println("updateTableRecord 추출한 fpath" + resultF_E_C_VOList.get(0).getFpath());
				File file = new File(resultF_E_C_VOList.get(i).getFpath());
				
				//파일 존재 시 파일 삭제
				if( file.exists() ){ file.delete(); }
				
			}
			fileEditorContentDAO.deleteEditorContent_dropEditorImageArray(mFileEditorContentVO);
			
			//save_editorImage작업 - updateEditorContentFid 활용
			
			mFileEditorContentVO.setEditorImageArray(templateZeroVO.getSave_editorImage().split(","));
			fileEditorContentDAO.updateEditorContentFid(mFileEditorContentVO);
			
		}
		
		templateZeroDAO.updateTableRecord(templateZeroVO);
	}

	@Override
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("editorImage : " + templateZeroVO.getEditorImage());
		System.out.println("b_file_id : " + templateZeroVO.getB_file_id());

		//공지사항 여부도 삭제하는 과정 주입하기

		FileEditorContentVO mFileEditorContentVO = new FileEditorContentVO();
		mFileEditorContentVO.setSiteCode(templateZeroVO.getSiteCode());
		mFileEditorContentVO.setFid(templateZeroVO.getCode());
		
		List<FileEditorContentVO> resultF_E_C_VOList = fileEditorContentDAO.selectTableRecordList_Fpath(mFileEditorContentVO);
		
		//System.out.println("추출한 fpath" + resultF_E_C_VOList.get(0).getFpath());
		for(int i = 0; i < resultF_E_C_VOList.size(); i++ ) {
			
			File file = new File(resultF_E_C_VOList.get(i).getFpath());
			
			//파일 존재 시 파일 삭제
			if( file.exists() ){ file.delete(); }
			
		}
		fileEditorContentDAO.deleteEditorContent_Fid(mFileEditorContentVO);
		
		//templateZeroVO.getB_file_id()가 ""일때가 귀찮아
		if( !"".equals(templateZeroVO.getB_file_id())  && Integer.parseInt(templateZeroVO.getB_file_id()) > 0 ){
			 
			templateZeroDAO.deleteFileRecord(templateZeroVO);
			
		}
		templateZeroDAO.deleteTableRecord(templateZeroVO);
	}
	
	private void writeFile(MultipartFile b_filename, String saveFileName) throws IOException, InterruptedException {
		
		byte[] data = b_filename.getBytes();
		
		FileOutputStream fos;
			
		//String path = "c:/upload";
		String path = this.SAVE_PATH;
		File Folder = new File(path);
		
		if (!Folder.isDirectory()) {
			
				System.out.println("����������");
			
			    Folder.mkdir(); //���� �����մϴ�
			    
			    Folder.setWritable(true, true);
			    Folder.setReadable(true, true);
			    Folder.setExecutable(true, true);
			    
		}
		
		try {fos = new FileOutputStream(path + "/" + saveFileName); fos.write(data); fos.close();} catch(Exception e) {}
		
		
	}
	
	//파일을 기입하거나 저장할때 경로는 지정
	private void makedir( HttpServletRequest req ) {
		
		String processerName = System.getProperty("os.name").toLowerCase();
		System.out.println("processerName : " + processerName); 
		
		if(processerName.contains("windows")) { this.SAVE_PATH = "c:/upload"; }
		else { this.SAVE_PATH = "/home/ec2-user/third_FileDir"; }
		
		
		this.PREFIX_URL =  SAVE_PATH + "/";
		
	}
	
	//파일을 기입하거나 저장할때 현재 시간과 fid, sign에 기반해서 코드를 생성
	private String makeSavingFileCode( String code, String fid, int fsign ) {

		LocalDate  savingDate = LocalDate.now();
		DateTimeFormatter savingDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		
		LocalTime  savingTime = LocalTime.now();
		DateTimeFormatter savingTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		
		return code + "_" + fid + "_" + fsign + "_" + savingDate.format(savingDateFormatter)+ "_" + savingTime.format(savingTimeFormatter);
	}
	
	
	
	private int makeSavingFileTypeIndex( String originFileName ) {

		List<Integer> indexList = new ArrayList<Integer> ();
		String fileTypeMark = ".";
		int savingFileTypeIndex = originFileName.indexOf(fileTypeMark);
		
		while(savingFileTypeIndex != -1) {
			
			if(savingFileTypeIndex == -1) break;
			
			indexList.add(savingFileTypeIndex);
			savingFileTypeIndex = originFileName.indexOf(fileTypeMark, savingFileTypeIndex + fileTypeMark.length());
		}
		
		return indexList.get(indexList.size() - 1);
	}

}
