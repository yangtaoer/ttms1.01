package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.product.dao.ProductDao;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Map<String, Object>> findObjects() {
		
		return productDao.findObjects();
	}

}
