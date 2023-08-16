package com.ljy.third.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljy.third.service.BoardService;
import com.ljy.third.service.FileService;
import com.ljy.third.vo.BoardVO;
import com.ljy.third.vo.FileVO;

@Controller
public class FileController {

	@Resource(name = "BoardService")
	private BoardService mBoardService;
	
	@Resource(name = "FileService")
	private FileService fileService;
	
	
	@RequestMapping(value = "/fileboard.go")
	public String fileboard(@ModelAttribute("searchVO")BoardVO mboardVO, ModelMap map) throws Exception {
		
		String filename = mboardVO.getB_file_name();//������ ���� �̸��� ���� ����
		
		mboardVO = mBoardService.lookBoardService(mboardVO);//�Խù� ���� ���� ȣ��
		mboardVO.setB_file_name(filename);//�ش� �Խù��� ������ �ٿ�ε��� ���ϸ� ����
		
		FileVO mfileVO = mBoardService.lookOneFileBoardService(mboardVO);
		
		File downloadFile = new File(mfileVO.getFpath());
		File realFileName = new File(mfileVO.getFname()); 
		
		if(!downloadFile.canRead()) { return "redirect:/mainboard.go"; }
		
		map.addAttribute("downloadFile", downloadFile);
		map.addAttribute("realFileName", realFileName);//��¥ ���� �̸����� �ٿ�ε��ϱ� ���� ����
		
		return "fileDownloadView";
	}
	
	@RequestMapping(value = "/file/fileAdmin.go")
	public String fileDownloadForm(FileVO fileVO, @RequestParam String atchFileId, ModelMap map) throws Exception {
		
		try {
			//atchFileId의 값을 분해하여 filemapper의 selectFileList 부분에 인식시켜야함
			List<FileVO> fileList = fileService.selectFileMenuList(atchFileId);
			
			map.addAttribute("fileList", fileList);
			
			
			return "/file/fileDownloadForm";
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/file/fileDownloadForm";
	}
	
	@RequestMapping(value = "/file/fileViewAdmin.go")
	public String fileViewDownloadForm(FileVO fileVO, @RequestParam String atchFileId, ModelMap map) throws Exception {
		
		try {
			
			List<FileVO> fileList = fileService.selectFileMenuList(atchFileId);
			
			map.addAttribute("fileList", fileList);
			
			
			return "/file/fileViewDownloadForm";
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/file/fileViewDownloadForm";
	}
	
	
	@RequestMapping(value = "/filedown.go")
	public String fileDown(String code, ModelMap map, HttpServletRequest req) throws Exception {
		
		FileVO mfileVO = mBoardService.lookOneFileBoardService(code);
		
		File downloadFile = new File(mfileVO.getFpath());
		File realFileName = new File(mfileVO.getFname());//
		
		
		if(!downloadFile.canRead()) { return "redirect:/mainboard.go"; }
		
		/*
		map.addAttribute("downloadFile", downloadFile);
		map.addAttribute("realFileName", realFileName);//
		*/
		
		req.setAttribute("realFileName", realFileName);
		System.out.println("1차 완료 realFileName : " + realFileName);
		
		return "forward:/fileDownloadView";
	}
	
	@RequestMapping(value = "/fileDelete.go")
	public String fileDelete(String code, String fid, String fsign, String sitecode, String fname) throws Exception {
		
		System.out.println("code : " + code);
		System.out.println("fid : " + fid);
		System.out.println("fsign : " + fsign);
		System.out.println("sitecode : " + sitecode);
		System.out.println("fname : " + fname);
		//request ���� httpservletrequest ����� �ʿ���
		//System.out.println(request.getSession().getServletContext().getRealPath("resources/image/")+"\\"+fname);
		System.out.println("C:/upload/"+fname);
		
		//File f = new File(request.getSession().getServletContext().getRealPath("resources/image/")+"\\"+fname);
		File f = new File("C:/upload/"+fname);
		
		try {
			if (f.delete()) {
			      System.out.println("���� �Ǵ� ���丮�� ���������� �������ϴ�: ");
			    } else {
			      System.err.println("���� �Ǵ� ���丮 ����� ����: ");
			    }
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		fileService.deleteFileMenu(code, fid, fsign, sitecode);
		
		if(fileService.selectAtchFileCount(fid) == 0) {
			
		}
		
		return "mainboard";
	
	}
	
	@RequestMapping(value = "/file/fileIconView.go")
	public String fileIconView(String code, String fid, ModelMap map) throws Exception{
		
		System.out.println("�޾Ƴ� fid : " + fid);
		System.out.println("�޾Ƴ� code : " + code);
		
		List<FileVO> mfileVO = fileService.selectFileMenuList(fid);
		
		map.addAttribute("mfile", mfileVO);
		map.addAttribute("code", code);
		
		return "/file/fileViewForm";
	}
	
	@RequestMapping(value = "/file/fileScreenView.go" , produces = "application/text; charset=utf8")
	@ResponseBody
	public String fileScreenView(String fname, ModelMap map) throws Exception{
		
		System.out.println("fname : " + fname);
		
		//String ajaxReturn = "/img/"+fname; 
		
		//String ajaxReturn = "<img id='screenView' name='screenView' class='screen_View' src='/controller/resources/image/"+fname+"' />";
		String ajaxReturn = "<img id='screenView' name='screenView' class='screen_View' src='C:/upload/"+fname+"' />";
		
		return ajaxReturn;
	}
	
	
	
	
	
	
	
}
