package com.my.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/upload")
public class UploadController {

	/**
	 * 文件上载
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/uploadFile.do")
	public String uploadFile(HttpServletRequest req) {
		OutputStream out = null;
		try {
			// 转换为复杂类型的request
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			// 接收文件
			MultipartFile mf = mr.getFile("fileName");
			// 获得文件的字符数组
			byte[] bs = mf.getBytes();
			// 给文件命名
			String fileName = new SimpleDateFormat("YYYYMMddHHmmssSSS")
					.format(new Date());
			Random rd = new Random();
			for (int i = 0; i < 3; i++) {
				fileName = fileName + rd.nextInt(10);
			}
			// 获得原始文件名
			String originalFileName = mf.getOriginalFilename();
			// 获取后缀名,包含"."，.jpg
			String suffix = originalFileName.substring(originalFileName
					.lastIndexOf("."));
			// 获得部署服务器的绝对路径
			String realPath = req.getSession().getServletContext()
					.getRealPath("/");
			// 创建输出流
			out = new FileOutputStream(new File(realPath + "\\upload\\" + fileName
					+ suffix));
			// 写入文件内容
			out.write(bs);
			// 刷新缓存
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "success";
	}

	/**
	 * 跳转到上传页面
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/toUpload.do")
	public String toUpload() {
		return "upload";
	}
}
