package com.retailers.sbj.web.controller;

import com.retailers.sbj.common.service.ProjectService;
import com.retailers.sbj.common.vo.ProjectVo;
import com.retailers.sbj.web.base.BaseController;
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
