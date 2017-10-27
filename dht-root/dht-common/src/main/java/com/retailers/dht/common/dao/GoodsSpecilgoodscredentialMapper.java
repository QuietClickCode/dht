package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsSpecilgoodscredential;
import com.retailers.dht.common.vo.GoodsSpecilgoodscredentialVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：特殊商品证件表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 17:15:12
 */
public interface GoodsSpecilgoodscredentialMapper {

	/**
	 * 添加特殊商品证件表
	 * @param goodsSpecilgoodscredential
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public int saveGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential);
	/**
	 * 编辑特殊商品证件表
	 * @param goodsSpecilgoodscredential
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public int updateGoodsSpecilgoodscredential(GoodsSpecilgoodscredential goodsSpecilgoodscredential);
	/**
	 * 根据GsgId删除特殊商品证件表
	 * @param gsgId
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public int deleteGoodsSpecilgoodscredentialByGsgId(Long gsgId);
	/**
	 * 根据GsgId查询特殊商品证件表
	 * @param gsgId
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public GoodsSpecilgoodscredential queryGoodsSpecilgoodscredentialByGsgId(Long gsgId);
	/**
	 * 查询特殊商品证件表列表
	 * @param pagination 分页对象
	 * @return  特殊商品证件表列表
	 * @author fanghui
	 * @date 2017-10-26 17:15:12
	 */
	public List<GoodsSpecilgoodscredentialVo> queryGoodsSpecilgoodscredentialList(Pagination<GoodsSpecilgoodscredentialVo> pagination);

}
