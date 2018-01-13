package com.retailers.hnc.common.util;

import java.util.Properties;

/**
 * 微信配置文件
 * @author zhongp
 * @version 1.0.1
 *@date 2017-09-07
 */
public class WxConfig {
    public static Properties properties;
    public static String appId="wxfd2628cfc7f6defb";
    public static String appSecret="89150c76c3925859cf95375fc901c047";
    public static String ACCESS_TOKEN="5_jwtqAx3sj-OQ6YQAt7O5OkT5BY2u1qBRF4bdFFDKcarfaqTOsb2_PjaeUiQ8_thIKHp_1BVbJa2GPqoIYm6sVniE0994dBCeM-EXTJXJguD6h7gErV8MP4kNF69VVxqU1aDkEGbNr5TwidLSXWJbAIAAZM";
    /**
     * 取得微信token 访问链接
     */
    public static String REQ_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
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

    static {
        properties = new Properties();
        try {
            properties.load(WxConfig.class.getResourceAsStream("/wx-config.properties"));
            appId = properties.getProperty("appId");
            appSecret = properties.getProperty("appSecret");
        }catch (Exception e){
        }
    }
}
