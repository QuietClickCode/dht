package com.retailers.dht.common.dao;


import com.retailers.dht.common.entity.Sequence;

import java.util.List;
import java.util.Map;

/**
 * 工具类
 */
public interface ProcedureToolsMapper {


    /**
     * 取得当前序列号
     * @return
     */
    public long queryCurSequence(Sequence sequence);

    /**
     * 删除序列表数据
     * @return
     */
    public long clearSequenceData(Long time);
}
