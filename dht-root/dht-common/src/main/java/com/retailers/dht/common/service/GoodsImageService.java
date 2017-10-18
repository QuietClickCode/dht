
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsImageVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsImage;
import java.util.Map;
/**
 * 描述：商品图片表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 11:48:27
 */
public interface GoodsImageService {

	/**
	 * 添加商品图片表
	 * @param goodsImage
	 * @return
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public boolean saveGoodsImage(GoodsImage goodsImage,Long uploadpersonId);
	/**
	 * 编辑商品图片表
	 * @param goodsImage
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsImage(GoodsImage goodsImage,Long uploadpersonId);
	/**
	 * 根据id查询商品图片表
	 * @param giId
	 * @return goodsImage
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public GoodsImage queryGoodsImageByGiId(Long giId);
	/**
	 * 查询商品图片表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public Pagination<GoodsImageVo> queryGoodsImageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据giId删除商品图片表
	 * @param giId
	 * @return
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public boolean deleteGoodsImageByGiId(Long giId,Long uploadpersonId);

}


