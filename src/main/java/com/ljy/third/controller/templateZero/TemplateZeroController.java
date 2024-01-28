package com.ljy.third.controller.templateZero;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ljy.third.util.FileUploadValidateWork;
import com.ljy.third.util.PageSet;
import com.ljy.third.service.FileService;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.service.templateZero.TemplateZeroService;
import com.ljy.third.vo.FileVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.site.SiteMenuVO;
import com.ljy.third.vo.templateZero.TemplateZeroVO;

//json 데이터 vo로 파싱하는법
//https://ynzu-dev.tistory.com/entry/JAVA-json-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-VO%EB%A1%9C-%ED%8C%8C%EC%8B%B1-%EB%B3%80%ED%99%98%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95-json-to-java-class

//일반 게시판의 동작을 담당하는 컨트롤러
@Controller
public class TemplateZeroController {	
	
	@Value("${server.servlet.context-path}")
	private String rootContextPath;

	@Resource(name ="TemplateZeroService")
	private TemplateZeroService templateZeroService; 
	
	@Resource(name ="SiteService")
	private SiteService siteService; 
	
	@Resource(name = "FileService")
	private FileService fileService;

	//private String SAVE_PATH =  "C:/sts-bundle/workspace-sts-3.9.4.RELEASE/first/src/main/webapp/resources/image";
	private String SAVE_PATH =  "";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	
	@RequestMapping("/template/templateZero{processMark:List|Write}.go")
	public String TemplateZeroList(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req
			,@PathVariable("processMark")String processMark ) throws Exception {
		
		String viewMessage = "";
		
		/*
		 	
		 	전체적으로 list작업은 field 정보가 담긴 templateInfoVO를 서비스에 넣어 TemplateZeroVO의 값을 가져오는 것을 목표로 한다
		 	templateInfoVO -> db에서 테이블의 field 값을 가져옴
		 	templateZeroVO -> 페이지에서 form의 input 값을 가져옴, 페이징 처리에 사용																									
		 	
		 */
		if( "List".equals(processMark) ) {

			TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
			templateZeroVO.setSiteTitle(templateInfoVO.getTitle());
			int countList = templateZeroService.selectTableRecordListCount(templateInfoVO);

			/*
			recordSet 작업 후, ZeroVO의 pageindex를 
			현재 페이징을 선언하면 currpage가 1인 상태에서 전달받을 경우, 1에서 더 늘어나는 현상이 보임
			*/
			PageSet paging = new PageSet(templateZeroVO.getPageIndex(), countList, templateZeroVO.getRecordCountPerPage());
			{
				templateZeroVO = (TemplateZeroVO) paging.recordSet(templateZeroVO);
				templateInfoVO.setPageIndex( templateZeroVO.getPageIndex()  );
				templateInfoVO.setRecordCountPerPage( templateZeroVO.getRecordCountPerPage()  );
				
			}
			
			List<TemplateInfoVO> fieldList = templateZeroService.selectTableFieldList(templateInfoVO);
			List<TemplateZeroVO> resultList = templateZeroService.selectTableRecordList(templateInfoVO);
			List<TemplateZeroVO> noticeList = new ArrayList<TemplateZeroVO>(); //공지사항 리스트 가져오기
			{
				noticeList = templateZeroService.selectTableNoticeList(templateZeroVO);
				if( noticeList.size() > 0 ) { map.addAttribute("noticeList", noticeList); } //noticelist
				else { map.addAttribute("noticeList", null); }
			}
			
			map.addAttribute("fieldWidth", templateInfoVO.getFieldWidth());
			map.addAttribute("fieldList", fieldList);
			map.addAttribute("resultList", resultList);
			map.addAttribute("countList", countList);
			map.addAttribute("paging", paging);
			viewMessage = "templateZero/templateZeroMenu";
			
		}else if( "Write".equals(processMark) ) {
			
			TemplateZeroVO resultVO = templateZeroService.selectTableRecordOne(templateZeroVO);
			
			SiteMenuVO mSiteMenuVO = new SiteMenuVO();
			mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
			SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);
			String primaryCode = resultSiteMenuVO.getPrimaryCode();
			
			String[] nArray =  new String[1];
			
			//현재 resultSiteMenuVO
			map.addAttribute("primaryCode", primaryCode);
			if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) {
				
				nArray = resultSiteMenuVO.getNoticeSwitch().split(",");
				for(int i = 0; i < nArray.length; i++) { if( nArray[i].equals ( templateZeroVO.getCode() ) ){ resultVO.setNoticeSwitch("1"); } }
			
			}

			//내용 첨부 이미지 불러오기
			//code 값이 없으면 내용첨부이미지 확인 절차를 생략하고, code 값이 있으면 내용첨부이미지 확인 절차 진행
			//System.out.println("들어올 때의 code값 : " + templateZeroVO.getCode() );
			if( !"".equals(templateZeroVO.getCode()) ) {

				TemplateZeroVO ECFresultVO = templateZeroService.selectTableECFRecordList(templateZeroVO);
				//이미첨부 기능이 없는 게시물이나 처음 화면에서 불러올때를 대비해 null대비함
				if(!"".equals(ECFresultVO.getEditorImage())) { resultVO.setEditorImage(ECFresultVO.getEditorImage()); }
				
			}
			map.addAttribute("resultList", resultVO);
			viewMessage = "templateZero/templateZeroWrite";
		}

		
		return viewMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/template/templateZero{processMark:Insert|Update}.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
//	@RequestMapping("/template/templateZero{processMark:Insert|Update}.go")
	public HashMap<String, Object> TemplateZeroProcess(HttpServletRequest request, @RequestBody HashMap<String, String> stringJson, 
			@PathVariable("processMark")String processMark ) throws Exception {

		System.out.println("templateZero dto 값 결과 : " + stringJson.toString());
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}

		//공지사항 표시용 sitemenuvo
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		//mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		mSiteMenuVO.setSiteCode(stringJson.get("siteCode"));
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);

		//파일 검증 과정
		FileUploadValidateWork fValidate = null;
		boolean resultValidateWork = false;
		/*
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			fValidate = new FileUploadValidateWork(multiRequest, resultSiteMenuVO);
			resultValidateWork = fValidate.isResultValidateWork();
			
			//파일 검증 실패 시 
			if(resultValidateWork == false) {
				map.addAttribute("System_errMessage", "파일 검증 실패 : " + fValidate.getMessage());
				resultMap.put("returnPage",  rootContextPath + "/alert/href" );
				return resultMap;
			}
		}
		*/
		
		if( "Insert".equals(processMark) ) {

			/*
			insert 작업시, 작업 시점에서의 가장 큰 maxcode를 먼저 찾아낸 다음 insert 작업을 수행하고, 이 수행 시의 maxcode는 upload에서 재사용되게 만든다.
			*/
			String maxCode = templateZeroService.selectTableRecordListMax(stringJson);
			stringJson.put("maxCode", maxCode);
			resultMap.put("resultCode", maxCode );
			templateZeroService.insertTableRecord(stringJson, request);
			
		}else if( "Update".equals(processMark) ) {
			
			templateZeroService.updateTableRecord(stringJson, request);
			resultMap.put("resultCode", stringJson.get("code") );
			
		}
		
		resultMap.put("returnPage",  rootContextPath + "/template/templateZeroList.go" );
		return resultMap;
	}
	@ResponseBody
	@RequestMapping(value = "/template/templateZeroDelete.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> TemplateZeroDelete(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		if( "N".equals( stringJson.get("del_chk") ) ) {
			
			System.out.println("비활성화 진행");
			templateZeroService.disableTableRecord(stringJson);
			
		} else {
			
			System.out.println("삭제 진행");
			templateZeroService.deleteTableRecord(stringJson);
			
		}
		
		resultMap.put("returnPage",  rootContextPath + "/template/templateZeroList.go" );
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/template/templateZeroRestore.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> TemplateZeroRestore(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		System.out.println("복구 진행");
		templateZeroService.restoreTableRecord(stringJson);
		
		resultMap.put("returnPage",  rootContextPath + "/template/templateZeroList.go" );
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/template/templateZeroUpload.go", method = RequestMethod.POST)
	public HashMap<String, Object> templateZeroUpload(HttpServletRequest request,
			@RequestPart(value = "siteCode") String siteCode, 
			@RequestPart(value = "code") String code, 
			@RequestPart(value="b_filename",required = false) List<MultipartFile> uploadFile
			) throws Exception {

		HashMap<String, String> atchFileIdMap = new HashMap<String, String>();
		HashMap<String, String> fileMap = new HashMap<String, String>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		System.out.println("templateZeroUpload uploadFile : " + siteCode.toString());
		System.out.println("templateZeroUpload uploadFile : " + code.toString());
		System.out.println("templateZeroUpload uploadFile : " + uploadFile.get(0).getOriginalFilename());
		
		String originFilename = "";
		String savingFilename = "";
		int savingFileTypeIndex = 0;
		String savingFileType = "";
		
		TemplateZeroVO atchFileIdVO = new TemplateZeroVO();
		
		if(!uploadFile.get(0).getOriginalFilename().equals("")){
			
	
			String maxCode;	//atchfileid를 담을 변수
			makedir(request);	//파일이 있다면 파일경로를 먼저 지정
			try {	maxCode = fileService.selectFileCodeMax();	}catch (Exception e) {	maxCode = "0";	}

			String maxAtchFileIdString = templateZeroService.selectTableAtchFileIdMax(siteCode); //최대 atchFileId값을를 가져옴
			int counttemp = 0;
			System.out.println("가져온 maxAtchFileIdString : " + maxAtchFileIdString);
			
			//maxTableAtchFileId의 값이 0이면 무조건 앞자리만 0이고, 해당 게시판에 처음 들어오는 첨부파일이니 counttemp 값을 1로 해준다
			//maxTableAtchFileId의 값이 0이 아니라면 _AND_SITE_ 구문 앞까지의 번호를 추출해서 1을 더한다			
			if( maxAtchFileIdString.substring(0,1) == "0" ) {
				counttemp = 1; 
			} else {
				maxAtchFileIdString = maxAtchFileIdString.substring(0, maxAtchFileIdString.indexOf("_AND_SITE_")); //_AND_SITE_ 부분을 제거된 값을 숫자로 채워서 넣음
				counttemp = Integer.parseInt(maxAtchFileIdString)+1; //최대 fid에 1을 더함
			}
			
			//FILE_TABLE에 넣을 fid 값을 setB_file_id 로 지정하기
		    //SITE_ 테이블에 넣을 atchFileId 값을 getB_file_id 로 지정하기
			atchFileIdVO.setB_file_id(Integer.toString(counttemp));
			atchFileIdVO.setAtchFileId(atchFileIdVO.getB_file_id() + "_AND_" + siteCode ); 
				
			for(int i = 0 ; i < uploadFile.size(); i++  ) {

				fileMap.put("siteCode", siteCode);
				//새로운 filevo 선언 후 sitecode에 현재 사용하고 있는 게시판 코드를 집어넣음

				//savingfname으로 파일 저장, savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾음
				originFilename = uploadFile.get(i).getOriginalFilename();
				savingFilename = makeSavingFileCode( code, atchFileIdVO.getB_file_id() , i);
				
				//savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾고 파일 형식을 잘라내어 저장함
				savingFileTypeIndex = makeSavingFileTypeIndex(originFilename);
				savingFileType = originFilename.substring(savingFileTypeIndex, originFilename.length());
				
				//최종 파일 저장 형식은 새로 지은 file의 이름과 파일 형식을 같이 하여 저장함
				writeFile(uploadFile.get(i), savingFilename + savingFileType);
				
				/*
				mfileVO.setFid(atchFileIdVO.getB_file_id());
				mfileVO.setFsign(i);							 //������ ����
				mfileVO.setFpath( PREFIX_URL + savingFilename + savingFileType); 
				//fname는 그대로 두고, savingfname는 날짜 + 시간 + code로 설정
				mfileVO.setSavingFname(savingFilename + savingFileType);
				mfileVO.setFname(originFilename); //������ �̸�
				*/
				/*
				INSERT INTO FILE_TABLE(code, siteCode, fid, fsign, fpath, savingFname, fname)
				VALUES(
					createCode(#{ maxCode }, concat(#{ primaryCode },'_'))
				   	<!-- #{code}, -->
				   	#{siteCode},
				    #{fid},
				    #{fsign},
				    #{fpath},
				    #{savingFname},
				    #{fname}
				   )
				)
				*/
				//fileMap.put("siteCode", siteCode);
				fileMap.put("maxCode", maxCode);
				fileMap.put("fid", atchFileIdVO.getB_file_id());
				fileMap.put("fsign", Integer.toString(i));
				fileMap.put("fpath", PREFIX_URL + savingFilename + savingFileType);
				fileMap.put("savingFname", savingFilename + savingFileType);
				fileMap.put("fname", originFilename);
				
				templateZeroService.insertFileRecord(fileMap); 
			}
			atchFileIdMap.put("siteCode", siteCode);
			atchFileIdMap.put("atchFileId", atchFileIdVO.getB_file_id() + "_AND_" + siteCode);
			atchFileIdMap.put("code", code);
			templateZeroService.updateAtchFileId(atchFileIdMap);
			
		}
		
		resultMap.put("successCode",  "success" );
		
		return resultMap;
	}

	
	//파일을 기입하거나 저장할때 경로는 지정
	private void makedir( HttpServletRequest req ) {
		
		String processerName = System.getProperty("os.name").toLowerCase();
		System.out.println("processerName : " + processerName); 
		
		if(processerName.contains("windows")) { this.SAVE_PATH = "c:/upload"; }
		else { this.SAVE_PATH = "/home/ec2-user/third_FileDir"; }
		
		
		this.PREFIX_URL =  SAVE_PATH + "/";
		
	}

	
	private void writeFile(MultipartFile b_filename, String saveFileName) throws IOException, InterruptedException {
		
		byte[] data = b_filename.getBytes();
		
		FileOutputStream fos;
			
		//String path = "c:/upload";
		String path = this.SAVE_PATH;
		File Folder = new File(path);
		
		if (!Folder.isDirectory()) {
			
			    Folder.mkdir(); //���� �����մϴ�
			    
			    Folder.setWritable(true, true);
			    Folder.setReadable(true, true);
			    Folder.setExecutable(true, true);
			    
		}
		
		//실질적으로 파일을 서버 디렉터리에 업로드 및 입력하는 부분
		try {fos = new FileOutputStream(path + "/" + saveFileName); fos.write(data); fos.close();} catch(Exception e) {}
		
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
	
	//파일을 기입하거나 저장할때 현재 시간과 fid, sign에 기반해서 코드를 생성
	private String makeSavingFileCode( String code, String fid, int fsign ) {

		LocalDate  savingDate = LocalDate.now();
		DateTimeFormatter savingDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		
		LocalTime  savingTime = LocalTime.now();
		DateTimeFormatter savingTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		
		return code + "_" + fid + "_" + fsign + "_" + savingDate.format(savingDateFormatter)+ "_" + savingTime.format(savingTimeFormatter);
	}
	
}
