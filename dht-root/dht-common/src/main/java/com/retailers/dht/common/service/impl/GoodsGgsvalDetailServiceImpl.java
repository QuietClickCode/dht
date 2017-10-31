
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

	public  boolean clearAllGgsrel(Long gid,Long updatepersonId){
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("gid",gid);
	    map.put("isDelete",0);
	    int index = 0;
	    int status = 0;
        boolean flag =false;

        Goods goods = goodsService.queryGoodsByGid(gid);
        goods.setIsChecked(0L);
        goodsService.updateGoods(goods,updatepersonId);

        Pagination<GoodsGgsval> paginationGoodsGgsval = goodsGgsvalService.queryGoodsGgsvalList(map,1,1000);
        index += paginationGoodsGgsval.getData().size();
        for(int i=0; i<paginationGoodsGgsval.getData().size(); i++){
            GoodsGgsval goodsGgsval = paginationGoodsGgsval.getData().get(i);
            goodsGgsval.setIsDelete(1L);
            flag =goodsGgsvalService.updateGoodsGgsval(goodsGgsval);
            if(flag){
                status ++;
            }
        }

        Pagination<GoodsDetail> paginationGoodsDetail = goodsDetailService.queryGoodsDetailList(map,1,1000);
        index += paginationGoodsDetail.getData().size();
        for(int i=0; i<paginationGoodsDetail.getData().size(); i++){
            GoodsDetail goodsDetail = paginationGoodsDetail.getData().get(i);
            goodsDetail.setIsDelete(1L);
            flag = goodsDetailService.updateGoodsDetail(goodsDetail);
            if(flag){
                status ++;
            }
        }

        Pagination<GoodsGgsvalDetail> paginationGoodsGgsvalDetail = queryGoodsGgsvalDetailList(map,1,1000);
        index += paginationGoodsGgsvalDetail.getData().size();
        for(int i=0; i<paginationGoodsGgsvalDetail.getData().size(); i++){
            GoodsGgsvalDetail goodsGgsvalDetail = paginationGoodsGgsvalDetail.getData().get(i);
            goodsGgsvalDetail.setIsDelete(1L);
            flag = updateGoodsGgsvalDetail(goodsGgsvalDetail);
            if(flag){
                status ++;
            }
        }

	    return index==status?true:false;
    }
}

