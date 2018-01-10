package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.Organization;
import com.retailers.auth.service.OrganizationService;
import com.retailers.auth.vo.OrganizationVo;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.hnc.manage.base.BaseController;
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
    @Menu(parentRes = "sys.manager.employee",resourse = "organization.openOrganization",description = "角色管理",sort = 1,label = "角色管理")
    public String openOrganization(){
        return "org/organization";
    }

    @RequestMapping("queryOrganizationLists")
    @Function(label="角色列表", description = "所有角色列表（树型展示)", resourse = "organization.queryOrganizationLists",sort=1,parentRes="organization.openOrganization")
    @ResponseBody
    public Map<String,Object> queryOrganizationLists(){
        List<OrganizationVo> menu=organizationService.queryOrganizationLists(null);
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",100);
        rtn.put("rows",menu);
        return rtn;
    }


    /**
     * 取得角色树型结构 排除传入的节点id(排除按钮）
     * @param orgId 角色id
     * @return
     */
    @RequestMapping("queryOrgNode")
    @ResponseBody
    public BaseResp queryMenuNode(Long orgId){
        List<OrganizationVo> menu=organizationService.queryOrganizationLists(orgId);
        return success(menu);
    }

    /**
     * 添加角色
     * @param org
     * @return
     */
    @RequestMapping("addOrganization")
    @Function(label = "添加角色",parentRes = "organization.openOrganization",resourse = "organization.addOrganization",description = "添加角色",sort = 2)
    @ResponseBody
    public BaseResp addOrganization( Organization org){
        try{
            ValidationUtils.validate(org);
        }catch (AppException e){
            return errorForParam(e.getMessage());
        }
        boolean flag = organizationService.editOrg(org);
        if(flag){
            return success("添加角色["+org.getOrgName()+"]成功");
        }else{
            return errorForSystem("添加角色["+org.getOrgName()+"]失败");
        }
    }
    /**
     * 添加角色
     * @param org
     * @return
     */
    @RequestMapping("editOrganization")
    @Function(label = "编辑角色",parentRes = "organization.openOrganization",resourse = "organization.editOrganization",description = "编辑角色",sort = 2)
    @ResponseBody
    public BaseResp editOrganization(Organization org){
        boolean flag = organizationService.editOrg(org);
        if(flag){
            return success("修改角色["+org.getOrgName()+"]成功");
        }else{
            return errorForSystem("修改角色["+org.getOrgName()+"]失败");
        }
    }

    /**
     * 删除菜单资 源
     * @param orgId 角色id
     * @return
     */
    @RequestMapping("/removeOrg")
    @Function(label="删除角色", description = "删除角色", resourse = "organization.removeOrg",sort=3,parentRes="organization.openOrganization")
    @ResponseBody
    public BaseResp removeOrg(Long orgId){
        boolean flag=organizationService.removeOrg(orgId);
        return success(flag);
    }

    /**
     * 取得角色下的所有权限
     * @param orgId 角色id
     * @return
     */
    @RequestMapping("/reqOrgPermission")
    @Function(label="取得角色权限", description = "取得角色权限", resourse = "organization.reqOrgPermission",sort=3,parentRes="organization.openOrganization")
    @ResponseBody
    public BaseResp reqOrgPermission(Long orgId){
        List<ZTreeVo> rtn = organizationService.reqOrgPermission(orgId);
        return success(rtn);
    }

    /**
     * 编辑角色权限
     * @param orgId 角色id
     * @return
     */
    @RequestMapping("/editorOrgPermission")
    @Function(label="编辑权限", description = "编辑权限", resourse = "organization.editorOrgPermission",sort=3,parentRes="organization.openOrganization")
    @ResponseBody
    public BaseResp editorOrgPermission(Long orgId,String resIds){
        if(ObjectUtils.isEmpty(orgId)){
            return errorForParam("授权角色不能为空");
        }
        boolean flag=organizationService.editorOrgPermission(orgId,resIds);
        return success(flag);
    }

    /**
     * 取得所有未删除角色，并根据角色id 选中当中数据
     * @param selectOrgIds 选中角色id
     * @return
     */
    @RequestMapping("/reqOrgTree")
    @ResponseBody
    public BaseResp reqOrgTree(String selectOrgIds){
        List<ZTreeVo> rtn = organizationService.reqOrgTree(selectOrgIds);
        return success(rtn);
    }
}
