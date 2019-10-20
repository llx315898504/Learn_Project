package com.my.test;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 索引查询应用
 * 
 * @author 86150
 * 
 */
public class LuceneTest3 {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * TermQuery项精确查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByTermQuery() throws Exception {
		// 创建语汇单元对象
		Term term = new Term("fileName", "亮");
		// 创建语汇分词的查询对象
		TermQuery tq = new TermQuery(term);
		printDoc(getIndexSearcher(), tq);
	}

	/**
	 * 数字范围查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByNumericRangeQuery() throws Exception {
		// 创建数据范围查询对象
		Query tq = NumericRangeQuery.newLongRange("fileSize", 0L, 100L, true, true);
		System.out.println("打印查询条件："+tq);
		printDoc(getIndexSearcher(), tq);
	}
	
	
	/**
	 * 多条件组合查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByBooleanQuery() throws Exception {
		//创建BooleanQuery查询对象，这种查询对象可以控制是“&”，“|”和“！”的逻辑组合条件
		BooleanQuery bq=new BooleanQuery();
		//创建语汇分词的查询对象
		TermQuery tq1 = new TermQuery(new Term("fileName", "spring"));
		TermQuery tq2 = new TermQuery(new Term("fileContent", "spring"));
		// 创建数据范围查询对象
		Query tq = NumericRangeQuery.newLongRange("fileSize", 0L, 100L, true, true);
		//通过BooleanQuery来组合查询调教
		bq.add(tq1, Occur.MUST);
		bq.add(tq2, Occur.SHOULD);
		bq.add(tq, Occur.MUST);
		System.out.println("打印查询条件："+bq);
		printDoc(getIndexSearcher(), bq);
	}

	/**
	 * 查询解析器的查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByQueryParser() throws Exception {
		//创建分词器
		Analyzer al=new IKAnalyzer();
		//创建查询解析对象
		QueryParser qp=new QueryParser("fileName", al);
		//通过qp来解析查询对象
		Query query=qp.parse("今天我们学习全文检索技术Lucene");
		System.out.println("打印查询条件："+query);
		printDoc(getIndexSearcher(), query);
	}
	
	/**
	 * 查询解析器的查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByQueryParser1() throws Exception {
		//创建分词器
		Analyzer al=new IKAnalyzer();
		//创建查询解析对象
		QueryParser qp=new QueryParser("fileName", al);
		//通过qp来解析查询对象
		Query query=qp.parse("fileName:Lucene AND fileContent:Lucene");
		System.out.println("打印查询条件："+query);
		printDoc(getIndexSearcher(), query);
	}
	
	
	
	/**
	 * 组合域查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByMultiFieldQueryParser() throws Exception {
		//创建分词器
		Analyzer al=new IKAnalyzer();
		//定义多个域
		String fields[]={"fileName","fileContent"};
		//创建查询解析对象
		MultiFieldQueryParser mq=new MultiFieldQueryParser(fields, al);
		//通过qp来解析查询对象
		Query query=mq.parse("今天我们学习全文检索技术Lucene");
		// 创建数据范围查询对象
		Query tq = NumericRangeQuery.newLongRange("fileSize", 0L, 1024L, true, true);
		BooleanQuery bq=new BooleanQuery();
		bq.add(query, Occur.MUST);
		bq.add(tq, Occur.MUST);
		System.out.println("打印查询条件："+bq);
		printDoc(getIndexSearcher(), bq);
	}
	
	/**
	 * 组合域查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void queryIndexByMultiFieldQueryParser1() throws Exception {
		//创建分词器
		Analyzer al=new IKAnalyzer();
		//定义多个域
		String fields[]={"fileName","fileContent"};
		//创建查询解析对象
		MultiFieldQueryParser mq=new MultiFieldQueryParser(fields, al);
		//通过qp来解析查询对象
		Query query=mq.parse("今天我们学习全文检索技术Lucene");
		System.out.println("打印查询条件："+query);
		printDoc(getIndexSearcher(), query);
	}
	
	
	/**
	 * 获得索引库的搜索对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public static IndexSearcher getIndexSearcher() throws Exception {
		// 获得索引库的位置
		Path path = Paths.get("E:\\Learn_Project\\learn_lucene\\index_stock");
		// 打开索引库
		FSDirectory dir = FSDirectory.open(path);
		// 创建索引库的读取对象
		DirectoryReader dr = DirectoryReader.open(dir);
		// 创建索引库的搜索对象
		IndexSearcher is = new IndexSearcher(dr);
		return is;
	}

	/**
	 * 打印文档
	 * 
	 * @param is
	 * @param tq
	 * @throws Exception
	 */
	public static void printDoc(IndexSearcher is, Query tq) throws Exception {
		// 查询
		TopDocs result = is.search(tq, 10);
		// 总记录数
		int totalHits = result.totalHits;
		System.out.println("查询结果的总记录数是：" + totalHits);
		// 遍历查询到的文档
		for (ScoreDoc scoreDoc : result.scoreDocs) {
			// 获得文档的ID
			int id = scoreDoc.doc;
			// 根据ID获得文档对象
			Document doc = is.doc(id);
			// 获得文档属性
			String fileName = doc.get("fileName");
			String fileContent = doc.get("fileContent");
			String fileSize = doc.get("fileSize");
			String filePath = doc.get("filePath");
			System.out.println("文件名：" + fileName);
			System.out.println("文件内容：" + fileContent);
			System.out.println("文件大小：" + fileSize);
			System.out.println("文件路径：" + filePath);
			System.out.println("---------------------------");
		}
	}

}
