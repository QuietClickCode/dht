package com.retailers.hnc.web.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.vo.ProjectVo;
import com.retailers.hnc.web.base.BaseController;
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

}
