
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsCgfrel;
import com.retailers.dht.common.dao.GoodsCgfrelMapper;
import com.retailers.dht.common.service.GoodsCgfrelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：地区与运费关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 10:49:29
 */
@Service("goodscgfrelService")
public class GoodsCgfrelServiceImpl implements GoodsCgfrelService {
	@Autowired
	private GoodsCgfrelMapper goodsCgfrelMapper;
	public boolean saveGoodsCgfrel(String cids,Long gfId) {
		Map map = new HashMap();
		map.put("gfId",gfId);
		map.put("isDelete",0L);
		Pagination<GoodsCgfrel> pagination = queryGoodsCgfrelList(map,1,50);
		for(GoodsCgfrel g:pagination.getData()){
			g.setIsDelete(1L);
			goodsCgfrelMapper.updateGoodsCgfrel(g);
		}
		int status = 0;
		GoodsCgfrel goodsCgfrel = new GoodsCgfrel();
		String[] cidArr = cids.split(",");
		for(int i=0; i<cidArr.length; i++){
			Long cidLong = Long.parseLong(cidArr[i]);
			goodsCgfrel.setIsDelete(0L);
			goodsCgfrel.setCid(cidLong);
			goodsCgfrel.setGfId(gfId);
			status += goodsCgfrelMapper.saveGoodsCgfrel(goodsCgfrel);
		}

		return status == cidArr.length ? true : false;
	}
	public boolean updateGoodsCgfrel(GoodsCgfrel goodsCgfrel) {
		int status = goodsCgfrelMapper.updateGoodsCgfrel(goodsCgfrel);
		return status == 1 ? true : false;
	}
	public GoodsCgfrel queryGoodsCgfrelByCgfId(Long cgfId) {
		return goodsCgfrelMapper.queryGoodsCgfrelByCgfId(cgfId);
	}

	public Pagination<GoodsCgfrel> queryGoodsCgfrelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsCgfrel> page = new Pagination<GoodsCgfrel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCgfrel> list = goodsCgfrelMapper.queryGoodsCgfrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCgfrelByCgfId(Long cgfId) {
		int status = goodsCgfrelMapper.deleteGoodsCgfrelByCgfId(cgfId);
		return status == 1 ? true : false;
	}
}

