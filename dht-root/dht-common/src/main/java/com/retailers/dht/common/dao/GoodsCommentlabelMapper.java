package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsCommentlabel;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品评论标签表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 17:36:13
 */
public interface GoodsCommentlabelMapper {

	/**
	 * 添加商品评论标签表
	 * @param goodsCommentlabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public int saveGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel);
	/**
	 * 编辑商品评论标签表
	 * @param goodsCommentlabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public int updateGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel);
	/**
	 * 根据GclId删除商品评论标签表
	 * @param gclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public int deleteGoodsCommentlabelByGclId(Long gclId);
	/**
	 * 根据GclId查询商品评论标签表
	 * @param gclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public GoodsCommentlabel queryGoodsCommentlabelByGclId(Long gclId);
	/**
	 * 查询商品评论标签表列表
	 * @param pagination 分页对象
	 * @return  商品评论标签表列表
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public List<GoodsCommentlabel> queryGoodsCommentlabelList(Pagination<GoodsCommentlabel> pagination);

}
