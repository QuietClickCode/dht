package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.User;
import com.retailers.dht.common.service.UserService;
import com.retailers.dht.common.vo.RechargeVo;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *平台用户管理
 * @author zhongp
 * @version 1.0.1
 */
@Controller
@RequestMapping("customer")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("openUserPage")
    @Menu(label = "会员管理",description = "会员管理",resourse = "customer.openUserPage",parentRes = "sys.manager.customer",sort = 1)
    public String openUserPage(HttpServletRequest request){
        return "customer/user";
    }
    /**
     * 会员例表
     * @param loginNm 登陆帐号
     * @param userNm 会员名称
     * @param phone 手机号
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryCustomerLists")
    @Function(label="会员列表", description = "会员列表", resourse = "customer.queryCustomerLists",parentRes="customer.openUserPage",sort=1)
    @ResponseBody
    public Map<String,Object> queryCustomerLists(String loginNm,String userNm,String phone,Long type,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("loginNm",loginNm);
        params.put("userNm",userNm);
        params.put("phone",phone);
        params.put("type",type);
        params.put("isDelete",0);
        Pagination<UserVo> pages= userService.queryUserList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }
}
