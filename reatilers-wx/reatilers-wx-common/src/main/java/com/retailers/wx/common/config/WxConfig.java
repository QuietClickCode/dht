package com.retailers.wx.common.config;

/**
 * 微信配置文件
 * @author zhongp
 * @version 1.0.1
 *@date 2017-09-07
 */
public class WxConfig {
    public static String APP_ID="";
    public static String APP_SECRET="";
    public static String ACCESS_TOKEN="nUd7jMakoJGO5QoAdOXgVedNGZcOqt3rLcLtjp6V_d1inAJ3pZb2l5CMbT0SG_2H6VyxZKyUfaHzgWe5flm8lVwPiWqJGYbHA5paSEBI7c-TwJ1y5bjN421ze4wf4MRVLFVjAFAGAQ";
    //js-sdk 耍要的token
    public static String ACCESS_TICKET="";
    //微信校验请求url token
    public static String WX_CHECK_REQ_TOKEN="";

    /** 请求消息类型：文本 */
    public static String REQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 微信mchId 用于支付
     */
    public static String WX_MCH_ID="";
    /**
     * 微信apiKey 用于支付
     */
    public static String WX_API_KEY="";


    /** 请求消息类型：图片 */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**  请求消息类型：推送 */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /** 事件类型：subscribe(订阅) */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /** 事件类型：unsubscribe(取消订阅) */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /** 事件类型：CLICK(自定义菜单点击事件) */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /** 事件类型：SCAN(自定义二维码扫描 -微信用户已关注该服务号) */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    //微信访问根地址
    public static final String BASE_WX_API_URL="https://api.weixin.qq.com";
    public static final String BASE_WX_OPEN_URL="https://open.weixin.qq.com";
    /*************************************************微信 用户授权开始*****************************************************************/
    //授权url
    public static String OAUTH2_AUTHORIZE_URL=BASE_WX_OPEN_URL+"/connect/oauth2/authorize";
    /**
     * 通过code换取网页授权access_token
     */
    public static final String AUTH2_GET_TOKEN_URL = BASE_WX_API_URL + "/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

//    /**
//     * 刷新access_token
//     */
//    public static final String URL_OAUTH2_REFRESH_TOKEN = WeixinCfg.BASE_API_HTTPS_URL + "/sns/oauth2/refresh_token";

/*    *//**
     * 检验授权凭证（access_token）是否有效
     *//*
    public static final String URL_OAUTH2_CHECK_TOKEN = WeixinCfg.BASE_API_HTTPS_URL + "/sns/auth";*/


    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     */
    public static final String OAUTH2_GET_USER_URL = BASE_WX_API_URL+ "/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";



    /*************************************************微信 用户授权结束****************************************************************/

    /**
     * js-sdk token取得
     */
    public static String REQ_TICKET_TOKEN_URL=BASE_WX_API_URL+"/cgi-bin/ticket/getticket?type=jsapi&access_token=%s";

    /**
     * 取得微信token 访问链接
     */
    public static String REQ_TOKEN_URL=BASE_WX_API_URL+"/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 获取自定义菜单配置接口
     */
    public static String QUERY_ALL_MENU_CONFIG_URL=BASE_WX_API_URL+"/cgi-bin/get_current_selfmenu_info?access_token=%s";
    /**
     * 自定义菜单查询接口
     */
    public static String QUERY_ALL_MENU_URL=BASE_WX_API_URL+"/cgi-bin/menu/get?access_token=%s";
    /**
     * 添加自己定义菜单
     */
    public static String ADD_MENU_URL=BASE_WX_API_URL+"/cgi-bin/menu/create?access_token=%s";
    /**
     * 删除用户自定义菜单
     */
    public static String DEL_MENU_URL=BASE_WX_API_URL+"/cgi-bin/menu/delete?access_token=%s";

    /****************************************  标签请求的url  ****************************************************************/
    /**
     * 创建用户标签
     */
    public static String ADD_USER_TAG_URL=BASE_WX_API_URL+"/cgi-bin/tags/create";
    /**
     * 取得公众号创建的标签
     */
    public static String REQ_USER_TAG_URL=BASE_WX_API_URL+"/cgi-bin/tags/get";
    /**
     * 修改己创建的标签
     */
    public static String UPDATE_USER_TAG_URL=BASE_WX_API_URL+"/cgi-bin/tags/update";
    /**
     * 删除己创建标签
     */
    public static String DEL_USER_TAG_URL=BASE_WX_API_URL+"/cgi-bin/tags/delete";
    /**
     * 取得标签下的用户数
     */
    public static String REQU_TAG_USERS_URL=BASE_WX_API_URL+"/cgi-bin/user/tag/get";
    /**
     *  批量为用户打标签
     */
    public static String BATCH_TAGGING_URL=BASE_WX_API_URL+"/cgi-bin/tags/members/batchtagging";
    /**
     *  批量为用户取消标签
     */
    public static String BATCH_UN_TAGGING_URL=BASE_WX_API_URL+"/cgi-bin/tags/members/batchuntagging";
    /**
     *  获取用户身上的标签列表
     */
    public static String REQ_USER_TAGS_ID_URL=BASE_WX_API_URL+"/cgi-bin/tags/getidlist";

    /************************************** 用户请求url ****************************************************************/
    /**
     * 获取用户基本信息（包括UnionID机制）
     */
    public static String REQU_USER_DETAIL_INFO_URL=BASE_WX_API_URL+"/cgi-bin/user/info";
    /**
     * 批量获取用户基本信息
     */
    public static String REQU_BATCH_USER_DETAIL_INFO_URL=BASE_WX_API_URL+"/cgi-bin/user/info/batchget";
    /**
     * 获取用户列表
     */
    public static String REQ_USER_LISTS=BASE_WX_API_URL+"/cgi-bin/user/get";

    /*****************************************   素材管理  *****************************************************************/
    /**
     * 添加临界时素材
     */
    public static String ADD_TEMP_MATERIA_URL=BASE_WX_API_URL+"/cgi-bin/media/upload";
    /**
     * 新建永久素材
     */
    public static String ADD_MATERIA_URL=BASE_WX_API_URL+"/cgi-bin/media/uploadimg";
    /**
     * 取得上传的临时素材
     */
    public static String GET_TEMP_MATERIA_URL=BASE_WX_API_URL+"/cgi-bin/media/get";
    /**
     * 取得永久素材列表(带分页)
     */
    public static String GET_BATCHGET_MATERIAL_URL=BASE_WX_API_URL+"/cgi-bin/material/batchget_material";

    /**
     * 创建带参数的二维码URL
     */
    public static final String WEIXIN_QRCODE_CREATE_URL = BASE_WX_API_URL+"/cgi-bin/qrcode/create?access_token={}";
    /**
     * 上传文件至微信服务器URL
     */
    public static final String WEIXIN_UPLOAD_FILE_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token={}&type={}";
    /**
     * 客服发送信息给微信用户URL
     */
    public static final String WEIXIN_KF_SEND_MSG_URL = BASE_WX_API_URL+"/cgi-bin/message/custom/send?access_token={}";













}
