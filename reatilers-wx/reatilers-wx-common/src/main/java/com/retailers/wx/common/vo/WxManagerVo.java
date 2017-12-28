package com.retailers.wx.common.vo;

import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.entity.WxManager;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/27
 */
public class WxManagerVo extends WxManager {
    /**
     * 二维码图片地址
     */
    private String wxQrCodeUrl;

    public String getWxQrCodeUrl() {
        if(ObjectUtils.isNotEmpty(wxQrCodeUrl)){
            if(wxQrCodeUrl.indexOf(AttachmentConstant.IMAGE_SHOW_URL)==-1&&wxQrCodeUrl.indexOf("http://")==-1){
                return StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,wxQrCodeUrl);
            }
        }
        return wxQrCodeUrl;
    }

    public void setWxQrCodeUrl(String wxQrCodeUrl) {
        this.wxQrCodeUrl = wxQrCodeUrl;
    }
}
