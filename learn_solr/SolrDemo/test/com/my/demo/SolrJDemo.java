package com.my.demo;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * SolrJApi的使用
 * 
 * @author 86150
 *
 */
public class SolrJDemo {
	// solr服务器地址
	private static final String SOLR_URL = "http://localhost:8888/solr/";
	// solr实例名
	private static final String CORE_NAME = "new_core";
	
	/**
	 * 向Solr索引库新增文档
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public void addDoc() throws Exception, Exception {
		// 创建solr客户端对象
		HttpSolrClient client = new HttpSolrClient.Builder(
				SOLR_URL.concat(CORE_NAME)).withConnectionTimeout(10000)
				.withSocketTimeout(60000).build();
		// 创建一个solr文档对象
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "10001");
		doc.addField("product_name", "索尼相机");
		doc.addField("product_catalog_name", "电子产品");
		doc.addField("product_description", "是日本索尼公司的产品，中国人应该抵制日货。");
		doc.addField("product_picture", "11111.jpg");
		doc.addField("product_price", 10000.00);
		// 把文档加入solr服务器
		client.add(doc);
		// 提交
		client.commit();
		// 释放资源
		client.close();
	}
	
	
	/**
	 * 根据文档ID删除文档
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public void deleteDocById() throws Exception, Exception {
		// 创建solr客户端对象
		HttpSolrClient client = new HttpSolrClient.Builder(
				SOLR_URL.concat(CORE_NAME)).withConnectionTimeout(10000)
				.withSocketTimeout(60000).build();
		String id="2";
		client.deleteById(id);
		//提交
		client.commit();
		// 释放资源
		client.close();
	}
	
	
	/**
	 * 根据查询结果删除文档
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public void deleteDocByQueryResult() throws Exception, Exception {
		// 创建solr客户端对象
		HttpSolrClient client = new HttpSolrClient.Builder(
				SOLR_URL.concat(CORE_NAME)).withConnectionTimeout(10000)
				.withSocketTimeout(60000).build();
		client.deleteByQuery("product_name:青蛙");
		//提交
		client.commit();
		// 释放资源
		client.close();
	}
	
	
	/**
	 * 查询文档
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public void queryDoc() throws Exception, Exception {
		// 创建solr客户端对象
		HttpSolrClient client = new HttpSolrClient.Builder(
				SOLR_URL.concat(CORE_NAME)).withConnectionTimeout(10000)
				.withSocketTimeout(60000).build();
		//创建solr的查询对象
		SolrQuery sq=new SolrQuery();
		//设置查询条件
		sq.add("q", "product_name:浪漫樱花 ");
		//添加排序
		sq.addSort("id", ORDER.asc);
		//分页查询
		//从多少条开始
		sq.setStart(60);
		//设置每页显示的记录数
		sq.setRows(10);
		//查询
		QueryResponse response = client.query(sq);
		//获取查询结果集
		SolrDocumentList results = response.getResults();
		//获取查询结果总记录数
		long numFound = results.getNumFound();
		System.out.println("总记录数："+numFound);
		//遍历查询结果
		for (SolrDocument solrDocument : results) {
			//获取文档属性
			String id = (String) solrDocument.getFieldValue("id");
			String product_name = (String) solrDocument.getFieldValue("product_name");
			String product_price = (String) solrDocument.getFieldValue("product_price");
			String product_catalog_name = (String) solrDocument.getFieldValue("product_catalog_name");
			String product_picture = (String) solrDocument.getFieldValue("product_picture");
			System.out.println("-----------------------------");
			System.out.println("id:"+id);
			System.out.println("商品名称:"+product_name);
			System.out.println("单价:"+product_price);
			System.out.println("商品类别:"+product_catalog_name);
			System.out.println("商品图片:"+product_picture);
		}
		// 释放资源
		client.close();
	}
	
	/**
	 * 查询高亮文档
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
	public void queryHighLightDoc() throws Exception, Exception {
		// 创建solr客户端对象
		HttpSolrClient client = new HttpSolrClient.Builder(
				SOLR_URL.concat(CORE_NAME)).withConnectionTimeout(10000)
				.withSocketTimeout(60000).build();
		//创建solr的查询对象
		SolrQuery sq=new SolrQuery();
		//设置查询条件
		sq.add("q", "product_name:时尚 AND product_catalog_name:时尚 ");
		//添加排序
		sq.addSort("id", ORDER.asc);
		//分页查询
		//从多少条开始
		sq.setStart(0);
		//设置每页显示的记录数
		sq.setRows(10);
		//开启高亮
		sq.setHighlight(true);
		//设置高亮的文档域
		sq.addHighlightField("product_name");
		sq.addHighlightField("product_catalog_name");
		//设置高亮的样式
		sq.setHighlightSimplePre("<b>");
		sq.setHighlightSimplePost("</b>");
		//查询
		QueryResponse response = client.query(sq);
		//获取查询结果集
		SolrDocumentList results = response.getResults();
		//获取查询结果总记录数
		long numFound = results.getNumFound();
		System.out.println("总记录数："+numFound);
		//遍历查询结果
		for (SolrDocument solrDocument : results) {
			//获取文档属性
			String id = (String) solrDocument.getFieldValue("id");
			String product_name = (String) solrDocument.getFieldValue("product_name");
			String product_price = (String) solrDocument.getFieldValue("product_price");
			String product_catalog_name = (String) solrDocument.getFieldValue("product_catalog_name");
			String product_picture = (String) solrDocument.getFieldValue("product_picture");
			System.out.println("-----------------------------");
			System.out.println("id:"+id);
			System.out.println("商品名称:"+product_name);
			System.out.println("单价:"+product_price);
			System.out.println("商品类别:"+product_catalog_name);
			System.out.println("商品图片:"+product_picture);
			
			//获取高亮的结构体
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			if(highlighting!=null){
				//根据ID获得每一个域的高亮内容
				Map<String, List<String>> map = highlighting.get(id);
				//根据具体的域来获取高亮的内容
				List<String> list1 = map.get("product_name");
				//打印高亮的内容
				for (String string : list1) {
					System.out.println("高亮的内容product_name："+string);
				}
				List<String> list = map.get("product_catalog_name");
				//打印高亮的内容
				for (String string : list) {
					System.out.println("高亮的内容product_catalog_name："+string);
				}
			}
		}
		// 释放资源
		client.close();
	}
}
