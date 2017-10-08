package com.retailers.dht.common.constant;

/**
 * 附件常量设置
 */
public class AttachmentConstant {
    /**
     * 附件所处状态 是否启用 0  未启用
     */
    public static long ATTACHMENT_STATUS_NO=0;
    /**
     *附件所处状态 是否启用 1  启用
     */
    public static long ATTACHMENT_STATUS_YES=1;
    /**
     * 附件存放类型 0 本地
     */
    public static long ATTACHMENT_SAVE_TYPE_LOCAL=0;
    /**
     * 附件存放类型 1 远程
     */
    public static long ATTACHMENT_SAVE_TYPE_REMOTE=1;
    //附件上传地址
    public static final  String UPLOAD_ATTACHEMNT_URL=SysParameterConfigConstant.getValue(SysParameterConfigConstant.ATTACHMENT_SERVER_ADDRESS)+"filesUpload";
    //展示图片地址
    public static final  String IMAGE_SHOW_URL=SysParameterConfigConstant.getValue(SysParameterConfigConstant.ATTACHMENT_SERVER_ADDRESS)+"attachment";
    //清除附件地址
    public static final  String CLEAR_ATTACHMENT_URL=SysParameterConfigConstant.getValue(SysParameterConfigConstant.ATTACHMENT_SERVER_ADDRESS)+"filesRemove";
}
