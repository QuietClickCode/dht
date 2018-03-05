package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsUggclrel;
import com.retailers.dht.common.service.*;
import com.retailers.dht.common.vo.CommentVo;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *  查询评论
 */
@Controller
@RequestMapping("comment")
public class GoodsGgclrelController extends BaseController {

    @Autowired
    GoodsGgclrelService goodsGgclrelService;
    @Autowired
    GoodsUggclrelService goodsUggclrelService;
    @Autowired
    GoodsService goodsService;

    /**
     * 查询类别关联的评论标签
     * @param gid
     * @return
     */
    @RequestMapping("/queryGclassGoodsGgclrelLists")
    @ResponseBody
    public Map<String,Object> queryGclassGoodsGgclrelLists(Long gid){
        List<GoodsGgclrelVo> list = goodsGgclrelService.queryGclassGoodsGgclrelLists(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    /**
     * 查询商品关联的评论标签
     * @param gid
     * @param pageForm
     * @return
     */
    @RequestMapping("/queryGoodsGgclrelLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsGgclrelLists(Long gid,PageUtils pageForm){
        Map map = new HashMap();
        map.put("gid",gid);
        map.put("isDelete",0L);
        List<GoodsGgclrelVo> list = goodsGgclrelService.queryMyGoodsGgclrelList(map);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }


    /**
     * 查询商品的评论，展示到商品详情页面
     */
    @RequestMapping("/queryComment")
    @ResponseBody
    public List<CommentVo> queryComment(Long gid) {
        List<CommentVo> list = goodsGgclrelService.queryComment(gid);
        return list;
    }

    /**
     * 用户评价商品评论
     */
    @RequestMapping("/addComment")
    @ResponseBody
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,redirectUrl = "/loginPage",msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG,isOpenPage =true)
    public BaseResp addComment(Long gid,Long uid,Long orderNo, String gclIdStr) {
        boolean b = goodsUggclrelService.saveGoodsUggclrel(gid, uid,orderNo, gclIdStr);
        return b?success("添加成功"):errorForSystem("添加失败");
    }

    /**
     * 评论页面
     */
    @RequestMapping("/toComment")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,redirectUrl = "/loginPage",msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG,isOpenPage =true)
    public String toComment(HttpServletRequest request, HttpServletResponse response,String gid,String orderNo) {
        Long uid = getCurLoginUserId(request);
        request.getSession().setAttribute("uid",uid);
        request.getSession().setAttribute("gid",gid);
        request.getSession().setAttribute("orderNo",orderNo);

        return redirectUrl(request,"/order/appraise-order");
    }

    /**
     * 查询是否评价过
     */
    @RequestMapping("/haveComment")
    @ResponseBody
    public String haveComment(HttpServletRequest request) {
        Object uid = request.getSession().getAttribute("uid");
        Object gid = request.getSession().getAttribute("gid");
        Object orderNo = request.getSession().getAttribute("orderNo");
        Map map = new HashMap();
        map.put("uid",uid);
        map.put("gid",gid);
        map.put("orderNo",orderNo);
        Pagination pagination = goodsUggclrelService.queryGoodsUggclrelList(map, 1, 1);
        if (pagination.getData().size() == 0) {
            return "not have";
        } else {
            return "have";
        }
    }

    /**
     * 查询商品
     */
    @RequestMapping("/queryGoods")
    @ResponseBody
    public List<GoodsVo> queryGoods(Long gid) {
        Map params = new HashMap();
        List list = new ArrayList();
        list.add(gid);
        params.put("gids",list);
        List<GoodsVo> goodsVos = goodsService.queryGoodsVoByIds(params, 1, 1);
        return goodsVos;
    }

}
