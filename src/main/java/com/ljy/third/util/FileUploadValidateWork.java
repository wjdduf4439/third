package com.ljy.third.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.dao.SiteDAO;
import com.ljy.third.vo.SiteMenuVO;

public class FileUploadValidateWork {

	//테이블에 등록된 각 사이트 정보를 가져와서 파일 검증에 필요한 필드를 가져온다.
	//bean 등록을 해야 제대로된 dao에서 값을 가져오고 검증 과정을 거칠 수 있음
	//의존성 주입이 제대로 이루어지지 않았다.

	
	private SiteMenuVO confSiteMenuVO;
	
	private boolean resultValidateWork = false;
	private String message = "";
	
	public FileUploadValidateWork(MultipartHttpServletRequest request, SiteMenuVO resultSiteMenuVO) throws Exception {
		
		//생성자에서부터 각 게시판 정보를 가져와서 검증 정보를 가져온다.
		//선언한 메소드 일부/전부를 끌어와서 생성자 내에서 검증 작업을 거친다
		//검증이 다 되고 난 값은 resultValidateWork에 저장하고 getter로 호출한다.
		this.confSiteMenuVO = resultSiteMenuVO;

		
		//request의 내재되어 있는 file 객체의 key값을 가져옴
		String fileKeyName = "";
		Iterator<String> fileNameIter = request.getFileNames();
		while (fileNameIter.hasNext()) {
			fileKeyName = fileNameIter.next();
			System.out.println("요청에서 가져온 파일 key 이름 value: " + fileKeyName);
		}
		
		//가져온 key값을 해당하는 항목에 맞춰서 총 파일 이름을 가져옴
		Map<String, List<MultipartFile>> paramMap = request.getMultiFileMap();
		List<MultipartFile> files = paramMap.get(fileKeyName);
		
		if( validatingFileType(files, fileKeyName) == true && 
			validatingFileNumber(files) == true
			) { 
			this.resultValidateWork = true;
			}
		
	}
	
	//파일 업로드 파일 검증
	public boolean validatingFileType(List<MultipartFile> files, String fileKeyName) throws Exception {
		
		//request의 파일정보를 가져와서 this.confSiteMenuVO에 저장된 검증정보와 대조를 하고 위반사항이 발생하면 false값을 return시킨다.		
		//Iterator = 반복 호출 리스트
		
		for (MultipartFile file : files) {
			System.out.println(fileKeyName + "안에 있는 파일 이름 :" + file.getOriginalFilename());
			System.out.println(fileKeyName + "안에 있는 파일 확장자 :" + makeSavingFileType(file.getOriginalFilename()) );
			
			//확장자명의 소문자가, 해당 게시판 설정의 허용 파일 타입과 맞지 않으면 false를 return하고 종료
			if( !this.confSiteMenuVO.getFileUploadType().contains(makeSavingFileType(file.getOriginalFilename()).toLowerCase()) ) {
				this.message = "허용되지 않은 파일명을 업로드하려 하였습니다. " + makeSavingFileType(file.getOriginalFilename());
				return false;
			}
				
				   
		}
		
		return true;
	}
	
	
	//파일 업로드 개수 검증
	public boolean validatingFileNumber(List<MultipartFile> files) throws Exception {
		
		//가져온 file list의 size가 resultSiteMenuVO에 허용된 파일 업로드 갯수보다 초과하면 false를 return 종료
		if( files.size() >  Integer.parseInt( this.confSiteMenuVO.getMaxFileUploadNumber() ) ) {
			this.message = this.confSiteMenuVO.getMaxFileUploadNumber() + "보다 많은" +  files.size() + "개의 파일명을 업로드하려 하였습니다.";
			return false;
		}
		
		return true;
	}

	//파일 이름을 받아서 확장자를 추출하는 메소드
	private String makeSavingFileType( String originFileName ) {

		List<Integer> indexList = new ArrayList<Integer> ();
		String fileTypeMark = ".";
		String resultFileType = "";
		int savingFileTypeIndex = originFileName.indexOf(fileTypeMark);
		
		while(savingFileTypeIndex != -1) {
			
			if(savingFileTypeIndex == -1) break;
			
			indexList.add(savingFileTypeIndex);
			//더이상 찾을 .이 없으면 originFileName.indexOf(의 값은 -1을 표시한다.
			savingFileTypeIndex = originFileName.indexOf(fileTypeMark, savingFileTypeIndex + fileTypeMark.length());
		}
		
		//System.out.println("indexList.get(indexList.size() - 1) : " + indexList.get(indexList.size() - 1));
		
		//. 다음에 있는 문자열부터 파일명의 끝까지의 범위를 가져와서 확장자명으로 정의함
		resultFileType = originFileName.substring(indexList.get(indexList.size() - 1) + 1, originFileName.length());
		
		return resultFileType;
	}
	
	//최종 파일 검증 결과값 출력
	public boolean isResultValidateWork() {
		return resultValidateWork;
	}

	public String getMessage() {
		return message;
	}
	
}
