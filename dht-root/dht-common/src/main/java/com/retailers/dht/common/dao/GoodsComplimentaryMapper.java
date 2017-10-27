package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsComplimentary;
import com.retailers.dht.common.vo.GoodsComplimentaryVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：赠品表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 14:58:13
 */
public interface GoodsComplimentaryMapper {

	/**
	 * 添加赠品表
	 * @param goodsComplimentary
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public int saveGoodsComplimentary(GoodsComplimentary goodsComplimentary);
	/**
	 * 编辑赠品表
	 * @param goodsComplimentary
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public int updateGoodsComplimentary(GoodsComplimentary goodsComplimentary);
	/**
	 * 根据GcId删除赠品表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public int deleteGoodsComplimentaryByGcId(Long gcId);
	/**
	 * 根据GcId查询赠品表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public GoodsComplimentary queryGoodsComplimentaryByGcId(Long gcId);
	/**
	 * 查询赠品表列表
	 * @param pagination 分页对象
	 * @return  赠品表列表
	 * @author fanghui
	 * @date 2017-10-27 14:58:13
	 */
	public List<GoodsComplimentaryVo> queryGoodsComplimentaryList(Pagination<GoodsComplimentaryVo> pagination);

}
