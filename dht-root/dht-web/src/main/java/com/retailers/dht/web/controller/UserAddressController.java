package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.dht.common.service.UserAddressService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *用户收货地址管理
 */
@Controller
@RequestMapping("userAddress")
public class UserAddressController extends BaseController {
    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("openAddUserAddress")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    public ModelAndView openAddUserAddress(HttpServletRequest request,Long uaId){
        ModelAndView mv = new ModelAndView();
        if(ObjectUtils.isNotEmpty(uaId)){
            UserAddress userAddress= userAddressService.queryUserAddressByUaId(uaId);
            mv.addObject("userAddress",userAddress);
        }
        String url= redirectUrl(request,"usercenter/add_user_address");
        mv.setViewName(url);
        return mv;
    }

    /**
     * 取得用户收货地址
     * @param request
     * @param pageForm
     * @return
     */
    @RequestMapping("queryUserAddress")
    @ResponseBody
    public Map<String,Object> queryUserAddress(HttpServletRequest request, PageUtils pageForm){
        Long uId=getCurLoginUserId(request);
        Pagination<UserAddress> page= userAddressService.queryUserAddress(uId,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(page);
    }

    /**
     * 添加用户地址
     * @param request
     * @param userAddress 收货地址详情
     * @return
     */
    @RequestMapping("addUserAddress")
    @ResponseBody
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    public BaseResp addUserAddress(HttpServletRequest request, UserAddress userAddress){
        Long uId=getCurLoginUserId(request);
        userAddress.setUaUid(uId);
        userAddressService.saveUserAddress(userAddress);
        return success("新增收货地址成功");
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
        try{
            userAddressService.updateUserAddress(userAddress);
            return success("修改收货地址成功");
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
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

    /*
    * 删除收货地址
    */
    @RequestMapping("removeUserAddress")
    @ResponseBody
    public BaseResp removeUserAddress(HttpServletRequest request,Long uaId){
        userAddressService.deleteUserAddressByUaId(uaId);
        return success("删除收货地址成功");
    }
}
