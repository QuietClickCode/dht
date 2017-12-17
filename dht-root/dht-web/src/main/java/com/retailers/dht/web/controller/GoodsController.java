package com.retailers.dht.web.controller;

import com.retailers.dht.common.constant.SystemConstant;
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
@RequestMapping("goods")
public class GoodsController extends BaseController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/{id}.html")
    public String service(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getFinalReturnString(id,"goods","goods",request);
    }
    @RequestMapping("/goodsList/{condition}~{gclass}.html")
    public String goodsList(HttpServletRequest request, @PathVariable("condition")String condition,@PathVariable("gclass")Long gclass){

        return redirectUrl(request,"goods/goods-list");
    }

    @RequestMapping("/setinviter")
    @ResponseBody
    public  String setinviter(HttpServletRequest request){
        UserInfoVIew u = new UserInfoVIew();
        u.setUid(11L);
        setCurLoginUser(request,u);
        return "";
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

    @RequestMapping("/querySamegclassGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsById(Long gid,int pageNo,int pageSize){
        System.out.println(gid);
        Map params = new HashMap();
        params.put("gid",gid);
        Pagination<GoodsVo> pagination = goodsService.querySamegclassGoods(params,pageNo,pageSize);
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(pagination.getData())){
            gtm.put("rows",pagination.getData());
        }
        return gtm;
    }

    /**
     * 根据条件或者所选子类查询商品
     * @param condition
     * @param gclass
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryGoodsList/queryGoodsList")
    @ResponseBody
    public BaseResp queryGoodsList( String condition, Long gclass, int pageNo, int pageSize){
        List<GoodsVo> list = new ArrayList<GoodsVo>();
        if(!ObjectUtils.isEmpty(condition)){
            //根据输入的条件来查询商品
        }else if(!ObjectUtils.isEmpty(gclass)){
            Map params = new HashMap();
            params.put("gclass",gclass);
            list = goodsService.queryGoodsListByGclass(params,pageNo,pageSize);
        }else{
            return null;
        }
        return success(list);
    }

    public String getFinalReturnString(String id,String controllerMapping,String page,HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        String path="";
        String[] arr = id.split("~");
        try {
        if(!ObjectUtils.isEmpty(uid)){
                String encryuid = DESUtils.encryptDES(uid.toString(), DesKey.WEB_KEY);
                encryuid = URLEncoder.encode(encryuid, SystemConstant.DEFAUT_CHARSET);
                String randStr = DESUtils.encryptDES(StringUtils.formate(""+uid,System.currentTimeMillis()+""),DesKey.WEB_KEY);
                randStr = URLEncoder.encode(randStr,SystemConstant.DEFAUT_CHARSET);
                if(arr.length==1){
                    path = "redirect:/"+controllerMapping+"/"+id+"~inviter_"+encryuid+".html?randStr="+randStr;
                    return path;
                }else{
                    String ivr = id.split("_")[1];
                    ivr = URLDecoder.decode(ivr,SystemConstant.DEFAUT_CHARSET);
                    ivr = DESUtils.decryptDES(ivr, DesKey.WEB_KEY);
                    if(!ivr.equals(uid.toString())){
                        Long ivrLong = Long.parseLong(ivr);
                        setShareUserId(request,ivrLong);
                        String gidstr = id.split("~")[0];
                        path = "redirect:/"+controllerMapping+"/"+gidstr+"~inviter_"+encryuid+".html?randStr="+randStr;
                        return path;
                    }
                }
        }else{
            if(arr.length==2){
                String ivr = id.split("_")[1];
                ivr = URLDecoder.decode(ivr,SystemConstant.DEFAUT_CHARSET);
                ivr = DESUtils.decryptDES(ivr, DesKey.WEB_KEY);
                Long ivrLong = Long.parseLong(ivr);
                setShareUserId(request,ivrLong);
                String gidstr = id.split("~")[0];
                path = "redirect:/"+controllerMapping+"/"+gidstr+".html";
                setShareGoodsId(request,Long.parseLong(gidstr));
                return path;
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return redirectUrl(request,controllerMapping+"/"+page);

    }
}
