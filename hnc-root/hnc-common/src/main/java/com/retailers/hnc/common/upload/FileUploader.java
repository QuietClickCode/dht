package com.retailers.hnc.common.upload;

import java.io.InputStream;
import java.util.Map;

/**
 * 文件上传类接口方法
 * @author zhongp
 * @version 1.0.1
 */
public interface FileUploader {
    /**
     * 图片上传
     * @param stream 图处流
     * @param type 上传类型（图片用途）
     * @param fileName 文件名
     * @param isCompress 是否压缩
     * @param isAddWatermark 是否添加水印
     * @return
     */
    public Map<String,String> upload(InputStream stream, String type, String fileName, boolean isCompress, boolean isAddWatermark);
    /**
     * 图片上传
     * @param stream 图处流
     * @param type 上传类型（图片用途）
     * @param fileName 文件名
     * @return
     */
    public Map<String,String> upload(InputStream stream, String type, String fileName);

}
