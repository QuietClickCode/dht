package com.retailers.dht.manage.controller;

import com.retailers.dht.common.vo.FamerGoodsVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.dht.common.entity.FamerGoods;
import com.retailers.dht.common.service.FamerGoodsService;
import com.retailers.auth.annotation.Menu;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农户商品关联表表controller
 * @author yiliangcheng
 * @version 1.0
 * @date 2018-02-07 16:35:32
 */

@RestController
@RequestMapping("/famerGoods")
public class FamerGoodsController extends BaseController {

	@Autowired
	private FamerGoodsService famerGoodsService;

	/**
	 * 根据农户ID查询已有商品
	 */
	@RequestMapping("queryHaveGoodsListByFid")
	public List<FamerGoodsVo> queryHaveGoodsListByFid(Long fid) {
		Map params = new HashMap();
		params.put("isDelete","0L");
		params.put("fid",fid);
		Pagination<FamerGoodsVo> pagination = famerGoodsService.queryHaveGoodsListByFid(params,1,999);
		List<FamerGoodsVo> list = pagination.getData();
		return list;
	}

	/**
	 * 根据农户ID查询未有商品
	 */
	@RequestMapping("queryNotHaveGoodsListByFid")
	public List<FamerGoodsVo> queryNotHaveGoodsListByFid(Long fid,String goodsName) {
		Map params = new HashMap();
		params.put("isDelete","0L");
//		params.put("fid",fid);
		params.put("goodsName",goodsName);
		Pagination<FamerGoodsVo> pagination = famerGoodsService.queryNotHaveGoodsListByFid(params,1,999);
		List<FamerGoodsVo> list = pagination.getData();
		return list;
	}




	/**
	 * 增加
	 */
	@RequestMapping("/saveFamerGoods")
	public BaseResp saveFamerGoods(FamerGoods famerGoods) {
		boolean b = famerGoodsService.saveFamerGoods(famerGoods);
		if (b) {
			return success("添加成功");
		} else {
			return errorForSystem("添加失败");
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteFamerGoods")
	public BaseResp deleteFamerGoods(Long fgId) {
		boolean b = famerGoodsService.deleteFamerGoodsByFgId(fgId);
		if (b) {
			return success("删除成功");
		} else {
			return errorForSystem("删除失败");
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping("/updateFamerGoods")
	public BaseResp updateFamerGoods(FamerGoods famerGoods) {
		boolean b =  famerGoodsService.updateFamerGoods(famerGoods);
		if (b) {
			return success("修改成功");
		} else {
			return errorForSystem("修改失败");
		}
	}

}

