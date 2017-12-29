package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.entity.Project;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class ClientManageVo extends ClientManage {
    private String fids;
    private String hids;
    private String iremark;
    private List<FloorManage> floorManageList;
    private List<HouseTypeManage> houseTypeManages;

    public String getFids() {
        return fids;
    }

    public void setFids(String fids) {
        this.fids = fids;
    }

    public String getHids() {
        return hids;
    }

    public void setHids(String hids) {
        this.hids = hids;
    }

    public String getIremark() {
        return iremark;
    }

    public void setIremark(String iremark) {
        this.iremark = iremark;
    }

    public List<FloorManage> getFloorManageList() {
        return floorManageList;
    }

    public void setFloorManageList(List<FloorManage> floorManageList) {
        this.floorManageList = floorManageList;
    }

    public List<HouseTypeManage> getHouseTypeManages() {
        return houseTypeManages;
    }

    public void setHouseTypeManages(List<HouseTypeManage> houseTypeManages) {
        this.houseTypeManages = houseTypeManages;
    }
}
