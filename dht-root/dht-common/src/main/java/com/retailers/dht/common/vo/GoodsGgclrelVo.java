package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsGgclrel;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsGgclrelVo extends GoodsCommentlabelVo {
    private Long ggclId;
    private Long gid;
    private String gname;
    private Long gclassId;
    private String gclassName;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Long getGgclId(){
       return this.ggclId;
   }

    public void setGgclId(Long ggclId) {
        this.ggclId = ggclId;
    }

    public Long getGclassId() {
        return gclassId;
    }

    public void setGclassId(Long gclassId) {
        this.gclassId = gclassId;
    }

    public String getGclassName() {
        return gclassName;
    }

    public void setGclassName(String gclassName) {
        this.gclassName = gclassName;
    }
}
