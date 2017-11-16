package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsUggclrel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsUggclrelVo extends GoodsUggclrel {
    private String uname;
    private String gclname;
    private Long gcllevel;
    private String imgurl;
    private Long sumgcl;
    private Float avglevel;
    private Long siglegclsum;

    public String getGclname() {
        return gclname;
    }

    public void setGclname(String gclname) {
        this.gclname = gclname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Long getGcllevel() {
        return gcllevel;
    }

    public void setGcllevel(Long gcllevel) {
        this.gcllevel = gcllevel;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Float getAvglevel() {
        return avglevel;
    }

    public void setAvglevel(Float avglevel) {
        this.avglevel = avglevel;
    }

    public Long getSumgcl() {
        return sumgcl;
    }

    public void setSumgcl(Long sumgcl) {
        this.sumgcl = sumgcl;
    }

    public Long getSiglegclsum() {
        return siglegclsum;
    }

    public void setSiglegclsum(Long siglegclsum) {
        this.siglegclsum = siglegclsum;
    }
}
