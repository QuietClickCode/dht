package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsLabel;
import com.retailers.dht.common.service.GoodsLabelService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
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
public class GoodsLabelController extends BaseController {

    @Autowired
    GoodsLabelService goodsLabelService;

    @RequestMapping("/openGoodsLabel")
    @Menu(parentRes = "sys.manager.goods", resourse = "goods.openGoodsLabel", description = "商品标签管理", sort = 1, label = "商品标签管理")
    public String openGoodsLabel() {
        return "goods/goodsLabel";
    }

    @RequestMapping("editGoodsLabel")
    @Function(label = "编辑商品标签", parentRes = "goods.openGoodsLabel", resourse = "goods.editGoodsLabel", description = "编辑商品标签", sort = 2)
    @ResponseBody
    public BaseResp editGoodsLabel(GoodsLabel goodsLabel,String glStarttimes, String glEndtimes) {
        addDate(goodsLabel,glStarttimes,glEndtimes);
        boolean flag = goodsLabelService.updateGoodsLabel(goodsLabel);
        if (flag) {
            return success("修改商品标签[" + goodsLabel.getGlName() + "]成功");
        } else {
            return errorForSystem("修改商品标签[" + goodsLabel.getGlName() + "]失败");
        }
    }

    @RequestMapping("/removeGoodsLabel")
    @Function(label = "删除商品品牌", description = "删除商品品牌", resourse = "goods.removeGoodsLabel", sort = 3, parentRes = "goods.openGoodsLabel")
    @ResponseBody
    public BaseResp removeGoodsLabel(Long glId) {
        boolean flag = goodsLabelService.deleteGoodsLabelByGlId(glId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsLabelLists")
    @Function(label = "商品标签列表", description = "所有商品标签列表", resourse = "goods.queryGoodsLabelLists", sort = 1, parentRes = "goods.openGoodsLabel")
    @ResponseBody
    public Map<String, Object> queryGoodsLabelLists(String glName,String now,PageUtils pageForm) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glName", glName);
        map.put("isDelete", 0);
        if(!ObjectUtils.isEmpty(now)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date nowDate = sdf.parse(now);
                map.put("nowDate",nowDate);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Pagination<GoodsLabel> GoodsLabelPagination = goodsLabelService.queryGoodsLabelList(map, pageForm.getPageNo(), pageForm.getPageSize());
        Map<String, Object> gtm = new HashMap<String, Object>();
        gtm.put("total", GoodsLabelPagination.getTotalCount());
        gtm.put("rows", GoodsLabelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsLabel")
    @Function(label = "增加商品标签", description = "增加商品标签", resourse = "goods.addGoodsLabel", parentRes = "goods.openGoodsLabel")
    @ResponseBody
    public BaseResp addGoodsLabel(GoodsLabel goodsLabel, String glStarttimes, String glEndtimes) {
        addDate(goodsLabel,glStarttimes,glEndtimes);
        goodsLabel.setIsDelete(0L);
        boolean flag = goodsLabelService.saveGoodsLabel(goodsLabel);
        return success(flag);
    }

    public GoodsLabel addDate(GoodsLabel goodsLabel,String glStarttimes, String glEndtimes ){
        if (!ObjectUtils.isEmpty(glStarttimes) && !ObjectUtils.isEmpty(glEndtimes)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date glStarttime = sdf.parse(glStarttimes);
                Date glEndtime = sdf.parse(glEndtimes);
                goodsLabel.setGlStarttime(glStarttime);
                goodsLabel.setGlEndtime(glEndtime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return goodsLabel;
    }
}