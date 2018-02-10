package com.retailers.dht.web.controller;

import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.FamerGoods;
import com.retailers.dht.common.service.FamerGoodsService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.view.UserInfoVIew;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("famerGoods")
public class FamerGoodsController extends BaseController {

    @Autowired
    FamerGoodsService famerGoodsService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("queryIsysjq")
    @ResponseBody
    public Map queryIsysjq(Long gid){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("gid",gid);
        List<FamerGoods> famerGoodss = famerGoodsService.queryFamerGoodsList(params,1,1).getData();
        Map map = new HashMap();
        map.put("rows",famerGoodss);
        return map;
    }

    @RequestMapping("queryFamerGoodsGoodsVoList")
    @ResponseBody
    public Map queryFamerGoodsGoodsVoList(Long famerid){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("fid",famerid);
        List<FamerGoods> famerGoodss = famerGoodsService.queryFamerGoodsList(params,1,999).getData();
        List<Long> gids = new ArrayList<Long>();
        if(ObjectUtils.isNotEmpty(famerGoodss)){
            for(FamerGoods famerGoods:famerGoodss){
                gids.add(famerGoods.getGid());
            }
        }
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(gids)){
            Map params1 = new HashMap();
            params1.put("gids",gids);
            List<GoodsVo> goodsVos = goodsService.queryGoodsVoByIds(params1,1,999);
            map.put("rows",goodsVos);
        }
        return map;
    }
}
