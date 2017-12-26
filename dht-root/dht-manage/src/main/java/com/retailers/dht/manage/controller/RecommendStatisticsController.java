package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.service.RecommendStatisticsService;
import com.retailers.dht.common.vo.RecommendStatisticsVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 推广人员业绩
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/27
 */
@Controller
@RequestMapping("recommend")
public class RecommendStatisticsController extends BaseController {
    @Autowired
    private RecommendStatisticsService recommendStatisticsService;

    /**
     * 打开推广人员例表
     * @return
     */
    @RequestMapping("openRecommendPage")
    @Menu(label = "推广业绩",description = "推广业绩",resourse = "recommend.openRecommendPage",parentRes = "sys.manager.customer",sort =3)
    public String openPopularizePage(){
        return "customer/recommend";
    }

    /**
     * 推广业绩列表
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param phone 推广人手机号
     * @param type 统计类型
     * @param userType 推广人类型
     * @param orderNo 订单号
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryRecommendLists")
    @Function(label="推广业绩列表", description = "推广业绩列表", resourse = "recommend.queryRecommendLists",parentRes="recommend.openRecommendPage",sort=1)
    @ResponseBody
    public Map<String,Object> queryRecommendLists(String startDate, String endDate, String phone,Long type, Long userType,String orderNo, PageUtils pageForm){

        Map<String,Object> params=new HashMap<String, Object>();
        List<Long> typs=new ArrayList<Long>();
        if(ObjectUtils.isNotEmpty(endDate)){
            try{
                params.put("endDate",DateUtil.addDay(endDate,1));
            }catch(Exception e){
                e.printStackTrace();;
            }
        }
        params.put("startDate",startDate);
        params.put("phone",phone);
        params.put("type",type);
        params.put("userType",userType);
        params.put("orderNo",orderNo);
        params.put("isDelete",0);
        Pagination<RecommendStatisticsVo> pages =recommendStatisticsService.queryRecommendLists(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }
}
