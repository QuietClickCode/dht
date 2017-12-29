package com.retailers.wx.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/29.
 */
public class WxMenuButton {
    private String name;
    @JSONField(name = "sub_button")
    private List<WxMenuChildBtn> subButton;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<WxMenuChildBtn> getSubButton() {
        return subButton;
    }
    public void setSubButton(List<WxMenuChildBtn> subButton) {
        this.subButton = subButton;
    }
    public static void main(String[] args) {
        WxMenuButton b=new WxMenuButton();
        b.setName("测试菜单");
        List<WxMenuChildBtn> childBtns=new ArrayList<WxMenuChildBtn>();
        WxMenuChildBtn childBtn=new WxMenuChildBtn();
        childBtn.setName("子菜单1");
        childBtn.setType("view");
        childBtns.add(childBtn);
        b.setSubButton(childBtns);
        System.out.println(JSON.toJSON(b));
    }
}
