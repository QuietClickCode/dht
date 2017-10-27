package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsComplimentary;
import com.retailers.dht.common.service.GoodsComplimentaryService;
import com.retailers.dht.common.vo.GoodsComplimentaryVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsComplimentaryController extends BaseController {

    @Autowired
    GoodsComplimentaryService goodsComplimentaryService;

    @RequestMapping("/openGoodsComplimentary")
    @Menu(parentRes = "sys.manager.goods", resourse = "goods.openGoodsComplimentary", description = "赠品管理", sort = 1, label = "赠品管理")
    public String openGoodsComplimentary() {
        return "goods/goodsComplimentary";
    }

    @RequestMapping("editGoodsComplimentary")
    @Function(label = "编辑赠品", parentRes = "goods.openGoodsComplimentary", resourse = "goods.editGoodsComplimentary", description = "编辑赠品", sort = 2)
    @ResponseBody
    public BaseResp editGoodsComplimentary(GoodsComplimentary goodsComplimentary) {
        boolean flag = goodsComplimentaryService.updateGoodsComplimentary(goodsComplimentary);
        if (flag) {
            return success("修改赠品[" + goodsComplimentary.getGcName() + "]成功");
        } else {
            return errorForSystem("修改赠品[" + goodsComplimentary.getGcName() + "]失败");
        }
    }

    @RequestMapping("/removeGoodsComplimentary")
    @Function(label = "删除赠品", description = "删除赠品", resourse = "goods.removeGoodsComplimentary", sort = 3, parentRes = "goods.openGoodsComplimentary")
    @ResponseBody
    public BaseResp removeGoodsComplimentary(Long gcId) {
        boolean flag = goodsComplimentaryService.deleteGoodsComplimentaryByGcId(gcId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsComplimentaryLists")
    @Function(label = "赠品列表", description = "所有赠品列表", resourse = "goods.queryGoodsComplimentaryLists", sort = 1, parentRes = "goods.openGoodsComplimentary")
    @ResponseBody
    public Map<String, Object> queryGoodsComplimentaryLists(String gcName,PageUtils pageForm) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gcName", gcName);
        map.put("isDelete", 0);
        Pagination<GoodsComplimentaryVo> GoodsComplimentaryPagination = goodsComplimentaryService.queryGoodsComplimentaryList(map, pageForm.getPageNo(), pageForm.getPageSize());
        Map<String, Object> gtm = new HashMap<String, Object>();
        gtm.put("total", GoodsComplimentaryPagination.getTotalCount());
        gtm.put("rows", GoodsComplimentaryPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsComplimentary")
    @Function(label = "增加赠品", description = "增加赠品", resourse = "goods.addGoodsComplimentary", parentRes = "goods.openGoodsComplimentary")
    @ResponseBody
    public BaseResp addGoodsComplimentary(GoodsComplimentary goodsComplimentary) {
        goodsComplimentary.setIsDelete(0L);
        boolean flag = goodsComplimentaryService.saveGoodsComplimentary(goodsComplimentary);
        return success(flag);
    }


}