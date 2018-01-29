package com.retailers.razz.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.razz.common.entity.PositionManage;
import com.retailers.razz.common.service.PositionManageService;
import com.retailers.razz.manage.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单列表
 * @author zhongp
 *
 */
@Controller
@RequestMapping("positionManage")
public class PositionManageController extends BaseController {
	@Autowired
	PositionManageService positionManageService;

	@RequestMapping("/positionManageMapping")
	@Menu(label="职位设置", description = "职位设置", resourse = "position.mapping",sort=2,parentRes="sys.position.manage")
	public String PositionManageMapping(){
		return "positionManage";
	}

	@RequestMapping("/queryPositionManages")
	@ResponseBody
	public Map queryPpositionManages(int pageNo,int pageSize){
		Map params = new HashMap();
		params.put("isDelete",0L);
		Pagination<PositionManage> positionManagePagination = positionManageService.queryPositionManageList(params,pageNo,pageSize);
		List<PositionManage> positionManages = positionManagePagination.getData();
		Map map = new HashMap();
		map.put("rows",positionManages);
		map.put("total",positionManagePagination.getTotalCount());
		return map;
	}

}
