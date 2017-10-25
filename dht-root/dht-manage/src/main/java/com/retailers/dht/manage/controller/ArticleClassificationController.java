package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.ArticleClassification;
import com.retailers.dht.common.service.ArticleClassificationService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/25.
 */
@Controller
@RequestMapping("openArticleClassification")
public class ArticleClassificationController extends BaseController {
    @Autowired
    ArticleClassificationService classificationService;

    @RequestMapping("/articleClassificationMapping")
    @Menu(parentRes = "sys.manager.articleManage",resourse = "openArticleClassification.articleClassificationMapping",description = "文章分类",label = "文章分类")
    public String homeAdvertisingMapping(){
        return "articleManage/articleClassification";
    }

    @RequestMapping("/addArticleClassify")
    @Function(label = "添加文章分类",description = "添加文章分类",resourse = "openArticleClassification.addArticleClassify",sort = 3,parentRes = "openArticleClassification.articleClassificationMapping")
    @ResponseBody
    public BaseResp addArticleClassify(ArticleClassification classification){
        boolean flag = classificationService.saveArticleClassification(classification);
        if(flag)
            return success("新增楼层成功");
        else
            return success("新增楼层失败");
    }

    @RequestMapping("/updateArticleClassify")
    @Function(label = "修改文章分类",description = "修改文章分类",resourse = "openArticleClassification.updateArticleClassify",sort = 3,parentRes = "openArticleClassification.articleClassificationMapping")
    @ResponseBody
    public BaseResp updateNavigatorBar(ArticleClassification classification){
        boolean flag = classificationService.updateArticleClassification(classification);
        if(flag)
            return success("修改首页导航[" + classification.getAname() + "]成功");
        else
            return errorForSystem("修改首页导航[" + classification.getAname() + "]失败");
    }

    @RequestMapping("/deleteArticleClassify")
    @Function(label = "删除首页广告",description = "删除首页广告",resourse = "openArticleClassification.deleteArticleClassify",sort = 3,parentRes = "openArticleClassification.articleClassificationMapping")
    @ResponseBody
    public BaseResp deleteArticleClassify(Long aid){
        boolean flag = classificationService.deleteArticleClassificationByAid(aid);
        return  success(flag);
    }

    @RequestMapping("/queryArticleClassifyList")
    @Function(label="首页广告集合", description = "首页广告集合", resourse = "openArticleClassification.queryArticleClassifyList",sort=1,parentRes="openArticleClassification.articleClassificationMapping")
    @ResponseBody
    public Map<String,Object> queryArticleClassifyList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<ArticleClassification> advertisingPagination = classificationService.queryArticleClassificationList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getTotalCount());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }
}
