package com.rl.ecps.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbArea;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.TsPtlUser;
import com.rl.ecps.service.EbAreaService;
import com.rl.ecps.service.EbShipAddrService;
import com.rl.ecps.service.TsPtlUserService;
import com.rl.ecps.util.ECPSUtils;
import com.rl.ecps.util.MD5Util;

import net.sf.json.JSONObject;


/**
 * 用户管理
 * 
 * @author 86150
 *
 */
@Controller
@RequestMapping("/user")
public class EbUserController {
	
	@Autowired
	private TsPtlUserService userService;
	
	@Autowired
	private EbShipAddrService shipAddrService;
	
	@Autowired
	private EbAreaService areaService;
	

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "passport/login";
	}
	
	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getImage.do")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("#######################生成数字和字母的验证码#######################");
		BufferedImage img = new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);
		//得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 68, 22);
		// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
			// 输出的 字体和大小
			g.drawString("" + ch[index], (i * 15) + 3, 18);
			// 写什么数字，在图片 的什么位置画
			sb.append(ch[index]);
		}
		// 把验证码的值存储在session中目的是校验
		request.getSession().setAttribute("piccode", sb.toString());
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param captcha
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(String username, String password, String captcha, HttpSession session, Model model){
		//获得正确的验证码
		String rCap = (String) session.getAttribute("piccode");
		if(!StringUtils.equalsIgnoreCase(captcha, rCap)){
			model.addAttribute("tip", "cap_error");
			return "passport/login";
		}
		//把密码加密
		password = MD5Util.GetMD5Code(password);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", username);
		map.put("passWord", password);
		TsPtlUser user = userService.selectUserByNameAndPassword(map);
		if(user == null){
			model.addAttribute("tip", "userpass_error");
			return "passport/login";
		}
		session.setAttribute("user", user);
		//不同Controller之间的从定向在：后面加/
		return "redirect:/item/toItemIndex.do";
	}
	
	/**
	 * Ajax方式的登录
	 * 
	 * @param username
	 * @param password
	 * @param captcha
	 * @param session
	 * @param model
	 */
	@RequestMapping("/ajaxLogin.do")
	public void ajaxLogin(String username, String password, String captcha, HttpSession session, PrintWriter out){
		//获得正确的验证码
		String rCap = (String) session.getAttribute("piccode");
		if(!StringUtils.equalsIgnoreCase(captcha, rCap)){
			out.write("cap_error");
			return;
		}
		//把密码加密
		password = MD5Util.GetMD5Code(password);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", username);
		map.put("passWord", password);
		TsPtlUser user = userService.selectUserByNameAndPassword(map);
		if(user == null){
			out.write("userpass_error");
			return ;
		}
		session.setAttribute("user", user);
		out.write("success");
	}
	
	/**
	 * 从session中获取用户信息
	 * 
	 * @param session
	 * @param out
	 */
	@RequestMapping("/getUser.do")
	public void getUser(HttpSession session,HttpServletResponse response){
		//从session中取出user
		TsPtlUser user = (TsPtlUser) session.getAttribute("user");
		//创建JSon对象
		JSONObject jo=new JSONObject();
		//把user放入到JSon对象中
		jo.accumulate("user", user);
		//把user变成JSon格式的字符串
		String result=jo.toString();
		//写入数据
		ECPSUtils.printAjax(response, result);
	}
	
	/**
	 * 根据用户Id查询收货地址
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login/toAddress.do")
	public String toAddress(HttpSession session,Model model){
		//从session中取出user
		TsPtlUser user = (TsPtlUser) session.getAttribute("user");
		//查询用户的收货地址列表
		List<EbShipAddr> addrList = shipAddrService.selectAddressByUserId(user.getPtlUserId());
		//查询区域中的省信息
		List<EbArea> areaList= areaService.selectAreaByPid(0l);
		model.addAttribute("areaList",areaList);
		model.addAttribute("addrList", addrList);
		return "person/deliverAddress";
	}
	
	
	/**
	 * 根据收货地址Id查询收货地址信息
	 * 
	 * @param shipAddrId
	 * @param response
	 */
	@RequestMapping("/login/selectAddrById.do")
	public void selectAddrById(Long shipAddrId,HttpServletResponse response){
		//根据收货地址Id查询收货地址信息
	    EbShipAddr ebShipAddr = shipAddrService.selectAddrById(shipAddrId);
		JSONObject jo=new JSONObject();
		jo.accumulate("addr", ebShipAddr);
		String result=jo.toString();
		ECPSUtils.printAjax(response, result);
	}
	
	
	/**
	 * 根据区域父节点Id查询子节点信息
	 * 
	 * @param areaId
	 * @param response
	 */
	@RequestMapping("/login/getChildArea.do")
	public void getChildArea(Long areaId,HttpServletResponse response){
		//根据区域父节点Id查询子节点信息
		List<EbArea> areaList= areaService.selectAreaByPid(areaId);
		JSONObject jo=new JSONObject();
		jo.accumulate("areaList", areaList);
		String result=jo.toString();
		ECPSUtils.printAjax(response, result);
	}
	
	
	/**
	 * 新增或者更新用户收货地址
	 *
	 * @param ebShipAddr
	 * @param session
	 */
	@RequestMapping("/login/saveOrUpdateAddr.do")
	public String saveOrUpdateAddr(EbShipAddr ebShipAddr, HttpSession session) {
		// 从session中取出user
		TsPtlUser user = (TsPtlUser) session.getAttribute("user");
		ebShipAddr.setPtlUserId(user.getPtlUserId());
		if(ebShipAddr.getDefaultAddr()==null){
			ebShipAddr.setDefaultAddr((short) 0);
		}
		shipAddrService.saveOrUpdateAddr(ebShipAddr);
		
		return "redirect:toAddress.do";
	}
	
	/**
	 * 新增或者更新用户收货地址
	 *
	 * @param ebShipAddr
	 * @param session
	 */
	@RequestMapping("/login/deleteAddrById.do")
	public String deleteAddrById(Long shipAddrId, HttpSession session) {
		// 从session中取出user
		TsPtlUser user = (TsPtlUser) session.getAttribute("user");
		shipAddrService.deleteAddrById(shipAddrId, user.getPtlUserId());
		return "redirect:toAddress.do";
	}
	
	
	
	/**
	 * 跳转到我的商城(个人中心)
	 * 
	 * @return
	 */
	@RequestMapping("/login/toPensonIndex.do")
	public String toPensonIndex(){
		return "person/index";
	}
}