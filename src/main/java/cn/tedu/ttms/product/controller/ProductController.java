package cn.tedu.ttms.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.service.ProductTypeService;
import cn.tedu.ttms.project.ProductType;

@Controller
@RequestMapping("/type/")
public class ProductController {
	@Autowired
	private ProductTypeService productTypeService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/type_list";
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		return new JsonResult(productTypeService.findObjects());
	}
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult deleteObject(Integer id){
		productTypeService.deleteObject(id);
		return new JsonResult("删除成功!");
				
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/type_edit";
	}
	
	@RequestMapping("doFindZtreeNodes")
	@ResponseBody
	public JsonResult doFindZtreeNodes(){		
		return new JsonResult(productTypeService.findZtreeNodes());
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType entity){	
		productTypeService.saveObject(entity);
		return new JsonResult("保存成功!");
	}
}
