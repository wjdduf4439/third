package com.ljy.third.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.dao.BoardDAO;
import com.ljy.third.dao.FileDAO;
import com.ljy.third.dao.TemplateOneDAO;
import com.ljy.third.vo.FileVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateOneVO;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Service("TemplateOneService")
public class TemplateOneServiceImpl implements TemplateOneService {
	/*
	@Resource(name = "TemplateOneDAO")
	TemplateOneDAO templateOneDAO; 
	
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
		
		templateInfoVO.setPageIndex( templateInfoVO.getPageIndex() * templateInfoVO.getRecordCountPerPage() );
		
		return templateOneDAO.selectTableFieldList(templateInfoVO);
	}
	
	@Override
	public List<TemplateOneVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordList(templateInfoVO);
	}
	
	@Override
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordListCount(templateInfoVO);
	}
	
	@Override
	public int selectTableRecordListCount(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordListCount(templateOneVO);
	}
	
	@Override
	public TemplateOneVO selectTableRecordOne(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		
		return templateOneDAO.selectTableRecordOne(templateOneVO);
	}
	
	@Override
	public TemplateOneVO selectTableRecordContext(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		
		return templateOneDAO.selectTableRecordContext(templateOneVO);
	}
	
	@Override
	public TemplateOneVO selectTableRecordRecent(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		return templateOneDAO.selectTableRecordRecent(templateOneVO);
	}
	
	@Override
	public void insertTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception {
		// TODO Auto-generated method stub
		
		
		try {
			String maxCode = templateOneDAO.selectTableRecordListMax(templateOneVO);
			int temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
			int tailNumber = temp;
			
			String newCode = "T_ONE_";
			int i = 1;
			
			while(temp >= 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 14-i ; j++) { newCode += "0"; System.out.println(newCode); }
			newCode += Integer.toString(tailNumber);
			
			System.out.println(newCode);
			
			templateOneVO.setCode(newCode);
			
			
		}catch(Exception e) {
			
			templateOneVO.setCode("T_ONE_00000000000001");
			
		}
		
		
		//filecode 존재 시
		
		if(!templateOneVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			System.out.println("파일명 + " + templateOneVO.getB_filename().get(0).getOriginalFilename());
			makedir(req);
			
			String f_id;										//atchfileid�� ã�� ����
			
			try {	f_id = templateOneDAO.selectTableFileListMax(templateOneVO);	}catch (Exception e) {	f_id = "0";	}
			
			if(f_id == null) { f_id = "0"; }
			
			System.out.println("f_id : " + f_id);
			
			int temp = Integer.parseInt(f_id); temp ++;			//������ �ڵ忡 �� temp ����
			
			System.out.println("after f_id : " + temp);
			
			int counttemp = Integer.parseInt(templateOneDAO.countTableAtchFileId(templateOneVO))+1;
			
			templateOneVO.setB_file_id(Integer.toString(counttemp));//file_table�� f_id�� �� ���� ���̵� ����
			templateOneVO.setAtchFileId(Integer.toString(counttemp));
	
			List<String> newCodeList = new ArrayList<String>();		//������ ÷���� ����ŭ file_table�� �� "������"�ڵ� ����
			
			for(int i = 0; i < templateOneVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//����ִ� �ڸ����ڸ�ŭ 0�� ä��
				
				newCode = newCode + (i+temp);					//file_0000,,,������ �ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}
	
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table�� ����ؾ��� filevo�� ����Ʈ
			
			for(int i = 0 ; i < templateOneVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//����Ʈ�� �ֱ� ���� ��� fileVO
				
				originFilename = templateOneVO.getB_filename().get(i).getOriginalFilename();//������ Ȯ���ڸ� ������ �̸�(���ϸ�)
				writeFile(templateOneVO.getB_filename().get(i), originFilename);//���ε� ������ ���� ���ε�
				
				mfileVO.setCode(newCodeList.get(i));
				mfileVO.setFid(templateOneVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("----------------------작성 파일의 세부 구성요소--------------------------");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
			System.out.println("-------------------------------------------------------------------");
		     
		    mboardDAO.insertFileBoardDAO(fileVO);
			
		}else {System.out.println("file����");}
		
		templateOneDAO.insertTableRecord(templateOneVO);
	}
	
	@Override
	public void updateTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception {
		
		
		//System.out.println("update���� ���ӵ�");
		//System.out.println(templateOneVO.getB_filename().get(0).getOriginalFilename());
		
		//delarray�� ���� �����ϰ� fsign �������ϱ�
		
		if( !templateOneVO.getDelArray()[0].equals("") ) { System.out.println("delarray :" + templateOneVO.getDelArray()[0]); fileDAO.deleteFileArray(templateOneVO.getDelArray()); } // delarray�� ���� ����
		
		
		if(!templateOneVO.getB_filename().get(0).getOriginalFilename().equals("")){
			
			makedir(req);
				
			int temp = 0;
			int tailNumber;
			String maxCode = templateOneDAO.selectTableFileListMax(templateOneVO);
			System.out.println("maxcode : " + maxCode);
			
			if(maxCode.equals("0")) {}
			
			FileVO mFileVO = fileDAO.selectFileCodeMax();
			
			try { 
				
				temp = Integer.parseInt(mFileVO.getCode().replace("FILE_", "")); //�̹� ��ϵ� �ڵ��� �ִ밪�� �����ͼ� file_�κ��� ����
				tailNumber = temp;
			
			}catch(Exception e) { tailNumber = Integer.parseInt(maxCode) + 1; System.out.println("ĳġ��"); } //������ ÷�εǾ� ���� ������  maxCode + 1�� ����(file table���� ���� ������ 1�� ��)
			
			System.out.println("�����ڵ�ѹ� : " + tailNumber);
			System.out.println("temp : " + temp);
			
			List<String> newCodeList = new ArrayList<String>();		//������ ÷���� ����ŭ file_table�� �� "������"�ڵ� ����
			
			for(int i = 0; i < templateOneVO.getB_filename().size(); i++) {
				
				String newCode = "FILE_";
				int j = 1;
				
				while(temp >= 10) { temp /= 10; j++; }				//temp�� �ڸ����� ����� 0������ ä�� Ƚ���� ����
				
				for(int k = 0; k < 15-j ; k++) { newCode += "0";  }	//����ִ� �ڸ����ڸ�ŭ 0�� ä��
				
				newCode = newCode + (i+temp+1);						//file_0000,,,������ �ڵ� ����, temp�� 1�� ���ؼ� ���ο� file�ڵ� ����
				System.out.println("newcode : " + newCode);
				
				newCodeList.add(newCode);
				
			}
			
			templateOneVO.setB_fileCode(newCodeList);
			
			//String atchFileId = templateOneDAO.selectTableAtchFileId(templateOneVO);//������ ���� code�� ���� atchfileid�� ����
			String atchFileId = templateOneVO.getAtchFileId();
			String countAtchFileId = templateOneDAO.countTableAtchFileId(templateOneVO);//�Խ��ǿ� atchfileid�� �ִ��� ����
			
			if(countAtchFileId == null || countAtchFileId.equals("") ) { System.out.println("atchfileid����"); atchFileId = "1"; }		//�Խ��ǿ� atchfileid�� ������ 1���� ����
			else if(templateOneVO.getAtchFileId().equals("")) {											//�Խù��� atchfileid�� ������
				
				System.out.println("�� atchfileid����");
				System.out.println("atchfileId = " + atchFileId);
				
				int countAtchFileNumber = Integer.parseInt(countAtchFileId)+1;
				
				templateOneVO.setAtchFileId(Integer.toString(countAtchFileNumber)); //�ش� �Խù��� ī��Ʈ+1�� atchfileid�� �־���
				
			}else { System.out.println("�� atchfileid����"); }
																//atchfileid�� ������ �ش� atchfileid�� ���ľ�
			//templateOneVO.setAtchFileId(atchFileId);			//������ �����ٰ� ���� ��츦 ����ؼ� ����
			
			
			int sign = 0;
	
			try { sign = fileDAO.selectFileSign(atchFileId); }catch (Exception e) {	sign = 0; }		//f_sign�ִ밪 ����
			System.out.println("fsign�� : " + sign);
			
			
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table�� ����ؾ��� filevo�� ����Ʈ
			
			for(int i = sign ; i < sign + templateOneVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//����Ʈ�� �ֱ� ���� ��� fileVO
				
				originFilename = templateOneVO.getB_filename().get(i - sign).getOriginalFilename();//������ Ȯ���ڸ� ������ �̸�(���ϸ�)
				writeFile(templateOneVO.getB_filename().get(i - sign), originFilename);//���ε� ������ ���� ���ε�
				
				System.out.println("��ȣ : " + Integer.toString(sign + templateOneVO.getB_filename().size() - i));
				mfileVO.setCode(templateOneVO.getB_fileCode().get(sign + templateOneVO.getB_filename().size() - i - 1));
				mfileVO.setFid(templateOneVO.getAtchFileId());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
			System.out.println("----------------------작성 파일의 세부 구성요소--------------------------");
			System.out.println(fileVO.get(0).getCode());
			System.out.println(fileVO.get(0).getFid());
			System.out.println(fileVO.get(0).getFsign());
			System.out.println(fileVO.get(0).getFpath());
			System.out.println(fileVO.get(0).getFname());
			System.out.println("-------------------------------------------------------------------");
			
		    mboardDAO.insertFileBoardDAO(fileVO);
	
			//filevolist�� atchfileid(fid)�� ������ ���� �Է��ϰ�, index�� fsign�� ������ ����, updateFile�� fsign�� update 
			List<FileVO> mFileVOList = new ArrayList<FileVO>();
			
			mFileVOList = fileDAO.selectFileMenuList(templateOneVO.getAtchFileId());
			System.out.println("sign�� fid : " + mFileVOList.get(0).getFid());
			
			for(int i = 0; i < mFileVOList.size(); i++) { 
				mFileVOList.get(i).setSiteCode(templateOneVO.getSiteCode());
				mFileVOList.get(i).setFsign(i);
				fileDAO.updateFileSign(mFileVOList.get(i));	
			}
			
		}
		
		//if(templateOneVO.getDelArray()[0] != "" )
		
		System.out.println("delArray : " + templateOneVO.getDelArray()[0]);
		
		templateOneDAO.updateTableRecord(templateOneVO);
	}
	
	@Override
	public void deleteTableRecord(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		
		templateOneDAO.deleteFileRecord(templateOneVO);
		templateOneDAO.deleteTableRecord(templateOneVO);
		
		//File f = new File("C:/upload/"+fname);
		
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
	*/
}
