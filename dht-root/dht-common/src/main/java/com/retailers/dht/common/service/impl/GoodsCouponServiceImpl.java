
package com.retailers.dht.common.service.impl;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.dao.CouponUseRangeMapper;
import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.*;
import com.retailers.dht.common.dao.GoodsCouponMapper;
import com.retailers.dht.common.service.*;
import com.retailers.dht.common.view.GoodsCouponView;
import com.retailers.dht.common.vo.*;
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
	private GoodsClassificationService goodsClassificationService;
	@Autowired
	private GoodsDetailService goodsDetailService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private GoodsMapper goodsMapper;

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

	/**
	 * 根据购买商品信息取得商品优惠列表
	 * @param gtpvs
	 * @return
	 */
	public Map<Long,List<GoodsCouponView>> queryGoodsCouponBuyGid(List<GoodsTypePriceVo> gtpvs) {
		//返回结果
		Map<Long,List<GoodsCouponView>> rtn=new HashMap<Long, List<GoodsCouponView>>();
		Date curDate=new Date();
		if(ObjectUtils.isNotEmpty(gtpvs)){
			//商品价格
			Map<Long,Long> gps=new HashMap<Long, Long>();
			Map<Long,Long> bgns=new HashMap<Long, Long>();
			List<Long> gids=new ArrayList<Long>();
			for(GoodsTypePriceVo gtpv:gtpvs){
				gps.put(gtpv.getgId(),gtpv.getgPrice());
				gids.add(gtpv.getgId());
				bgns.put(gtpv.getgId(),gtpv.getNum());
			}
			//取得商品优惠列表
			List<GoodsCouponView> xzAllows=goodsCouponMapper.queryGoodsCouponByGids(gids,curDate);
			//取得没有限制的商品优惠
			List<GoodsCouponView> wxzs=goodsCouponMapper.queryUnRestrictedGoodsCoupon(curDate);
			//商品优惠id 对应的优惠数据
			Map<Long,GoodsCouponView> maps=new HashMap<Long, GoodsCouponView>();
			for(GoodsCouponView gcv:wxzs){
				maps.put(gcv.getGcpId(),gcv);
			}
			Map<Long,List<GoodsCouponView>> map=new HashMap<Long, List<GoodsCouponView>>();
			//商品对应的商品优惠列表
			Map<Long,Map<Long,GoodsCouponView>> spyhMap=new HashMap<Long, Map<Long, GoodsCouponView>>();
			//取得商品下的优惠列表
			for(GoodsCouponView gcv:xzAllows){
				if(map.containsKey(gcv.getGid())){
					map.get(gcv.getGid()).add(gcv);
					spyhMap.get(gcv.getGid()).put(gcv.getGcpId(),gcv);
				}else{
					List<GoodsCouponView> gcvs=new ArrayList<GoodsCouponView>();
					gcvs.add(gcv);
					map.put(gcv.getGid(),gcvs);
					Map<Long,GoodsCouponView> spyh=new HashMap<Long, GoodsCouponView>();
					spyh.put(gcv.getGcpId(),gcv);
					spyhMap.put(gcv.getGid(),spyh);
				}
			}
			System.out.println(JSON.toJSON(spyhMap));
			System.out.println(JSON.toJSON(maps));
			//商品所有能够使用的优惠
			Map<Long,GoodsCouponView> acgMaps=new HashMap<Long, GoodsCouponView>();
			//设置商品对应的优惠
			for(Long gid:gids){
				rtn.put(gid,assembleGoodsCoupon(maps,spyhMap.get(gid),gps.get(gid),bgns.get(gid)));
			}
		}
		return rtn;
	}

	/**
	 * 组装商品可用的优惠列表
	 * @param wxzuyhs 无限制优惠例表
	 * @param xzyh 限制优惠列表
	 * @param buyPrce 购买总价
	 * @param buyNum 购买数量
	 * @return
	 */
	private List<GoodsCouponView> assembleGoodsCoupon(Map<Long,GoodsCouponView> wxzuyhs,Map<Long,GoodsCouponView> xzyh,Long buyPrce,Long buyNum){
		List<GoodsCouponView> rtn=new ArrayList<GoodsCouponView>();
		List<GoodsCouponView> bjs=new ArrayList<GoodsCouponView>();
		if(ObjectUtils.isNotEmpty(wxzuyhs)){
			for(Long key:wxzuyhs.keySet()){
				if(ObjectUtils.isNotEmpty(xzyh)&&xzyh.containsKey(key)){
					GoodsCouponView vo = xzyh.get(key);
					if(vo.getAllow().indexOf("1")<0){
						bjs.add(vo);
					}
					xzyh.remove(key);
				}else{
					bjs.add(wxzuyhs.get(key));
				}
			}
		}
		if(ObjectUtils.isNotEmpty(xzyh)){
			for(Long key:xzyh.keySet()){
				bjs.add(xzyh.get(key));
			}
		}
		for(GoodsCouponView gcv:bjs){
			//判断该优惠卷是否满足条件
			if(gcv.getGcpUnits().intValue()==CouponConstant.GOODS_UNITS_NUM){
				if(buyNum>=gcv.getGcpCondition().longValue()){
					rtn.add(gcv);
				}
			}else if(gcv.getGcpUnits().intValue()==CouponConstant.GOODS_UNITS_PRICE){
				if(buyPrce>=gcv.getGcpCondition().longValue()){
					rtn.add(gcv);
				}
			}
		}
		return rtn;
	}


	/**
	 * 根据商品购买信息取得商品对应的优惠列表，和用户能够使用的优惠卷列表
	 * @param uid 用户id
	 * @param gbs 购买商品信息
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> queryGoodsCouponLists(Long uid, List<BuyGoodsDetailVo> gbs){
		String gdIds="";
		List<Long> gIds=new ArrayList<Long>();
		//商品gid对应的购买数量
		Map<Long,Integer> gdtn= new HashMap<Long, Integer>();
		//商品对应的购买数量
		Map<Long,Integer> gtn= new HashMap<Long, Integer>();
		for(BuyGoodsDetailVo gb:gbs){
			gdIds+=gb.getGdId()+",";
			gdtn.put(gb.getGdId(),gb.getNum());
		}

		//取得商品价格
		List<GoodsDetail> gts = goodsDetailService.queryGoodsDetailByGdIds(gdIds);


		//商品购买总价
		Map<Long,Long> gtp=new HashMap<Long, Long>();
		for(GoodsDetail gt:gts){
			//取得商品价格
			gtp.put(gt.getGid(),gt.getGdPrice()*gdtn.get(gt.getGdId()));
			gIds.add(gt.getGid());
			gtn.put(gt.getGid(),gdtn.get(gt.getGdId()));
		}
		//商品列表
		List<Goods> goodss=goodsMapper.queryGoodsByGids(gIds);
		Map<Long,Long> gType=new HashMap<Long, Long>();
		for(Goods g:goodss){
			gType.put(g.getGid(),g.getGclassification());
		}

		//取得商吕购买信息
		List<GoodsTypePriceVo> gtpvs= new ArrayList<GoodsTypePriceVo>();
		for(Long gid:gIds){
			GoodsTypePriceVo gtpv=new GoodsTypePriceVo();
			gtpv.setgId(gid);
			gtpv.setgType(gType.get(gid));
			gtpv.setgPrice(gtp.get(gid));
			gtpv.setNum(gtn.get(gid).longValue());
			gtpvs.add(gtpv);
		}
		//取得商品关联的优惠卷
		Map<Long,List<GoodsCouponView>> gcs=queryGoodsCouponBuyGid(gtpvs);
		//取得用户此次购买满足条件的未使用优惠卷
		List<CouponWebVo> uacs=couponService.queryUserUseCoupons(uid,gtpvs);
		Map<String,Object> rtn=new HashMap<String, Object>();
		//商品优惠列表（根据商品id）
		rtn.put("gcLists",gcs);
		//用户优惠卷
		rtn.put("userCoupons",uacs);
		//商品优惠
		return rtn;
	}
}



