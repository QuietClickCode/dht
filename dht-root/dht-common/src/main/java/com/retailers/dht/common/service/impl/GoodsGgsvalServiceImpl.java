
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGgsval;
import com.retailers.dht.common.dao.GoodsGgsvalMapper;
import com.retailers.dht.common.service.GoodsGgsvalService;
import com.retailers.dht.common.vo.GoodsGgsvalOnceVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与规格值关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 09:54:38
 */
@Service("goodsggsvalService")
public class GoodsGgsvalServiceImpl implements GoodsGgsvalService {
	@Autowired
	private GoodsGgsvalMapper goodsGgsvalMapper;
	public boolean saveGoodsGgsval(GoodsGgsval goodsGgsval) {
		int status = goodsGgsvalMapper.saveGoodsGgsval(goodsGgsval);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGgsval(GoodsGgsval goodsGgsval) {
		int status = goodsGgsvalMapper.updateGoodsGgsval(goodsGgsval);
		return status == 1 ? true : false;
	}
	public GoodsGgsval queryGoodsGgsvalByGgsId(Long ggsId) {
		return goodsGgsvalMapper.queryGoodsGgsvalByGgsId(ggsId);
	}

	public Pagination<GoodsGgsval> queryGoodsGgsvalList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGgsval> page = new Pagination<GoodsGgsval>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgsval> list = goodsGgsvalMapper.queryGoodsGgsvalList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgsvalByGgsId(Long ggsId) {
		int status = goodsGgsvalMapper.deleteGoodsGgsvalByGgsId(ggsId);
		return status == 1 ? true : false;
	}

	public List<GoodsGgsvalOnceVo> queryGgsrelListsOnce(Long gid){
		List<GoodsGgsvalOnceVo> list = goodsGgsvalMapper.queryGgsrelListsOnce(gid);
		return list;
	}

	public boolean addGoodsGgsvalByMyData(String gsIds,String gsvalIds,Long gid){
		String[] gsIdsArr = gsIds.split("_");
		String[] gsvalIdsArr = gsvalIds.split("_");
		GoodsGgsval goodsGgsval = new GoodsGgsval();
		goodsGgsval.setIsDelete(0L);
		goodsGgsval.setGid(gid);
		int status = 0;
		for(int i=0;i<gsIdsArr.length;i++){
			Long gsIdLong = Long.parseLong(gsIdsArr[i]);
			Long gsvalIdLong = Long.parseLong(gsvalIdsArr[i]);
			goodsGgsval.setGsId(gsIdLong);
			goodsGgsval.setGsvId(gsvalIdLong);
			status += goodsGgsvalMapper.saveGoodsGgsval(goodsGgsval);
		}

		return status==gsIdsArr.length?true:false;
	}
	public boolean deleteGoodsGgsvalByGid(Long gid){
		int status = goodsGgsvalMapper.deleteGoodsGgsvalByGid(gid);
		return status>0?true:false;
	}
}

