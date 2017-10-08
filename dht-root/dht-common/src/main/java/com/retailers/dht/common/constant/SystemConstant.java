package com.retailers.dht.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 大汇堂系统 常量配置
 */
public class SystemConstant {
    /**
     * 压缩类型（ small 小图，middle 中图 ，originalfile 原图)
     */
    public static List<String> compressTypes=new ArrayList<String>();

    static {
        compressTypes.add("small");
        compressTypes.add("middle");
        compressTypes.add("originalfile");
    }
}
