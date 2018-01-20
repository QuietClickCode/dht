
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.GoodsGdsprelMapper;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
		params.put("spId",goodsGdsprel.getSpId());
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
		GoodsGdsprel goodsGdsprel = goodsGdsprelMapper.queryGoodsGdsprelByGdspId(gdspId);
		goodsGdsprel.setIsDelete(1L);
		int status = goodsGdsprelMapper.updateGoodsGdsprel(goodsGdsprel);
		return status == 1 ? true : false;
	}

	public List<GoodsGdsprelVo> queryGoodsGdsprelListsByGid(Long gid,Long spId){
		List<GoodsGdsprelVo> list = goodsGdsprelMapper.queryGoodsGdsprelListsByGid(gid,spId);
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

	public boolean editGoodsInventorys(Map<Long,Long> goodsGdsprelMap) throws AppException{
		int status = 0;
		if(ObjectUtils.isNotEmpty(goodsGdsprelMap)){
			List<Long> gdspIds = new ArrayList<Long>();
			for (Map.Entry<Long, Long> entry : goodsGdsprelMap.entrySet()) {
				gdspIds.add(entry.getKey());
			}
			List<GoodsGdsprel> list = goodsGdsprelMapper.queryGoodsGdcprelListByGdspIds(gdspIds);
			for(GoodsGdsprel GoodsGdsprel:list){
				for (Map.Entry<Long, Long> entry : goodsGdsprelMap.entrySet()) {
					Long gdspId1 = GoodsGdsprel.getGdspId();
					Long gdspId2 = entry.getKey();
					if(gdspId1.equals(gdspId2)){
						Long reduceInventory = entry.getValue();
						GoodsGdsprel.setSpInventory(reduceInventory);
						break;
					}
				}
			}
			status = goodsGdsprelMapper.editGoodsInventorys(list);
			if(status!=goodsGdsprelMap.size()){
				throw new AppException("参数错误");
			}
		}
		return status==goodsGdsprelMap.size()?true:false;
	}

	public GoodsGdsprelVo queryGoodsGdsprelVoByGdspId(Long gdspId){
		GoodsGdsprelVo goodsGdsprelVo = goodsGdsprelMapper.queryGoodsGdsprelVoByGdspId(gdspId);
		return goodsGdsprelVo;
	}
}

