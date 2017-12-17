
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.dao.BuyCarMapper;
import com.retailers.dht.common.service.BuyCarService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：购物车表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 15:01:54
 */
@Service("buycarService")
public class BuyCarServiceImpl implements BuyCarService {
	@Autowired
	private BuyCarMapper buyCarMapper;
	public boolean saveBuyCar(BuyCar buyCar) {
		Map params = new HashMap();
		Long gid = buyCar.getGid();
		Long uid = buyCar.getUid();
		String bcUrl = buyCar.getBcUrl();
		params.put("gid",gid);
		params.put("uid",uid);
		params.put("bcUrl",bcUrl);
		params.put("isDelete",0L);
		List<BuyCar> list = queryBuyCarList(params,1,1).getData();
		int status = 0;
		if(ObjectUtils.isNotEmpty(list)){
			BuyCar bc = list.get(0);
			bc.setBcInviterid(null);
			bc.setGcount(bc.getGcount()+buyCar.getGcount());
			status = buyCarMapper.updateBuyCar(bc);
		}else{
			status = buyCarMapper.saveBuyCar(buyCar);
		}
		return status == 1 ? true : false;
	}
	public boolean updateBuyCar(BuyCar buyCar) {
		BuyCar bc = buyCarMapper.queryBuyCarByBcId(buyCar.getBcId());
		if(!ObjectUtils.isEmpty(buyCar.getBcGsval())){
			bc.setBcGsval(buyCar.getBcGsval());
		}
		if(!ObjectUtils.isEmpty(buyCar.getGcount())){
			bc.setGcount(buyCar.getGcount());
		}
		if(!ObjectUtils.isEmpty(buyCar.getBcGsvalids())){
			bc.setBcGsvalids(buyCar.getBcGsvalids());
		}
		int status = buyCarMapper.updateBuyCar(bc);
		return status == 1 ? true : false;
	}
	public BuyCar queryBuyCarByBcId(Long bcId) {
		return buyCarMapper.queryBuyCarByBcId(bcId);
	}

	public Pagination<BuyCar> queryBuyCarList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<BuyCar> page = new Pagination<BuyCar>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<BuyCar> list = buyCarMapper.queryBuyCarList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteBuyCarByBcId(Long bcId) {
		BuyCar buyCar = buyCarMapper.queryBuyCarByBcId(bcId);
		buyCar.setIsDelete(1L);
		int status = buyCarMapper.updateBuyCar(buyCar);
		return status == 1 ? true : false;
	}

	public List<GoodsVo> queryGoodsVoList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<GoodsVo> page = new Pagination<GoodsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsVo> list = buyCarMapper.queryGoodsVoList(page);
		for(GoodsVo goodsVo:list){
			goodsVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsVo.getImgUrl());
			goodsVo.setGdimgurl(AttachmentConstant.IMAGE_SHOW_URL+goodsVo.getGdimgurl());
		}
		return list;
	}

	public boolean deleteBuyCarByBcIds(String bcIds) {
		String[] bcIdsArr = bcIds.split(",");
		boolean flag=true;
		for(String bcIdStr:bcIdsArr){
			Long bcIdLong = Long.parseLong(bcIdStr);
			flag=flag && deleteBuyCarByBcId(bcIdLong);
		}
		return flag;
	}
}

