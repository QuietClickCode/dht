package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/{id}.html")
    public String service(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getFinalReturnString(id,"goods","goods",request);
    }


    @RequestMapping("/queryGoodsById")
    @ResponseBody
    public  Map<String,Object> queryGoodsById(Long gid,Long isShow,Long isChecked){
        Map params = new HashMap();
        params.put("gid",gid);
        params.put("isDelete",0L);
        params.put("isShow",isShow);
        params.put("isChecked",isChecked);
        Pagination<GoodsVo> pagination = goodsService.queryGoodsList(params,1,1);
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(pagination.getData())){
            gtm.put("row",pagination.getData().get(0));
        }
        return gtm;
    }

    public String getFinalReturnString(String id,String controllerMapping,String page,HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        String path="";
        String[] arr = id.split("~");
        if(!ObjectUtils.isEmpty(uid)){
            try {
                String encryuid = DESUtils.encryptDES(uid.toString(), DesKey.WEB_KEY);
                if(arr.length==1){
                    path = "redirect:/"+controllerMapping+"/"+id+"~inviter_"+encryuid+".html";
                    return path;
                }else{
                    String ivr = id.split("_")[1];
                    ivr = DESUtils.decryptDES(ivr, DesKey.WEB_KEY);
                    if(!ivr.equals(uid.toString())){
                        Long ivrLong = Long.parseLong(ivr);
                        setShareUserId(request,ivrLong);
                        String gidstr = id.split("~")[0];
                        path = "redirect:/"+controllerMapping+"/"+gidstr+"~inviter_"+uid+".html";
                        return path;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return redirectUrl(request,controllerMapping+"/"+page);

    }
}
