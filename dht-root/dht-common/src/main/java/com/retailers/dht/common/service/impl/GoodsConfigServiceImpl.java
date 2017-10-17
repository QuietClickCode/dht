
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsConfig;
import com.retailers.dht.common.dao.GoodsConfigMapper;
import com.retailers.dht.common.service.GoodsConfigService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品配置表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 10:37:31
 */
@Service("goodsconfigService")
public class GoodsConfigServiceImpl implements GoodsConfigService {
	@Autowired
	private GoodsConfigMapper goodsConfigMapper;
	public boolean saveGoodsConfig(GoodsConfig goodsConfig) {
		int status = goodsConfigMapper.saveGoodsConfig(goodsConfig);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsConfig(GoodsConfig goodsConfig) {
		int status = 0;
		if(ObjectUtils.isEmpty(goodsConfig.getGcId())){
			status = goodsConfigMapper.saveGoodsConfig(goodsConfig);
		}else{
			System.out.println(goodsConfig.getIsServicegoods());
			status = goodsConfigMapper.updateGoodsConfig(goodsConfig);
		}
		return status == 1 ? true : false;
	}
	public GoodsConfig queryGoodsConfigByGcId(Long gcId) {
		return goodsConfigMapper.queryGoodsConfigByGcId(gcId);
	}

	public Pagination<GoodsConfig> queryGoodsConfigList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsConfig> page = new Pagination<GoodsConfig>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsConfig> list = goodsConfigMapper.queryGoodsConfigList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsConfigByGcId(Long gcId) {
		GoodsConfig goodsConfig = goodsConfigMapper.queryGoodsConfigByGcId(gcId);
		goodsConfig.setIsDelete(1L);
		int status = goodsConfigMapper.updateGoodsConfig(goodsConfig);
		return status == 1 ? true : false;
	}
}

