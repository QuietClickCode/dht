package com.retailers.mybatis.common.service;

/**
 * 异步加载方法
 * @author  zhongp
 */
public interface ThreadService {
    /**
     * 异步加载数据
     * @param url 请求的地址
     * @param context 上下文
     */
    public void loadHttp(String url, String context);
}
