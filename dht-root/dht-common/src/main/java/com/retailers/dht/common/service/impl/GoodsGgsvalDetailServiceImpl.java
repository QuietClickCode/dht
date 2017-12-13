
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGgsval;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import com.retailers.dht.common.dao.GoodsGgsvalDetailMapper;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
import com.retailers.dht.common.service.GoodsGgsvalService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsInventoryVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与规格值详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 09:52:11
 */
@Service("goodsggsvaldetailService")
public class GoodsGgsvalDetailServiceImpl implements GoodsGgsvalDetailService {
	@Autowired
	private GoodsGgsvalDetailMapper goodsGgsvalDetailMapper;
	@Autowired
    private GoodsGgsvalService goodsGgsvalService;
	@Autowired
    private GoodsDetailService goodsDetailService;
	@Autowired
    private GoodsService goodsService;

	public boolean saveGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail) {
		int status = goodsGgsvalDetailMapper.saveGoodsGgsvalDetail(goodsGgsvalDetail);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGgsvalDetail(GoodsGgsvalDetail goodsGgsvalDetail) {
		int status = goodsGgsvalDetailMapper.updateGoodsGgsvalDetail(goodsGgsvalDetail);
		return status == 1 ? true : false;
	}
	public GoodsGgsvalDetail queryGoodsGgsvalDetailByGgdId(Long ggdId) {
		return goodsGgsvalDetailMapper.queryGoodsGgsvalDetailByGgdId(ggdId);
	}

	public Pagination<GoodsGgsvalDetail> queryGoodsGgsvalDetailList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGgsvalDetail> page = new Pagination<GoodsGgsvalDetail>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgsvalDetail> list = goodsGgsvalDetailMapper.queryGoodsGgsvalDetailList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgsvalDetailByGgdId(Long ggdId) {
		int status = goodsGgsvalDetailMapper.deleteGoodsGgsvalDetailByGgdId(ggdId);
		return status == 1 ? true : false;
	}

	public  boolean clearAllGgsrel(Long gid,Long updatepersonId){;
        Goods goods = goodsService.queryGoodsByGid(gid);
        goods.setIsChecked(0L);
        goodsService.updateGoods(goods,updatepersonId);

        boolean goodsGgsvalFlag = goodsGgsvalService.deleteGoodsGgsvalByGid(gid);

        boolean goodsDetailFlag = goodsDetailService.deleteGoodsDetailByGid(gid);

        boolean goodsGgsvalDetailFlag = deleteGoodsGgsvalDetailByGid(gid);


	    return goodsGgsvalFlag&&goodsDetailFlag&&goodsGgsvalDetailFlag;
    }

    public Pagination<GoodsInventoryVo> queryGoodsInventoryLists(Map<String, Object> params,int pageNo,int pageSize){
        Pagination<GoodsInventoryVo> page = new Pagination<GoodsInventoryVo>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setParams(params);
        List<GoodsInventoryVo> list = goodsGgsvalDetailMapper.queryGoodsInventoryLists(page);
        page.setData(list);
        return page;
    }

    public boolean editGoodsInventory(Long gdId,Long inventory,Long updatepersonId){
        GoodsDetail goodsDetail = goodsDetailService.queryGoodsDetailByGdId(gdId);
        if(inventory>0){
            goodsDetail.setGdInventory(goodsDetail.getGdInventory()+inventory);
        }
        goodsDetail.setGdResidueinventory(goodsDetail.getGdResidueinventory()+inventory);
        return goodsDetailService.updateGoodsDetail(goodsDetail,updatepersonId);
    }

    public boolean deleteGoodsGgsvalDetailByGid(Long gid){
        int status = goodsGgsvalDetailMapper.deleteGoodsGgsvalDetailByGid(gid);
        return status>0?true:false;
    }
    public boolean saveGoodsGgsvalDetails( List<GoodsGgsvalDetail> list){
        int status = goodsGgsvalDetailMapper.saveGoodsGgsvalDetails(list);
        return status>0?true:false;
    }
}

