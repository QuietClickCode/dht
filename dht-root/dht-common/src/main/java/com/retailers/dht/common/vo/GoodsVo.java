package com.retailers.dht.common.vo;
import com.retailers.dht.common.entity.Goods;

/**
 * Created by Administrator on 2017/9/30.
 */
public class GoodsVo extends Goods {
    private String gclassificationName;
    private String gareaName;

    public void setGareaName(String value) {
        this.gareaName = value;
    }

    public String getGareaName() {
        return this.gareaName;
    }

    public void setGclassificationName(String value) {
        this.gclassificationName = value;
    }

    public String getGclassificationName() {
        return this.gclassificationName;
    }


}
