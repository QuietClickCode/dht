package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("project")
public class ProjectController extends BaseController {
//
//    @Autowired
//    ProjectService ProjectService;
//
//    @RequestMapping("/openProject")
//    @Menu(parentRes = "sys.manager.projectManager",resourse = "project.openProject",description = "项目管理",sort = 1,label = "项目管理")
//    public String openProject(){
//        return "project/project";
//    }
//
//    @RequestMapping("editProject")
//    @Function(label = "编辑商品",parentRes = "Project.openProject",resourse = "Project.editProject",description = "编辑商品",sort = 2)
//    @ResponseBody
//    public BaseResp editProject(Project Project,HttpServletRequest request){
//        boolean flag = ProjectService.updateProject(Project,getCurLoginUserId(request));
//        if(flag){
//            return success("修改商品["+Project.getGname()+"]成功");
//        }else{
//            return errorForSystem("修改商品["+Project.getGname()+"]失败");
//        }
//    }
//
//    @RequestMapping("/removeProject")
//    @Function(label="删除商品", description = "删除商品", resourse = "Project.removeProject",sort=3,parentRes="Project.openProject")
//    @ResponseBody
//    public BaseResp removeProject(Long gid,HttpServletRequest request){
//        boolean flag=ProjectService.deleteProjectByGid(gid,getCurLoginUserId(request));
//        return success(flag);
//    }
//
//    @RequestMapping("/queryProjectLists")
//    @Function(label="商品列表", description = "所有商品列表", resourse = "Project.queryProjectLists",sort=1,parentRes="Project.openProject")
//    @ResponseBody
//    public  Map<String,Object> queryProjectLists(String gname,Long gclassification,Long gmaindirection,Long isChecked,PageUtils pageForm){
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("gname",gname);
//        map.put("gclassification",gclassification);
//        map.put("gmaindirection",gmaindirection);
//        map.put("isChecked",isChecked);
//        map.put("isDelete",0);
//        Pagination<ProjectVo> ProjectPagination = ProjectService.queryProjectList(map,pageForm.getPageNo(),pageForm.getPageSize());
//        Map<String,Object> gtm = new HashMap<String,Object>();
//        gtm.put("total",ProjectPagination.getTotalCount());
//        gtm.put("rows",ProjectPagination.getData());
//        return gtm;
//    }
//
//    @RequestMapping("/queryProjectById")
//    @Function(label="商品列表", description = "所有商品列表", resourse = "Project.queryProjectById",sort=1,parentRes="Project.openProject")
//    @ResponseBody
//    public  Map<String,Object> queryProjectById(Long gid){
//        Project Project = ProjectService.queryProjectByGid(gid);
//        Map<String,Object> gtm = new HashMap<String,Object>();
//        gtm.put("row",Project);
//        return gtm;
//    }
//
//    @RequestMapping("/addProject")
//    @Function(label="增加商品", description = "增加商品", resourse = "Project.addProject",parentRes="Project.openProject")
//    @ResponseBody
//    public Map<String,Object> addProject(Project Project, HttpServletRequest request){
//        Project.setIsDelete(0L);
//        Project.setIsChecked(0L);
//        Project returnProject=ProjectService.saveProject(Project,getCurLoginUserId(request));
//        Map<String,Object> gtm = new HashMap<String,Object>();
//        if (returnProject==null){
//            gtm.put("error","新增失败！");
//        }else {
//            gtm.put("Project",returnProject);
//        }
//        return gtm;
//    }
//
//    @RequestMapping("/checkProject")
//    @Function(label="审核商品", description = "审核商品", resourse = "Project.checkProject",parentRes="Project.openProject")
//    @ResponseBody
//    public BaseResp checkProject(Long gid,String message,Long myidea,HttpServletRequest request){
//        Project Project = ProjectService.queryProjectByGid(gid);
//        Project.setIsChecked(myidea);
//        Project.setGcheckmessage(message);
//        Project.setGcheckperson(getCurLoginUserId(request));
//        boolean flag = ProjectService.checkProject(Project);
//        return success(flag);
//    }
//
//    @RequestMapping("/updateProjectSetNotChecked")
//    @Function(label="设置商品未审核", description = "设置商品未审核", resourse = "Project.updateProjectSetNotChecked",parentRes="Project.openProject")
//    @ResponseBody
//    public BaseResp updateProjectSetNotChecked(Long gid,HttpServletRequest request){
//        boolean flag = ProjectService.updateProjectSetNotChecked(gid,getCurLoginUserId(request));
//        return success(flag);
//    }
//
//    /**
//     *根据商品类型取得商品
//     * @param request
//     * @param gt 商品类型
//     * @return
//     */
//    @RequestMapping("queryProjectByGt")
//    @ResponseBody
//    public BaseResp queryProjectByGt(HttpServletRequest request,Long gt){
//        List<ZTreeVo> list = ProjectService.queryProjectByGt(gt);
//        return success(list);
//    }

    @RequestMapping("/test")
    @ResponseBody
    public String queryProjectByGt(String url){
        String respStr = HttpClientUtil.doGet(url);
        return respStr;
    }
}
