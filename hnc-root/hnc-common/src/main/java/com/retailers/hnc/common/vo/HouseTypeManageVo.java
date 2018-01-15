package com.retailers.hnc.common.vo;

import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;

import java.util.List;

/**
 * Created by niconiconi on 2017/12/18.
 */
public class HouseTypeManageVo extends HouseTypeManage {
    private String imagePath;
    private String showImgPath;
    private List<FloorManage> floorManages;
    private Long isLike;

    public Long getIsLike() {
        return isLike;
    }

    public void setIsLike(Long isLike) {
        this.isLike = isLike;
    }

    public List<FloorManage> getFloorManages() {
        return floorManages;
    }

    public void setFloorManages(List<FloorManage> floorManages) {
        this.floorManages = floorManages;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getShowImgPath() {
        return showImgPath;
    }

    public void setShowImgPath(String showImgPath) {
        this.showImgPath = showImgPath;
    }
}
