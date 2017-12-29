package com.retailers.wx.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.service.WxMenuService;
import com.retailers.wx.common.vo.WxMenu;
import com.sun.glass.ui.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信菜单管理
 * @author zhongp
 * @version 1.0.1
 */
@Service("wxMenuServiceImpl")
public class WxMenuServiceImpl implements WxMenuService {
    Logger logger = LoggerFactory.getLogger(WxMenuServiceImpl.class);
    /**
     * 取得微信菜单
     * @return
     * @throws AppException
     */
    public WxMenu queryWxMenu(){
        //取得微信自定菜单请求url
        String reqUrl=String.format(WxConfig.QUERY_ALL_MENU_URL,WxConfig.ACCESS_TOKEN);
        //取得自定义菜单数据（json）
        String str= HttpClientUtil.doGet(reqUrl);
        try{
            JSONObject json=JSONObject.parseObject(str);
            if(json.containsKey("menu")){
                WxMenu wxMenu =JSONObject.parseObject(json.getString("menu"), WxMenu.class);
                return wxMenu;
            }
        }catch(Exception e){
            logger.error(StringUtils.getErrorInfoFromException(e));
        }
        return null;
    }
}
