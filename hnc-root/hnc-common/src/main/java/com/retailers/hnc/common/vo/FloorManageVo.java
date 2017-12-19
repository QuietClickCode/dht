package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class FloorManageVo extends FloorManage {
    private List<HouseTypeManage> typeManages;

    public List<HouseTypeManage> getTypeManages() {
        return typeManages;
    }

    public void setTypeManages(List<HouseTypeManage> typeManages) {
        this.typeManages = typeManages;
    }
}
