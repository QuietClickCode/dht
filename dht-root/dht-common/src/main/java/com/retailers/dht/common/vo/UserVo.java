package com.retailers.dht.common.vo;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;

import java.util.Date;

/**
 * 用户信息
 */
public class UserVo {
    /**uid*/
    private Long uid;
    /**推荐人名称*/
    private String uRecommendNm;
    /**登陆帐户*/
    private String uaccount;
    /**会员手机号（唯一值）*/
    private String uphone;
    /**用户邮箱*/
    private String uemail;
    /**会员姓名*/
    private String uname;
    /**用户头像*/
    private String heardUrl;
    /**用户类型*/
    private Integer utype;
    /**
     * 充值会员关联会员卡名称
     */
    private String rechageNm;
    /**
     * 会员折扣
     */
    private Long discount;
    /**用户状态（0 正常，1 禁用）*/
    private Integer ustatus;
    /**创建时间*/
    private Date ucreateTime;
    /**姓别（0 男，1 女）*/
    private Integer usex;
    /**
     * 微信openid
     */
    private String openId;
    /**
     * 充值金额
     */
    private Long uTotalWallet;
    /**
     * 当前余额
     */
    private Long uCurWallet;
    /**
     * 钱包总消费
     */
    private Long walletConsumeTotal;
    /**
     * 在线消费
     */
    private Long onLineConsumeTotal;
    /**
     * 总消费
     */
    private Long consumeTotal;
    /**
     *首单推荐提成
     */
    private Long ufirstCommission;
    /**
     * 推荐消费提成
     */
    private Long urecommendCommission;
    //选择主推类型
    private Integer uUseModule;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getuRecommendNm() {
        return uRecommendNm;
    }

    public void setuRecommendNm(String uRecommendNm) {
        this.uRecommendNm = uRecommendNm;
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

    public String getHeardUrl() {
        if(ObjectUtils.isNotEmpty(heardUrl)){
            if(heardUrl.indexOf(AttachmentConstant.IMAGE_SHOW_URL)==-1&&heardUrl.indexOf("http://")==-1){
                return StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,heardUrl);
            }
        }
        return heardUrl;
    }

    public void setHeardUrl(String heardUrl) {
        this.heardUrl = heardUrl;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public String getRechageNm() {
        return rechageNm;
    }

    public void setRechageNm(String rechageNm) {
        this.rechageNm = rechageNm;
    }

    public String getDiscount() {
        return NumberUtils.formaterNumberPower(discount,true);
//        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Date getUcreateTime() {
        return ucreateTime;
    }

    public void setUcreateTime(Date ucreateTime) {
        this.ucreateTime = ucreateTime;
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getuTotalWallet() {
        return NumberUtils.formaterNumberPower(uTotalWallet);
//        return uTotalWallet;
    }

    public void setuTotalWallet(Long uTotalWallet) {
        this.uTotalWallet = uTotalWallet;
    }

    public String getuCurWallet() {
        return NumberUtils.formaterNumberPower(uCurWallet);
//        return uCurWallet;
    }

    public void setuCurWallet(Long uCurWallet) {
        this.uCurWallet = uCurWallet;
    }

    public String getWalletConsumeTotal() {
        return NumberUtils.formaterNumberPower(walletConsumeTotal);
//        return walletConsumeTotal;
    }

    public void setWalletConsumeTotal(Long walletConsumeTotal) {
        this.walletConsumeTotal = walletConsumeTotal;
    }

    public String getOnLineConsumeTotal() {
        return NumberUtils.formaterNumberPower(onLineConsumeTotal);
//        return onLineConsumeTotal;
    }

    public void setOnLineConsumeTotal(Long onLineConsumeTotal) {
        this.onLineConsumeTotal = onLineConsumeTotal;
    }

    public String getConsumeTotal() {
        return NumberUtils.formaterNumberPower(consumeTotal);
//        return consumeTotal;
    }

    public void setConsumeTotal(Long consumeTotal) {
        this.consumeTotal = consumeTotal;
    }

    public String getUfirstCommission() {
        return NumberUtils.formaterNumberPower(ufirstCommission,true);
//        return ufirstCommission;
    }

    public void setUfirstCommission(Long ufirstCommission) {
        this.ufirstCommission = ufirstCommission;
    }

    public String getUrecommendCommission() {
        return NumberUtils.formaterNumberPower(urecommendCommission,true);
//        return urecommendCommission;
    }
    public void setUrecommendCommission(Long urecommendCommission) {
        this.urecommendCommission = urecommendCommission;
    }
    public Integer getuUseModule() {
        return uUseModule;
    }

    public void setuUseModule(Integer uUseModule) {
        this.uUseModule = uUseModule;
    }
}
