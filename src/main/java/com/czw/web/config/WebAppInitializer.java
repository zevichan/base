package com.czw.web.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.czw.web.servlet.MyServlet;

/**
 * @author Zevi Chan
 * @Date 2016年6月28日
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	
	/**
	 * 注册Servlet,Filter,Listener
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
		myServlet.addMapping("/custom/**");
	}
	
	/**
	 * 便捷的Filter注册方式
	 */
	@Override
	protected Filter[] getServletFilters() {
		return super.getServletFilters();
	}
	
	/**
	 * 文件上传的配置
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads",2097152,4194304,0));
	}
	
	
}
