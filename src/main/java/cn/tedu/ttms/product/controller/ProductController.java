package cn.tedu.ttms.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.service.ProductTypeService;

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
	
}
