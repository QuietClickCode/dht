package com.retailers.dht.common.view;

import com.retailers.tools.utils.NumberUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 用户信息（详情）
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/22
 */
public class UserInfoVIew {
    /**uid*/
    private Long uid;
    /**推荐人id*/
    private Long urecommendId;
    /**登陆帐户*/
    private String uaccount;
    /**会员手机号（唯一值）*/
    private String uphone;
    /**用户邮箱*/
    private String uemail;
    /**会员姓名*/
    private String uname;
    /**登录密码*/
    private String upwd;
    /**支付密码*/
    private String upayPwd;
    /**用户头像Id*/
    private Integer uimgid;
    /**用户类型*/
    private Integer utype;
    /**用户状态（0 正常，1 禁用）*/
    private Integer ustatus;
    /**是否删除*/
    private Integer isDelete;
    /**ucreateTime*/
    private Date ucreateTime;
    /**是否是老板用户（0 新平台用户，1 老平台用户）*/
    private Integer uisOld;
    /**是否是新加密规则（0 是，1 否 )老平台用户迁移用户*/
    private Integer uoldPwd;
    /**姓别（0 男，1 女）*/
    private Integer usex;
    /**充值总金额*/
    private Long utotalWallet;
    /**当前可用金额*/
    private Long ucurWallet;
    /**总积分*/
    private Long utotalIntegral;
    /**当前可用积分*/
    private Long ucurIntegral;
    /**
     * 微信公从号openid
     */
    private String wauOpenid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUrecommendId() {
        return urecommendId;
    }

    public void setUrecommendId(Long urecommendId) {
        this.urecommendId = urecommendId;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUpayPwd() {
        return upayPwd;
    }

    public void setUpayPwd(String upayPwd) {
        this.upayPwd = upayPwd;
    }

    public Integer getUimgid() {
        return uimgid;
    }

    public void setUimgid(Integer uimgid) {
        this.uimgid = uimgid;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUcreateTime() {
        return ucreateTime;
    }

    public void setUcreateTime(Date ucreateTime) {
        this.ucreateTime = ucreateTime;
    }

    public Integer getUisOld() {
        return uisOld;
    }

    public void setUisOld(Integer uisOld) {
        this.uisOld = uisOld;
    }

    public Integer getUoldPwd() {
        return uoldPwd;
    }

    public void setUoldPwd(Integer uoldPwd) {
        this.uoldPwd = uoldPwd;
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public Long getUtotalWallet() {
        return utotalWallet;
    }

    public void setUtotalWallet(Long utotalWallet) {
        this.utotalWallet = utotalWallet;
    }

    public String getUcurWallet() {
        return NumberUtils.formaterNumberPower(ucurWallet);
    }

    public void setUcurWallet(Long ucurWallet) {
        this.ucurWallet = ucurWallet;
    }

    public Long getUtotalIntegral() {
        return utotalIntegral;
    }

    public void setUtotalIntegral(Long utotalIntegral) {
        this.utotalIntegral = utotalIntegral;
    }

    public Long getUcurIntegral() {
        return ucurIntegral;
    }

    public void setUcurIntegral(Long ucurIntegral) {
        this.ucurIntegral = ucurIntegral;
    }

    public String getWauOpenid() {
        return wauOpenid;
    }

    public void setWauOpenid(String wauOpenid) {
        this.wauOpenid = wauOpenid;
    }
}
