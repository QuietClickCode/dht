
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.FamerGoodsVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.FamerGoods;
import java.util.Map;
/**
 * 描述：农户商品关联表表Service
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 16:35:32
 */
public interface FamerGoodsService {

	/**
	 * 添加农户商品关联表表
	 * @param famerGoods
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public boolean saveFamerGoods(FamerGoods famerGoods);
	/**
	 * 编辑农户商品关联表表
	 * @param famerGoods
	 * @return
	 * @author yiliangcheng
	 * @date
	 */
	public boolean updateFamerGoods(FamerGoods famerGoods);
	/**
	 * 根据id查询农户商品关联表表
	 * @param fgId
	 * @return famerGoods
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public FamerGoods queryFamerGoodsByFgId(Long fgId);
	/**
	 * 查询农户商品关联表表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public Pagination<FamerGoods> queryFamerGoodsList(Map<String, Object> params, int pageNo, int pageSize);
	public Pagination<FamerGoodsVo> queryHaveGoodsListByFid(Map<String, Object> params, int pageNo, int pageSize);
	public Pagination<FamerGoodsVo> queryNotHaveGoodsListByFid(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据fgId删除农户商品关联表表
	 * @param fgId
	 * @return
	 * @author yiliangcheng
	 * @date 2018-02-07 16:35:32
	 */
	public boolean deleteFamerGoodsByFgId(Long fgId);

}


