package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.entity.ProjectImg;
import com.retailers.hnc.common.service.ProjectImgService;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.vo.ProjectVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("project")
public class ProjectController extends BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectImgService imgService;

    @RequestMapping("/openProject")
    @Menu(parentRes = "sys.manager.project",resourse = "project.openProject",description = "项目管理",sort = 1,label = "项目管理")
    public String openProject(){
        return "project";
    }

    @RequestMapping("saveProject")
    @ResponseBody
    public BaseResp editProject(Project project){
        boolean flag = projectService.saveProject(project);
        return success(flag);
    }
    @RequestMapping("updateProject")
    @ResponseBody
    public BaseResp updateProject(Project Project){
        boolean flag = projectService.updateProject(Project);
        return success(flag);
    }
    @RequestMapping("queryProject")
    @ResponseBody
    public Map<String,Object> queryProject(){
        List<ProjectVo> list = projectService.queryProjectVo();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(list)){
            ProjectVo p = list.get(0);
            map.put("project",p);
        }
        return map;
    }

    @RequestMapping("saveProjectImg")
    @ResponseBody
    public BaseResp savaProjectImg(ProjectImg projectImg){
        boolean flag = imgService.saveProjectImg(projectImg);
        return success(flag);
    }



//    public static void main (String[] a){
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx3e1ce3039c616778" +
//                "&secret=dae5052f2a8667bc420d76924da6a144&grant_type=authorization_code" +
//                "&js_code=013caAc91YBD8Q13mYc91bbNc91caAcI";
//        String respStr = GetFromServer(url);
//        System.out.println(respStr);
//    }

}
