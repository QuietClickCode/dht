package com.retailers.dht.common.upload;

import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 上传至远程服务器
 */
@Component
public class RemoteServerUpload implements FileUploader{
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
