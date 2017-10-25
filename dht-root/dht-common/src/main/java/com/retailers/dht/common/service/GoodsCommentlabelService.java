
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsCommentlabel;
import java.util.Map;
/**
 * 描述：商品评论标签表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 17:36:13
 */
public interface GoodsCommentlabelService {

	/**
	 * 添加商品评论标签表
	 * @param goodsCommentlabel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public boolean saveGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel);
	/**
	 * 编辑商品评论标签表
	 * @param goodsCommentlabel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel);
	/**
	 * 根据id查询商品评论标签表
	 * @param gclId
	 * @return goodsCommentlabel
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public GoodsCommentlabel queryGoodsCommentlabelByGclId(Long gclId);
	/**
	 * 查询商品评论标签表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public Pagination<GoodsCommentlabel> queryGoodsCommentlabelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gclId删除商品评论标签表
	 * @param gclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:36:13
	 */
	public boolean deleteGoodsCommentlabelByGclId(Long gclId);

}


