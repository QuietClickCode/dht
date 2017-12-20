package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGgsval;

/**
 * Created by Administrator on 2017/10/19.
 */
public class GoodsDetailVo extends GoodsDetail {
    private String imgUrl;
    private String gsid;
    private String gsname;
    private String hasgsvalid;
    private String hasgsval;
    private String gunitname;
    private Long isMenberdiscount;
    private Long gclass;

    public Long getGclass() {
        return gclass;
    }

    public void setGclass(Long gclass) {
        this.gclass = gclass;
    }

    public Long getIsMenberdiscount() {
        return isMenberdiscount;
    }

    public void setIsMenberdiscount(Long isMenberdiscount) {
        this.isMenberdiscount = isMenberdiscount;
    }

    public String getGunitname() {
        return gunitname;
    }

    public void setGunitname(String gunitname) {
        this.gunitname = gunitname;
    }

    public String getGsid() {
        return gsid;
    }

    public void setGsid(String gsid) {
        this.gsid = gsid;
    }

    public String getGsname() {
        return gsname;
    }

    public void setGsname(String gsname) {
        this.gsname = gsname;
    }

    public String getHasgsvalid() {
        return hasgsvalid;
    }
    public void setHasgsvalid(String hasgsvalid) {
        this.hasgsvalid = hasgsvalid;
    }

    public String getHasgsval() {
        return hasgsval;
    }

    public void setHasgsval(String hasgsval) {
        this.hasgsval = hasgsval;
    }

    public void setImgUrl(String value) {
        this.imgUrl = value;
    }

    public String getImgUrl() {
        return this.imgUrl;

    }
}