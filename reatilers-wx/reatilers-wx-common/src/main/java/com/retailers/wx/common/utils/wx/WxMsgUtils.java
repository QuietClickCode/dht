package com.retailers.wx.common.utils.wx;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/13
 */
public class WxMsgUtils {

    private static final Logger logger = Logger.getLogger(WxMsgUtils.class);

    private static final String typeArr = "CLICK,VIEW,NEWS";

//    private static WxMessageService wxMessageService = SpringContextUtils.getBean("wxMessageServiceImpl");

    /**
     *
     * <b>方法名：</b>：getWxQRcodeTicket<br>
     * <b>功能说明：</b>：获取带参数的二维码信息，使用临时二维码接口<br>
     *
     * @author <font color='blue'>韩俊</font>
     * @date 2016-02-25 上午09:30:33
     * @param accessToken
     * @param scene_id  场景值ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getWxQRcodeTicket(String accessToken, String scene_id) {
        Map<String, Object> resMap = null;
        String reqUrl = StringUtils.formates(WxConfig.WEIXIN_QRCODE_CREATE_URL,accessToken);
        /**
         * expire_seconds 表示二维码有效期为7天
         * action_name 表示为临时二维码接口
         * action_info 表示为二维码信息
         * scene_id 临时二维码时为32位非0整型
         */
        JSONObject jsonstr = new JSONObject();
        jsonstr.put("expire_seconds", 604800);
        jsonstr.put("action_name", "QR_SCENE");
        jsonstr.put("action_info", new JSONObject().put("scene", new JSONObject().put("scene_id", scene_id)));
        try {
//            JSONObject json = HttpRequest.httpPost(reqUrl, jsonstr);
//            // 相同的sceneId 获取的 ticket 不同 由此可以把sceneId 作为自定义唯一标识
//            // json
//            // 范围数据类型={"expire_seconds":604800,"ticket":"gQGy8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzRFT3Fpb2JsRVo5b215M1RGbTBrAAIEn3TOVgMEgDoJAA==","url":"http://weixin.qq.com/q/4EOqioblEZ9omy3TFm0k"}
//            // {"expire_seconds":604800,"ticket":"gQFS8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzEwTzFMRFRsRHA5M3VCcDFDVzBrAAIEAXXOVgMEgDoJAA==","url":"http://weixin.qq.com/q/10O1LDTlDp93uBp1CW0k"}
//            if (ObjectUtils.isNotEmpty(json) && !json.containsKey("errcode")) {
//                ObjectMapper mapper = new ObjectMapper();
//                resMap = mapper.readValue(json.toString(), Map.class);
//                resMap.put("ImgUrl", OAOConstants.WEIXIN_QRCODE_IMG_URL.replace("{TICKET}", resMap.get("ticket").toString()));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     *
     * <b>方法名：</b>：uploadFileToWx<br>
     * <b>功能说明：</b>：上传附件值微信服务器，临时文件上传接口，数据在微信端保留时间为3天,
     * 	素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式<br>
     *
     * @author <font color='blue'>韩俊</font>
     * @date 2016-02-25 上午13:10:11
     * @param accessToken
     * @param fileType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> uploadFileToWx(String accessToken, String fileType, File file) {
        Map<String, Object> resMap = null;
        String uploadUrl = StringUtils.formates(WxConfig.WEIXIN_UPLOAD_FILE_URL,accessToken,fileType);

//        try {
//            JSONObject json = HttpRequest.uploadPost(uploadUrl, file);
//            if (!EmptyUtil.isNullOrEmpty(json) && json.isNull("errcode")) {
//                ObjectMapper mapper = new ObjectMapper();
//                resMap = mapper.readValue(json.toString(), Map.class);
//                System.out.println("" + resMap.toString());
//            }else{
//
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//            logger.error("发送消息json 错误", e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ExceptionsCustom("微信图片上传失败");
//        }
        return resMap;
    }

    /**
     * <b>方法名：</b>：sendTextToWxUser<br>
     * <b>功能说明：</b>：利用客服接口发送文本信息给微信用户  <br>
     * 	msgType 消息类型
     *  		（文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息为news，卡券为wxcard）
     * @author <font color='blue'>韩俊</font>
     * @date  2016-3-1 上午10:07:01
     * @param msgType
     * @param text
     * @param openId
     * @param accessToken
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> sendTextToWxUser(String msgType, String text, String openId, String accessToken) {
        Map<String, Object> resMap = null;
        String sendUrl =StringUtils.formates(WxConfig.WEIXIN_KF_SEND_MSG_URL,accessToken);
//		String jsonStr = "{\"touser\":\"" + openId + "\", \"msgtype\":\"text\", \"text\": { \"content\":\"" + text + "\" } }";
        JSONObject jsonStr = new JSONObject();
        jsonStr.put("touser", openId);
        jsonStr.put("msgtype", "text");
        jsonStr.put("text", new JSONObject().put("content", text));

//        WxMessage wxMessage = new WxMessage();
//        wxMessage.setCreateTime(new Date());
//        wxMessage.setToId(openId);
//        wxMessage.setMessageType("KF");
//        try {
//            JSONObject json = HttpRequest.httpPost(sendUrl, jsonStr);
//
//            if (!EmptyUtil.isNullOrEmpty(json) && json.isNull("errcode")) {
//                ObjectMapper mapper = new ObjectMapper();
//                resMap = mapper.readValue(json.toString(), Map.class);
//                wxMessage.setContent(json.toString()+"  "+text);
//            }else{
//                logger.info("发送微信消息 错误信息："+json);
//                wxMessage.setContent(json.toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            logger.error("发送消息json 错误="+text, e);
//            wxMessage.setContent("发送消息json 错误="+text);
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error("发送消息 io错误 ="+text, e);
//            wxMessage.setContent("发送消息 io错误="+text);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("发送消息错误 ="+text, e);
//            wxMessage.setContent("发送消息错误="+text);
//        } finally {
//            wxMessageService.save(wxMessage);
//        }

        return resMap;
    }

    /**
     * <b>方法名：</b>：sendNewsToWxUser<br>
     * <b>功能说明：</b>：利用客服接口发送单图文信息给微信用户<br>
     * @author <font color='blue'>韩俊</font>
     * @date  2016-5-4 下午12:58:23
     * @param openId
     * @param title 标题
     * @param description 简介
     * @param picUrl 图片链接
     * @param linkUrl 跳转链接
     * @param accessToken
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> sendNewsToWxUser(String openId, String title,
                                                       String description, String picUrl, String linkUrl, String accessToken) {
        Map<String, Object> resMap = null;
        String sendUrl =StringUtils.formates(WxConfig.WEIXIN_KF_SEND_MSG_URL,accessToken);
        JSONObject jsonStr = new JSONObject();
        jsonStr.put("touser", openId);
        jsonStr.put("msgtype", "news");

        //单个图文消息内容
        JSONObject article = new JSONObject();
        article.put("title", title);
        article.put("description", description);
        article.put("url", linkUrl);
        article.put("picurl", picUrl);
        JSONArray arr = new JSONArray();

        //添加消息至组
        arr.add(article);

        //内部图文消息组的key - value
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("articles", arr);

        //整理整个推送消息内容
        jsonStr.put("news", jsonObj);

//        WxMessage wxMessage = new WxMessage();
//        wxMessage.setCreateTime(new Date());
//        wxMessage.setToId(openId);
//        wxMessage.setMessageType("KF");
//        try {
//            JSONObject json = HttpRequest.httpPost(sendUrl, jsonStr);
//            logger.info("推送图文消息 微信端反馈========="+json);
//            if (!EmptyUtil.isNullOrEmpty(json) && !json.isNull("errcode") && "0".equals(json.get("errcode").toString())) {
//                ObjectMapper mapper = new ObjectMapper();
//                resMap = mapper.readValue(json.toString(), Map.class);
//                logger.info("msgType=news,openId="+openId);
//                wxMessage.setContent(json.toString()+"  "+article.toString());
//            }else{
//                wxMessage.setContent(json.toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            logger.error("发送消息json 错误="+article.toString(), e);
//            wxMessage.setContent("发送消息json 错误="+article.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error("发送消息 io错误 ="+article.toString(), e);
//            wxMessage.setContent("发送消息 io错误="+article.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("发送消息错误 ="+article.toString(), e);
//            wxMessage.setContent("发送消息错误="+article.toString());
//        } finally {
//            wxMessageService.save(wxMessage);
//        }

        return resMap;
    }


    /**
     * <b>方法名：</b>：sendImageToWxUser<br>
     * <b>功能说明：</b>：利用客服接口发送图片信息给微信用户 <br>
     * @author <font color='blue'>韩俊</font>
     * @date  2016-3-1 上午10:13:53
     * @param msgType
     * @param mediaId
     * @param openId
     * @param accessToken
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> sendImageToWxUser(String msgType, String mediaId, String openId, String accessToken) {
        Map<String, Object> resMap = null;
        String sendUrl =StringUtils.formates(WxConfig.WEIXIN_KF_SEND_MSG_URL,accessToken);
        JSONObject jsonStr = new JSONObject();

        jsonStr.put("touser", openId);
        jsonStr.put("msgtype", "image");
        jsonStr.put("image", new JSONObject().put("media_id", mediaId));
//        try {
//            JSONObject json = HttpRequest.httpPost(sendUrl, jsonStr);
//            if (!EmptyUtil.isNullOrEmpty(json) && json.isNull("errcode")) {
//                ObjectMapper mapper = new ObjectMapper();
//                resMap = mapper.readValue(json.toString(), Map.class);
//                System.out.println("" + resMap.toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return resMap;
    }

    /**
     * <b>方法名：</b>：checkSignature<br>
     * <b>功能说明：</b>：验证签名 <br>
     *
     * @author <font color='blue'>韩俊</font>
     * @date 2016-02-25 上午13:30:11
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * <b>方法名：</b>：parseXml<br>
     * <b>功能说明：</b>：根据流获取微信端反馈的xml信息<br>
     * @author <font color='blue'>韩俊</font>
     * @date  2016-2-29 下午4:05:46
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = documentBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx = 0; idx < nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                map.put(element.getNodeName(), element.getTextContent());
            }
        }
        try {
            inputStream.close();
        } catch (Exception ex) {
            // do nothing
        }
        return map;
    }

//    /**
//     * 扩展xstream，使其支持CDATA块
//     */
//    private static XStream xstream = new XStream(new XppDriver() {
//        public HierarchicalStreamWriter createWriter(Writer out) {
//            return new PrettyPrintWriter(out) {
//                // 对所有xml节点的转换都增加CDATA标记
//                boolean cdata = true;
//
//                public void startNode(String name, Class clazz) {
//                    super.startNode(name);
//                }
//
//                protected void writeText(QuickWriter writer, String text) {
//                    if (cdata) {
//                        writer.write("<![CDATA[");
//                        writer.write(text);
//                        writer.write("]]>");
//                    } else {
//                        writer.write(text);
//                    }
//                }
//            };
//        }
//    });

//    /**
//     * 文本消息对象转换成xml
//     * @param wxMsgTextVo 文本消息对象
//     * @return xml
//     */
//    public static String textMessageToXml(WxMsgTextVo wxMsgTextVo) {
//        xstream.alias("xml", wxMsgTextVo.getClass());
//        return xstream.toXML(wxMsgTextVo);
//    }

//    /**
//     * 图片消息对象转换成xml
//     * @param wxMsgImageVo 音乐消息对象
//     * @return xml
//     */
//    public static String imageMessageToXml(WxMsgImageVo wxMsgImageVo) {
//        xstream.alias("xml", wxMsgImageVo.getClass());
//        return xstream.toXML(wxMsgImageVo);
//    }


//
//    /**
//     * <b>方法名：</b>：getWxMenu<br>
//     * <b>功能说明：</b>：TODO<br>
//     * @author <font color='blue'>韩俊</font>
//     * @date  2016-5-16 上午11:35:19
//     * @param accessToken
//     * @return
//     */
//    public static List<WxMenuSetVo> getWxMenu(String accessToken) {
//        List<WxMenuSetVo> wxMenuSetList = new LinkedList<WxMenuSetVo>();
//        Map<String, Object> resMap = null;
//        String reqUrl = OAOConstants.WEIXIN_MENU_SET_URL.replace("{ACCESS_TOKEN}", accessToken);
//        for (int i = 0; i < 10; i++) {
//            try {
//                JSONObject json = new JSONObject(HttpRequest.getRequestData(null, reqUrl));
//                logger.info("微信端获取的反馈信息===="+json.toString());
//                if (!EmptyUtil.isNullOrEmpty(json) && json.isNull("errcode")) {
//                    ObjectMapper mapper = new ObjectMapper();
//                    resMap = mapper.readValue(json.toString(), Map.class);
//                    logger.info("获取当前微信菜单具体信息==="+resMap.toString());
//
//                    String isMenuOpen = resMap.get("is_menu_open").toString();
//                    //解析从微信端获取的菜单信息
////					if(!EmptyUtil.isNullOrEmpty(isMenuOpen) && "1".equals(isMenuOpen)) {
//                    if(!EmptyUtil.isNullOrEmpty(isMenuOpen)) {
//                        Map<String, Object> buttonMap = (Map<String, Object>) resMap.get("selfmenu_info");
//
//                        if(!EmptyUtil.isNullOrEmpty(buttonMap)) {
//                            List<Map<String, Object>> buttonList = (List<Map<String, Object>>) buttonMap.get("button");
//                            logger.info("微信端菜单selfmenu==="+buttonMap);
//
//                            //一级菜单循环
//                            for(Map<String, Object> menuInfo : buttonList) {
//                                WxMenuSetVo wxMenuSet = new WxMenuSetVo();
//                                String name = MapUtils.getString(menuInfo, "name");
//                                String type = MapUtils.getString(menuInfo, "type");
//
//                                wxMenuSet.setParentFlag(OAOConstants.YES);
//                                wxMenuSet.setMenuName(name);
//
//                                Map<String, Object> subButton = MapUtils.getMap(menuInfo, "sub_button");
//
//                                //不存在二级菜单
//                                if(!EmptyUtil.isNullOrEmpty(type)) {
//                                    wxMenuSet.setSubFlag(OAOConstants.NO);
//                                    wxMenuSet.setType(type);
//                                    MenuType enumValue = WxMenuSetVo.MenuType.valueOf(type.toUpperCase());
//                                    if(!EmptyUtil.isNullOrEmpty(enumValue)) {
//                                        wxMenuSet.setTypeValue(MapUtils.getString(menuInfo, enumValue.getValue().toLowerCase()));
//                                    }
//
//                                    //图文消息的保存
//                                    if("news".equals(type)) {
//                                        Map<String, Object> newsListInfo = MapUtils.getMap(menuInfo, "news_info");
//                                        if(!EmptyUtil.isNullOrEmpty(newsListInfo)) {
//                                            ArrayList<Map<String, Object>> newsList = (ArrayList<Map<String, Object>>) newsListInfo.get("list");
//                                            setWxNewsInfo(wxMenuSet, newsList);
//                                        }
//                                    }
//                                }
//
//                                //存在二级菜单
//                                if(!EmptyUtil.isNullOrEmpty(subButton)) {
//                                    wxMenuSet.setSubFlag(OAOConstants.YES);
//                                    //二级菜单内容
//                                    List<Map<String, Object>> subButtonList = (List<Map<String, Object>>) subButton.get("list");
//                                    for(Map<String, Object> button : subButtonList) {
//                                        WxMenuSetVo subMenuSet = new WxMenuSetVo();
//                                        subMenuSet.setParentFlag(OAOConstants.NO);//设置非父级
//                                        subMenuSet.setMenuName(MapUtils.getString(button, "name"));
//                                        String subType = MapUtils.getString(button, "type");
//                                        subMenuSet.setType(subType);
//                                        //设置具体的key  value  url
//                                        if(!EmptyUtil.isNullOrEmpty(subType)) {
//                                            MenuType enumValue = WxMenuSetVo.MenuType.valueOf(subType.toUpperCase());
//                                            if(!EmptyUtil.isNullOrEmpty(enumValue)) {
//                                                subMenuSet.setTypeValue(MapUtils.getString(button, enumValue.getValue().toLowerCase()));
//                                            }
//
//                                            //图文消息的保存
//                                            if("news".equals(subType)) {
//                                                Map<String, Object> newsListInfo = MapUtils.getMap(button, "news_info");
//                                                if(!EmptyUtil.isNullOrEmpty(newsListInfo)) {
//                                                    ArrayList<Map<String, Object>> newsList = (ArrayList<Map<String, Object>>) newsListInfo.get("list");
//                                                    setWxNewsInfo(subMenuSet, newsList);
//                                                }
//                                            }
//                                        }
//                                        wxMenuSet.getSubButtonMenus().add(subMenuSet);
//                                    }
//                                }
//                                wxMenuSetList.add(wxMenuSet);
//                            }
//                        }
//
//                    }
//                }
//                break;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        logger.info("wxMenuSetList======"+wxMenuSetList.toString());
//        return wxMenuSetList;
//    }
//
//    /**
//     * <b>方法名：</b>：setWxNewsInfo<br>
//     * <b>功能说明：</b>：编辑图文消息内容<br>
//     * @author <font color='blue'>韩俊</font>
//     * @date  2016-5-16 下午4:26:46
//     * @param wxMenuSetVo
//     * @param newsList
//     */
//    private static void setWxNewsInfo(WxMenuSetVo wxMenuSetVo, List<Map<String, Object>> newsList) {
//        for(Map<String, Object> news : newsList) {
//            WxMenuSetNewsVo newsVo = new WxMenuSetNewsVo();
//            newsVo.setAuthor(MapUtils.getString(news, "author"));
//            newsVo.setContentUrl(MapUtils.getString(news, "content_url"));
//            newsVo.setCoverUrl(MapUtils.getString(news, "cover_url"));
//            newsVo.setDigest(MapUtils.getString(news, "digest"));
//            newsVo.setShowCover(MapUtils.getString(news, "show_cover"));
//            newsVo.setSourceUrl(MapUtils.getString(news, "source_url"));
//            newsVo.setTitle(MapUtils.getString(news, "title"));
//            wxMenuSetVo.getNewsInfos().add(newsVo);
//        }
//    }
//
//    /**
//     * <b>方法名：</b>：toObject<br>
//     * <b>功能说明：</b>：对象转化为指定JSON对象   目前只支持指定的click  view事件（包括图文推送）
//     * 	将原先的微信菜单 转成接口模式<br>
//     * @author <font color='blue'>韩俊</font>
//     * @date  2016-5-17 下午1:26:16
//     * @param menuList
//     * @return
//     */
//    public static JSONObject toObject(List<WxMenuSetVo> menuList) {
//        JSONObject obj = new JSONObject();
//        JSONArray objArray = new JSONArray();
//        for(WxMenuSetVo wxMenuSetVo : menuList) {
//            JSONObject btnObj = new JSONObject();
//            String subFlag = wxMenuSetVo.getSubFlag();
//
//            //只保存click view 事件  news内容将用客服接口保存  在此需要将图文临时保存下来（稍后处理图文处 再处理）
//
//            //type不为空表示为不存在二级菜单
//            if(OAOConstants.NO.equals(subFlag)) {
//                dealMenuJson(wxMenuSetVo, btnObj);
//            }
//
//            if(OAOConstants.YES.equals(subFlag)){
//                btnObj.put("name", wxMenuSetVo.getMenuName());
//                JSONArray subArray = new JSONArray();
//                List<WxMenuSetVo> subMenuList = wxMenuSetVo.getSubButtonMenus();
//                for(WxMenuSetVo subMenuVo : subMenuList) {
//                    JSONObject subObj = new JSONObject();
//                    dealMenuJson(subMenuVo, subObj);
//                    if(subObj.length() > 0) {
//                        subArray.put(subObj);
//                    }
//                }
//                btnObj.put("sub_button", subArray);
//            }
//            objArray.put(btnObj);
//        }
//        obj.put("button", objArray);
//        logger.info("button==="+obj.toString());
//        return obj;
//    }
//
//    /**
//     * <b>方法名：</b>：dealMenuJson<br>
//     * <b>功能说明：</b>：TODO<br>
//     * @author <font color='blue'>韩俊</font>
//     * @date  2016-5-17 下午2:29:43
//     * @param wxMenuSetVo
//     * @param jsonObj
//     */
//    private static void dealMenuJson(WxMenuSetVo wxMenuSetVo, JSONObject jsonObj) {
//        String type = wxMenuSetVo.getType();
//        if(!EmptyUtil.isNullOrEmpty(type)
//                && typeArr.toString().toLowerCase().contains(type)
//                && !EmptyUtil.isNullOrEmpty(WxMenuSetVo.MenuType.valueOf(type.toUpperCase()))) {
//            jsonObj.put("type", type);
//            jsonObj.put("name", wxMenuSetVo.getMenuName());
//            if("click".equals(type)) {
//                jsonObj.put("key", wxMenuSetVo.getTypeValue());
//            }else if("view".equals(type)) {
//                jsonObj.put("url", wxMenuSetVo.getTypeValue());
//            }else{
//                jsonObj.put("key", wxMenuSetVo.getTypeValue()); //临时处理
//                jsonObj.put("type", "click");
//            }
//        }
//    }
//
//    /**
//     * <b>方法名：</b>：sendTextToWxUser<br>
//     * <b>功能说明：</b>：利用客服接口发送文本信息给微信用户  <br>
//     * 	msgType 消息类型
//     *  		（文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息为news，卡券为wxcard）
//     * @author <font color='blue'>韩俊</font>
//     * @date  2016-3-1 上午10:07:01
//     * @param msgType
//     * @param text
//     * @param openId
//     * @param accessToken
//     */
//    @SuppressWarnings("unchecked")
//    public static Map<String, Object> createWxMenuButton(String accessToken, JSONObject jsonObject) {
//        Map<String, Object> resMap = null;
//        String sendUrl = OAOConstants.WEIXIN_MENU_CREATE_URL.replace("{ACCESS_TOKEN}", accessToken);
//        try {
//            JSONObject json = HttpRequest.httpPost(sendUrl, jsonObject);
//            logger.info("菜单创建返回=="+json);
//            if (!EmptyUtil.isNullOrEmpty(json)) {
//                ObjectMapper mapper = new ObjectMapper();
//                resMap = mapper.readValue(json.toString(), Map.class);
//                logger.info("菜单创建返回==="+resMap);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            logger.error("发送消息json 错误="+jsonObject.toString(), e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error("发送消息 io错误 ="+jsonObject.toString(), e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("发送消息错误 ="+jsonObject.toString(), e);
//        }
//        return resMap;
//    }

    //
    public static void main(String[] args) {
        //JSONObject json = WxMsgUtils.toObject(WxMsgUtils.getWxMenu("VP5O9pbCC_dijFLQKvvFh2Cw3eJjbKSVdZSFS1An5uR6Ewz718otohyJbIsOl10ZcBfPUb8Z4UOau10wTABmA07zug70PEyhJnQMiMyRsAWeY54DWvHozSajumPQOwgVOUNhAJADJC"));
        //logger.info(json.toString());

        //WxMsgUtils.createWxMenuButton("VP5O9pbCC_dijFLQKvvFh2Cw3eJjbKSVdZSFS1An5uR6Ewz718otohyJbIsOl10ZcBfPUb8Z4UOau10wTABmA07zug70PEyhJnQMiMyRsAWeY54DWvHozSajumPQOwgVOUNhAJADJC", json);

    }
}
