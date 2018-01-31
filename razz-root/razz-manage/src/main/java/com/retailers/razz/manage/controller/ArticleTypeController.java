package com.retailers.razz.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.razz.common.entity.ArticleType;
import com.retailers.razz.common.service.ArticleTypeService;
import com.retailers.razz.common.vo.ArticleTypeVo;
import com.retailers.razz.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2018/1/30.
 */
@Controller
@RequestMapping("article")
public class ArticleTypeController extends BaseController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("/articleTypeMapping")
    @Menu(label="文章类别", description = "文章类别", resourse = "article.articleTypeMapping",sort=2,parentRes="sys.manager.article")
    public String PositionManageMapping(){
        return "/article/articleType";
    }

    @RequestMapping("/queryArticleTypeList")
    @ResponseBody
    public Map<String,Object> queryArticleTypeList(){
        List<ArticleTypeVo> articleTypeVos = articleTypeService.queryArticleTypeTree();
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("total",1000);
        map.put("rows",articleTypeVos);
        return map;
    }

    @RequestMapping("/addArticleType")
    @ResponseBody
    public BaseResp addArticleType(ArticleType type){
        boolean flag = articleTypeService.saveArticleType(type);
        if(flag){
            return success("添加成功");
        }else{
            return errorForSystem("添加失败");
        }
    }
}
