package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.model.EbBrand;

/**
 * 商品品牌接口
 * 
 * @author 86150
 *
 */
public interface EbBrandService {
	/**
	 * 新增品牌
	 * 
	 * @param brand
	 */
	public void saveBrand(EbBrand brand);

	/**
	 * 查询所有的品牌信息
	 * 
	 * @return
	 */
	public List<EbBrand> selectBrandAll();

	/**
	 * 根据品牌名称查询品牌记录
	 * 
	 * @param brandName
	 * @return
	 */
	public List<EbBrand> selectBrandByName(String brandName);
	
	
	/**
	 * 根据品牌ID删除品牌记录
	 * 
	 * @param brandId
	 */
	void deleteBrandById(Long brandId);
	
	/**
	 * 更新品牌名称
	 * 
	 * @param ebBrand
	 */
	void updateBrand(EbBrand ebBrand);
	
	
	/**
	 * 根据品牌ID查询品牌记录
	 * 
	 * @param brandId
	 */
	EbBrand selectBrandById(Long brandId);

}
