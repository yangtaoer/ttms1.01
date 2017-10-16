package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.service.ProjectService;
import cn.tedu.ttms.project.Project;

@Controller
@RequestMapping("/project/")
public class ProjectController {
	  private  Logger log=
			  Logger.getLogger(ProjectController.class.getName());

	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	/**返回项目列表页面*/
   @RequestMapping("listUI.do")
   public String listUI(){
	   return "product/project_list";//WEB-INF中的页面jsp
   }
   
  /* @ExceptionHandler(ServiceException.class)
   @ResponseBody
   public JsonResult handleServiceException(ServiceException e) {
	   e.printStackTrace();
	   return new JsonResult(e);
   }*/
   
   
   @RequestMapping("doGetObjects")
   @ResponseBody//将返回对象转换为json对象
   public JsonResult doGetObjects(){
		  log.info("projectService="+projectService);

	   List<Project> list = projectService.findObjects();
	return new JsonResult(list);
	   
   }
   
   /**
    * 禁用或启用项目信息
    * @param valid
    * @param ids
    */
   @RequestMapping("doValidById")
   @ResponseBody
   public JsonResult doValidById(Integer valid,
		   String ids){
	   projectService.validById(valid, ids);
	   return new JsonResult();
	   
	   
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   @RequestMapping("doGetPageObjects")
   @ResponseBody//将返回对象转换为json对象
   public Map<String,Object> doGetPageObjects(Integer pageCurrent,String name,Integer valid){
		  log.info("name="+name);
		  log.info("valid="+valid);
		  log.info("pageCurrent="+pageCurrent);
	   Map<String,Object> map = projectService.findPageObjects(pageCurrent,  name, valid);  
		  log.info("map="+map);

	    return map; //map对象转换为json对象的表示形式如下注释
   }
   
   /*
    * {
    *  "list":[{id:1,name:"环球游",...},{...}]
    *  "pageObject":{pageCurrent:1,pageCount:2,...}
    * }
    */
   
   
}
