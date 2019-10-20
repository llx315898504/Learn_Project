package com.my.test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 管理索引
 * 
 * @author 86150
 * 
 */
public class LuceneTest2 {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 添加索引
	 * 
	 * @throws Exception
	 */
	@Test
	public void addIndex() throws Exception {
		IndexWriter iw = getIndexWriter();
		// 采集原始文档
		File file = new File(
				"E:\\Learn_Project\\learn_lucene\\searchsource\\hibernate.txt");
		// 获取文件的属性
		String fileName = file.getName();
		String fileContent = FileUtils.readFileToString(file);
		Long fileSize = FileUtils.sizeOf(file);
		String filePath = file.getPath();
		// 创建域对象
		Field fieldName = new TextField("fileName", fileName, Store.YES);
		Field fieldContent = new TextField("fileContent", fileContent, Store.NO);
		Field fieldSize = new LongField("fileSize", fileSize, Store.YES);
		Field fieldPath = new TextField("filePath", filePath, Store.YES);
		// 创建文档对象
		Document document = new Document();
		// 把域对象加入到文档中
		document.add(fieldName);
		document.add(fieldContent);
		document.add(fieldSize);
		document.add(fieldPath);
		// 把文档写入到索引库
		iw.addDocument(document);
		// 提交
		iw.commit();
		iw.close();
	}

	/**
	 * 获得IndexWriter对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public IndexWriter getIndexWriter() throws Exception {
		// 获得索引库的位置
		Path path = Paths.get("E:\\Learn_Project\\learn_lucene\\index_stock");
		// 打开索引库
		FSDirectory dir = FSDirectory.open(path);
		// 创建分词器
		Analyzer al = new IKAnalyzer();
		// 创建索引的写入配置对象
		IndexWriterConfig iwc = new IndexWriterConfig(al);
		// 创建索引的writer
		IndexWriter iw = new IndexWriter(dir, iwc);
		return iw;
	}
	
	
	/**
	 * 删除所有索引
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteAllIndex() throws Exception {
		IndexWriter iw = getIndexWriter();
		iw.deleteAll();
		// 提交
		iw.commit();
		iw.close();
	}
	
	/**
	 * 根据查询对象删除索引
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteIndexByQuery() throws Exception {
		IndexWriter iw = getIndexWriter();
		//创建语汇单元项
		Term term=new Term("fileName", "spring");
		//根据语汇单元创建查询对象
		TermQuery query=new TermQuery(term);
		//根据查询对象删除索引
		iw.deleteDocuments(query);
		//提交
		iw.commit();
		iw.close();
	}
}
