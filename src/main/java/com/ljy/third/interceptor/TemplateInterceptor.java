package com.ljy.third.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ljy.third.controller.TemplateInfoController;
import com.ljy.third.service.TemplateInfoService;
import com.ljy.third.util.EnumLogs;
import com.ljy.third.vo.TemplateInfoVO;


/*
	servletConfig를 사용하여 해당 인터셉터를 등록해주어야 한다.

	- preHandle : 클라이언트의 요청을 컨트롤러에 전달하기 전에 호출된다. 여기서 false를 리턴하면 다음 내용(Controller)을 실행하지 않는다.
	- postHandle : 클라이언트의 요청을 처리한 뒤에 호출된다. 컨트롤러에서 예외가 발생되면 실행되지 않는다.
	- afterCompletion : 클라이언트 요청을 마치고 클라이언트에서 뷰를 통해 응답을 전송한뒤 실행이 된다. 뷰를 생성할 때에 예외가 발생할 경우에도 실행이 된다.
 
*/

/*
 
 	생성자 주입을 통해 파라미터들을 등록하지만 파라미터들이 빈으로 등록되어 있지 않으면 Parameter 0 of constructor in com.ljy.third. ~ 에러가 발생한다
 
 */

@Component
public class TemplateInterceptor implements HandlerInterceptor { 

		//생성자 주입 전역변수 -- 인터셉터에서는 활용불가
		/*
		 * private final TemplateInfoVO mtemplateInfoVO;
		 * 
		 * @Autowired public TemplateInterceptor(TemplateInfoVO mtemplateInfoVO) {
		 * 
		 * this.mtemplateInfoVO = mtemplateInfoVO;
		 * 
		 * }
		 */
	
		/*
			마지막 인자인 handler는 HandlerMethod 타입으로 캐스팅한 후
			원래의 메소드와 빈(객체)을 확인할 수 있다.
		*/
	
		@Resource(name ="TemplateInfoService")
		private TemplateInfoService templateInfoService;
		
		@Override
   		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			
			//request의 sitecode 부분을 찾아서 mTemplateInfoVO를 패밒시킨 다음 mTemplateInfoVO의 정보를 기존의 request에 넣을 수 있도록 해야 한다.
			//siteCode부분은 가져오되..... siteCode를 db에서 돌리고 값을 찾아오게 시킨다.
			//기존 CONTROLLER에서는 MAP형식으로 값을 보냈지만, REQUST형식으로 값을 담아서 보낸다.
			
			String[] fieldNumber;		//게시판에 나오는 항목들의 순번을 결정하는 값
			String[] fieldName;			//게시판의 나오는 항목들의 이름을 결정할 값
			String[] fieldWidth = {"50"};	//게시판의 폭을 결정할 값
			
			TemplateInfoVO tableInfo = new TemplateInfoVO(); tableInfo.setSiteCode(request.getParameter("siteCode"));
			TemplateInfoVO tableName = templateInfoService.selectTableName(tableInfo);
			tableName.setSiteCode(tableName.getSiteCode());
			tableName.setTitle(tableName.getTitle());
			
			//표시할 열 번호를 가져오고 배치하는 구간.
			fieldNumber = tableName.getPlaceRow().toString().split(",");
			tableName.setFieldNumber(fieldNumber);//배열로 변환된 값을 삽입
			
			//열이름은 각 테이블 필드의 주석을 바탕으로 한다.
			fieldName = tableName.getPlaceName().toString().split(",");
			tableName.setFieldName(fieldName);//배열로 변환된 값을 삽입
			
			//열너비는 null예외를 방지하기 위해 다음과 같이 처리
			try {  fieldWidth = tableName.getPlaceWidth().toString().split(","); }
			catch(NullPointerException e) { System.out.println(e.getMessage()); }
			finally { tableName.setFieldWidth(fieldWidth); } 
			
			
			request.setAttribute("templateInfoVO", tableName);
			EnumLogs e = new EnumLogs(request);  e = null;
			
			
			//System.out.println("request siteCode : " + (TemplateInfoVO) request.getAttribute("templateInfoVO") );
			
			//return preHandle( request,  response,  handler,  mTemplateInfoVO);
			return true;
		}

		
	 
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	        // TODO Auto-generated method stub
	    	
	    }
	        
	
}

