
package com.retailers.dht.common.service.impl;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.constant.ExecuteQueueConstant;
import com.retailers.dht.common.dao.*;
import com.retailers.dht.common.entity.*;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.service.ExecuteQueueService;
import com.retailers.dht.common.utils.AttachmentUploadImageUtils;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.dht.common.vo.CouponVo;
import com.retailers.dht.common.vo.CouponWebVo;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
	@Autowired
	private CouponUseRangeMapper couponUseRangeMapper;
	@Autowired
	private GoodsMapper goodsMapper;

	@Transactional(rollbackFor = Exception.class)
	public boolean saveCoupon(CouponVo couponVo,Long optionId) {
		couponVo.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
		couponVo.setCpCreate(new Date());
		Coupon cp = new Coupon();
		BeanUtils.copyProperties(couponVo,cp);
		cp.setCpSurplusNum(cp.getCpNum());
		int status = couponMapper.saveCoupon(cp);
		List<Long> attachmentIds= new ArrayList<Long>();
		if(ObjectUtils.isNotEmpty(cp.getCpLogo())){
			attachmentIds.add(cp.getCpLogo());
		}
		Map<Long,Long> atts= AttachmentUploadImageUtils.findUploadImages(cp.getCpContext());
		if(!atts.isEmpty()){
			for(Long id:atts.keySet()){
				attachmentIds.add(id);
			}
		}
		attachmentService.editorAttachment(attachmentIds);
		couponVo.setCpId(cp.getCpId());
		saveCouponUseRange(couponVo);
		return status == 1 ? true : false;
	}
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCoupon(CouponVo couponVo,Long optionId) throws AppException{
		Coupon cp = new Coupon();
		BeanUtils.copyProperties(couponVo,cp);
		cp.setCpCreateSid(optionId);
		Coupon cu=couponMapper.queryCouponByCpId(cp.getCpId());
		if(cu.getCpNum().intValue()!=cu.getCpSurplusNum().intValue()){
			throw new AppException("优惠卷己被领取过，不能进行编辑");
		}
		cp.setCpSurplusNum(cp.getCpNum());
		cp.setIsDelete(cu.getIsDelete());
		cp.setCpCreate(cu.getCpCreate());
		int status = couponMapper.updateCoupon(cp);
		//设置优惠卷使用范围
		couponUseRangeMapper.clearCouponUseRangeByCpId(cp.getCpId());
		saveCouponUseRange(couponVo);
		//优惠卷附件管理
		List<Long> clearAtt=new ArrayList<Long>();
		List<Long> addAtt= new ArrayList<Long>();
		if(ObjectUtils.isEmpty(couponVo.getCpLogo())){
			if(ObjectUtils.isNotEmpty(cu.getCpLogo())){
				clearAtt.add(cu.getCpLogo());
			}
		}else{
			if(!ObjectUtils.compare(cu.getCpLogo(),couponVo.getCpLogo())){
				if(ObjectUtils.isNotEmpty(couponVo.getCpLogo())){
					addAtt.add(couponVo.getCpLogo());
				}
				if(ObjectUtils.isNotEmpty(cu.getCpLogo())){
					clearAtt.add(cu.getCpLogo());
				}
			}
		}
		Map<Long,Long> curAtts= AttachmentUploadImageUtils.findUploadImages(couponVo.getCpContext());
		Map<Long,Long> oldAtts= AttachmentUploadImageUtils.findUploadImages(cu.getCpContext());
		if(ObjectUtils.isEmpty(curAtts)){
			if(ObjectUtils.isNotEmpty(oldAtts)){
				for(long key:oldAtts.keySet()){
					clearAtt.add(key);
				}
			}
		}else{
			for(long key:curAtts.keySet()){
				addAtt.add(key);
			}
			if(ObjectUtils.isNotEmpty(oldAtts)){
				for(Long key:oldAtts.keySet()){
					if(!curAtts.containsKey(key)){
						clearAtt.add(key);
					}
				}
			}
		}
		attachmentService.editorAttachment(addAtt);
		attachmentService.editorAttachment(clearAtt, AttachmentConstant.ATTACHMENT_STATUS_NO);


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
	 *抢夺优惠卷
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
	public Pagination<CouponWebVo> queryUserCoupon(Long uid,long type, int pageNo, int pageSize){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("uid",uid);
		params.put("status",type);
		Pagination<CouponWebVo> page = new Pagination<CouponWebVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		page.setData(couponMapper.queryUserCoupon(page));
		return  page;
	}

	/**
	 * 清除地期优惠卷
	 */
	public void clearExpireCoupon() {
		Date curDate=new Date();
		List<Coupon> lists = couponMapper.queryExpireCoupon(curDate);
		for(Coupon coupon:lists){
			try{
				//设置用户拥有的优惠卷过期
				couponUserMapper.expireCouponUser(coupon.getCpId());
				//设置该优惠卷己过期
				couponMapper.expireCoupn(coupon.getCpId());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存使用范围
	 * @param couponVo
	 */
	private void saveCouponUseRange(CouponVo couponVo){
		List<Long> ids=new ArrayList<Long>();
		//优惠卷使用范围 1 指定商品种类
		if(couponVo.getCpIsRestricted().intValue()==CouponConstant.COUPON_USED_RANGE_GOODS_TYPE){
			String[] cids=couponVo.getSpzlIds().split(",");
			for(String id:cids){
				ids.add(Long.parseLong(id));
			}
		}
		if(couponVo.getCpIsRestricted().intValue()==CouponConstant.COUPON_USED_RANGE_GOODS){
			String[] cids=couponVo.getSpIds().split(",");
			for(String id:cids){
				ids.add(Long.parseLong(id));
			}
		}
		if(ObjectUtils.isNotEmpty(ids)){
			List<CouponUseRange> curs=new ArrayList<CouponUseRange>();
			for(long id:ids){
				CouponUseRange cur=new CouponUseRange();
				cur.setCpId(couponVo.getCpId());
				cur.setCpurIsAllow(CouponUseRangeConstant.IS_ALLOW_USE_YES);
				cur.setType(CouponUseRangeConstant.TYPE_COUPON);
				cur.setCpurRelevanceId(id);
				cur.setCpurType(couponVo.getCpIsRestricted());
				curs.add(cur);
			}
			//批量添加使用范围
			couponUseRangeMapper.saveCouponUseRanges(curs);
		}
	}

	/**
	 * 取得商品可用的优惠卷
	 * @param gid 商品id
	 * @return
	 */
	public List<CouponShowVo> queryCouponByGid(Long gid) {
		if(ObjectUtils.isNotEmpty(gid)){
			Goods goods =goodsMapper.queryGoodsByGid(gid);
			if(ObjectUtils.isNotEmpty(goods)){

			}
		}
		return null;
	}
}

