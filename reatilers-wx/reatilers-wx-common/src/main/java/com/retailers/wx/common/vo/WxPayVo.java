package com.retailers.wx.common.vo;

import com.retailers.wx.common.entity.WxPay;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/28
 */
public class WxPayVo extends WxPay {
    /**
     * 远程文件地址
     */
    private String romoteFile;

    public String getRomoteFile() {
        return romoteFile;
    }
    public void setRomoteFile(String romoteFile) {
        this.romoteFile = romoteFile;
    }
}
