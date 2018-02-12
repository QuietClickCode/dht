package com.retailers.dht.manage.controller;

import com.retailers.dht.common.vo.FamerImgVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.dht.common.entity.FamerImg;
import com.retailers.dht.common.service.FamerImgService;
import com.retailers.auth.annotation.Menu;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农户背景图片关联表表controller
 * @author yiliangcheng
 * @version 1.0
 * @date 2018-02-09 09:42:03
 */

@RestController
@RequestMapping("/famerImg")
public class FamerImgController extends BaseController {

	@Autowired
	private FamerImgService famerImgService;

	/**
	 *根据农户ID查询
	 */
	@RequestMapping("/queryFamerImgListByFid")
	public List<FamerImgVo> queryFamerImgListByFid(int pageNo, int pageSize, Long fid) {
		Map params = new HashMap();
		params.put("isDelete","0L");
		params.put("fid",fid);
		Pagination<FamerImgVo> pagination = famerImgService.queryFamerImgVoList(params,pageNo,pageSize);
		List<FamerImgVo> list = pagination.getData();
		return list;
	}

	/**
	 * 增加
	 */
	@RequestMapping("/saveFamerImg")
	public BaseResp saveFamerImg(FamerImg famerImg) {
		boolean b = famerImgService.saveFamerImg(famerImg);
		if (b) {
			return success("添加成功");
		} else {
			return errorForSystem("添加失败");
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/deleteFamerImg")
	public BaseResp deleteFamerImg(Long imgId) {
		Map params = new HashMap();
		params.put("isDelete","0L");
		params.put("imgId",imgId);
		Pagination<FamerImg> pagination = famerImgService.queryFamerImgList(params,1,999);
		FamerImg famerImg = pagination.getData().get(0);
		boolean b = famerImgService.deleteFamerImgByFimgId(famerImg.getFimgId());
		if (b) {
			return success("删除成功");
		} else {
			return errorForSystem("删除失败");
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping("/updateFamerImg")
	public BaseResp updateFamerImg(FamerImg famerImg) {
		boolean b =  famerImgService.updateFamerImg(famerImg);
		if (b) {
			return success("修改成功");
		} else {
			return errorForSystem("修改失败");
		}
	}

}

