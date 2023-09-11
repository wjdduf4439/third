package com.ljy.third.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.vo.FileEditorContentVO;

public interface FileEditorContentService {

	public FileEditorContentVO insertContentFile(FileEditorContentVO mFileEditorContentVO, MultipartHttpServletRequest request) throws Exception;
	
	
}
