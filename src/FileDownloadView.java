package com.first.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView {
	
	public FileDownloadView() {
		
		setContentType("appllication/download; charset=utf-8");
		
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		 
		//HttpServletResponse : ������ Ŭ���̾�Ʈ�� ���� ����/������ ������ �ִ� ��ü
		
		File file = (File) model.get("downloadFile");
		File realfile = (File) model.get("realFileName");
		
		res.setContentType(getContentType());
		//�������� MIME Ÿ��(���۵� ������ � �������� �˷��ִ� Ÿ��)��  �⺻������ text/htmlŸ��������
		//�� Ŭ����(��)������ MIMEŸ���� �����ڿ��� ������ appllication/download; charset=utf-8�� �ȴ�.
		
        res.setContentLength((int) file.length());
        //��û�� ������ ��������� �����ϱ� ���� ����ϴ� �ڵ�
        
        res.setHeader("Content-Disposition", "attachment; filename=\"" +  java.net.URLEncoder.encode(realfile.getName(), "UTF-8") + "\";");
        //ǥ�� ���� ����� �����ϴ� �κ�
        //MIME Content-disposition ����� ���� �κп� ���� ǥ�� ������ �����մϴ�.
        //����� ÷�� ������ ������ ���� �̸����� ǥ�������� �����Ѵ�.
        //urlencoder�� �ش� ����� ������ �����Ѵ�.
        
        res.setHeader("Content-Transfer-Encoding", "UTF-8");
        //res.setHeader("Content-Transfer-Encoding", "utf-8");
        //����� ���ڼ°� ���ڵ� ����� ����
        
        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(file);

            FileCopyUtils.copy(fis, out);
            //�ٿ�ε� �� �� �����Ͽ� ������ ����� �ڵ�

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if(fis != null) {

                try { fis.close(); }
                catch (IOException e) { e.printStackTrace(); }

            }

        }

        out.flush();
		
	}

}
