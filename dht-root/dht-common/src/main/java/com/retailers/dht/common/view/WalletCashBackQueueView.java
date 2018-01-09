package com.retailers.dht.common.view;

import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/9
 */
public class WalletCashBackQueueView {
    /**
     * 行数
     */
    private Long rownum;
    /**
     * 当前返现行数
     */
    private Long curCashQueue;
    /**ccbqId*/
    private Long ccbqId;
    /**订单id*/
    private Long ccbqOrderId;
    /**关联商品id*/
    private Long ccbqGid;
    /**商品类型（第一级）*/
    private Long ccbqGoodsType;
    /**用户id*/
    private Long ccbqUid;
    /**返现金额（分）*/
    private Long ccbqMoney;
    /**当前状态（0 排队中，1 正在筹款，2 己返现）*/
    private Long ccbqStatus;
    /**创建时间*/
    private Date ccbqCreateTime;
    /**返现时间*/
    private Date ccbqCashBackTime;
    /**等待时间*/
    private Long ccbqWaitTime;
    /**数据版本*/
    private Integer version;
    /**
     * 购买用户id
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userHead;
    /**
     * 订单编号
     */
    private String orderNo;

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public Long getCurCashQueue() {
        return curCashQueue;
    }

    public void setCurCashQueue(Long curCashQueue) {
        this.curCashQueue = curCashQueue;
    }

    public Long getCcbqId() {
        return ccbqId;
    }

    public void setCcbqId(Long ccbqId) {
        this.ccbqId = ccbqId;
    }

    public Long getCcbqOrderId() {
        return ccbqOrderId;
    }

    public void setCcbqOrderId(Long ccbqOrderId) {
        this.ccbqOrderId = ccbqOrderId;
    }

    public Long getCcbqGid() {
        return ccbqGid;
    }

    public void setCcbqGid(Long ccbqGid) {
        this.ccbqGid = ccbqGid;
    }

    public Long getCcbqGoodsType() {
        return ccbqGoodsType;
    }

    public void setCcbqGoodsType(Long ccbqGoodsType) {
        this.ccbqGoodsType = ccbqGoodsType;
    }

    public Long getCcbqUid() {
        return ccbqUid;
    }

    public void setCcbqUid(Long ccbqUid) {
        this.ccbqUid = ccbqUid;
    }

    public Long getCcbqMoney() {
        return ccbqMoney;
    }
    public String getCcbqMoneys() {
        return NumberUtils.formaterNumberPower(ccbqMoney);
    }

    public void setCcbqMoney(Long ccbqMoney) {
        this.ccbqMoney = ccbqMoney;
    }

    public Long getCcbqStatus() {
        return ccbqStatus;
    }

    public void setCcbqStatus(Long ccbqStatus) {
        this.ccbqStatus = ccbqStatus;
    }

    public Date getCcbqCreateTime() {
        return ccbqCreateTime;
    }

    public void setCcbqCreateTime(Date ccbqCreateTime) {
        this.ccbqCreateTime = ccbqCreateTime;
    }

    public Date getCcbqCashBackTime() {
        return ccbqCashBackTime;
    }

    public void setCcbqCashBackTime(Date ccbqCashBackTime) {
        this.ccbqCashBackTime = ccbqCashBackTime;
    }

    public Long getCcbqWaitTime() {
        return ccbqWaitTime;
    }

    public void setCcbqWaitTime(Long ccbqWaitTime) {
        this.ccbqWaitTime = ccbqWaitTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
