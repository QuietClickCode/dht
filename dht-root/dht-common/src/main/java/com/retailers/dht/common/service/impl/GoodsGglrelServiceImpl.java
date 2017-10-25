
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGglrel;
import com.retailers.dht.common.dao.GoodsGglrelMapper;
import com.retailers.dht.common.entity.GoodsGglrelCopy;
import com.retailers.dht.common.service.GoodsGglrelCopyService;
import com.retailers.dht.common.service.GoodsGglrelService;
import com.retailers.dht.common.vo.GoodsGglrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与标签关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 14:28:44
 */
@Service("goodsgglrelService")
public class GoodsGglrelServiceImpl implements GoodsGglrelService {
	@Autowired
	private GoodsGglrelMapper goodsGglrelMapper;
	@Autowired
	private GoodsGglrelCopyService goodsGglrelCopyService;
	public boolean saveGoodsGglrel(String glIds,Long gid,Long uploadpersonId) {
		String[] glIdsArr = glIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(glIdsArr)){
			for (String glId:glIdsArr){
				Long glIdLong = Long.parseLong(glId);
				GoodsGglrel goodsGglrel = new GoodsGglrel();
				goodsGglrel.setIsDelete(0L);
				goodsGglrel.setGlId(glIdLong);
				goodsGglrel.setGid(gid);
				GoodsGglrelCopy g = new GoodsGglrelCopy();
				BeanUtils.copyProperties(goodsGglrel,g);
				g.setGglUploadpersonId(uploadpersonId);
				goodsGglrelCopyService.saveGoodsGglrelCopy(g);
				status += goodsGglrelMapper.saveGoodsGglrel(goodsGglrel);
			}
		}
		return status == glIdsArr.length ? true : false;
	}
	public boolean updateGoodsGglrel(GoodsGglrel goodsGglrel) {
		int status = goodsGglrelMapper.updateGoodsGglrel(goodsGglrel);
		return status == 1 ? true : false;
	}
	public GoodsGglrel queryGoodsGglrelByGglId(Long gglId) {
		return goodsGglrelMapper.queryGoodsGglrelByGglId(gglId);
	}

	public Pagination<GoodsGglrelVo> queryGoodsGglrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGglrelVo> page = new Pagination<GoodsGglrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGglrelVo> list = goodsGglrelMapper.queryGoodsGglrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGglrelByGglId(String gglIds,Long uploadpersonId) {
		String[] gglIdsArr = gglIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gglIdsArr)){
			for (String gglId:gglIdsArr){
				Long gglIdLong = Long.parseLong(gglId);
				GoodsGglrel goodsGglrel = goodsGglrelMapper.queryGoodsGglrelByGglId(gglIdLong);
				goodsGglrel.setIsDelete(1L);
				GoodsGglrelCopy g = new GoodsGglrelCopy();
				BeanUtils.copyProperties(goodsGglrel,g);
				g.setGglUploadpersonId(uploadpersonId);
				goodsGglrelCopyService.saveGoodsGglrelCopy(g);
				status += goodsGglrelMapper.updateGoodsGglrel(goodsGglrel);
			}
		}
		return status == gglIdsArr.length ? true : false;
	}
}

