package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.project.ProductType;


public interface ProductDao {
	List<Map<String,Object>> findObjects();
	
	/**
	 * 判断此id下是否有子元素
	 * @param id
	 * @return 
	 */
	int hasChilds(Integer id);
	int deleteObject (Integer id);
	List<Node> findZtreeNodes();
	/**
	 * 保存到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(ProductType entity);
}
