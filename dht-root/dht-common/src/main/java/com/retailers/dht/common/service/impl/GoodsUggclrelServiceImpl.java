
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.entity.GoodsUggclrel;
import com.retailers.dht.common.dao.GoodsUggclrelMapper;
import com.retailers.dht.common.service.GoodsUggclrelService;
import com.retailers.dht.common.vo.GoodsUggclrelVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品用户评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 13:54:57
 */
@Service("goodsuggclrelService")
public class GoodsUggclrelServiceImpl implements GoodsUggclrelService {
	@Autowired
	private GoodsUggclrelMapper goodsUggclrelMapper;
	public boolean saveGoodsUggclrel(GoodsUggclrel goodsUggclrel) {
		int status = goodsUggclrelMapper.saveGoodsUggclrel(goodsUggclrel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsUggclrel(GoodsUggclrel goodsUggclrel) {
		int status = goodsUggclrelMapper.updateGoodsUggclrel(goodsUggclrel);
		return status == 1 ? true : false;
	}
	public GoodsUggclrel queryGoodsUggclrelByUggclId(Long uggclId) {
		return goodsUggclrelMapper.queryGoodsUggclrelByUggclId(uggclId);
	}

	public Pagination<GoodsUggclrel> queryGoodsUggclrelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsUggclrel> page = new Pagination<GoodsUggclrel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsUggclrel> list = goodsUggclrelMapper.queryGoodsUggclrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsUggclrelByUggclId(Long uggclId) {
		int status = goodsUggclrelMapper.deleteGoodsUggclrelByUggclId(uggclId);
		return status == 1 ? true : false;
	}
	public Pagination<GoodsUggclrelVo> queryGoodsUggclrelListVo(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<GoodsUggclrelVo> page = new Pagination<GoodsUggclrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsUggclrelVo> list = goodsUggclrelMapper.queryGoodsUggclrelListVo(page);
		for(GoodsUggclrelVo goodsUggclrelVo:list){
			goodsUggclrelVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+goodsUggclrelVo.getImgurl());
	}
		page.setData(list);
		return page;
	}
	public GoodsUggclrelVo queryGoodsclsumandavg(Long gid){
		GoodsUggclrelVo goodsUggclrelVo = goodsUggclrelMapper.queryGoodsclsumandavg(gid);
		return goodsUggclrelVo;
	}
	public List<GoodsUggclrelVo> queryHasGoodscls(Long gid){
		List<GoodsUggclrelVo> list = goodsUggclrelMapper.queryHasGoodscls(gid);
		return list;
	}
}

