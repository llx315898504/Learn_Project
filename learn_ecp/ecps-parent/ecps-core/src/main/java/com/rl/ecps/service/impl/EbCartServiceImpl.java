package com.rl.ecps.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.model.EbCart;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.service.EbCartService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.util.ECPSUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
@Service
public class EbCartServiceImpl implements EbCartService{
	
	@Autowired
	private EbSkuService ebSkuService;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void addCart(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer quantity) {
		List<EbCart> cartList =new ArrayList<EbCart>();
		//把json数组转换成Java对象
		JsonConfig jc=new JsonConfig();
		jc.setRootClass(EbCart.class);
		jc.setExcludes(new String[]{"ebSku"});
		//获取当前项目的所有cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			//遍历cookie
			for (Cookie cookie : cookies) {
				//获取cookie的key
				String cookieName = cookie.getName();
				if(StringUtils.equals(cookieName, ECPSUtils.readProp("cookie_cart_key"))){
					//获得购物车cookie的值
					String cookieVal = cookie.getValue();
					//解码
					cookieVal = URLDecoder.decode(cookieVal);
					//把字符串转换成JSon数组
					JSONArray ja=JSONArray.fromObject(cookieVal);
					cartList=(List<EbCart>) JSONSerializer.toJava(ja, jc);
					boolean isExist=false;
					for (EbCart ebCart : cartList) {
						//在购物车中已经存在，商品数量累加
						if(ebCart.getSkuId().longValue()==skuId.longValue()){
							ebCart.setQuantity(ebCart.getQuantity()+quantity);
							isExist=true;
							break;
						}
					}
					//在购物车中不存在，商品新增
					if(!isExist){
						EbCart ebCart=new EbCart();
						ebCart.setQuantity(quantity);
						ebCart.setSkuId(skuId);
						cartList.add(ebCart);
					}
				}else{
					EbCart ebCart=new EbCart();
					ebCart.setQuantity(quantity);
					ebCart.setSkuId(skuId);
					cartList.add(ebCart);
				}
			}
		}
		JSONArray ja=JSONArray.fromObject(cartList, jc);
		String result = ja.toString();
		result=URLEncoder.encode(result);
		Cookie cookie=new Cookie(ECPSUtils.readProp("cookie_cart_key"), result);
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EbCart> selectCartList(HttpServletRequest request, HttpServletResponse response) {
		List<EbCart> cartList = new ArrayList<EbCart>();
		// 把json数组转换成Java对象
		JsonConfig jc = new JsonConfig();
		jc.setRootClass(EbCart.class);
		jc.setExcludes(new String[] { "ebSku" });
		// 获取当前项目的所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历cookie
			for (Cookie cookie : cookies) {
				// 获取cookie的key
				String cookieName = cookie.getName();
				if (StringUtils.equals(cookieName, ECPSUtils.readProp("cookie_cart_key"))) {
					// 获得购物车cookie的值
					String cookieVal = cookie.getValue();
					// 解码
					cookieVal = URLDecoder.decode(cookieVal);
					// 把字符串转换成JSon数组
					JSONArray ja = JSONArray.fromObject(cookieVal);
					cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
					for (EbCart eCart : cartList) {
						EbSku sku = ebSkuService.selectSkuDetailByIdWithRedis(eCart.getSkuId());
						eCart.setEbSku(sku);
					}
				}
			}
		}
		return cartList;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void removeCart(HttpServletRequest request, HttpServletResponse response, Long skuId) {
		List<EbCart> cartList = new ArrayList<EbCart>();
		// 把json数组转换成Java对象
		JsonConfig jc = new JsonConfig();
		jc.setRootClass(EbCart.class);
		jc.setExcludes(new String[] { "ebSku" });
		// 获取当前项目的所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历cookie
			for (Cookie cookie : cookies) {
				// 获取cookie的key
				String cookieName = cookie.getName();
				if (StringUtils.equals(cookieName, ECPSUtils.readProp("cookie_cart_key"))) {
					// 获得购物车cookie的值
					String cookieVal = cookie.getValue();
					// 解码
					cookieVal = URLDecoder.decode(cookieVal);
					// 把字符串转换成JSon数组
					JSONArray ja = JSONArray.fromObject(cookieVal);
					cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
					for(int i=0;i<cartList.size();i++){
						EbCart eCart=cartList.get(i);
						//移除商品
						if(eCart.getSkuId().longValue()==skuId.longValue()){
							cartList.remove(eCart);
						}
					}
				}
			}
		}
		JSONArray ja=JSONArray.fromObject(cartList, jc);
		String result = ja.toString();
		result=URLEncoder.encode(result);
		Cookie cookie=new Cookie(ECPSUtils.readProp("cookie_cart_key"), result);
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void modifyCart(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer modQuantity) {
		List<EbCart> cartList = new ArrayList<EbCart>();
		// 把json数组转换成Java对象
		JsonConfig jc = new JsonConfig();
		jc.setRootClass(EbCart.class);
		jc.setExcludes(new String[] { "ebSku" });
		// 获取当前项目的所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历cookie
			for (Cookie cookie : cookies) {
				// 获取cookie的key
				String cookieName = cookie.getName();
				if (StringUtils.equals(cookieName, ECPSUtils.readProp("cookie_cart_key"))) {
					// 获得购物车cookie的值
					String cookieVal = cookie.getValue();
					// 解码
					cookieVal = URLDecoder.decode(cookieVal);
					// 把字符串转换成JSon数组
					JSONArray ja = JSONArray.fromObject(cookieVal);
					cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
					for (EbCart eCart : cartList) {
						//更新商品的数量
						if(eCart.getSkuId().longValue()==skuId.longValue()){
							eCart.setQuantity(modQuantity);
						}
					}
				}
			}
		}
		JSONArray ja=JSONArray.fromObject(cartList, jc);
		String result = ja.toString();
		result=URLEncoder.encode(result);
		Cookie cookie=new Cookie(ECPSUtils.readProp("cookie_cart_key"), result);
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void clearCart(HttpServletRequest request, HttpServletResponse response) {
		List<EbCart> cartList = new ArrayList<EbCart>();
		// 把json数组转换成Java对象
		JsonConfig jc = new JsonConfig();
		jc.setRootClass(EbCart.class);
		jc.setExcludes(new String[] { "ebSku" });
		// 获取当前项目的所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历cookie
			for (Cookie cookie : cookies) {
				// 获取cookie的key
				String cookieName = cookie.getName();
				if (StringUtils.equals(cookieName, ECPSUtils.readProp("cookie_cart_key"))) {
					// 获得购物车cookie的值
					String cookieVal = cookie.getValue();
					// 解码
					cookieVal = URLDecoder.decode(cookieVal);
					// 把字符串转换成JSon数组
					JSONArray ja = JSONArray.fromObject(cookieVal);
					cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
					cartList.clear();
				}
			}
		}
		JSONArray ja=JSONArray.fromObject(cartList, jc);
		String result = ja.toString();
		result=URLEncoder.encode(result);
		Cookie cookie=new Cookie(ECPSUtils.readProp("cookie_cart_key"), result);
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public String validCart(HttpServletRequest request, HttpServletResponse response) {
		String result="success";
		List<EbCart> cartList = new ArrayList<EbCart>();
		// 把json数组转换成Java对象
		JsonConfig jc = new JsonConfig();
		jc.setRootClass(EbCart.class);
		jc.setExcludes(new String[] { "ebSku" });
		// 获取当前项目的所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			// 遍历cookie
			for (Cookie cookie : cookies) {
				// 获取cookie的key
				String cookieName = cookie.getName();
				if (StringUtils.equals(cookieName, ECPSUtils.readProp("cookie_cart_key"))) {
					// 获得购物车cookie的值
					String cookieVal = cookie.getValue();
					// 解码
					cookieVal = URLDecoder.decode(cookieVal);
					// 把字符串转换成JSon数组
					JSONArray ja = JSONArray.fromObject(cookieVal);
					cartList = (List<EbCart>) JSONSerializer.toJava(ja, jc);
					for (EbCart eCart : cartList) {
						EbSku sku = ebSkuService.selectSkuDetailByIdWithRedis(eCart.getSkuId());
						if(eCart.getQuantity()>sku.getStockInventory()){
							result=sku.getEbItem().getItemName();
							for (EbSpecValue ebSpecValue : sku.getEbSpecList()) {
								result=result+ebSpecValue.getSpecValue();
							}
							result=result+"库存不足"+eCart.getQuantity()+"个，实际库存是"+sku.getStockInventory()+"个";
							break;
						}
					}
				}
			}
		}
		return result;
	}
}