
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.dht.common.dao.RechargeMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.RechargeService;
import com.retailers.dht.common.vo.RechargeVo;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private AttachmentService attachmentService;

	@Transactional(rollbackFor = Exception.class)
	public boolean saveRecharge(Recharge recharge)throws AppException {
		recharge.setVersion(0);
		recharge.setRsnapshot(UUIDUtils.getUUID());
		int status = rechargeMapper.saveRecharge(recharge);
		if(ObjectUtils.isNotEmpty(recharge.getRlogo())){
            attachmentService.editorAttachment(recharge.getRlogo());
        }
		status = rechargeMapper.saveRechargeSnapshot(recharge);
		if(status==0){
			throw new AppException("添加充值金额异常");
		}
		return status == 1 ? true : false;
	}
	public boolean updateRecharge(Recharge recharge)throws AppException {
		Recharge r = rechargeMapper.queryRechargeByRid(recharge.getRid());
		if(ObjectUtils.isEmpty(r)){
			throw new AppException("编辑充值金额不存在");
		}
		if(recharge.getVersion().intValue()!=r.getVersion().intValue()){
			throw new AppException("充值金额己充更新重试");
		}
		if(recharge.getIsDelete().intValue()== SystemConstant.SYS_IS_DELETE_YES){
			throw new AppException("编辑充值金额不存在");
		}
		recharge.setVersion(recharge.getVersion()+1);
		int status = rechargeMapper.saveRecharge(recharge);
		if(ObjectUtils.isNotEmpty(recharge.getRlogo())){
			attachmentService.editorAttachment(recharge.getRlogo());
		}
		status = rechargeMapper.saveRechargeSnapshot(recharge);
		if(status==0){
			throw new AppException("添加充值金额异常");
		}
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
	public boolean deleteRechargeByRid(Long rid,Long sid) {
		int status = rechargeMapper.deleteRechargeByRid(rid);
		return status == 1 ? true : false;
	}
}

