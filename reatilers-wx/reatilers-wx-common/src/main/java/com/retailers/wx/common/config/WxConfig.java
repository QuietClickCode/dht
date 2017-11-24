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
    /**
     * 取得微信token 访问链接
     */
    public static String REQ_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * js-sdk token取得
     */
    public static String REQ_TICKET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=%s";
    /**
     * 获取自定义菜单配置接口
     */
    public static String QUERY_ALL_MENU_CONFIG_URL="https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s";
    /**
     * 自定义菜单查询接口
     */
    public static String QUERY_ALL_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
    /**
     * 添加自己定义菜单
     */
    public static String ADD_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    /**
     * 删除用户自定义菜单
     */
    public static String DEL_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    /****************************************  标签请求的url  ****************************************************************/
    /**
     * 创建用户标签
     */
    public static String ADD_USER_TAG_URL="https://api.weixin.qq.com/cgi-bin/tags/create";
    /**
     * 取得公众号创建的标签
     */
    public static String REQ_USER_TAG_URL="https://api.weixin.qq.com/cgi-bin/tags/get";
    /**
     * 修改己创建的标签
     */
    public static String UPDATE_USER_TAG_URL="https://api.weixin.qq.com/cgi-bin/tags/update";
    /**
     * 删除己创建标签
     */
    public static String DEL_USER_TAG_URL="https://api.weixin.qq.com/cgi-bin/tags/delete";
    /**
     * 取得标签下的用户数
     */
    public static String REQU_TAG_USERS_URL="https://api.weixin.qq.com/cgi-bin/user/tag/get";
    /**
     *  批量为用户打标签
     */
    public static String BATCH_TAGGING_URL="https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging";
    /**
     *  批量为用户取消标签
     */
    public static String BATCH_UN_TAGGING_URL="https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging";
    /**
     *  获取用户身上的标签列表
     */
    public static String REQ_USER_TAGS_ID_URL="https://api.weixin.qq.com/cgi-bin/tags/getidlist";

    /************************************** 用户请求url ****************************************************************/
    /**
     * 获取用户基本信息（包括UnionID机制）
     */
    public static String REQU_USER_DETAIL_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info";
    /**
     * 批量获取用户基本信息
     */
    public static String REQU_BATCH_USER_DETAIL_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info/batchget";
    /**
     * 获取用户列表
     */
    public static String REQ_USER_LISTS="https://api.weixin.qq.com/cgi-bin/user/get";

    /*****************************************   素材管理  *****************************************************************/
    /**
     * 添加临界时素材
     */
    public static String ADD_TEMP_MATERIA_URL="https://api.weixin.qq.com/cgi-bin/media/upload";
    /**
     * 新建永久素材
     */
    public static String ADD_MATERIA_URL="https://api.weixin.qq.com/cgi-bin/media/uploadimg";
    /**
     * 取得上传的临时素材
     */
    public static String GET_TEMP_MATERIA_URL="https://api.weixin.qq.com/cgi-bin/media/get";
    /**
     * 取得永久素材列表(带分页)
     */
    public static String GET_BATCHGET_MATERIAL_URL="https://api.weixin.qq.com/cgi-bin/material/batchget_material";
}
