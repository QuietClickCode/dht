package com.retailers.hnc.common.upload;

import com.retailers.tools.utils.SpringUtils;

/**
 * Created by zpapj on 2017/9/12.
 */
public class UploadFacatory {
    /**
     * 上传图片
     * @return
     */
    public static FileUploader getUploaer(){
        FileUploader uploade =(RemoteServerUpload) SpringUtils.getBean("remoteServerUpload");
//        //如果开启fastdfs
//        if(ClusterSetting.getFdfs_open()==1){
//            return new FastDFSUploader();
//        }else if(FastDfsSetting.getFdfs_open()==1){
//            return new FDFSUploader();
//        }
        return uploade;
    }
}
