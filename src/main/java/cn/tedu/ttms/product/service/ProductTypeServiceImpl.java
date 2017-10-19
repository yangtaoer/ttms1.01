package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.dao.ProductDao;
import cn.tedu.ttms.project.ProductType;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Map<String, Object>> findObjects() {
		
		return productDao.findObjects();
	}

	@Override
	public void deleteObject(Integer id) {
		if(id==null || id<0) {
			throw new ServiceException("id不能为空或小于0!");
		}
		if(productDao.hasChilds(id)==0){//判断是否有子元素
			int row = productDao.deleteObject(id);
			if(row<1) {
				throw new ServiceException("删除失败!");
			}
		} else {
			throw new ServiceException("含有下级元素不能删除!");
		}
		
	}

	@Override
	public List<Node> findZtreeNodes() {
		return productDao.findZtreeNodes();
	}

	@Override
	public void saveObject(ProductType entity) {
		if(entity==null) {
			throw new ServiceException("保存对象不能为空");
		}
		int row = productDao.insertObject(entity);
		if(row<1)
			throw new ServiceException("数据保存失败");
	}

}
