package com.retailers.wx.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.tools.utils.UUIDUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.entity.WxMessage;
import com.retailers.wx.common.service.WxMessageService;
import com.retailers.wx.common.utils.wx.WxMsgTextVo;
import com.retailers.wx.common.utils.wx.WxMsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/13
 */
@Controller
@RequestMapping("wechat")
public class WxMessageController{
    Logger logger = LoggerFactory.getLogger(WxMessageController.class);

    @Autowired
    private WxMessageService wxMessageService;

    /**
     * <b>方法名：</b>：core<br>
     * <b>功能说明：</b>：接送微信端发送过来的数据|服务器本地校验配置<br>
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public
    void core(HttpServletRequest request,HttpServletResponse response,
                @RequestParam(value = "signature", required = false) String signature,
                @RequestParam(value = "timestamp", required = false) String timestamp,
                @RequestParam(value = "nonce", required = false) String nonce,
                @RequestParam(value = "echostr", required = false) String echostr)
            throws ServletException, IOException {
        logger.info("signature=" + signature + "  timestamp=" + timestamp+ "nonce=" + nonce + "  echostr=" + echostr);
        if(SignUtil.checkSignature(signature,timestamp,nonce,WxConfig.WX_TOKEN)){
            response.getOutputStream().println(echostr);
        }
    }

    /**
     * <b>方法名：</b>：core<br>
     * <b>功能说明：</b>：获取从微信端的消息以及事件内容， 反馈自定义信息<br>
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public @ResponseBody String core(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
        String retStr = "";
        try {
            Map<String, String> wxMsgMap = null;
            try {
                wxMsgMap = WxMsgUtils.parseXml(request.getInputStream());
                logger.info("取得消息内容：{}", JSON.toJSON(wxMsgMap));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("微信信息，流解析错误");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("微信信息，流解析错误");
            }
//		log.info("wxMsgMap======"+wxMsgMap);

        // 主要信息为空 表示错误 防止连续三次报错 直接返回一个空字符串
        // 判断接收消息是否为空
        if (ObjectUtils.isEmpty(wxMsgMap)|| ObjectUtils.isEmpty(wxMsgMap.get("MsgType"))) {
            return "";
        }
        WxMessage wxMessage=new WxMessage();
        wxMessage.setWmContext(JSON.toJSONString(wxMsgMap));
        wxMessage.setWmCreateDate(new Date());
        wxMessage.setWmMessageType(wxMsgMap.get("MsgType"));
        wxMessage.setWmType(0l);
        wxMessage.setWmUuid(UUIDUtils.getUUID());
        wxMessageService.saveWxMessage(wxMessage);

        String originalId = wxMsgMap.get("ToUserName"); // 开发者微信号
        String openId = wxMsgMap.get("FromUserName"); // openId
        String msgType = wxMsgMap.get("MsgType"); // 消息类型
         String createTime = wxMsgMap.get("CreateTime");//消息接收时间
        String event = wxMsgMap.get("Event");// 事件类型 subscribe(订阅)、unsubscribe(取消订阅)
        String ticket = wxMsgMap.get("Ticket");// 二维码的ticket，可用来换取二维码图片

        // 回复信息内容
        WxMsgTextVo tm = new WxMsgTextVo(openId, originalId, new Date().getTime(), WxConfig.REQ_MESSAGE_TYPE_TEXT, "正在发送消息，请稍候...");

//        // 异步发送海报
//        ThreadBuildHelper.sendImageByKf(openId, ownerId, productSerialNumber, accessToken);
//        return WxMsgUtils.textMessageToXml(tm);
            System.out.println(WxMsgUtils.textMessageToXml(tm));
//            response.getOutputStream().println(WxMsgUtils.textMessageToXml(tm));
            return WxMsgUtils.textMessageToXml(tm);
//
////		log.info("当前微信端发送的消息内容===" + wxMsgMap.toString());
//
//        // 异步发送海报
////		String accessToken = WeiXinUtils.getAccessToken(ownerId);
////		ThreadBuildHelper.sendImageByKf(openId, ownerId, productSerialNumber, accessToken);
//
//        /**
//         * 判断消息类型 目前的业务逻辑都是用户主动推送时间  所以监听【event】类型信息
//         * 若不是event类型信息 那么 可以设置自动统一自动回复信息
//         * */
//        if (!msgType.equals(WxConfig.REQ_MESSAGE_TYPE_EVENT)) {
//            return "";
//        }
//
//        /**
//         * 关注类型 ----------无分享人
//         * 	1. 通过服务号二维码
//         *  2. 微信用户主动搜索出的公众号进行关注
//         *
//         *  数据要求 ticket 为空  && event=subscribe
//         * */
//        if (ObjectUtils.isEmpty(ticket) && msgType.equals(WxConfig.REQ_MESSAGE_TYPE_EVENT) && event.equals(WxConfig.EVENT_TYPE_SUBSCRIBE)) {
//            //发送图文消息
////            ThreadBuildHelper.sendImageTextMessageByProductSerialNumber(openId, productSerialNumber, ownerId);
////
//////			log.info("无分享人的关注=====openId=" + openId + "-------------------------------------------------------");
////            return this.handleSubscribeMsg(openId, originalId, ownerId, productSerialNumber);
//        }
//
//        /**
//         * 扫二维码类型  是否关注微信服务端自动判断
//         * 	1.微信用户未关注  判断条件 ticket != null + event = subscribe
//         *  2.微信用户已关注 判断条件  ticket != null + event = SCAN
//         */
//        String eventKey = wxMsgMap.get("EventKey");
//        if (!ObjectUtils.isNotEmpty(ticket) && msgType.equals(WxConfig.REQ_MESSAGE_TYPE_EVENT) && event.equals(WxConfig.EVENT_TYPE_SUBSCRIBE)) {
//            //发送图文消息
//           // ThreadBuildHelper.sendImageTextMessageByProductSerialNumber(openId, productSerialNumber, ownerId);
//
//            if (ObjectUtils.isNotEmpty(eventKey)&& eventKey.contains("qrscene_")) {
//               // return handleScanMsg(openId, originalId, ticket, eventKey.substring(eventKey.indexOf("qrscene_") + 8, eventKey.length()), ownerId, productSerialNumber);
//            }
//        }
//        if (!ObjectUtils.isNotEmpty(ticket) && msgType.equals(WxConfig.REQ_MESSAGE_TYPE_EVENT) && event.equals(WxConfig.EVENT_TYPE_SCAN)) {
//           // return handleScanMsg(openId, originalId, ticket, eventKey, ownerId, productSerialNumber);
//        }
//
//        /**
//         * 点击生成海报的数据
//         * 自定义菜单-数据类型
//         */
//        if (wxMsgMap.get("MsgType").equals(WxConfig.REQ_MESSAGE_TYPE_EVENT) && wxMsgMap.get("Event").equals(WxConfig.EVENT_TYPE_CLICK)) {
//            /**根据EventKey 区别不同的点击操作类型*/
//            /**海报生成类型 createwallpaper*/
////            if (wxMsgMap.get("EventKey").equals("createwallpaper")) {
////                return this.handleClickMsg(openId, originalId, ownerId, productSerialNumber);
////            }
////            List<WxGraphicsDetailVo> wxGraphics = wxGraphicsService.findWxGraphicsDetailVo(wxMsgMap.get("EventKey"), productSerialNumber, ownerId);
////            if(!EmptyUtil.isNullOrEmpty(wxGraphics) && wxGraphics.size()>0) {
////                sendTW(openId, wxGraphics, productSerialNumber, ownerId);
////            }
//        }
//
//        return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }
}
