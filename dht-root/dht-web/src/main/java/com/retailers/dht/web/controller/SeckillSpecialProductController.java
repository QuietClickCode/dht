package com.retailers.dht.web.controller;

import com.retailers.dht.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
        return redirectUrl(request,"sksppdt/seckill-product");
    }

    @RequestMapping("/specialp/{id}.html")
    public String specialp(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return redirectUrl(request,"sksppdt/special-product");
    }

    @RequestMapping("/secspep/{id}.html")
    public String test(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return redirectUrl(request,"sksppdt/special");
    }

}
