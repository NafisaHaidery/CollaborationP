package com.niit.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getRootConfigClasses() {//To load beans for applicationContext.xml
	// TODO Auto-generated method stub
	return new Class[]{DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {//To load beans for DS
	// TODO Auto-generated method stub
	return new Class[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {//Any request forwarded to DS
	// TODO Auto-generated method stub
	return new String[]{"/"};

	}
}
