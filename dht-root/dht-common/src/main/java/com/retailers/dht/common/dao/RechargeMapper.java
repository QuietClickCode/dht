package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：充值管理DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 23:56:18
 */
public interface RechargeMapper {

	/**
	 * 添加充值管理
	 * @param recharge
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public int saveRecharge(Recharge recharge);
	/**
	 * 编辑充值管理
	 * @param recharge
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public int updateRecharge(Recharge recharge);
	/**
	 * 根据Rid删除充值管理
	 * @param rid
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public int deleteRechargeByRid(Long rid);
	/**
	 * 根据Rid查询充值管理
	 * @param rid
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public Recharge queryRechargeByRid(Long rid);
	/**
	 * 查询充值管理列表
	 * @param pagination 分页对象
	 * @return  充值管理列表
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public List<Recharge> queryRechargeList(Pagination<Recharge> pagination);

}
