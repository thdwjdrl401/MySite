package com.example.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/profileImages/**") // --1
				.addResourceLocations("file:///C:/Temp/profileImages/"); // --2
		registry.addResourceHandler("/boardImages/**") // --1
				.addResourceLocations("file:///C:/Temp/boardImages/"); // --2
		registry.addResourceHandler("/homeImage/**") // --1
		.addResourceLocations("file:///C:/Temp/homeImage/"); // --2
		registry.addResourceHandler("/mapImage/**") // --1
		.addResourceLocations("file:///C:/Temp/mapImage/"); // --2
//        registry.addResourceHandler("/profileImages/**") // --1
//        .addResourceLocations("file:///home/lazy/profileImages/"); //--2
//		registry.addResourceHandler("/boardImages/**") // --1
//		.addResourceLocations("file:///home/lazy/boardImages/"); // --2
//		registry.addResourceHandler("/homeImage/**") // --1
//		.addResourceLocations("file:///home/lazy/homeImage/"); // --2
//		registry.addResourceHandler("/mapImage/**") // --1
//		.addResourceLocations("file:///home/lazy/mapImage/"); // --2
	}

}