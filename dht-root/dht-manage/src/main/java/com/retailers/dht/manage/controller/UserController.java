package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.constant.UserConstant;
import com.retailers.dht.common.service.UserService;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *平台用户管理
 * @author zhongp
 * @version 1.0.1
 */
@Controller
@RequestMapping("customer")
public class UserController extends BaseController{
    Logger logger = LoggerFactory.getLogger(UserController.class);
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

    /**
     * 编辑用户类型
     * @param request
     * @param uid 用户id
     * @param utype 用户类型
     * @param ufirstCommission 首单提成
     * @param urecommendCommission 消费提成
     * @return
     */
    @RequestMapping("editorUserType")
    @Function(label="编辑用户类型", description = "编辑用户类型", resourse = "customer.editorUserType",sort=1,parentRes="customer.queryCustomerLists")
    @ResponseBody
    public BaseResp editorUserType(HttpServletRequest request,Long uid,Long utype,String ufirstCommission,String urecommendCommission){
        Long sysUid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(uid)){
            return errorForParam("用户id不能为空");
        }
        if(ObjectUtils.isEmpty(utype)){
            return errorForParam("用户类型不能为空");
        }
        Long ufirstCommission_=null;
        Long urecommendCommission_=null;
        if(utype.intValue()!= UserConstant.USER_TYPE_PT){
            long max= SysParameterConfigConstant.getValue(SysParameterConfigConstant.USER_RECOMMEND_COMMISSION_MAX,Long.class);
            if(ObjectUtils.isEmpty(ufirstCommission)){
                return errorForParam("用户类型为推广员时，首单提成不能为空");
            }else{
                if(NumberUtils.isNumber(ufirstCommission)){
                    ufirstCommission_=NumberUtils.priceChangeFen(NumberUtils.formaterNumber(Double.parseDouble(ufirstCommission),2));
                    if(max<ufirstCommission_){
                        return errorForParam("推广提成不能大于平台设置最大值["+NumberUtils.priceChangeYuan(max)+"]");
                    }
                }else{
                    return errorForParam("首单提成只能为数字类型");
                }
            }
            if(ObjectUtils.isEmpty(urecommendCommission)){
                return errorForParam("用户类型为推广员时，消费提成不能为空");
            }else{
                if(NumberUtils.isNumber(urecommendCommission)){
                    urecommendCommission_=NumberUtils.priceChangeFen(NumberUtils.formaterNumber(Double.parseDouble(urecommendCommission),2));
                    if(max<urecommendCommission_){
                        return errorForParam("推广提成不能大于平台设置最大值["+NumberUtils.priceChangeYuan(max)+"]");
                    }
                }else{
                    return errorForParam("消费提成只能为数字类型");
                }
            }
        }
        try{
            userService.editorUserType(sysUid,uid,utype,ufirstCommission_,urecommendCommission_);
            return  success("编辑用户类型成功");
        }catch(AppException e){
            logger.error("设置用户类型出错：\r\n{}",e);
            return errorForParam(e.getMessage());
        }
    }

    /**
     * 打开推广人员例表
     * @return
     */
    @RequestMapping("openPopularizePage")
    @Menu(label = "推广人员",description = "推广人员",resourse = "customer.openPopularizePage",parentRes = "sys.manager.customer",sort =2)
    public String openPopularizePage(){
        return "customer/popularize_user";
    }

    /**
     * 会员例表
     * @param loginNm 登陆帐号
     * @param userNm 会员名称
     * @param phone 手机号
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryPopularizeLists")
    @Function(label="推广会员列表", description = "推广会员列表", resourse = "customer.queryPopularizeLists",parentRes="customer.openUserPage",sort=1)
    @ResponseBody
    public Map<String,Object> queryPopularizeLists(String loginNm,String userNm,String phone,Long type,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        List<Long> typs=new ArrayList<Long>();
        params.put("loginNm",loginNm);
        params.put("userNm",userNm);
        params.put("phone",phone);
        if(ObjectUtils.isEmpty(type)){
            typs.add(1l);
            typs.add(2l);
        }else{
            typs.add(type);
        }
        params.put("types",typs);
        params.put("isDelete",0);
        Pagination<UserVo> pages= userService.queryUserList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }
}
