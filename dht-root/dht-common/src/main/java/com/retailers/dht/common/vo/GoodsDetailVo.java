package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsDetail;
import com.retailers.dht.common.entity.GoodsGgsval;

/**
 * Created by Administrator on 2017/10/19.
 */
public class GoodsDetailVo extends GoodsDetail {
    private String imgUrl;//规格图片地址
    private String gsid;
    private String gsname;
    private String hasgsvalid;
    private String hasgsval;
    private String gunitname;
    private Long isMenberdiscount;
    private Long gclass;
    private Long type;//购买类型 0普通 1砍价 2特价/秒杀
    private String gname;
    private String gimgurl;

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGimgurl() {
        return gimgurl;
    }

    public void setGimgurl(String gimgurl) {
        this.gimgurl = gimgurl;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

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