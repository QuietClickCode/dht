package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.Organization;
import com.retailers.auth.service.OrganizationService;
import com.retailers.auth.vo.OrganizationVo;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织结构管理
 * @author zhongp
 * @version 1.0.1
 * @date 2017-09-19
 */
@Controller
@RequestMapping("org")
public class OrganizationController extends BaseController {
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("openOrganization")
    @Menu(parentRes = "sys.manager.org",resourse = "organization.openOrganization",description = "部门管理",sort = 1,label = "部门管理")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    public String openOrganization(){
        return "org/organization";
    }

    @RequestMapping("queryOrganizationLists")
    @Function(label="部门列表", description = "所有部门列表（树型展示)", resourse = "organization.queryOrganizationLists",sort=1,parentRes="organization.openOrganization")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public Map<String,Object> queryOrganizationLists(){
        List<OrganizationVo> menu=organizationService.queryOrganizationLists(null);
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",100);
        rtn.put("rows",menu);
        return rtn;
    }


    /**
     * 取得部门树型结构 排除传入的节点id(排除按钮）
     * @param orgId 部门id
     * @return
     */
    @RequestMapping("queryOrgNode")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp queryMenuNode(Long orgId){
        List<OrganizationVo> menu=organizationService.queryOrganizationLists(orgId);
        return success(menu);
    }

    /**
     * 添加部门
     * @param org
     * @return
     */
    @RequestMapping("addOrganization")
    @Function(label = "添加部门",parentRes = "organization.openOrganization",resourse = "organization.addOrganization",description = "添加部门",sort = 2)
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY,msg = "未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp addOrganization( Organization org){
        try{
            ValidationUtils.validate(org);
        }catch (AppException e){
            return errorForParam(e.getMessage());
        }
        boolean flag = organizationService.editOrg(org);
        if(flag){
            return success("添加部门["+org.getOrgName()+"]成功");
        }else{
            return errorForSystem("添加部门["+org.getOrgName()+"]失败");
        }
    }
    /**
     * 添加部门
     * @param org
     * @return
     */
    @RequestMapping("editOrganization")
    @Function(label = "编辑部门",parentRes = "organization.openOrganization",resourse = "organization.editOrganization",description = "编辑部门",sort = 2)
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY,msg = "未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp editOrganization(Organization org){
        boolean flag = organizationService.editOrg(org);
        if(flag){
            return success("修改部门["+org.getOrgName()+"]成功");
        }else{
            return errorForSystem("修改部门["+org.getOrgName()+"]失败");
        }
    }

    /**
     * 删除菜单资 源
     * @param orgId 部门id
     * @return
     */
    @RequestMapping("/removeOrg")
    @Function(label="删除部门", description = "删除部门", resourse = "organization.removeOrg",sort=3,parentRes="organization.openOrganization")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp removeOrg(Long orgId){
        boolean flag=organizationService.removeOrg(orgId);
        return success(flag);
    }

    /**
     * 取得部门下的所有权限
     * @param orgId 部门id
     * @return
     */
    @RequestMapping("/reqOrgPermission")
    @Function(label="取得部门权限", description = "取得部门权限", resourse = "organization.reqOrgPermission",sort=3,parentRes="organization.openOrganization")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp reqOrgPermission(Long orgId){
        List<ZTreeVo> rtn = organizationService.reqOrgPermission(orgId);
        return success(rtn);
    }

    /**
     * 编辑部门权限
     * @param orgId 部门id
     * @return
     */
    @RequestMapping("/editorOrgPermission")
    @Function(label="编辑权限", description = "编辑权限", resourse = "organization.editorOrgPermission",sort=3,parentRes="organization.openOrganization")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp editorOrgPermission(Long orgId,String resIds){
        if(ObjectUtils.isEmpty(orgId)){
            return errorForParam("授权部门不能为空");
        }
        boolean flag=organizationService.editorOrgPermission(orgId,resIds);
        return success(flag);
    }

    /**
     * 取得所有未删除部门，并根据部门id 选中当中数据
     * @param selectOrgIds 选中部门id
     * @return
     */
    @RequestMapping("/reqOrgTree")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp reqOrgTree(String selectOrgIds){
        List<ZTreeVo> rtn = organizationService.reqOrgTree(selectOrgIds);
        return success(rtn);
    }
}
