package com.air.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class RequestToBean {
	/**
	 * 将request对象转换成T对象
	 * @param request 
	 * @param clazz
	 * @return
	 */
	public static <T> T request2Bean(HttpServletRequest request,Class<T> clazz){
		try{
			T bean = clazz.newInstance();
			Enumeration<String> e = request.getParameterNames(); 
			while(e.hasMoreElements()){
				String name = (String) e.nextElement();
				
				String value = getUFTString(request.getParameter(name));
				System.out.println(name + ": " + value);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getUFTString(String s) throws UnsupportedEncodingException {
		return new String(s.getBytes("ISO-8859-1"),"UTF-8");
	}
}
