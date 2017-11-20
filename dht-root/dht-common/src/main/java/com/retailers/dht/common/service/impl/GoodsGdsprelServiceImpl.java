
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.GoodsGdsprelMapper;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：商品与特价关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-08 17:29:16
 */
@Service("goodsgdsprelService")
public class GoodsGdsprelServiceImpl implements GoodsGdsprelService {
	@Autowired
	private GoodsGdsprelMapper goodsGdsprelMapper;
	public boolean saveGoodsGdsprel(GoodsGdsprel goodsGdsprel) {
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("gdId",goodsGdsprel.getGdId());
		Pagination<GoodsGdsprel> pagination = queryGoodsGdsprelList(params,1,100);
		if(!ObjectUtils.isEmpty(pagination.getData())){
			for(GoodsGdsprel g:pagination.getData()){
				deleteGoodsGdsprelByGdspId(g.getGdspId());
			}
		}
		int status = goodsGdsprelMapper.saveGoodsGdsprel(goodsGdsprel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGdsprel(GoodsGdsprel goodsGdsprel) {
		int status = goodsGdsprelMapper.updateGoodsGdsprel(goodsGdsprel);
		return status == 1 ? true : false;
	}
	public GoodsGdsprel queryGoodsGdsprelByGdspId(Long gdspId) {
		return goodsGdsprelMapper.queryGoodsGdsprelByGdspId(gdspId);
	}

	public Pagination<GoodsGdsprel> queryGoodsGdsprelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGdsprel> page = new Pagination<GoodsGdsprel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGdsprel> list = goodsGdsprelMapper.queryGoodsGdsprelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGdsprelByGdspId(Long gdspId) {
		int status = goodsGdsprelMapper.deleteGoodsGdsprelByGdspId(gdspId);
		return status == 1 ? true : false;
	}

	public List<GoodsGdsprelVo> queryGoodsGdsprelListsByGid(Long gid){
		List<GoodsGdsprelVo> list = goodsGdsprelMapper.queryGoodsGdsprelListsByGid(gid);
		return list;
	}

	public GoodsGdsprelVo queryGoodsGdsprelVoLists(Map<String, Object> params){
		List<GoodsGdsprelVo> list = goodsGdsprelMapper.queryGoodsGdsprelVoLists(params);
		if(!ObjectUtils.isEmpty(list)){
			GoodsGdsprelVo goodsGdsprelVo = list.get(0);
			goodsGdsprelVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+goodsGdsprelVo.getImgurl());
			return  goodsGdsprelVo;
		}
		return null;
	}
}

