
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsIsbuycp;
import com.retailers.dht.common.dao.GoodsIsbuycpMapper;
import com.retailers.dht.common.service.GoodsIsbuycpService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：是否购买砍价商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-09 10:10:14
 */
@Service("goodsisbuycpService")
public class GoodsIsbuycpServiceImpl implements GoodsIsbuycpService {
	@Autowired
	private GoodsIsbuycpMapper goodsIsbuycpMapper;
	public boolean saveGoodsIsbuycp(GoodsIsbuycp goodsIsbuycp) {
		int status = goodsIsbuycpMapper.saveGoodsIsbuycp(goodsIsbuycp);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsIsbuycp(GoodsIsbuycp goodsIsbuycp) {
		int status = goodsIsbuycpMapper.updateGoodsIsbuycp(goodsIsbuycp);
		return status == 1 ? true : false;
	}
	public GoodsIsbuycp queryGoodsIsbuycpByIbcp(Long ibcp) {
		return goodsIsbuycpMapper.queryGoodsIsbuycpByIbcp(ibcp);
	}

	public Pagination<GoodsIsbuycp> queryGoodsIsbuycpList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsIsbuycp> page = new Pagination<GoodsIsbuycp>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsIsbuycp> list = goodsIsbuycpMapper.queryGoodsIsbuycpList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsIsbuycpByIbcp(Long ibcp) {
		int status = goodsIsbuycpMapper.deleteGoodsIsbuycpByIbcp(ibcp);
		return status == 1 ? true : false;
	}
	public boolean queryIsBuycpByGdcpId(Long gdcpId,Long uid){
		GoodsIsbuycp goodsIsbuycp = goodsIsbuycpMapper.queryIsBuycpByGdcpId(gdcpId,uid);
		if(ObjectUtils.isEmpty(goodsIsbuycp)){
			return true;
		}else{
			return  false;
		}
	}
}

