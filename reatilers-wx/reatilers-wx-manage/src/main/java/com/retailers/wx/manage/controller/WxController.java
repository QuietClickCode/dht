package com.retailers.wx.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.annotation.Resourse;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.ValidationUtils;
import com.retailers.wx.common.enm.WXAccountEnum;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.entity.WxPay;
import com.retailers.wx.common.service.WxManagerService;
import com.retailers.wx.common.service.WxMenuService;
import com.retailers.wx.common.service.WxPayService;
import com.retailers.wx.common.vo.WxManagerVo;
import com.retailers.wx.common.vo.WxMenu;
import com.retailers.wx.common.vo.WxPayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/30
 */
@Controller
@RequestMapping("wx")
public class WxController{

    @Autowired
    private WxManagerService wxManagerService;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private WxMenuService wxMenuService;


    @Resourse(resourse = "system.manager.wx",label = "微信管理",sort = 4)
    private String wxManager;

    /**
     * 打开公众号绑定页面
     * @param request
     * @return
     */
    @RequestMapping("openWxPage")
    @Menu(resourse = "wx.openWxPage",label = "公众号绑定",parentRes = "system.manager.wx",sort = 1)
    public ModelAndView openWxPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        WxManagerVo wxManager= wxManagerService.queryCurUsedWx(WXAccountEnum.WX_GZH);
        mav.setViewName("wx/wx_info"); //返回的文件名
        mav.addObject("curWx",wxManager);
        mav.addObject("domainName", SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_PC_URL));
        return mav;
    }

    /**
     * 微信公众号设置
     * @param wxManager
     * @return
     */
    @RequestMapping("editorWxManager")
    @Function(label = "绑定公众号",description = "绑定公众号",resourse = "wx.editorWxManager",parentRes = "wx.openWxPage",sort =1)
    @ResponseBody
    public Map<String,Object> editorWxManager(HttpServletRequest request,WxManager wxManager){
        Map<String,Object> rtn=new HashMap<String,Object>();
        try{
            ValidationUtils.validate(wxManager);
            if(ObjectUtils.isNotEmpty(request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY))){
                SysUser sysUser=(SysUser)request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
                wxManager.setCreateUid(sysUser.getUid());
                wxManager.setCreateTime(new Date());
            }
            wxManagerService.editorWxManager(wxManager);
            rtn.put("status",0);
        }catch(Exception e){
            e.printStackTrace();
            rtn.put("status",-1);
            rtn.put("msg",e.getMessage());
        }
        return rtn;
    }
    /**
     * 打开微信支付设置页面
     * @param request
     * @return
     */
    @RequestMapping("openWxPayPage")
    @Menu(resourse = "wx.openWxPayPage",label = "支付设置",parentRes = "system.manager.wx",sort = 2)
    public ModelAndView openWxPayPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        WxManagerVo wxManager= wxManagerService.queryCurUsedWx(WXAccountEnum.WX_GZH);
        int  wx=0;
        if(ObjectUtils.isNotEmpty(wxManager)){
            wx=1;
        }
        WxPayVo wxPay= wxPayService.queryCurUsedPay();
        mav.setViewName("wx/wx_pay"); //返回的文件名
        mav.addObject("wxPay",wxPay);
        mav.addObject("setWx",wx);
        mav.addObject("domainName", SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_PC_URL));
        return mav;
    }

    /**
     * 微信公众号设置
     * @param wxPay
     * @return
     */
    @RequestMapping("editorWxPay")
    @Function(label = "微信支付",description = "微信支付",resourse = "wx.editorWxPay",parentRes = "wx.openWxPayPage",sort =1)
    @ResponseBody
    public Map<String,Object> editorWxPay(HttpServletRequest request,WxPay wxPay){
        Map<String,Object> rtn=new HashMap<String,Object>();
        try{
            ValidationUtils.validate(wxPay);
            if(ObjectUtils.isNotEmpty(request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY))){
                SysUser sysUser=(SysUser)request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
                wxPay.setCreateUid(sysUser.getUid());
                wxPay.setCreateTime(new Date());
            }
            wxPayService.editorWxPay(wxPay);
            rtn.put("status",0);
        }catch(Exception e){
            e.printStackTrace();
            rtn.put("status",-1);
            rtn.put("msg",e.getMessage());
        }
        return rtn;
    }

    /**
     * 微信菜单管理页面
     * @return
     */
    @RequestMapping("openWxMenuPage")
    @Menu(resourse = "wx.openWxMenuPage",label = "菜单管理",parentRes = "system.manager.wx",sort = 3)
    public ModelAndView openWxMenuPage(){
        ModelAndView modelAndView=new ModelAndView();
        WxMenu wxMenu =wxMenuService.queryWxMenu();
        if(ObjectUtils.isNotEmpty(wxMenu)){
            modelAndView.addObject("menus", JSON.toJSONString(wxMenu));
        }else{
            modelAndView.addObject("menus","");
        }
        //微信打开页面
        String redirectUrl="wx/wx_menu";
        //取得当前微信菜单
        modelAndView.setViewName(redirectUrl);
        return modelAndView;
    }



}
