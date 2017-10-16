package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsImage;
import com.retailers.dht.common.vo.GoodsImageVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品图片表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 11:48:27
 */
public interface GoodsImageMapper {

	/**
	 * 添加商品图片表
	 * @param goodsImage
	 * @return
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public int saveGoodsImage(GoodsImage goodsImage);
	/**
	 * 编辑商品图片表
	 * @param goodsImage
	 * @return
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public int updateGoodsImage(GoodsImage goodsImage);
	/**
	 * 根据GiId删除商品图片表
	 * @param giId
	 * @return
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public int deleteGoodsImageByGiId(Long giId);
	/**
	 * 根据GiId查询商品图片表
	 * @param giId
	 * @return
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public GoodsImage queryGoodsImageByGiId(Long giId);
	/**
	 * 查询商品图片表列表
	 * @param pagination 分页对象
	 * @return  商品图片表列表
	 * @author fanghui
	 * @date 2017-10-15 11:48:27
	 */
	public List<GoodsImageVo> queryGoodsImageList(Pagination<GoodsImageVo> pagination);

}
