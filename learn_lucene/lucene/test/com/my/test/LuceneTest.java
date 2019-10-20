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
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 生成索引
 * 
 * @author 86150
 * 
 */
public class LuceneTest {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	/**
	 * 导入索引
	 * 
	 * @throws Exception
	 */
	public void importIndex() throws Exception {
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
		// 采集原始文档
		File sourceFile = new File(
				"E:\\Learn_Project\\learn_lucene\\searchsource");
		// 获得原始文件的文件夹下的所有文件
		File files[] = sourceFile.listFiles();
		// 遍历每一个文件
		for (File file : files) {
			// 获取文件的属性
			String fileName = file.getName();
			String fileContent = FileUtils.readFileToString(file);
			Long fileSize = FileUtils.sizeOf(file);
			String filePath = file.getPath();
			// 创建域对象
			Field fieldName = new TextField("fileName", fileName, Store.YES);
			Field fieldContent = new TextField("fileContent", fileContent,Store.NO);
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
		}
		// 提交
		iw.commit();
		iw.close();
	}

}
