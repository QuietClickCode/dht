package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.vo.CutPriceVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
import java.util.Map;

/**
 * 描述：砍价表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:14:43
 */
public interface CutPriceMapper {

	/**
	 * 添加砍价表
	 * @param cutPrice
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public int saveCutPrice(CutPrice cutPrice);
	/**
	 * 编辑砍价表
	 * @param cutPrice
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public int updateCutPrice(CutPrice cutPrice);
	/**
	 * 根据CpId删除砍价表
	 * @param cpId
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public int deleteCutPriceByCpId(Long cpId);
	/**
	 * 根据CpId查询砍价表
	 * @param cpId
	 * @return
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public CutPrice queryCutPriceByCpId(Long cpId);
	/**
	 * 查询砍价表列表
	 * @param pagination 分页对象
	 * @return  砍价表列表
	 * @author fanghui
	 * @date 2017-12-05 16:14:43
	 */
	public List<CutPrice> queryCutPriceList(Pagination<CutPrice> pagination);

	public List<CutPriceVo> queryCutPriceTree();

	public  List<Goods> queryHasNoSpGoods(Pagination<Goods> pagination);

	public List<CutPriceVo> queryCutPriceListsByGid(Map params);
}
