
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.GoodsDetailMapper;
import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsDetailCopy;
import com.retailers.dht.common.entity.GoodsGgsvalDetail;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsDetailCopyService;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsGgsvalDetailService;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
	@Autowired
	private GoodsDetailCopyService goodsDetailCopyService;
	public GoodsDetail saveGoodsDetail(GoodsDetail goodsDetail,Long uploadpersonId) {
		Long gdImgid = goodsDetail.getGdImgid();
		int status = goodsDetailMapper.saveGoodsDetail(goodsDetail);
		if(!ObjectUtils.isEmpty(gdImgid)){
			if(gdImgid != -1 && status ==1){
				attachmentService.editorAttachment(gdImgid);
			}
		}
		copyGoodsDetail(goodsDetail,status,uploadpersonId);
		return status == 1 ? goodsDetail : null;
	}
	public boolean updateGoodsDetail(GoodsDetail goodsDetail,Long uploadpersonId) {
		int status = goodsDetailMapper.updateGoodsDetail(goodsDetail);
		copyGoodsDetail(goodsDetail,status,uploadpersonId);
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
	public boolean deleteGoodsDetailByGdId(Long gdId,Long uploadpersonId) {
		GoodsDetail goodsDetail = queryGoodsDetailByGdId(gdId);
		goodsDetail.setIsDelete(1L);
		return updateGoodsDetail(goodsDetail,uploadpersonId);
	}

	public List<GoodsDetailVo> queryGoodsDetailOnce(Long gid){
		List<GoodsDetailVo> list = goodsDetailMapper.queryGoodsDetailOnce(gid);
		for(GoodsDetailVo goodsDetailVo:list){
			goodsDetailVo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+goodsDetailVo.getImgUrl());
		}
		return list;
	}

	public boolean addMyData(String mydata,Long gid,Long uploadpersonId){
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
			Long gdCostprice = jsonObject.getLong("gdCostprice");
			Long gdPrice = jsonObject.getLong("gdPrice");

			goodsDetail.setGdImgid(gdImgid);
			goodsDetail.setGdResidueinventory(gdResidueinventory);
			goodsDetail.setGdInventory(gdInventory);
			goodsDetail.setGdCostprice(gdCostprice);
			goodsDetail.setGdPrice(gdPrice);
			goodsDetail = saveGoodsDetail(goodsDetail,uploadpersonId);

			if(!ObjectUtils.isEmpty(gsids)&&!ObjectUtils.isEmpty(gsvalids)){
				String[] gsidsArr = gsids.split("_");
				String[] gsvalidsArr = gsvalids.split("_");
				List<GoodsGgsvalDetail> list = new ArrayList<GoodsGgsvalDetail>();
				for(int j=0;j<gsidsArr.length;j++){
					Long gsidLong = Long.parseLong(gsidsArr[j]);
					Long gsvalidLong = Long.parseLong(gsvalidsArr[j]);

					goodsGgsvalDetail.setGsId(gsidLong);
					goodsGgsvalDetail.setGsvId(gsvalidLong);

					System.out.println("gsidLong:"+gsidLong+",gsvalidLong:"+gsvalidLong);

					goodsGgsvalDetail.setGid(gid);
					goodsGgsvalDetail.setGdId(goodsDetail.getGdId());
					goodsGgsvalDetailService.saveGoodsGgsvalDetail(goodsGgsvalDetail);
				}
//				goodsGgsvalDetailService.saveGoodsGgsvalDetails(list);
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
	public List<GoodsDetailVo> queryGoodsDetailByGdIds(String gdIds){
		String[] gdIdsArr = gdIds.split(",");
		if(!ObjectUtils.isEmpty(gdIds)){
			List<Long> gdIdsList = new ArrayList<Long>();
			for(String gdId:gdIdsArr){
				Long gdIdLong = Long.parseLong(gdId);
				gdIdsList.add(gdIdLong);
			}
			List<GoodsDetailVo> list = goodsDetailMapper.queryGoodsDetailByGdIds(gdIdsList);
			return list;
		}
		return null;
	}

	public void copyGoodsDetail(GoodsDetail goodsDetail,int status,Long uploadpersonId){
		if(status==1){
			GoodsDetailCopy goodsDetailCopy = new GoodsDetailCopy();
			BeanUtils.copyProperties(goodsDetail,goodsDetailCopy);

			goodsDetailCopy.setCreateTime(new Date());
			goodsDetailCopy.setGdUploadpersionId(uploadpersonId);
			goodsDetailCopyService.saveGoodsDetailCopy(goodsDetailCopy);
		}
	}
	public boolean deleteGoodsDetailByGid(Long gid){
		int status = goodsDetailMapper.deleteGoodsDetailByGid(gid);
		return status>0 ? true:false;
	}
}

