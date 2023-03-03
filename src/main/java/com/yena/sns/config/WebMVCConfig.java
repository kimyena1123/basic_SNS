package com.yena.sns.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yena.sns.common.FileManagerService;
import com.yena.sns.common.PermissionInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{

	@Autowired
	private PermissionInterceptor interceptor;
	
	//내 컴퓨터에 있는 파일을 특정한 경로로
	//메소드 하나를 Override
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//images로 시작하고 그 뒤에 따라오는 모든 것
		registry.addResourceHandler("/images/sns/**")
		.addResourceLocations("file:///" + FileManagerService.PILE_UPLOAD_PATH + "/");
								//리눅스 기반이라서 슬래스/ 하나 더 붙어서 3개.
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")  // 인터셉터를 거쳐서 처리할 페이지 의 url 규칙
		.excludePathPatterns("/user/signout", "/static/**", "/images/**");
	}
}
