package com.retailers.dht.common.upload;

import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 上传文件到本地
 * @author zhongp
 * @version 1.0.1
 */
@Component
public class LocalUploader implements FileUploader {
    /**
     *
     * @param stream 图处流
     * @param type 上传类型（图片用途）
     * @param fileName 文件名
     * @param isCompress 是否压缩
     * @param isAddWatermark 是否添加水印
     * @return
     */
    public String upload(InputStream stream, String type, String fileName, boolean isCompress, boolean isAddWatermark) {
        return null;
    }

    /**
     *
     * @param stream 图处流
     * @param type 上传类型（图片用途）
     * @param fileName 文件名
     * @return
     */
    public String upload(InputStream stream, String type, String fileName) {
        return upload(stream,type,fileName,false,false);
    }
}
