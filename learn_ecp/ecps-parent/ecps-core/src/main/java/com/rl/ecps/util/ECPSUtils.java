package com.rl.ecps.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class ECPSUtils {
	
	//创建solr客户端对象
    public static HttpSolrClient client;
	
    /**
     * 获取资源文件中属性的值
     * 
     * @param key
     * @return
     */
	public static String readProp(String key){
		String value = null;
		InputStream in = ECPSUtils.class.getClassLoader().getResourceAsStream("ecps_prop.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * 获取solr客户端对象
	 * 
	 * @return
	 */
	public  static  HttpSolrClient getHttpSolrClient(){
		if(client!=null){
			return client;
		}else{
			//solr服务器地址
			String SOLR_URL = ECPSUtils.readProp("solr_path").concat("/");
			//solr实例名
			String CORE_NAME =ECPSUtils.readProp("core_name");
			 client= new HttpSolrClient.Builder(SOLR_URL.concat(CORE_NAME))
					 .withConnectionTimeout(10000).withSocketTimeout(60000).build();
		}
		return client;
	}
	
	/**
	 * ajax异步调用写数据
	 * 
	 * @param response
	 * @param result
	 */
	public static void printAjax(HttpServletResponse response,String result){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}