package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.FloorRelationship;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/15.
 */
public class FloorManageVo extends FloorManage {
    private List<FloorRelationship> relationships;

    public List<FloorRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<FloorRelationship> relationships) {
        this.relationships = relationships;
    }
}
