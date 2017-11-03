package com.retailers.dht.common.vo;

import com.retailers.dht.common.entity.GoodsBrand;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsInventoryVo {
    private Long gid;
    private String gname;
    private Long inventory;
    private Long residueInventory;

    public void setGname(String value) {
        this.gname = value;
    }
    public String getGname() {
        return this.gname;
    }

    public Long getGid() {
        return this.gid;
    }
    public void setGid(Long value) {
        this.gid = value;
    }

    public Long getInventory() {
        return this.inventory;
    }
    public void setInventory(Long value) {
        this.inventory = value;
    }

    public Long getResidueInventory() {
        return this.residueInventory;
    }
    public void setResidueInventory(Long value) {
        this.residueInventory = value;
    }
}
