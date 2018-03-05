package com.retailers.dht.common.vo;

/**
 * 评论Vo
 */
public class CommentVo {
    // 评论标签名称
    private String tagName;
    // 评论标签数量
    private Long count;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
