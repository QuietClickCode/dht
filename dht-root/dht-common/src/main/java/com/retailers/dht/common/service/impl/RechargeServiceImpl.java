
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.dht.common.dao.RechargeMapper;
import com.retailers.dht.common.service.RechargeService;
import com.retailers.dht.common.vo.RechargeVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：充值管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 23:56:18
 */
@Service("rechargeService")
public class RechargeServiceImpl implements RechargeService {
	@Autowired
	private RechargeMapper rechargeMapper;
	public boolean saveRecharge(Recharge recharge) {
		int status = rechargeMapper.saveRecharge(recharge);
		return status == 1 ? true : false;
	}
	public boolean updateRecharge(Recharge recharge) {
		int status = rechargeMapper.updateRecharge(recharge);
		return status == 1 ? true : false;
	}
	public Recharge queryRechargeByRid(Long rid) {
		return rechargeMapper.queryRechargeByRid(rid);
	}

	public Pagination<RechargeVo> queryRechargeList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<RechargeVo> page = new Pagination<RechargeVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<RechargeVo> list = rechargeMapper.queryRechargeList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteRechargeByRid(Long rid) {
		int status = rechargeMapper.deleteRechargeByRid(rid);
		return status == 1 ? true : false;
	}
}

