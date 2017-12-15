package com.retailers.dht.web.controller;

import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/9
 */
@Controller
public class SeckillSpecialProductController extends BaseController {

    @RequestMapping("/seckillp/{id}.html")
    public String seckillp(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getFinalReturnString(id,"seckillp","sksppdt","seckill-product",request);
    }

    @RequestMapping("/specialp/{id}.html")
    public String specialp(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getFinalReturnString(id,"specialp","sksppdt","special-product",request);
    }
    @RequestMapping("/bargainp/{id}.html")
    public String bargainp(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getFinalReturnString(id,"bargainp","sksppdt","bargain-product",request);
    }
    @RequestMapping("/bargainpd/{id}.html")
    public String bargainpd(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getOtherFinalReturnString(id,"bargainpd","sksppdt","bargain-detail",request);
    }
    @RequestMapping("/bargainpsd/{id}.html")
    public String bargainpsd(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return getOtherFinalReturnString(id,"bargainpsd","sksppdt","bargain-sharedetail",request);
    }
    @RequestMapping("/secspep/{id}.special")
    public String test(HttpServletRequest request, @PathVariable("id")String id){
        return redirectUrl(request,"sksppdt/special");
    }

    public String getFinalReturnString(String id,String controllerMapping,String dir,String page,HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        String path="";
        String[] arr = id.split("~");
        try {
            if(!ObjectUtils.isEmpty(uid)){
                String encryuid = DESUtils.encryptDES(uid.toString(), DesKey.WEB_KEY);
                encryuid = URLEncoder.encode(encryuid);
                String randStr = DESUtils.encryptDES(StringUtils.formate(""+uid,System.currentTimeMillis()+""),DesKey.WEB_KEY);
                randStr = URLEncoder.encode(randStr);
                if(arr.length==1){
                    path = "redirect:/"+controllerMapping+"/"+id+"~inviter_"+encryuid+".html?randStr="+randStr;
                    return path;
                }else{
                    String ivr = id.split("_")[1];
                    ivr = URLDecoder.decode(ivr);
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
                    ivr = URLDecoder.decode(ivr);
                    ivr = DESUtils.decryptDES(ivr, DesKey.WEB_KEY);
                    Long ivrLong = Long.parseLong(ivr);
                    setShareUserId(request,ivrLong);
                    String gidstr = id.split("~")[0];
                    path = "redirect:/"+controllerMapping+"/"+gidstr+".html";
                    return path;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return redirectUrl(request,dir+"/"+page);
    }

    public String getOtherFinalReturnString(String id,String controllerMapping,String dir,String page,HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        String path="";
        String[] arr = id.split("~");
        try {
            if(!ObjectUtils.isEmpty(uid)){
                String encryuid = DESUtils.encryptDES(uid.toString(), DesKey.WEB_KEY);
                encryuid = URLEncoder.encode(encryuid);
                String randStr = DESUtils.encryptDES(StringUtils.formate(""+uid,System.currentTimeMillis()+""),DesKey.WEB_KEY);
                randStr = URLEncoder.encode(randStr);
                if(arr.length==1){
                    path = "redirect:/"+controllerMapping+"/"+id+"~inviter_"+encryuid+".html?randStr="+randStr;
                    return path;
                }else{
                    String ivr = id.split("_")[1];
                    ivr = URLDecoder.decode(ivr);
                    ivr = DESUtils.decryptDES(ivr, DesKey.WEB_KEY);
                    if(!ivr.equals(uid.toString())){
                        Long ivrLong = Long.parseLong(ivr);
                        setShareUserId(request,ivrLong);
                        return redirectUrl(request,dir+"/bargain-sharedetail");
                    }
                }
            }else{
//                if(arr.length==2){
//                    String ivr = id.split("_")[1];
//                    ivr = URLDecoder.decode(ivr);
//                    ivr = DESUtils.decryptDES(ivr, DesKey.WEB_KEY);
//                    Long ivrLong = Long.parseLong(ivr);
//                    setShareUserId(request,ivrLong);
//                    String gidstr = id.split("~")[0];
//                    path = "redirect:/"+controllerMapping+"/"+gidstr+".html";
//                    return path;
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return redirectUrl(request,dir+"/"+page);
    }
}
