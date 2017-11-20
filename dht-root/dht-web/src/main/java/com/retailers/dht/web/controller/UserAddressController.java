package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.CityArea;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.dht.common.service.UserAddressService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *用户收货地址管理
 */
@Controller
@RequestMapping("userAddress")
public class UserAddressController extends BaseController {
    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("openAddUserAddress")
    public String openAddUserAddress(HttpServletRequest request){
        return redirectUrl(request,"usercenter/add_user_address");
    }

    @RequestMapping("queryUserAddress")
    @ResponseBody
    public BaseResp queryUserAddress(HttpServletRequest request,PageUtils pageForm){
        Long uId=getCurLoginUserId(request);
        Pagination<UserAddress> page= userAddressService.queryUserAddress(uId,pageForm.getPageNo(),pageForm.getPageSize());
        return success(page);
    }

    /**
     * 添加用户地址
     * @param request
     * @param userAddress 收货地址详情
     * @return
     */
    @RequestMapping("addUserAddress")
    @ResponseBody
    public BaseResp addUserAddress(HttpServletRequest request, UserAddress userAddress){
        Long uId=getCurLoginUserId(request);
        userAddress.setUaUid(uId);
        userAddressService.saveUserAddress(userAddress);
        return success(null);
    }
    /**
     * 编辑用户收货地址
     * @param request
     * @param userAddress
     * @return
     */
    @RequestMapping("editorUserAddress")
    @ResponseBody
    public BaseResp editorUserAddress(HttpServletRequest request, UserAddress userAddress){
        Long uId=getCurLoginUserId(request);
        userAddress.setUaUid(uId);
        userAddressService.updateUserAddress(userAddress);
        return success(null);
    }

    /**
     * 设置收货地址为默认地址
     * @param request
     * @param uaId 收货地址id
     * @return
     */
    @RequestMapping("defaultUserAddress")
    @ResponseBody
    public BaseResp defaultUserAddress(HttpServletRequest request,Long uaId){
        Long uId=getCurLoginUserId(request);
        try{
            userAddressService.defaultUserAddress(uId,uaId);
            return success(true);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }
    }
}
