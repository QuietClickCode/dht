package com.retailers.wx.common.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/29.
 */
public class WxMenu {
    private List<WxMenuButton> button;

    public List<WxMenuButton> getButton() {
        return button;
    }

    public void setButton(List<WxMenuButton> button) {
        this.button = button;
    }

    public static void main(String[] args) {
        WxMenu w=new WxMenu();
        WxMenuButton b=new WxMenuButton();
        b.setName("测试菜单");
        List<WxMenuChildBtn> childBtns=new ArrayList<WxMenuChildBtn>();
        WxMenuChildBtn childBtn=new WxMenuChildBtn();
        childBtn.setName("子菜单1");
        childBtn.setType("view");
        childBtn.setUrl("http://www.kuaiyis.com");
        childBtns.add(childBtn);
        b.setSubButton(childBtns);
        System.out.println(JSON.toJSON(b));
        List<WxMenuButton> lists=new ArrayList<WxMenuButton>();
        lists.add(b);
        w.setButton(lists);
        System.out.println(JSON.toJSON(w));
    }
}
