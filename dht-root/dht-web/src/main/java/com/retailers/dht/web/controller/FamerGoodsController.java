package com.retailers.dht.web.controller;

import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.FamerGoods;
import com.retailers.dht.common.service.FamerGoodsService;
import com.retailers.dht.common.view.UserInfoVIew;
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
}
