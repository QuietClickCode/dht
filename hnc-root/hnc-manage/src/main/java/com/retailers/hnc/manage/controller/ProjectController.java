package com.retailers.hnc.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.service.impl.ProjectServiceImpl;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SpringUtils;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
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

    @Autowired
    ProjectService projectService;

    @RequestMapping("/openProject")
    @Menu(parentRes = "sys.manager.project",resourse = "project.openProject",description = "项目管理",sort = 1,label = "项目管理")
    public String openProject(){
        return "project";
    }

    @RequestMapping("saveProject")
    @ResponseBody
    public BaseResp editProject(Project Project, HttpServletRequest request){
        boolean flag = projectService.saveProject(Project);
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
        List<Project> list = projectService.queryProjectList(new HashMap(),1,1).getData();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(list)){
            Project p = list.get(0);
            map.put("project",p);
        }
        return map;
    }


    @RequestMapping("/test")
    @ResponseBody
    public String queryProjectByGt(String url){
        String respStr = HttpClientUtil.doGet(url);
        return respStr;
    }

    public static void main (String[] a){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx3e1ce3039c616778" +
                "&secret=dae5052f2a8667bc420d76924da6a144&grant_type=authorization_code" +
                "&js_code=013caAc91YBD8Q13mYc91bbNc91caAcI";
        String respStr = GetFromServer(url);
        System.out.println(respStr);
    }

    public static String GetFromServer(String url) {
        String retStr="";
        ClientConnectionManager connManager = new PoolingClientConnectionManager();
        DefaultHttpClient client = new DefaultHttpClient(connManager);

        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            retStr = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(url);
            return "";
        }
        return retStr;

    }
}
