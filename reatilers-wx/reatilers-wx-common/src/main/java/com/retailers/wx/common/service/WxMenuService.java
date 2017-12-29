package com.retailers.wx.common.service;

import com.retailers.tools.exception.AppException;
import com.retailers.wx.common.vo.WxMenu;

/**
 * 微信菜单管理
 * @author zhongp
 * @version 1.0.1
 */
public interface WxMenuService {
    /**
     * 取得微信菜单
     * @return
     * @throws AppException
     */
    public WxMenu queryWxMenu();
}
