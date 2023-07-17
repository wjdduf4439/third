package com.ljy.third.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ljy.third.vo.BoardVO;
import com.ljy.third.vo.FileVO;
import com.ljy.third.dao.BoardDAO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "BoardDAO")
	private BoardDAO mboardDAO;
	
	private String SAVE_PATH = "";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Override
	public List<BoardVO> listBoardService(BoardVO mboardVO) throws InterruptedException {
		return mboardDAO.listBoardDAO(mboardVO);
	}

	@Override
	public BoardVO lookBoardService(BoardVO mboardVO) throws InterruptedException {
		return mboardDAO.lookBoardDAO(mboardVO);
	}

	@Override
	public List<FileVO> lookFileBoardService(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return mboardDAO.lookFileBoardDAO(mboardVO);
	}

	@Override
	public FileVO lookOneFileBoardService(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return mboardDAO.lookOneFileBoardDAO(mboardVO);
	}

	@Override
	public FileVO lookOneFileBoardService(String atchFileId) {
		// TODO Auto-generated method stub
		return mboardDAO.lookOneFileBoardDAO(atchFileId);
	}

	@Override
	public int countBoardService() throws InterruptedException {
		return mboardDAO.countBoardDAO();
	}

	@Override
	public void insertBoardService(BoardVO mboardVO, HttpServletRequest req) throws Exception {
		
		makedir(req);
		
		BoardVO newboardVO = mboardDAO.oneBoardDAO(mboardVO);//code����� ���� vo����
		int newcode = newboardVO.getB_code();
		mboardVO.setB_code(newcode+1);
		mboardVO.setB_file_id(Integer.toString(newcode+1));//file_table�� f_id�� �� ���� ���̵� ����
		
		try {
			
			String originFilename = "";
			List<FileVO> fileVO = new ArrayList<FileVO>();
			
			//file_table�� ����ؾ��� filevo�� ����Ʈ
			
			for(int i = 0 ; i < mboardVO.getB_filename().size(); i++  ) {
	
				FileVO mfileVO = new FileVO();//����Ʈ�� �ֱ� ���� ��� fileVO
				
				originFilename = mboardVO.getB_filename().get(i).getOriginalFilename();//������ Ȯ���ڸ� ������ �̸�(���ϸ�)
				writeFile(mboardVO.getB_filename().get(i), originFilename, req);//���ε� ������ ���� ���ε�
				
				mfileVO.setFid(mboardVO.getB_file_id());
				mfileVO.setFsign(i); 
				mfileVO.setFpath( PREFIX_URL + originFilename); //������ ��� + ������ �̸����� �������ϰ�� ����
				mfileVO.setFname(originFilename); //������ �̸�
				
				fileVO.add(mfileVO);
				
			}
		     
		     mboardDAO.insertFileBoardDAO(fileVO);
		}catch(Exception e){
			
		}
	     
		 mboardDAO.insertBoardDAO(mboardVO);
		
	}

	@Override
	public void deleteBoardService(BoardVO mboardVO) throws Exception {
		mboardDAO.deleteBoardDAO(mboardVO);
	}
	
	@Override
	public void updatehitBoardService(BoardVO mboardVO) throws Exception {
		 mboardDAO.updatehitBoardDAO(mboardVO);
	}
	
	//file의 데이터를 기입하고 실제 디렉터리에 파일을 넣음
	private void writeFile(MultipartFile b_filename, String saveFileName, HttpServletRequest req) throws IOException {
		makedir(req);
		byte[] data = b_filename.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data); fos.close();
		
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
