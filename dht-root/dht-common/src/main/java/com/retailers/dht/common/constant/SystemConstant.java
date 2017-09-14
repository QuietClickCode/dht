package com.retailers.dht.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/14.
 */
public class SystemConstant {

    public static List<String> compressTypes=new ArrayList<String>();
    static {
        //small,middle,originalfile
        compressTypes.add("small");
        compressTypes.add("middle");
        compressTypes.add("originalfile");
    }

}
