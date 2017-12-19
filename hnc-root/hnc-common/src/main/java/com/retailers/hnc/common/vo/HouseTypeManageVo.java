package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/18.
 */
public class HouseTypeManageVo extends HouseTypeManage {
    private List<FloorManage> floorManages;

    public List<FloorManage> getFloorManages() {
        return floorManages;
    }

    public void setFloorManages(List<FloorManage> floorManages) {
        this.floorManages = floorManages;
    }
}
