
package com.retailers.dht.common.service.impl;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.ExecuteQueueConstant;
import com.retailers.dht.common.dao.CouponUserMapper;
import com.retailers.dht.common.dao.ExecuteQueueMapper;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.dht.common.dao.CouponMapper;
import com.retailers.dht.common.entity.CouponUser;
import com.retailers.dht.common.entity.ExecuteQueue;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.service.ExecuteQueueService;
import com.retailers.dht.common.utils.AttachmentUploadImageUtils;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.dht.common.vo.CouponWebVo;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：优惠卷Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 20:34:20
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {
	Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private CouponUserMapper couponUserMapper;
	@Autowired
	private ExecuteQueueMapper executeQueueMapper;
	@Autowired
	private ExecuteQueueService executeQueueService;

	public boolean saveCoupon(Coupon coupon) {
		coupon.setCpSurplusNum(coupon.getCpNum());
		int status = couponMapper.saveCoupon(coupon);
		List<Long> attachmentIds= new ArrayList<Long>();
		attachmentIds.add(coupon.getCpLogo());
		Map<Long,Long> atts= AttachmentUploadImageUtils.findUploadImages(coupon.getCpContext());
		if(!atts.isEmpty()){
			for(Long id:atts.keySet()){
				attachmentIds.add(id);
			}
		}
		attachmentService.editorAttachment(attachmentIds);
		return status == 1 ? true : false;
	}
	public boolean updateCoupon(Coupon coupon) throws AppException{
		Coupon cu=couponMapper.queryCouponByCpId(coupon.getCpId());
		if(cu.getCpNum().intValue()!=cu.getCpSurplusNum().intValue()){
			throw new AppException("优惠卷己被领取过，不能进行编辑");
		}
		coupon.setCpSurplusNum(coupon.getCpNum());
		int status = couponMapper.updateCoupon(coupon);
		return status == 1 ? true : false;
	}
	public Coupon queryCouponByCpId(Long cpId) {
		return couponMapper.queryCouponByCpId(cpId);
	}

	public Pagination<CouponShowVo> queryCouponList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<CouponShowVo> page = new Pagination<CouponShowVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CouponShowVo> list = couponMapper.queryCouponList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCouponByCpId(Long cpId) {
		int status = couponMapper.deleteCouponByCpId(cpId);
		return status == 1 ? true : false;
	}

	/**
	 *
	 * @param uid 用户id
	 * @return
	 * @throws AppException
	 */
	public List<CouponWebVo> queryCouponList(Long uid, int pageNo, int pageSize) throws AppException {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("uid",uid);
		Pagination<CouponWebVo> page = new Pagination<CouponWebVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		return couponMapper.queryCurValidCoupon(page);
	}

	/**
	 *
	 * @param userId 用户id
	 * @param cpId 优惠卷id
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean userGrabCoupon(Long userId, Long cpId) throws AppException {
		logger.info("用户抢夺优惠卷开始");
		Date curDate=new Date();
		ExecuteQueue eq=new ExecuteQueue();
		try{
			eq.setSeqType(ExecuteQueueConstant.EQ_TYPE_COUPON);
			eq.setSeqCreateTime(curDate);
			eq.setSeqUid(userId);
			eq.setSeqExeId(cpId+"");
			eq.setSeqStatus(SystemConstant.FAIL);
			executeQueueMapper.saveExecuteQueue(eq);

			Coupon coupon=couponMapper.queryCouponByCpId(cpId);
			if(ObjectUtils.isEmpty(coupon)){
				throw new AppException("优惠卷不存在");
			}

			//判断优惠卷是否异常
			checkCoupon(coupon,userId);
			//设置用户获得优惠卷
			CouponUser cu = new CouponUser();
			//判断优惠卷类型
			if(coupon.getCpCoinType().intValue()== CouponConstant.CP_COIN_TYPE_FIXED){
				if(coupon.getCpType().intValue()==CouponConstant.GCP_TYPE_MONEY){
					cu.setCpuVal(coupon.getCpMoney());
				}else{
					cu.setCpuVal(coupon.getCpDiscount());
				}
			}else if(coupon.getCpCoinType().intValue()==CouponConstant.CP_COIN_TYPE_RAND){
				if(coupon.getCpType().intValue()==CouponConstant.GCP_TYPE_DISCOUNT){
					long discount= NumberUtils.randomNumber(coupon.getCpMinDiscount().intValue(),coupon.getCpMaxDiscount().intValue());
					cu.setCpuVal(discount);
				}
			}
			cu.setCpId(coupon.getCpId());
			cu.setCpuCycleId(0);
			cu.setCpuUid(userId);
			cu.setCpuStatus(0l);
			cu.setCpuCreateTime(curDate);
			couponUserMapper.saveCouponUser(cu);
			//优惠卷剩余数量减一
			int total =couponMapper.reduceCouponNum(cpId);
			if(total==0){
				throw new AppException("对不起，此次发放的"+coupon.getCpNum()+"张，优惠卷己被领完。");
			}
			eq.setSeqStatus(SystemConstant.SUCCESS);
		}catch(AppException e){
			eq.setSeqRemark(e.getMessage());
			throw e;
		}catch(DuplicateKeyException e){
			eq.setSeqRemark(e.getMessage());
			throw new AppException("请求正在执行，请不要重复提交");
		}catch (Exception e){
			e.printStackTrace();
			eq.setSeqRemark(e.getMessage());
			throw new AppException(e.getMessage());
		}finally {
			eq.setSeqExeTime(new Date());
			eq.setSeqTime((System.currentTimeMillis()-curDate.getTime()));
			eq.setSeqWaitTime(0l);
			System.out.println(JSON.toJSON(eq));
			executeQueueService.addHistoryExecuteQueue(eq);
			logger.info("用户抢夺优惠卷结束,执行时间：{}",(System.currentTimeMillis()-curDate.getTime()));
		}
		return true;
	}

	/**
	 * 优惠卷校验
	 * @param coupon
	 */
	private void checkCoupon(Coupon coupon,Long uid)throws AppException{
		Date curDate=new Date();
		//判断优惠卷是否删除 或是否有效
		if(coupon.getIsDelete().intValue()==SystemConstant.SYS_IS_DELETE_YES||coupon.getIsValid().intValue()==SystemConstant.SYS_IS_VALID_NO){
			throw new AppException("优惠卷状态异常");
		}
		//判断是否还有剩余的优惠卷
		if(ObjectUtils.isEmpty(coupon.getCpSurplusNum())||coupon.getCpSurplusNum().intValue()-1<0){
			throw new AppException("对不起，此次发放的"+coupon.getCpNum()+"张，优惠卷己被领完。");
		}
		//判断是否在有效时间范围内
		//无领取时间限制
		if(ObjectUtils.isEmpty(coupon.getCpSendStartDate())&&ObjectUtils.isEmpty(coupon.getCpSendEndDate())){
			//判断是否己过优惠卷使用期 过期优惠卷不能再领取
			if(ObjectUtils.isNotEmpty(coupon.getCpEndDate())&&coupon.getCpEndDate().getTime()<curDate.getTime()){
				throw new AppException("优惠发放己结束，不能再领取");
			}
		}
		//判断用户是否领取得该优惠卷
		int total =couponUserMapper.checkCouponUser(coupon.getCpId(),uid);
		if(total>0){
			throw new AppException("您己领取过该优惠卷,不能重复领取");
		}
	}

	/**
	 *
	 * @param uid 用户id
	 * @return
	 * @throws AppException
	 */
	public Pagination<CouponWebVo> queryUserCoupon(Long uid,long type, int pageNo, int pageSize) throws AppException {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("uid",uid);
		Pagination<CouponWebVo> page = new Pagination<CouponWebVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		page.setData(couponMapper.queryCurValidCoupon(page));
		return  page;
	}

}

