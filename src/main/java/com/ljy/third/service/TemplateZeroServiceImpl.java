package com.ljy.third.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.dao.BoardDAO;
import com.ljy.third.dao.FileDAO;
import com.ljy.third.dao.TemplateZeroDAO;
import com.ljy.third.vo.FileVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateZeroVO;

@Service("TemplateZeroService")
public class TemplateZeroServiceImpl implements TemplateZeroService {

	@Resource(name = "TemplateZeroDAO")
	TemplateZeroDAO templateZeroDAO; 
	
	@Resource(name = "FileDAO")
	FileDAO fileDAO;

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
	public void insertTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			String maxCode = templateZeroDAO.selectTableRecordListMax(templateZeroVO);
			int temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
			int tailNumber = temp;
			
			String newCode = "TZERO_";
			int i = 1;
			
			while(temp >= 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 14-i ; j++) { newCode += "0"; System.out.println(newCode); }
			newCode += Integer.toString(tailNumber);
			
			System.out.println(newCode);
			
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
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//����ִ� �ڸ����ڸ�ŭ 0�� ä��
				
				newCode = newCode + (i+temp);					//file_0000,,,������ �ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
				
				
			}
			//AtchFileid의 형식을 FileCode를 만든 다음 설정하는것으로 변경
			//AtchFileid의 형식을 숫자에서 fid _AND_ siteCode의 형식으로의 변환이 필요함
			templateZeroVO.setAtchFileId(Integer.toString(counttemp) + "_AND_" + templateZeroVO.getSiteCode());

			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table�� ����ؾ��� filevo�� ����Ʈ
			
			for(int i = 0 ; i < templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO(); mfileVO.setSiteCode(templateZeroVO.getSiteCode());
				//새로운 filevo 선언 후 sitecode에 현재 사용하고 있는 게시판 코드를 집어넣음
				
				originFilename = templateZeroVO.getB_filename().get(i).getOriginalFilename();//������ Ȯ���ڸ� ������ �̸�(���ϸ�)
				writeFile(templateZeroVO.getB_filename().get(i), originFilename);//���ε� ������ ���� ���ε�
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateZeroVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
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
			System.out.println("maxcode : " + maxCode);
			
			
			
			
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
			List<FileVO> fileVO = new ArrayList<FileVO>();
			//System.out.println("�ڵ� : " + fileDAO.selectFileCodeMax().getCode() );
			
			for(int i = sign ; i < sign + templateZeroVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO(); mfileVO.setSiteCode(templateZeroVO.getSiteCode());
				//새로운 filevo 선언 후 sitecode에 현재 사용하고 있는 게시판 코드를 집어넣음
				
				originFilename = templateZeroVO.getB_filename().get(i - sign).getOriginalFilename();
				writeFile(templateZeroVO.getB_filename().get(i - sign), originFilename);//���ε� ������ ���� ���ε�
				
				System.out.println("��ȣ : " + Integer.toString(sign + templateZeroVO.getB_filename().size() - i));
				mfileVO.setCode(templateZeroVO.getB_fileCode().get(sign + templateZeroVO.getB_filename().size() - i - 1));
				//update의 setfid는 기존 사용하던 fid를 사용해야함
				mfileVO.setFid(templateZeroVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
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
		
		templateZeroDAO.updateTableRecord(templateZeroVO);
	}

	@Override
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		templateZeroDAO.deleteFileRecord(templateZeroVO);
		templateZeroDAO.deleteTableRecord(templateZeroVO);
	}
	
	private void writeFile(MultipartFile b_filename, String saveFileName) throws IOException, InterruptedException {
		
		byte[] data = b_filename.getBytes();
		
		FileOutputStream fos;
			
		String path = "c:/upload";
		File Folder = new File(path);
		
		if (!Folder.isDirectory()) {
			
				System.out.println("����������");
			
			    Folder.mkdir(); //���� �����մϴ�
			    
			    Folder.setWritable(true, true);
			    Folder.setReadable(true, true);
			    Folder.setExecutable(true, true);
			    
		}
		
		try {fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName); fos.write(data); fos.close();} catch(Exception e) {}
		
		
	}
	
	//파일을 기입하거나 저장할때 경로는 지정
	private void makedir( HttpServletRequest req ) {
		
		String processerName = System.getProperty("os.name").toLowerCase();
		System.out.println("processerName : " + processerName); 
		
		if(processerName.contains("windows")) { this.SAVE_PATH = "c:/upload"; }
		else { this.SAVE_PATH = "/home/ec2-user/third_FileDir"; }
		
		
		this.PREFIX_URL =  SAVE_PATH + "/";
		
	}

}
