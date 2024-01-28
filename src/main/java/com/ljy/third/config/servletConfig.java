package com.ljy.third.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ljy.third.filter.searchFilter;
import com.ljy.third.filter.uriFilter;
import com.ljy.third.interceptor.CommonInterceptor;
import com.ljy.third.interceptor.TemplateInterceptor;
import com.ljy.third.interceptor.TemplateViewInterceptor;

/*스프링 컨테이너는 @Configuration이 붙어있는 클래스를 자동으로 빈으로 등록해두고, 해당 클래스를 파싱해서 @Bean이 있는 메소드를 찾아서 빈을 생성해준다.
하지만 어떤 임의의 클래스를 만들어서 @Bean 어노테이션을 붙인다고 되는 것이 아니고,
@Bean을 사용하는 클래스에는 반드시 @Configuration 어노테이션을 활용하여 해당 클래스에서 Bean을 등록하고자 함을 명시해주어야 한다.
스프링 빈으로 등록된 다른 클래스 안에서 @Bean으로 직접 빈을 등록해주는 것도 동작은 한다. 
하지만 @Configuration 안에서 @Bean을 사용해야 싱글톤을 보장받을 수 있으므로 @Bean 어노테이션은 반드시 @Configuration과 함께 사용해주어야 한다.
@Bean은 반드시 @Configuration 안에서 사용되어야 한다. 
*/
@Configuration
public class servletConfig implements WebMvcConfigurer{
	
		//실질적인 인터셉터 등록
		@Autowired
		private CommonInterceptor mcommonInterceptor;
		
		//실질적인 인터셉터 등록
		@Autowired
		private TemplateInterceptor mtemplateInterceptor;
		
		//실질적인 인터셉터 등록
		@Autowired
		private TemplateViewInterceptor mtemplateViewInterceptor;
		
		// Interceptor에서 제외되는 URL 주소
	    private static final String[] EXCLUDE_PATHS = { "/api/error", "/log/**"};

		 @Bean
		 FilterRegistrationBean uriFilterRegistrationBean() {
	        FilterRegistrationBean uriFilterRegistrationBean = new FilterRegistrationBean();
	        uriFilterRegistrationBean.setFilter(new uriFilter());
	        uriFilterRegistrationBean.addUrlPatterns("/template/*");
	        uriFilterRegistrationBean.setOrder(1);

	        return uriFilterRegistrationBean;
		 }
		 @Bean
		 FilterRegistrationBean searchFilterRegistrationBean() {
	        FilterRegistrationBean searchFilterRegistrationBean = new FilterRegistrationBean();
	        searchFilterRegistrationBean.setFilter( new searchFilter());
	        searchFilterRegistrationBean.addUrlPatterns("/template/*");
	        searchFilterRegistrationBean.setOrder(1);

	        return searchFilterRegistrationBean;
		 }
    		
	    
	    //인터셉터 등록 빈
	    @Override
	    public void addInterceptors(InterceptorRegistry registry){
	    	
	    	registry.addInterceptor(mcommonInterceptor)
		    		//intercepter 주입 구간
		            //.addPathPatterns("/template/templateInfo*")
		    		
		            // Interceptor에서 제외되는 URL 주소 기입
	    			.addPathPatterns("/**.go")
	    			.addPathPatterns("/**/**.go")
	    			.addPathPatterns("/**/**/**.go")
	    			.addPathPatterns("/**/**/**/**.go")
		            .excludePathPatterns(EXCLUDE_PATHS);
	        registry.addInterceptor(mtemplateInterceptor)
	        		//intercepter 주입 구간
	                //.addPathPatterns("/template/templateInfo*")
	        		.addPathPatterns("/template/**")
	                // Interceptor에서 제외되는 URL 주소 기입
	                .excludePathPatterns(EXCLUDE_PATHS);

	        registry.addInterceptor(mtemplateViewInterceptor)
	        		//intercepter 주입 구간
	                //.addPathPatterns("/template/templateInfo*")
	        		.addPathPatterns("/templateView/**")
	                // Interceptor에서 제외되는 URL 주소 기입
	                .excludePathPatterns(EXCLUDE_PATHS);
	    }
	    
	    @Bean
		MappingJackson2JsonView jsonView() {
			return new MappingJackson2JsonView();
		}
	  
}