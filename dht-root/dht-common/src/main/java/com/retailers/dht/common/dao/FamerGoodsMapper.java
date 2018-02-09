package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.FamerGoods;
import com.retailers.dht.common.vo.FamerGoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：农户商品关联表表DAO
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 16:35:32
 */
public interface FamerGoodsMapper {

	/**
	 * 添加农户商品关联表表
	 * @param famerGoods
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public int saveFamerGoods(FamerGoods famerGoods);
	/**
	 * 编辑农户商品关联表表
	 * @param famerGoods
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public int updateFamerGoods(FamerGoods famerGoods);
	/**
	 * 根据FgId删除农户商品关联表表
	 * @param fgId
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public int deleteFamerGoodsByFgId(Long fgId);
	/**
	 * 根据FgId查询农户商品关联表表
	 * @param fgId
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public FamerGoods queryFamerGoodsByFgId(Long fgId);
	/**
	 * 查询农户商品关联表表列表
	 * @param pagination 分页对象
	 * @return  农户商品关联表表列表
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public List<FamerGoods> queryFamerGoodsList(Pagination<FamerGoods> pagination);
	public List<FamerGoodsVo> queryHaveGoodsListByFid(Pagination<FamerGoodsVo> pagination);
	public List<FamerGoodsVo> queryNotHaveGoodsListByFid(Pagination<FamerGoodsVo> pagination);

}
