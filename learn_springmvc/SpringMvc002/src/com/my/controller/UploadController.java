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
	 * �ļ�����
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/uploadFile.do")
	public String uploadFile(HttpServletRequest req) {
		OutputStream out = null;
		try {
			// ת��Ϊ�������͵�request
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			// �����ļ�
			MultipartFile mf = mr.getFile("fileName");
			// ����ļ����ַ�����
			byte[] bs = mf.getBytes();
			// ���ļ�����
			String fileName = new SimpleDateFormat("YYYYMMddHHmmssSSS")
					.format(new Date());
			Random rd = new Random();
			for (int i = 0; i < 3; i++) {
				fileName = fileName + rd.nextInt(10);
			}
			// ���ԭʼ�ļ���
			String originalFileName = mf.getOriginalFilename();
			// ��ȡ��׺��,����"."��.jpg
			String suffix = originalFileName.substring(originalFileName
					.lastIndexOf("."));
			// ��ò���������ľ���·��
			String realPath = req.getSession().getServletContext()
					.getRealPath("/");
			// ���������
			out = new FileOutputStream(new File(realPath + "\\upload\\" + fileName
					+ suffix));
			// д���ļ�����
			out.write(bs);
			// ˢ�»���
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
	 * ��ת���ϴ�ҳ��
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/toUpload.do")
	public String toUpload() {
		return "upload";
	}
}
