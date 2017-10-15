package com.retailers.dht.common.utils;

import com.alibaba.fastjson.JSON;
import com.retailers.tools.utils.ObjectUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * 富文本图片解析
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/13
 */
public class AttachmentUploadImageUtils {
    public static void main(String[] args) {
        Map<Long,Long> map = findUploadImages("<p><br/></p><p><img src=\"http://image.kuaiyis.com/attachment/goods/2017/10/13/8301d418528bb9ba2d7a26c4c32fa64e_originalfile.jpg?random=123\" style=\"\" title=\"QQ截图20170730110815.jpg\"/></p><p><img src=\"http://image.kuaiyis.com/attachment/goods/2017/10/13/07637915440468d3d9ce8974402a41f9_originalfile.jpg\" style=\"\" title=\"QQ截图20170729224134.jpg\"/></p><p><img src=\"http://image.kuaiyis.com/attachment/goods/2017/10/13/664eb0a3a3d3f5c05aa68c354d7cf93c_originalfile.jpg\" style=\"\" title=\"QQ截图20170729224830.jpg\"/></p><p><br/></p>");
        System.out.println(JSON.toJSON(map));
        long key =123;
        System.out.println(map.get(key));
    }

    /**
     * 取得富文本中的附件图片id
     * @param context
     * @return
     */
    public static Map<Long,Long> findUploadImages(String context){
        Map<Long,Long> rtn = new HashMap<Long, Long>();
        Document doc = Jsoup.parse(context);
        Elements elements = doc.select("img");
        for(Element el:elements){
            String url = el.attr("src");
            System.out.println(url);
            long imageIds=urlParams(url);
            System.out.println(imageIds);
            if(imageIds>0){
                rtn.put(imageIds,imageIds);
            }
        }
        return rtn;
    }

    /**
     * 取得图片url中的附件id
     * @param url
     * @return
     */
    private static long urlParams(String url){
        if(ObjectUtils.isEmpty(url)){
            return -1;
        }
        if(url.indexOf("?")>=0){
            url=url.substring(url.indexOf("?")+1);
            String[] params=url.split("&");
            for(String str:params){
                if(str.indexOf("=")>=0){
                    String[] strs=str.split("=");
                    String key=strs[0];
                    String value=strs[1];
                    if(key.equals("random")){
                        return Long.parseLong(value);
                    }
                }
            }
        }
        return -1;

    }
}
