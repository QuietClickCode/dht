
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.FamerGoods;
import com.retailers.dht.common.dao.FamerGoodsMapper;
import com.retailers.dht.common.service.FamerGoodsService;
import com.retailers.dht.common.vo.FamerGoodsVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：农户商品关联表表Service
 * @author yiliangcheng
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 16:35:32
 */
@Service("famergoodsService")
public class FamerGoodsServiceImpl implements FamerGoodsService {
	@Autowired
	private FamerGoodsMapper famerGoodsMapper;

	public boolean saveFamerGoods(FamerGoods famerGoods) {
		famerGoods.setIsDelete(0L);
		int status = famerGoodsMapper.saveFamerGoods(famerGoods);
		return status == 1 ? true : false;
	}

	public boolean updateFamerGoods(FamerGoods famerGoods) {
		int status = famerGoodsMapper.updateFamerGoods(famerGoods);
		return status == 1 ? true : false;
	}

	public FamerGoods queryFamerGoodsByFgId(Long fgId) {
		return famerGoodsMapper.queryFamerGoodsByFgId(fgId);
	}

	public Pagination<FamerGoods> queryFamerGoodsList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FamerGoods> page = new Pagination<FamerGoods>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerGoods> list = famerGoodsMapper.queryFamerGoodsList(page);
		page.setData(list);
		return page;
	}

	public Pagination<FamerGoodsVo> queryHaveGoodsListByFid(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<FamerGoodsVo> page = new Pagination<FamerGoodsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerGoodsVo> list = famerGoodsMapper.queryHaveGoodsListByFid(page);
		page.setData(list);
		return page;
	}

	public Pagination<FamerGoodsVo> queryNotHaveGoodsListByFid(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<FamerGoodsVo> page = new Pagination<FamerGoodsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FamerGoodsVo> list = famerGoodsMapper.queryNotHaveGoodsListByFid(page);
		page.setData(list);
		return page;
	}

	public boolean deleteFamerGoodsByFgId(Long fgId) {
		FamerGoods famerGoods = famerGoodsMapper.queryFamerGoodsByFgId(fgId);
		famerGoods.setIsDelete(1l);
		int status = famerGoodsMapper.updateFamerGoods(famerGoods);
		return status == 1 ? true : false;
	}
}

