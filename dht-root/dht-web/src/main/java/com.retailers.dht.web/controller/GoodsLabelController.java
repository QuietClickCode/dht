package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsLabel;
import com.retailers.dht.common.service.GoodsLabelService;
import com.retailers.dht.common.vo.GoodsLabelVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goodsLabel")
public class GoodsLabelController extends BaseController {

    @Autowired
    GoodsLabelService goodsLabelService;

    @RequestMapping("/{id}.html")
    public String seckillp(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return redirectUrl(request,"glpdt/glpdt");
    }

    @RequestMapping("/queryGoodsLabelLists")
    @ResponseBody
    public Map<String, Object> queryGoodsLabelLists(String glName,Long glId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glNameeq", glName);
        map.put("glId", glId);
        map.put("isDelete", 0);
        Pagination<GoodsLabel> GoodsLabelPagination = goodsLabelService.queryGoodsLabelList(map,1,1);
        Map<String, Object> gtm = new HashMap<String, Object>();
        gtm.put("total", GoodsLabelPagination.getTotalCount());
        if(!ObjectUtils.isEmpty(GoodsLabelPagination.getData())){
            gtm.put("row", GoodsLabelPagination.getData().get(0));
        }
        return gtm;
    }

    @RequestMapping("/queryGoodsListsByGoodsLabel")
    @ResponseBody
    public Map<String, Object> queryGoodsListsByGoodsLabel(Long glId,Integer pageNo,Integer pageSize) {
        List<GoodsVo> list = goodsLabelService.queryGoodsListsByGoodsLabel(glId,pageNo,pageSize);
        Map<String, Object> gtm = new HashMap<String, Object>();
        if(!ObjectUtils.isEmpty(list)){
            gtm.put("rows", list);
        }
        return gtm;
    }
}