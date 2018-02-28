
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.CashMoneyConstant;
import com.retailers.dht.common.constant.UserCardPackageConstant;
import com.retailers.dht.common.dao.CashOrderMapper;
import com.retailers.dht.common.dao.LogUserCardPackageMapper;
import com.retailers.dht.common.dao.UserCardPackageMapper;
import com.retailers.dht.common.dao.UserMapper;
import com.retailers.dht.common.entity.CashOrder;
import com.retailers.dht.common.entity.LogUserCardPackage;
import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.dht.common.service.CashOrderService;
import com.retailers.dht.common.service.PayService;
import com.retailers.dht.common.service.WalletCashBackQueueService;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.common.vo.CashOrderVo;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：用户提现订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-02-27 23:04:40
 */
@Service("cashorderService")
public class CashOrderServiceImpl implements CashOrderService {
	Logger logger = LoggerFactory.getLogger(CashOrderServiceImpl.class);

	@Autowired
	private CashOrderMapper cashOrderMapper;
	@Autowired
	private ProcedureToolsService procedureToolsService;
	@Autowired
	private UserCardPackageMapper userCardPackageMapper;
	@Autowired
	private WalletCashBackQueueService walletCashBackQueueService;
	@Autowired
	private LogUserCardPackageMapper logUserCardPackageMapper;
	@Autowired
	private PayService payService;
	@Autowired
	private UserMapper userMapper;


	public boolean saveCashOrder(CashOrder cashOrder) {
		int status = cashOrderMapper.saveCashOrder(cashOrder);
		return status == 1 ? true : false;
	}
	public boolean updateCashOrder(CashOrder cashOrder) {
		int status = cashOrderMapper.updateCashOrder(cashOrder);
		return status == 1 ? true : false;
	}
	public CashOrder queryCashOrderByCoId(Long coId) {
		return cashOrderMapper.queryCashOrderByCoId(coId);
	}

	public Pagination<CashOrder> queryCashOrderList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CashOrder> page = new Pagination<CashOrder>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CashOrder> list = cashOrderMapper.queryCashOrderList(page);
		page.setData(list);
		return page;
	}

	/**
	 * 后台展示提现用户列表
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination<CashOrderVo> queryCashOrderLists(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<CashOrderVo> page = new Pagination<CashOrderVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CashOrderVo> list = cashOrderMapper.queryCashOrderLists(page);
		page.setData(list);
		return page;
	}

	public boolean deleteCashOrderByCoId(Long coId) {
		int status = cashOrderMapper.deleteCashOrderByCoId(coId);
		return status == 1 ? true : false;
	}

	/**
	 * 用户创建提现订单
	 * @param uid 提现用户id
	 * @param money 提现金额
	 * @param remark 提现备注
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> userCashMoney(Long uid, Long money, String remark)throws AppException {
		logger.info("用户发起提现请求，提现用户:{},提现金额:{}",uid,money);
		if(money.intValue()<100){
			throw new AppException("最新提现金额1元");
		}
		Date curDate=new Date();
		Map<String,Object> rtn=new HashMap<String,Object>();
		String lockKey= StringUtils.formate(SingleThreadLockConstant.USER_CASH_MONEY,uid+"");
		procedureToolsService.singleUnLockManager(lockKey);
		String orderNo=procedureToolsService.executeOrderNo(OrderEnum.CASH_MONEY);
		rtn.put("orderNo",orderNo);
		try{
			//取得用户钱包
			UserCardPackage ucp= userCardPackageMapper.queryUserCardPackageById(uid);
			if(ObjectUtils.isEmpty(ucp)){
				throw new AppException("可用提现额度不足");
			}
			//用户返现详情
			Map<String,Long> cashDetail= walletCashBackQueueService.queryUserCashBackDetailMoney(uid);
			long allowCashMoney=cashDetail.get("allowCash");
			if(allowCashMoney<money){
				throw new AppException("可用提现额度余额不足");
			}
			CashOrder co=new CashOrder();
			co.setCoNo(orderNo);
			co.setCoRemark(remark);
			co.setCoUid(uid);
			co.setCoMoney(money);
			co.setCoCreateTime(curDate);
			co.setCoStatus(CashMoneyConstant.CASH_STATUS_CREATE);
			cashOrderMapper.saveCashOrder(co);
			//减少用户提现金额
			int total = userCardPackageMapper.userCashMoney(uid,money);
			if(total==0){
				throw new AppException("可提现金额不足");
			}
			//添加提现日志
			LogUserCardPackage lucp= new LogUserCardPackage();
			lucp.setUid(uid);
			lucp.setVal(money);
			lucp.setCurVal(ucp.getUcashCurPrice());
			lucp.setRemark("用户发起提现操作，提现金额:"+money);
			lucp.setCreateTime(curDate);
			lucp.setRelationOrderId(co.getCoId());
			lucp.setType(UserCardPackageConstant.USER_CARD_PACKAGE_TYPE_WITHDRAWALS);
			logUserCardPackageMapper.saveLogUserCardPackage(lucp);
		}finally {
			logger.info("用户发起提现请求结束,执行时间:{},单号:{}",(System.currentTimeMillis()-curDate.getTime()),orderNo);
			procedureToolsService.singleUnLockManager(lockKey);
		}
		return rtn;
	}

	/**
	 * 提现申请审核
	 * @param sysUid 后台用户id
	 * @param orId 提现申请id
	 * @param status 审核状态
	 * @param remark 提现备注
	 * @return
	 * @throws AppException
	 */
	public boolean auditingCashOrder(Long sysUid, Long orId, Long status, String remark) throws AppException {
		logger.info("提现申请审核，审核用户:{},提现订单id:{},审核状态:{},审核备注:{}",sysUid,orId,status,remark);
		Date curDate=new Date();
		Map<String,Object> rtn=new HashMap<String,Object>();
		String lockKey= StringUtils.formate(SingleThreadLockConstant.AUDITING_CASH_MONEY,orId+"");
		procedureToolsService.singleUnLockManager(lockKey);
		try{
			CashOrder co= cashOrderMapper.queryCashOrderByCoId(orId);
			if(ObjectUtils.isEmpty(co)){
				throw new AppException("提现订单不存在");
			}
			if(co.getCoStatus().intValue()!=CashMoneyConstant.CASH_STATUS_CREATE){
				throw new AppException("提现订单己处理");
			}
			co.setCoAuditingRemark(remark);
			co.setCoStatus(status);
			co.setCoAuditingSid(sysUid);
			co.setCoAuditingTime(curDate);
			cashOrderMapper.updateCashOrder(co);
			//如果拒绝 返还金额
			if(status.intValue()==CashMoneyConstant.CASH_AUDITING_STATUS_FAILE){
				//减少用户提现金额
				int total = userCardPackageMapper.userCashMoney(co.getCoUid(),-co.getCoMoney());
				if(total==0){
					throw new AppException("可提现金额不足");
				}
				//取得用户钱包
				UserCardPackage ucp= userCardPackageMapper.queryUserCardPackageById(co.getCoUid());
				//添加提现日志
				LogUserCardPackage lucp= new LogUserCardPackage();
				lucp.setUid(co.getCoUid());
				lucp.setVal(co.getCoMoney());
				lucp.setCurVal(ucp.getUcashCurPrice());
				lucp.setRemark("提现审核拒绝，金额返还，返还金额:"+co.getCoMoney());
				lucp.setCreateTime(curDate);
				lucp.setRelationOrderId(co.getCoId());
				lucp.setType(UserCardPackageConstant.USER_CARD_PACKAGE_TYPE_WITHDRAWALS_RETURN);
				logUserCardPackageMapper.saveLogUserCardPackage(lucp);
			}

		}finally {
			logger.info("提现申请审核，审核用户:{},提现订单id:{},审核状态:{},审核备注:{},执行时间:{},",sysUid,orId,status,remark,(System.currentTimeMillis()-curDate.getTime()));
			procedureToolsService.singleUnLockManager(lockKey);
		}
		return true;
	}

	/**
	 * 向用户打款
	 * @param ocId 提现订单id
	 * @param suid 打款用户
	 * @return
	 * @throws AppException
	 */
	public String playMoneyToUser(Long ocId, Long suid,String ip) throws AppException {
		logger.info("用户提现，资金下划，审核用户:{},提现订单id:{}",suid,ocId);
		Date curDate=new Date();
		Map<String,Object> rtn=new HashMap<String,Object>();
		String lockKey= StringUtils.formate(SingleThreadLockConstant.CASH_PLAY_MONEY,ocId+"");
		procedureToolsService.singleUnLockManager(lockKey);
		String rtnMsg="";
		try{
			CashOrder co= cashOrderMapper.queryCashOrderByCoId(ocId);
			if(ObjectUtils.isEmpty(co)){
				throw new AppException("打款订单不存在");
			}
			if(co.getCoStatus().intValue()!=CashMoneyConstant.CASH_AUDITING_STATUS_SUCCESS&&co.getCoStatus().intValue()!=CashMoneyConstant.CASH_STATUS_FAILE){
				throw new AppException("订单己处理");
			}
			//取得用户
			UserInfoVIew uiv = userMapper.queryLoginUserInfoView(co.getCoUid());
			co.setCoCashSid(suid);
			co.setCoReturnTime(curDate);
			try{
				//微信打款
				Map<String,String> rtnMap = payService.wxPlayMoney(co.getCoNo(),co.getCoMoney(),uiv.getWauOpenid(),uiv.getUname(),ip);
				String return_code = rtnMap.get("return_code");//通信标识（ SUCCESS/FAIL）
				String return_msg = rtnMap.get("return_msg");//返回信息
				String result_code = rtnMap.get("result_code");//交易标识（ SUCCESS/FAIL）
				if(return_code.equals("SUCCESS")&&result_code.equals("SUCCESS")){
					co.setCoReturnNo(rtnMap.get("payment_no"));
					co.setCoStatus(CashMoneyConstant.CASH_STATUS_SUCCESS);
					co.setCoReturnData("下划成功");
				}else{
					co.setCoStatus(CashMoneyConstant.CASH_STATUS_FAILE);
					rtnMsg=rtnMap.get("err_code_des");
					co.setCoReturnData(StringUtils.formates(return_msg,rtnMsg));

				}
			}catch (Exception e){
				logger.info(StringUtils.getErrorInfoFromException(e));
				throw new AppException(e.getMessage());
			}
			cashOrderMapper.updateCashOrder(co);
		}finally {
			logger.info("用户提现，资金下划，审核用户:{},提现订单id:{},执行时间:{}",suid,ocId,(System.currentTimeMillis()-curDate.getTime()));
			procedureToolsService.singleUnLockManager(lockKey);
		}
		return rtnMsg;
	}
}

