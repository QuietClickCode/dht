package com.retailers.sbj.web.utils;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/9
 */

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 *新建一个html的解析器并继承 InternalResourceView 后重写 checkResource
 * @ClassName: HtmlResourceView
 * @author caixl
 * @date 2016-6-8 上午11:01:41
 *
 */
public class HtmlResourceView extends InternalResourceView {
    @Override
    public boolean checkResource(Locale locale) {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();// 判断该页面是否存在
    }
}
