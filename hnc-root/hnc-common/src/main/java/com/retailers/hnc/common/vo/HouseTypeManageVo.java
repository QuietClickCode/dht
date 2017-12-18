package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/18.
 */
public class HouseTypeManageVo extends HouseTypeManage {
    private List<FloorRelationship> relationships;

    public List<FloorRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<FloorRelationship> relationships) {
        this.relationships = relationships;
    }
}
