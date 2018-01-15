package com.retailers.dht.common.vo;

/**
 * 商品返现装态以及返现名称
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/14
 */
public class GoodsReturnVo {
    /**
     * 商品id
     */
    private Long gid;
    /**
     * 商品名称
     */
    private String gName;
    /**
     * 排名公示id
     */
    private Long rtId;
    /**
     * 排名公示名称
     */
    private String rtName;
    /**
     * 排名公示类型 0 不立即返现，1 立即返现
     */
    private Integer rtType;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Long getRtId() {
        return rtId;
    }

    public void setRtId(Long rtId) {
        this.rtId = rtId;
    }

    public String getRtName() {
        return rtName;
    }

    public void setRtName(String rtName) {
        this.rtName = rtName;
    }

    public Integer getRtType() {
        return rtType;
    }

    public void setRtType(Integer rtType) {
        this.rtType = rtType;
    }
}
