
package com.retailers.dht.common.service.impl;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.dao.CouponUseRangeMapper;
import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.CouponUseRange;
import com.retailers.dht.common.entity.CouponUser;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.dao.GoodsCouponMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.CouponVo;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：商品优惠活动Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 20:29:50
 */
@Service("goodscouponService")
public class GoodsCouponServiceImpl implements GoodsCouponService {
	@Autowired
	private GoodsCouponMapper goodsCouponMapper;
	@Autowired
	private CouponUseRangeMapper couponUseRangeMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsClassificationService goodsClassificationService;

	@Transactional(rollbackFor = Exception.class)
	public boolean saveGoodsCoupon(GoodsCouponVo goodsCouponVo) {
		goodsCouponVo.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
		GoodsCoupon gcp = new GoodsCoupon();
		BeanUtils.copyProperties(goodsCouponVo,gcp);
		int status = goodsCouponMapper.saveGoodsCoupon(gcp);
		goodsCouponVo.setGcpId(gcp.getGcpId());
		saveCouponUseRange(goodsCouponVo);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsCoupon(GoodsCoupon goodsCoupon) {
		int status = goodsCouponMapper.updateGoodsCoupon(goodsCoupon);
		return status == 1 ? true : false;
	}
	public GoodsCoupon queryGoodsCouponByGcpId(Long gcpId) {
		return goodsCouponMapper.queryGoodsCouponByGcpId(gcpId);
	}

	public Pagination<GoodsCouponShowVo> queryGoodsCouponList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsCouponShowVo> page = new Pagination<GoodsCouponShowVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCouponShowVo> list = goodsCouponMapper.queryGoodsCouponList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCouponByGcpId(Long gcpId) {
		int status = goodsCouponMapper.deleteGoodsCouponByGcpId(gcpId);
		return status == 1 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean editorGoodsCoupon(GoodsCouponVo goodsCouponVo) {
		GoodsCoupon gcp = new GoodsCoupon();
		goodsCouponVo.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
		BeanUtils.copyProperties(goodsCouponVo,gcp);
		int status = goodsCouponMapper.updateGoodsCoupon(gcp);
		//清除该优惠下的使用范围
		couponUseRangeMapper.clearCouponUseRangeByCpId(CouponUseRangeConstant.TYPE_GOODS_COUPON,gcp.getGcpId());
		if(gcp.getGcpIsRestricted().intValue()!=CouponConstant.COUPON_USED_RANGE_ALL){
			saveCouponUseRange(goodsCouponVo);
		}
		return status == 1 ? true : false;
	}

	/**
	 * 保存使用范围
	 * @param couponVo
	 */
	private void saveCouponUseRange(GoodsCouponVo couponVo){
		List<Long> ids=new ArrayList<Long>();
		//优惠卷使用范围 1 指定商品种类
		List<CouponUseRange> curs=new ArrayList<CouponUseRange>();
		if(ObjectUtils.isNotEmpty(couponVo.getSpzlId())){
			String[] cids=couponVo.getSpzlId().split(",");
			for(String id:cids){
				if(ObjectUtils.isNotEmpty(id)){
					CouponUseRange cur=createCouponUseRange(couponVo.getGcpId(),Long.parseLong(id),CouponConstant.COUPON_USED_RANGE_GOODS_TYPE);
					curs.add(cur);
				}
			}
		}
		//优惠卷使用范围2 指定商品
		if(ObjectUtils.isNotEmpty(couponVo.getSpId())){
			String[] cids=couponVo.getSpId().split(",");
			for(String id:cids){
				if(ObjectUtils.isNotEmpty(id)){
					CouponUseRange cur=createCouponUseRange(couponVo.getGcpId(),Long.parseLong(id),CouponConstant.COUPON_USED_RANGE_GOODS);
					curs.add(cur);
				}
			}
		}
		if(ObjectUtils.isNotEmpty(curs)){
			//批量添加使用范围
			couponUseRangeMapper.saveCouponUseRanges(curs);
		}
	}

	private CouponUseRange createCouponUseRange(Long gcpId,Long relevanceId,Long isRestricted){
		CouponUseRange cur=new CouponUseRange();
		cur.setCpId(gcpId);
		cur.setCpurIsAllow(CouponUseRangeConstant.IS_ALLOW_USE_YES);
		cur.setCpurRelevanceId(relevanceId);
		cur.setType(CouponUseRangeConstant.TYPE_GOODS_COUPON);
		cur.setCpurType(isRestricted);
		return cur;
	}

	/**
	 * 根据商品取得该商品下的所有优惠
	 * @param goodsId
	 * @return
	 */
	public List<GoodsCouponShowVo> queryGoodsCouponByGid(Long goodsId){
        //取得允许的商品优惠
        Goods goods=goodsMapper.queryGoodsByGid(goodsId);
        List<GoodsCouponShowVo> list = goodsCouponMapper.queryAllowGoodsCouponByGid(goods.getGclassification(),goodsId,new Date());
		return list;
	}

	/**
	 * 根据商品取得该商品下的所有优惠
	 * @param goodsIds
	 * @return
	 */
	private List<GoodsCouponShowVo> queryGoodsCouponByGid(List<Long> goodsIds){
		//取得允许的商品优惠
		//List<Goods> goods=goodsMapper.queryGoodsByGids(goodsIds);
		List<GoodsCouponShowVo> list = goodsCouponMapper.queryAllowGoodsCouponByGids(goodsIds,new Date());
		return list;
	}
	/**
	 * 根据优惠名称取得优惠列表（排除己存在的）
	 * @param couponNm 优惠名称
	 * @param goodsId 商品id
	 * @return
	 */
	public List<GoodsCouponShowVo> queryUnBindGoodsCouponByGid(String couponNm,Long goodsId){
	    List<GoodsCouponShowVo> lists = queryGoodsCouponByGid(goodsId);
        List<Long> gcIds=new ArrayList<Long>();
        for(GoodsCouponShowVo vo:lists){
            gcIds.add(vo.getGcpId());
        }
		return goodsCouponMapper.queryUnBindGoodsCuoponByGid(couponNm,gcIds,new Date());
	}

	/**
	 * 商品绑定优惠
	 * @param goodsId 商品id
	 * @param gcpIds 优惠ids（多个之间用逗号隔开)
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
    public boolean goodsBindCoupon(Long goodsId, String gcpIds) throws AppException {
		return bindOptions(goodsId,gcpIds,CouponUseRangeConstant.IS_ALLOW_USE_YES);
    }

	/**
	 * 解绑商品优惠卷
	 * @param goodsId 商品id
	 * @param gcpIds 优惠ids（多个之间用逗号隔开)
	 * @return
	 * @throws AppException
	 */
    public boolean goodsUnBindCoupon(Long goodsId, String gcpIds) throws AppException {
    	return bindOptions(goodsId,gcpIds,CouponUseRangeConstant.IS_ALLOW_USE_NO);
    }

    private boolean bindOptions(Long goodsId, String gcpIds,Integer allowStatus){
		String[] gcpIds_ = gcpIds.split(",");
		List<Long> gcpLists=new ArrayList<Long>();
		Map<Long,Long> gcpMaps=new HashMap<Long, Long>();
		List<Long> bindcpurIds=new ArrayList<Long>();
		for(String id:gcpIds_){
			gcpMaps.put(Long.parseLong(id),Long.parseLong(id));
			gcpLists.add(Long.parseLong(id));
		}
		List<CouponUseRange> curs=couponUseRangeMapper.queryCouponUseRangeByGid(CouponUseRangeConstant.TYPE_GOODS_COUPON,gcpLists,goodsId);
		if(ObjectUtils.isNotEmpty(curs)){
			for(CouponUseRange cur:curs){
				gcpMaps.remove(cur.getCpId());
				bindcpurIds.add(cur.getCpurId());
			}
		}
		List<CouponUseRange> batchAddCurs=new ArrayList<CouponUseRange>();
		//判断是否有新增
		if(ObjectUtils.isNotEmpty(gcpMaps)){
			for(Long key:gcpMaps.keySet()){
				CouponUseRange cur=new CouponUseRange();
				cur.setCpurIsAllow(allowStatus);
				cur.setCpId(key);
				cur.setType(CouponUseRangeConstant.TYPE_GOODS_COUPON);
				cur.setCpurType(CouponConstant.COUPON_USED_RANGE_GOODS);
				cur.setCpurRelevanceId(goodsId);
				batchAddCurs.add(cur);
			}
		}
		// 判断优惠是否有新增
		if(ObjectUtils.isNotEmpty(batchAddCurs)){
			couponUseRangeMapper.saveCouponUseRanges(batchAddCurs);
		}
		if(ObjectUtils.isNotEmpty(bindcpurIds)){
			//判断是否存在修改数据
			couponUseRangeMapper.updateCouponUseRangeAllow(bindcpurIds,allowStatus);
		}
		return true;
	}

	public Object checkGoodsCoupon(Map<Long,List<Long>> gcpMaps,Map<Long,Long> buyPrices)throws AppException{
    	List<Long> goodsIds=new ArrayList<Long>();
    	for(Long gid:gcpMaps.keySet()){
			goodsIds.add(gid);
		}
    	//取得商品例表
		List<GoodsCouponShowVo> list = queryGoodsCouponByGid(goodsIds);

		return null;
	}
}



