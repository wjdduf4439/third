package com.ljy.third.controller;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.third.service.logAdminService;
import com.ljy.third.vo.logAdminVO;

//로그 기입 동작을 담당하는 컨트롤러
@RestController
public class logInsertController {

	@Resource(name = "logAdminService")
	logAdminService logAdminService;
	
	//ajax처리로 로그 테이블을 기록하는 용도로 쓰임
	//restController로 쓸것!
	@RequestMapping(value = "/log/logInsert.go")
	public String logInsert(HttpServletRequest request) throws Exception {
		
		/*
		 * System.out.println("logInsert 메소드 접속");
		 * System.out.println("ip : " + logAdminVO.getIp());
		 * System.out.println("logReq : " + logAdminVO.getLogReq());
		 */
		//logCode는 service에서 결정		
		//logtime는 db에서 설정

		ObjectMapper objectMapper = new ObjectMapper();
		
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		//System.out.println("messageBody={} : " + messageBody);
		
		logAdminVO logAdminVO = objectMapper.readValue(messageBody, logAdminVO.class);
		
		//System.out.println("ip : " + logAdminVO.getIp());
		//System.out.println("logReq : " + logAdminVO.getLogReq());
		
		logAdminService.insertlogAdmin(logAdminVO);
		
		return "success";
	}
		
		//request를 json으로 변환하는 과정
	/*
		public static String readBody(HttpServletRequest request) throws IOException {
	        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
	        StringBuilder builder = new StringBuilder();
	        String buffer;
	        while ((buffer = input.readLine()) != null) {
	            if (builder.length() > 0) {
	                builder.append("\n");
	            }
	            builder.append(buffer);
	        }
	        return builder.toString();
		}
	*/
	
}

