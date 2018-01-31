package com.retailers.razz.common.vo;

import com.retailers.razz.common.entity.ArticleType;

/**
 * Created by niconiconi on 2018/1/30.
 */
public class ArticleTypeVo extends ArticleType {
    private Long level;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
