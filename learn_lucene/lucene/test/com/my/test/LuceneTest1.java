package com.my.test;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 分词器的应用
 * 
 * @author 86150
 * 
 */
public class LuceneTest1 {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 标准分词器的应用
	 * 
	 * @throws Exception
	 */
	@Test
	public void createStandardAnalyzer() throws Exception {
		// 创建分词器（标准分词器）
		Analyzer al = new StandardAnalyzer();
		// 分词
		TokenStream stream = al.tokenStream("content",
				"Serving Web Content with Spring MVC");
		// 分词对象的重置
		stream.reset();
		// 获得每一个语汇的偏移量的属性对象
		OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);
		// 获得分词的语汇属性
		CharTermAttribute ca = stream.addAttribute(CharTermAttribute.class);
		// 遍历分词的语汇流
		while (stream.incrementToken()) {
			System.out.println("--------------------------");
			System.out.println("开始索引：" + oa.startOffset() + ",结束索引："
					+ oa.endOffset());
			System.out.println("分词的语汇单元：" + ca);

		}
		// 关闭分词器
		al.close();
	}
	
	/**
	 * IK中文分词器的应用
	 * 
	 * @throws Exception
	 */
	@Test
	public void createIKAnalyzer() throws Exception {
		// 创建分词器（标准分词器）
		Analyzer al = new IKAnalyzer();
		// 分词
		TokenStream stream = al.tokenStream("content",
				"根据IT市场要求，针对世界第一编程语言java开设java基础班课程和javaee+Hadoop就业班课程");
		// 分词对象的重置
		stream.reset();
		// 获得每一个语汇的偏移量的属性对象
		OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);
		// 获得分词的语汇属性
		CharTermAttribute ca = stream.addAttribute(CharTermAttribute.class);
		// 遍历分词的语汇流
		while (stream.incrementToken()) {
			System.out.println("--------------------------");
			System.out.println("开始索引：" + oa.startOffset() + ",结束索引："
					+ oa.endOffset());
			System.out.println("分词的语汇单元：" + ca);

		}
		// 关闭分词器
		al.close();
	}

}
