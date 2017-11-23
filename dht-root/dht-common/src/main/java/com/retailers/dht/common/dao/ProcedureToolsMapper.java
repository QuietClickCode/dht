package com.retailers.dht.common.dao;


import com.retailers.dht.common.entity.Sequence;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    /**
     * 添加单线程锁
     * @param key
     * @param curDate
     * @return
     */
    public int singleLockManager(@Param("key")String key, @Param("curDate")Date curDate);
    /**
     * 解除单线程锁
     * @param key
     * @return
     */
    public int singleUnLockManager(@Param("key")String key);
}
