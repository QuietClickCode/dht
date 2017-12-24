package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.AccumulativeAmount;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：平台累计金额(按商品类型分配）DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 00:46:54
 */
public interface AccumulativeAmountMapper {

	/**
	 * 添加平台累计金额(按商品类型分配）
	 * @param accumulativeAmount
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:46:54
	 */
	public int saveAccumulativeAmount(AccumulativeAmount accumulativeAmount);
	/**
	 * 编辑平台累计金额(按商品类型分配）
	 * @param accumulativeAmount
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:46:54
	 */
	public int updateAccumulativeAmount(AccumulativeAmount accumulativeAmount);
	/**
	 * 根据AaId删除平台累计金额(按商品类型分配）
	 * @param aaId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:46:54
	 */
	public int deleteAccumulativeAmountByAaId(Long aaId);
	/**
	 * 根据AaId查询平台累计金额(按商品类型分配）
	 * @param aaId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:46:54
	 */
	public AccumulativeAmount queryAccumulativeAmountByAaId(Long aaId);
	/**
	 * 查询平台累计金额(按商品类型分配）列表
	 * @param pagination 分页对象
	 * @return  平台累计金额(按商品类型分配）列表
	 * @author zhongp
	 * @date 2017-12-25 00:46:54
	 */
	public List<AccumulativeAmount> queryAccumulativeAmountList(Pagination<AccumulativeAmount> pagination);
}
