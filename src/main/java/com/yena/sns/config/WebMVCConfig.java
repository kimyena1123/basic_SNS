package com.yena.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yena.sns.common.FileManagerService;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{

	//내 컴퓨터에 있는 파일을 특정한 경로로
	//메소드 하나를 Override
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//images로 시작하고 그 뒤에 따라오는 모든 것
		registry.addResourceHandler("/images/sns/**")
		.addResourceLocations("file:///" + FileManagerService.PILE_UPLOAD_PATH + "/");
	}
}
