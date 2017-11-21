
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.GoodsDetailMapper;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 10:51:10
 */
@Service("goodsdetailService")
public class GoodsDetailServiceImpl implements GoodsDetailService {
	@Autowired
	private GoodsDetailMapper goodsDetailMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private GoodsGgsvalDetailService goodsGgsvalDetailService;
	public GoodsDetail saveGoodsDetail(GoodsDetail goodsDetail) {
		Long gdImgid = goodsDetail.getGdImgid();
		int status = goodsDetailMapper.saveGoodsDetail(goodsDetail);
		if(!ObjectUtils.isEmpty(gdImgid)){
			if(gdImgid != -1 && status ==1){
				attachmentService.editorAttachment(gdImgid);
			}
		}

		return status == 1 ? goodsDetail : null;
	}
	public boolean updateGoodsDetail(GoodsDetail goodsDetail) {
		int status = goodsDetailMapper.updateGoodsDetail(goodsDetail);
		return status == 1 ? true : false;
	}
	public GoodsDetailVo queryGoodsDetailByGdId(Long gdId) {
		GoodsDetailVo goodsDetailVo = goodsDetailMapper.queryGoodsDetailByGdId(gdId);
		goodsDetailVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsDetailVo.getImgUrl());
		return goodsDetailVo;
	}

	public Pagination<GoodsDetail> queryGoodsDetailList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsDetail> page = new Pagination<GoodsDetail>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsDetail> list = goodsDetailMapper.queryGoodsDetailList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsDetailByGdId(Long gdId) {
		int status = goodsDetailMapper.deleteGoodsDetailByGdId(gdId);
		return status == 1 ? true : false;
	}

	public List<GoodsDetailVo> queryGoodsDetailOnce(Long gid){
		List<GoodsDetailVo> list = goodsDetailMapper.queryGoodsDetailOnce(gid);
		for(GoodsDetailVo goodsDetailVo:list){
			goodsDetailVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsDetailVo.getImgUrl());
		}
		return list;
	}

	public boolean addMyData(String mydata,Long gid){
		JSONArray jsonArray = JSON.parseArray(mydata);
		GoodsDetail goodsDetail = new GoodsDetail();
		goodsDetail.setGid(gid);
		goodsDetail.setIsDelete(0L);
		GoodsGgsvalDetail goodsGgsvalDetail = new GoodsGgsvalDetail();
		goodsGgsvalDetail.setIsDelete(0L);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String gsids = jsonObject.getString("gsid");
			String gsvalids = jsonObject.getString("gsvalid");
			Long gdImgid = jsonObject.getLong("gdImgid");
			Long gdResidueinventory = jsonObject.getLong("gdResidueinventory");
			Long gdInventory = jsonObject.getLong("gdInventory");
			Float gdCostprice = jsonObject.getFloat("gdCostprice");
			Float gdPrice = jsonObject.getFloat("gdPrice");

			goodsDetail.setGdImgid(gdImgid);
			goodsDetail.setGdResidueinventory(gdResidueinventory);
			goodsDetail.setGdInventory(gdInventory);
			goodsDetail.setGdCostprice(gdCostprice);
			goodsDetail.setGdPrice(gdPrice);
			goodsDetail = saveGoodsDetail(goodsDetail);
			System.out.println("gdid:"+goodsDetail.getGdId());
			if(!ObjectUtils.isEmpty(gsids)&&!ObjectUtils.isEmpty(gsvalids)){
				String[] gsidsArr = gsids.split("_");
				String[] gsvalidsArr = gsvalids.split("_");

				for(int j=0;j<gsidsArr.length;j++){
					Long gsidLong = Long.parseLong(gsidsArr[j]);
					Long gsvalidLong = Long.parseLong(gsvalidsArr[j]);
					goodsGgsvalDetail.setGsId(gsidLong);
					goodsGgsvalDetail.setGsvId(gsvalidLong);
					goodsGgsvalDetail.setGid(gid);
					goodsGgsvalDetail.setGdId(goodsDetail.getGdId());
					goodsGgsvalDetailService.saveGoodsGgsvalDetail(goodsGgsvalDetail);
				}
			}
		}

		return true;
	}

	public GoodsDetailVo queryGoodsDetailVoLists(Map map){
		List<GoodsDetailVo> list = goodsDetailMapper.queryGoodsDetailVoLists(map);
		if(!ObjectUtils.isEmpty(list)){
			GoodsDetailVo goodsDetailVo = list.get(0);
			goodsDetailVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsDetailVo.getImgUrl());
			return  goodsDetailVo;
		}
		return null;
	}
}

