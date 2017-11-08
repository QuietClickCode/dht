package com.retailers.dht.common.vo;
import com.retailers.dht.common.entity.Goods;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public class PagintionVo extends Pagination {
    private List<Long> gclassIds;

    public List<Long> getGclassIds() {
        return gclassIds;
    }

    public void setGclassIds(List<Long> gclassIds) {
        this.gclassIds = gclassIds;
    }
}
