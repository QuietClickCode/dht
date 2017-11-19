package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsLabel;
import com.retailers.dht.common.vo.GoodsLabelVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：商品标签表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 13:44:22
 */
public interface GoodsLabelMapper {

	/**
	 * 添加商品标签表
	 * @param goodsLabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:44:22
	 */
	public int saveGoodsLabel(GoodsLabel goodsLabel);
	/**
	 * 编辑商品标签表
	 * @param goodsLabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:44:22
	 */
	public int updateGoodsLabel(GoodsLabel goodsLabel);
	/**
	 * 根据GlId删除商品标签表
	 * @param glId
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:44:22
	 */
	public int deleteGoodsLabelByGlId(Long glId);
	/**
	 * 根据GlId查询商品标签表
	 * @param glId
	 * @return
	 * @author fanghui
	 * @date 2017-10-09 13:44:22
	 */
	public GoodsLabel queryGoodsLabelByGlId(Long glId);
	/**
	 * 查询商品标签表列表
	 * @param pagination 分页对象
	 * @return  商品标签表列表
	 * @author fanghui
	 * @date 2017-10-09 13:44:22
	 */
	public List<GoodsLabel> queryGoodsLabelList(Pagination<GoodsLabel> pagination);

	public List<GoodsLabelVo> queryGoodslabelAdvertisingList(@Param("glName") String glName);

	public List<GoodsLabelVo> queryGoodsLabelAdvertisingVo();

	public List<GoodsVo> queryGoodsListsByGoodsLabelIsNotGoodsLabel(Long glId);

	public List<GoodsVo> queryGoodsListsByGoodsLabelIsGoodsLabel(Long glId);

}
