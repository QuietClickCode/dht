
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGtgclrel;
import com.retailers.dht.common.dao.GoodsGtgclrelMapper;
import com.retailers.dht.common.service.GoodsCommentlabelService;
import com.retailers.dht.common.service.GoodsGtgclrelService;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.dht.common.vo.GoodsGtgclrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：大类与商品评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 10:54:33
 */
@Service("goodsgtgclrelService")
public class GoodsGtgclrelServiceImpl implements GoodsGtgclrelService {
	@Autowired
	private GoodsGtgclrelMapper goodsGtgclrelMapper;
	@Autowired
	private GoodsCommentlabelService goodsCommentlabelService;
	public boolean saveGoodsGtgclrel(String gclIds,Long gtId) {
		int status = 0;
		GoodsGtgclrel goodsGtgclrel = new GoodsGtgclrel();
		String[] gclIdArr = gclIds.split(",");
		for(int i=0; i<gclIdArr.length; i++){
			Long gclId = Long.parseLong(gclIdArr[i]);
			goodsGtgclrel.setIsDelete(0L);
			goodsGtgclrel.setGtId(gtId);
			goodsGtgclrel.setGclId(gclId);
			status += goodsGtgclrelMapper.saveGoodsGtgclrel(goodsGtgclrel);
		}
		return status == gclIdArr.length ? true : false;
	}
	public boolean updateGoodsGtgclrel(GoodsGtgclrel goodsGtgclrel) {
		int status = goodsGtgclrelMapper.updateGoodsGtgclrel(goodsGtgclrel);
		return status == 1 ? true : false;
	}
	public GoodsGtgclrel queryGoodsGtgclrelByGtgclId(Long gtgclId) {
		return goodsGtgclrelMapper.queryGoodsGtgclrelByGtgclId(gtgclId);
	}

	public List<GoodsGtgclrelVo> queryGoodsGtgclrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGtgclrel> page = new Pagination<GoodsGtgclrel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGtgclrel> list = goodsGtgclrelMapper.queryGoodsGtgclrelList(page);
		page.setData(list);
		List<GoodsGtgclrelVo> personList = new ArrayList<GoodsGtgclrelVo>();
		for(GoodsGtgclrel g:list){
			GoodsGtgclrelVo goodsGtgclrelVo = new GoodsGtgclrelVo();
			Map map = new HashMap();
			map.put("isDelete",0L);
			map.put("gclId",g.getGclId());
			Pagination<GoodsGgclrelVo> pagination = goodsCommentlabelService.queryGoodsCommentlabelList(map,1,2);
			if(!ObjectUtils.isEmpty(pagination.getData())){
				BeanUtils.copyProperties(pagination.getData().get(0),goodsGtgclrelVo);
				goodsGtgclrelVo.setGtgclId(g.getGtgclId());
				personList.add(goodsGtgclrelVo);
			}
		}
		return personList;
	}
	public boolean deleteGoodsGtgclrelByGtgclId(String gtgclIds) {

		String[] gtgclIdArr = gtgclIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		for (int i=0; i<gtgclIdArr.length; i++){
			Long gtgclLong = Long.parseLong(gtgclIdArr[i]);
			GoodsGtgclrel goodsGtgclrel = goodsGtgclrelMapper.queryGoodsGtgclrelByGtgclId(gtgclLong);
			goodsGtgclrel.setIsDelete(1L);
			status += goodsGtgclrelMapper.updateGoodsGtgclrel(goodsGtgclrel);
		}
		return status == gtgclIdArr.length ? true : false;
	}
}

